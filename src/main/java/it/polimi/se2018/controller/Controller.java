package it.polimi.se2018.controller;

import it.polimi.se2018.controller.public_objective_cards.PublicObjectiveCard;
import it.polimi.se2018.controller.tool_card.ToolCardParameters;
import it.polimi.se2018.files.ConfigFile;
import it.polimi.se2018.files.SchemeCardsLoader;
import it.polimi.se2018.files.ToolCardsLoader;
import it.polimi.se2018.files.exceptions.CannotReadFileException;
import it.polimi.se2018.files.exceptions.InvalidFileException;
import it.polimi.se2018.files.exceptions.LoadingFilesException;
import it.polimi.se2018.model.Card;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.mvc_comunication.Event;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;
import it.polimi.se2018.mvc_comunication.EventVisitor;
import it.polimi.se2018.view.ViewInterface;
import it.polimi.se2018.utils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This is the Controller of the Game, created by the Server.
 * It's an observer of the events notified by a View,
 * it saves the reference of this current view,
 * and it modifies the Model thanks to a proper EventVisitor.
 * @author Matteo Gagliano
 * @author Carmelo Fascella
 * @author Generoso Imparato
 */
public class Controller implements Observer<Event>
{
    private static final String         SCHEME_CARDS_DIRECTORY =        "./resources/scheme_cards/";
    private static final String         TOOL_CARDS_DIRECTORY =          "./resources/tool_cards/";
    private static final String         CONFIG_FILE =                   "./resources/config.sagradaconfig";
    private static final int            MINIMUM_TOOL_CARDS_REQUIRED =   3 ;
    public  static final int            TOTAL_ROUNDS =                  10;
    public  static final int            MIN_PLAYERS =                   2;

    private Model                       model;
    private ViewInterface               view;

    private EventVisitor                eventParser;

    private PlayerTurnIterator          playerTurnIterator;

    protected GameTimer                 startTimer;
    protected GameTimer                 turnTimer;

    private List<ToolCard>              toolCards;
    private ToolCard                    currentToolCard;

    private List<PublicObjectiveCard>   publicCards;

    private boolean                     usingToolCard = false;

    private boolean                     playerHasDrafted = false;
    private boolean                     playerUsedToolCard = false;


    /**
     * Constructor that sets a default tool card directory
     * @param model current Model
     */
    public Controller(Model model)
    {
        create(model, TOOL_CARDS_DIRECTORY);
    }

    /**
     * Constructor that sets a personalized tool card directory
     * @param model current model
     * @param toolCardsPath path of the tool cards
     */
    public Controller(Model model, String toolCardsPath)
    {
        create(model, toolCardsPath);
    }

    /** Helper called by each constructor
     * @param model current model
     * @param toolCardsPath path of the tool cards
     */
    private void create(Model model, String toolCardsPath)
    {
        setModel(model);

        setEventParser(new GameNotStartedEventParser(this));

        playerTurnIterator = new PlayerTurnIterator(this);

        start(toolCardsPath);
    }

    /**
     * It's the method overridden by the Observer.
     * It saves the reference of the view that notifies the controller
     * and it calls the method acceptVisitor of the event
     * @param event it's used to call the proper method of the EventVisitor saved
     */
    @Override
    public void update(Event event)
    {
        setView(event.getView());
        turnTimer.reset();
        event.acceptVisitor(eventParser);
    }

    /**
     * Returns the current Model
     * @return current Model
     */
    public Model getModel()
    {
        return model;
    }

    /**
     * Returns the set view
     * @return set view
     */
    public ViewInterface getView()
    {
        return view;
    }

    /**
     * Sets the view
     * @param view set view
     */
    public void setView(ViewInterface view)
    {
        this.view = view;
    }

    /**
     * Sets the model
     * @param model current Model
     */
    public void setModel(Model model)
    {
        this.model = model;
    }


    private void start(String toolCardPath)
    {
        Logger logger = new Logger()
        {
            @Override
            public void logMessage(String message)
            {
                System.out.println(message);
            }

            @Override
            public void logErrorMessage(String message)
            {
                System.out.println("Error: " + message);
            }
        };

        loadSchemeCardFiles(logger);
        loadToolCardFiles(logger, toolCardPath);
        loadConfigFile(logger);
        selectRandomPublicCards();
    }

    private void loadSchemeCardFiles(Logger logger)
    {
        try
        {
            SchemeCardsLoader loader = new SchemeCardsLoader(SCHEME_CARDS_DIRECTORY, logger);
            model.setSchemeCards(loader.getGeneratedBoards());
        }
        catch(LoadingFilesException e)
        {
            System.exit(0);
        }
    }

    private void loadToolCardFiles(Logger logger, String toolCardPath)
    {
        try
        {
            ToolCardsLoader loader = new ToolCardsLoader(toolCardPath, logger);
            toolCards = loader.getToolCards();

            if(toolCards.size() < MINIMUM_TOOL_CARDS_REQUIRED)
            {
                logger.logErrorMessage("Not enough tool cards to play!");
                System.exit(0);
            }

            selectRandomToolCards();
        }
        catch(LoadingFilesException e)
        {
            System.exit(0);
        }
    }

