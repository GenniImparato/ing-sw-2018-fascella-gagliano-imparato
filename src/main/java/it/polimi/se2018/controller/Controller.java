package it.polimi.se2018.controller;

import it.polimi.se2018.controller.tool_card.ToolCardParameters;
import it.polimi.se2018.files.SchemeCardsLoader;
import it.polimi.se2018.files.ToolCardsLoader;
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
    private static final int            MINIMUM_TOOL_CARDS_REQUIRED =   3 ;

    private Model               model;
    private ViewInterface       view;

    private EventVisitor        eventParser;

    private PlayerTurnIterator  playerTurnIterator;

    private List<ToolCard>      toolCards;
    private ToolCard            currentToolCard;

    private boolean             usingToolCard = false;

    private boolean             playerHasDrafted = false;
    private boolean             playerUsesToolCard = false;

    private int                 currentRound;
    private static final int    TOTAL_ROUNDS = 10;

    /**
     * Constructor that saves the reference to the model passed by parameter,
     * sets the default EventVisitor, creates a PlayerTurnIterator and invoke the method start()
     * @param model it's the model that will be saved
     */
    public Controller(Model model)
    {
        setModel(model);

        setEventParser(new GameNotStartedEventParser(this));

        playerTurnIterator = new PlayerTurnIterator(this);

        start();
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
        event.acceptVisitor(eventParser);
    }

    /**
     * Getter of the Model
     * @return the reference of the model
     */
    public Model getModel()
    {
        return model;
    }

    /**
     * Getter of the ViewInterface
     * @return the reference of the viewInterface
     */
    public ViewInterface getView()
    {
        return view;
    }

    /**
     * Setter of the ViewInterface that notifies the Controller
     * @param view that will be saved
     */
    public void setView(ViewInterface view)
    {
        this.view = view;
    }

    /**
     * Setter of the Model
     * @param model that will be saved
     */
    public void setModel(Model model)
    {
        this.model = model;
    }


    public void start()
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
        loadToolCardFiles(logger);
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

    private void loadToolCardFiles(Logger logger)
    {
        try
        {
            ToolCardsLoader loader = new ToolCardsLoader(TOOL_CARDS_DIRECTORY, logger);
            toolCards = loader.getToolCards();

            if(toolCards.size() < MINIMUM_TOOL_CARDS_REQUIRED)
            {
                logger.logErrorMessage("Not enough tool cards to play!");
                System.exit(0);
            }

            toolCards.remove(2);
            toolCards.remove(0);
            List<Card> cards = new ArrayList<>();
            for(int i =0; i< 3; i++)
                cards.add(toolCards.get(i).generateCard());

            model.setToolCards(cards);
        }
        catch(LoadingFilesException e)
        {
            System.exit(0);
        }
    }

    protected void addNewPlayer(String nickname) throws ChangeModelStateException
    {
        model.addNewPlayer(nickname);
    }

    protected void removePlayer(String nickname) throws ChangeModelStateException
    {
        model.removePlayer(nickname);
    }

    protected void chosePlayerSchemeCard(Player player, int choice) throws ChangeModelStateException
    {
        model.setPlayerSchemeCard(player, choice);
    }

    protected void startGameSetup() throws ChangeModelStateException
    {
        setEventParser(new GameSetupEventParser(this));
        model.selectRandomSchemeCardsForPlayers();
    }

    protected void setPlayerReady(Player player, boolean ready)
    {
        model.setPlayerReady(player, ready);
    }

    protected void startGame() throws ChangeModelStateException
    {
        model.startGame();
        setEventParser(new GameRunningEventParser(this));
        beginRound();
    }

    protected void draftDie(int dieNum) throws ChangeModelStateException
    {
        model.draftDie(dieNum);
    }

    protected void selectDieFromBoard(int row, int column) throws ChangeModelStateException
    {
        model.selectDieFromBoard(model.getCurrentPlayer(), row, column);
    }

    protected void startToolCardActions(int cardNum) throws ChangeModelStateException
    {
        model.setCurrentToolCard(cardNum);

        setEventParser(new UsingToolCardEventParser(this));

        usingToolCard = true;
        currentToolCard = toolCards.get(cardNum);
        currentToolCard.use(this);
    }

    protected void endToolCardActions()
    {
        usingToolCard = false;

        setEventParser(new GameRunningEventParser(this));

        view.showTurn();
    }

    protected void nextToolCardStep()
    {
        if(currentToolCard != null)
            currentToolCard.executeNextAction(this);
    }

    public boolean isToolCardBeingUsed()
    {
        return usingToolCard;
    }


    protected void beginRound()
    {
        model.drawFromDiceBag();
        beginPlayerTurn();
    }

    protected void beginPlayerTurn()
    {
        model.setCurrentPlayer(playerTurnIterator.next());
    }

    protected void endPlayerTurn()
    {
        if(playerTurnIterator.isLastTurn())
            endRound();
        else
            beginPlayerTurn();
    }

    //add the remaining dice in the DraftPool to the RoundTrack
    protected void endRound()
    {
        model.addLastDiceToRoundTrack();

        if(model.getCurrentRound() < TOTAL_ROUNDS-1)
        {
            try
            {
                model.setCurrentRound(model.getCurrentRound()+1);
            }
            catch (ChangeModelStateException e)
            {

            }

            beginRound();
        }
    }

    protected ToolCardParameters getCurrentToolCardParameters()
    {
        if(isToolCardBeingUsed())
            return currentToolCard.getCurrentAction().getParameters();
        else return null;
    }

    protected void setEventParser(EventVisitor eventParser)
    {
        this.eventParser = eventParser;
    }
}

