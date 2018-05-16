package it.polimi.se2018.controller;

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

    protected void beginToolCardActions(int cardNum) throws ChangeModelStateException
    {
        try
        {
            model.setCurrentToolCard(cardNum);
            usingToolCard = true;
            toolCardsActions = new ToolCardsActions(this);
            model.getCurrentToolCard().acceptVisitor(toolCardsActions);
        }
        catch (ChangeModelStateException e)
        {
            throw e;
        }
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