    private void loadConfigFile(Logger logger)
    {
        try
        {
            logger.logMessage("Parsing " + CONFIG_FILE + "...");
            ConfigFile configFile = new ConfigFile(CONFIG_FILE);
            logger.logMessage("Config file loaded!");

            startTimer = new GameTimer(configFile.getServerTimer())
            {
                @Override
                public void timeUpdated()
                {
                    model.setStartTimer(this.getTime());
                    if(this.getTime() == 0)
                    {
                        try
                        {
                            startGameSetup();
                        }
                        catch(ChangeModelStateException e)
                        {
                            getView().showErrorMessage(e.getMessage());
                        }
                    }
                }
            };

            turnTimer = new GameTimer(configFile.getTurnTimer())
            {
                @Override
                public void timeUpdated()
                {
                    model.setTurnTimer(this.getTime());
                    if(this.getTime() == 0)
                    {
                        model.setCurrentPlayerActive(false);
                        turnTimer.stop();
                        endPlayerTurn();
                    }
                }
            };
        }
        catch(InvalidFileException|CannotReadFileException e)
        {
            logger.logErrorMessage(e.getMessage());
            System.exit(0);
        }
    }

    private void selectRandomToolCards()
    {
        List<ToolCard> selectedToolCards = new ArrayList<>();

        for(int i=0; i<MINIMUM_TOOL_CARDS_REQUIRED; i++)
            selectedToolCards.add(toolCards.remove(new Random().nextInt(toolCards.size())));

        toolCards = selectedToolCards;

        List<Card> modelToolCards = new ArrayList<>();

        for(ToolCard toolCard : toolCards)
            modelToolCards.add(toolCard.generateCard());

        model.setToolCards(modelToolCards);
    }

    private void selectRandomPublicCards()
    {
        publicCards = PublicObjectiveCard.getRandomCards(3);

        List<Card> modelCards = new ArrayList<>();

        for(PublicObjectiveCard pubCard : publicCards)
            modelCards.add(pubCard.generateCard());

        model.setPublicCards(modelCards);
    }

    /**
     * Adds a new player to the Game
     * @param nickname nickname of the player
     * @throws ChangeModelStateException if the Model cannot be modified
     */
    protected void addNewPlayer(String nickname) throws ChangeModelStateException
    {
        model.addNewPlayer(nickname);
    }

    /**
     * Removes a player from the Game
     * @param nickname nickname of the player
     * @throws ChangeModelStateException if the Model cannot be modified
     */
    protected void removePlayer(String nickname) throws ChangeModelStateException
    {
        model.removePlayer(nickname);
    }

