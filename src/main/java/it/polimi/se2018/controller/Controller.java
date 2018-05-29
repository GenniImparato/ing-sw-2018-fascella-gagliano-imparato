package it.polimi.se2018.controller;

import it.polimi.se2018.files.SchemeCardLoader;
import it.polimi.se2018.files.exceptions.LoadingFilesException;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.mvc_comunication.Event;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;
import it.polimi.se2018.mvc_comunication.messages.StartedGameMessage;
import it.polimi.se2018.view.ViewInterface;
import it.polimi.se2018.utils.*;

public class Controller implements Observer<Event>
{
    private Model               model;
    private ViewInterface       view;

    private EventParser         parser;

    private PlayerTurnIterator  playerTurnIterator;
    private ToolCardsActions    toolCardsActions;

    private boolean             usingToolCard = false;

    private boolean             ignoreValueRestriction;
    private boolean             ignoreColorRestriction;

    private boolean             playerHasDrafted = false;
    private boolean             playerUsesToolCard = false;

    private int                 currentRound;
    private static final int    TOTAL_ROUNDS = 10;

    public Controller(Model model)
    {
        setModel(model);

        parser = new EventParser(this);

        playerTurnIterator = new PlayerTurnIterator(this);

        start();
    }

    @Override
    public void update(Event event)
    {
        setView(event.getView());
        event.acceptVisitor(parser);
    }

    public Model getModel()
    {
        return model;
    }

    public ViewInterface getView()
    {
        return view;
    }

    public void setView(ViewInterface view)
    {
        this.view = view;
    }

    public void setModel(Model model)
    {
        this.model = model;
    }

    public void start()
    {
        loadSchemeCardFiles();
    }

    protected void loadSchemeCardFiles()
    {
        try
        {
            SchemeCardLoader loader = new SchemeCardLoader("./resources/schemecards/", new Logger() {
                @Override
                public void logMessage(String message) {

                }

                @Override
                public void logErrorMessage(String message) {

                }
            });
            model.setSchemeCards(loader.getGeneratedBoards());
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
        model.selectRandomSchemeCardsForPlayers();
    }

    protected void setPlayerReady(Player player, boolean ready)
    {
        model.setPlayerReady(player, ready);
    }

    protected void startGame() throws ChangeModelStateException
    {
        model.startGame();
        beginRound();
    }

    protected void draftDie(int dieNum) throws ChangeModelStateException
    {
        model.draftDie(dieNum);
    }

    protected void addDraftedDie(int row, int column) throws ChangeModelStateException
    {
        model.addDraftedDieToBoard(model.getCurrentPlayer(), row, column);
    }

    protected void selectDieFromBoard(int row, int column) throws ChangeModelStateException
    {
        model.selectDieFromBoard(model.getCurrentPlayer(), row, column);
    }

    protected void moveSelectedDie(int row, int column) throws ChangeModelStateException
    {
        model.moveSelectedDie(model.getCurrentPlayer(), row, column, ignoreValueRestriction, ignoreColorRestriction);
    }

    protected void beginToolCardActions(int cardNum) throws ChangeModelStateException
    {
        model.setCurrentToolCard(cardNum);
        usingToolCard = true;
        toolCardsActions = new ToolCardsActions(this);
        model.getCurrentToolCard().acceptVisitor(toolCardsActions);

    }

    protected void endToolCardActions()
    {
        usingToolCard = false;
        view.showTurn();
    }

    protected void nextToolCardStep()
    {
        model.getCurrentToolCard().acceptVisitor(toolCardsActions);
    }

    public boolean isToolCardBeingUsed()
    {
        return usingToolCard;
    }

    //used by the moveSelectedDie
    protected void setMoveDieOptions(boolean ignoreColorRestriction, boolean ignoreValueRestriction)
    {
        this.ignoreColorRestriction = ignoreColorRestriction;
        this.ignoreValueRestriction = ignoreValueRestriction;
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
}

