package it.polimi.se2018.controller;

import it.polimi.se2018.files.SchemeCardLoader;
import it.polimi.se2018.files.exceptions.LoadingFilesException;
import it.polimi.se2018.mvc_comunication.Event;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;
import it.polimi.se2018.utils.*;
import it.polimi.se2018.view.View;

public class Controller implements Observer<Event>
{
    private Model               model;
    private View                view;

    private EventParser         parser;

    private PlayerTurnIterator  playerTurnIterator;
    private ToolCardsActions    toolCardsActions;

    private boolean             usingToolCard = false;

    private boolean             ignoreValueRestriction;
    private boolean             ignoreColorRestriction;

    public Controller(Model model)
    {
        setModel(model);

        parser = new EventParser(this);

        playerTurnIterator = new PlayerTurnIterator(this);
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

    public View getView()
    {
        return view;
    }

    public void setView(View view)
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
        view.start();
    }

    protected void loadSchemeCardFiles()
    {
        try
        {
            SchemeCardLoader loader = new SchemeCardLoader("resources/schemecards/", view.getLogger());
        }
        catch(LoadingFilesException e)
        {
        }
    }

    protected void addNewPlayer(String nickname) throws ChangeModelStateException
    {
        model.addNewPlayer(nickname);
    }

    protected void startGame() throws ChangeModelStateException
    {
        beginRound();
        model.startGame();
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


    private void beginRound()
    {
        model.drawFromDiceBag();
        beginPlayerTurn();
    }

    private void beginPlayerTurn()
    {
        model.setCurrentPlayer(playerTurnIterator.next());
    }
}