    /**
     * Manages the start timer
     */
    protected void manageStartTime()
    {
        int playerNum = getModel().getPlayerNum();

        if(playerNum == 1)
        {
            startTimer.stop();
            startTimer.reset();
        }
        else if(playerNum == 2 || playerNum == 3)
        {
            startTimer.start();
        }
        else if(playerNum == 4)
        {
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        startGameSetup();
                    }
                    catch(ChangeModelStateException e)
                    {
                        getView().showErrorMessage(e.getMessage());
                    }
                }
            }).start();
        }
    }

    /**
     * Sets the scheme card of a player
     * @param player player to associate to a scheme card
     * @param choice number associated to a tool card of the current Game
     * @throws ChangeModelStateException if the Model cannot be modified
     */
    protected void chosePlayerSchemeCard(Player player, int choice) throws ChangeModelStateException
    {
        model.setPlayerSchemeCard(player, choice);
    }

    /**
     * Starts to sets up the game. Stops the start time, sets the new parser and selects random scheme cards for the player
     * @throws ChangeModelStateException if the Model cannot be modified
     */
    protected void startGameSetup() throws ChangeModelStateException
    {
        if(getModel().getPlayerNum() >= MIN_PLAYERS)
        {
            startTimer.stop();
            setEventParser(new GameSetupEventParser(this));
            model.selectRandomSchemeCardsForPlayers();
        }
    }

    /**
     * Sets a player ready/unready
     * @param player player to set ready or not ready
     * @param ready tells if the player needs to be ready or !ready
     */
    protected void setPlayerReady(Player player, boolean ready)
    {
        model.setPlayerReady(player, ready);
    }

    /**
     * Starts the game setting the proper event parser
     * @throws ChangeModelStateException if the Model cannot be modified
     */
    protected void startGame() throws ChangeModelStateException
    {
        model.startGame();
        setEventParser(new GameRunningEventParser(this));
        turnTimer.start();
        beginRound();
    }

    /**
     * Drafts a die
     * @param dieNum number of the die in the draftpool
     * @throws ChangeModelStateException if a change in the model is forbidden
     */
    protected void draftDie(int dieNum) throws ChangeModelStateException
    {
        model.draftDie(dieNum);
        playerHasDrafted = true;
    }

    /**
     * Skips the current next player second turn
     */
    public void skipNextPlayerTurn()
    {
        playerTurnIterator.skipSecondPlayerTurn();
    }

    /**
     * Selects a die from the board of a player
     * @param row first coordinate of the die in the board
     * @param column second coordiate of the die in the board
     * @throws ChangeModelStateException if the Model cannot be modified
     */
    protected void selectDieFromBoard(int row, int column) throws ChangeModelStateException
    {
        model.selectDieFromBoard(model.getCurrentPlayer(), row, column);
    }

    /**
     * Selects the same color die from the board of the current player
     * @param row first coordinate of the die in the board
     * @param column second coordinate of the die in the board
     * @throws ChangeModelStateException if the Model cannot be modified
     */
    protected void selectSameColorDieFromBoard(int row, int column) throws ChangeModelStateException
    {
        ToolCardParameters param = getCurrentToolCardParameters();

        int dieType = Model.CHOOSEN_DIE;

        if(param.isDraftedDie())
            dieType = Model.DRAFTET_DIE;
        else if(param.isSelectedDie())
            dieType = Model.SELECTED_DIE;

        model.selectSameColorDieFromBoard(model.getCurrentPlayer(), row, column, dieType);
    }

    /**
     * Starts the effects of the tool cards and sets the new parser
     * @param cardNum number of the tool card in the Game
     * @throws ChangeModelStateException if the Model cannot be modified
     */
    protected void startToolCardActions(int cardNum) throws ChangeModelStateException
    {
        model.setCurrentToolCard(cardNum);

        playerUsedToolCard = true;
        setEventParser(new UsingToolCardEventParser(this));

        usingToolCard = true;
        currentToolCard = toolCards.get(cardNum);
        currentToolCard.use(this);
    }

    /**
     * Ends the effects of the tool card and sets the new parser
     */
    protected void endToolCardActions()
    {
        usingToolCard = false;

        model.setNoCurrentToolCard();

        setEventParser(new GameRunningEventParser(this));

        view.showTurn();
    }

    /**
     * Performs the next tool card step ( executes the next action )
     */
    protected void nextToolCardStep()
    {
        if(currentToolCard != null)
            currentToolCard.executeNextAction(this);
    }

    /**
     * Tells if the tool card is being used
     * @return true if the tool card is being used, false otherwise
     */
    public boolean isToolCardBeingUsed()
    {
        return usingToolCard;
    }


    /**
     * Starts the round
     */
    protected void beginRound()
    {
        model.drawFromDiceBag();
        beginPlayerTurn();
    }

    /**
     * Begins the turn of the next player
     */
    protected void beginPlayerTurn()
    {
        playerHasDrafted = false;
        playerUsedToolCard = false;
        turnTimer.reset();
        turnTimer.start();
        model.setCurrentPlayer(playerTurnIterator.next());
    }

    /**
     * Ends the turn of a player
     */
    public void endPlayerTurn()
    {
        if(playerTurnIterator.isLastTurn())
            endRound();
        else
            beginPlayerTurn();
    }

    /**
     * Adds the remaining dice to the DraftPool to the RoundTrack and changes the current round
     */
    protected void endRound()
    {
        model.addLastDiceToRoundTrack();

        if(model.getCurrentRound() < TOTAL_ROUNDS-1)
        {
            try
            {
                model.setCurrentRound(model.getCurrentRound()+1);
            }
            catch(ChangeModelStateException e)
            {
            }

            beginRound();
        }
        else
        {
            score();
            model.endGame();
        }
    }

    /**
     * Returns the current tool card action parameters
     * @return current tool card action parameters
     */
    protected ToolCardParameters getCurrentToolCardParameters()
    {
        if(isToolCardBeingUsed())
            return currentToolCard.getCurrentAction().getParameters();
        else return null;
    }

    /**
     * Sets the proper event parser
     * @param eventParser proper event parser
     */
    protected void setEventParser(EventVisitor eventParser)
    {
        this.eventParser = eventParser;
    }

    /**
     * Tells if a player has drafted or not
     * @return true if the player has drafted, false if it has not
     */
    public boolean hasPlayerDrafted()
    {
        return playerHasDrafted;
    }

    /**
     * Tells if the player has already used the tool card in the current turn
     * @return true if the player has already used the tool card, false otherwise
     */
    public boolean hasPlayerUsedToolCard()
    {
        return playerUsedToolCard;
    }

    public void score()
    {
        for(int i=0; i<model.getPlayers().size(); i++)
        {
            Player player = model.getPlayers().get(i);

            for(int j=0; j<publicCards.size(); j++)
            {
                player.incrementScore(publicCards.get(j).score(player.getBoard()));
            }

            //player.incrementPrivateScore();
        }

    }
}

