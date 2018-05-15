package it.polimi.se2018.controller;

import it.polimi.se2018.events.Event;
import it.polimi.se2018.model.CannotAddPlayerException;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.utils.*;
import it.polimi.se2018.view.View;

public class Controller implements Observer<Event>
{
    private Model               model;
    private View                view;

    private EventParser         parser;

    private PlayerTurnIterator  playerTurnIterator;

    public Controller(View view, Model model)
    {
        this.view = view;
        this.model = model;

        parser = new EventParser(this);

        playerTurnIterator = new PlayerTurnIterator(this);
    }

    @Override
    public void update(Event event)
    {
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

    protected void addNewPlayer(String nickname)
    {
        try
        {
            model.addNewPlayer(nickname);
        }
        catch(CannotAddPlayerException e)
        {
            view.showErrorMessage(e.getMessage());
        }
    }
}

