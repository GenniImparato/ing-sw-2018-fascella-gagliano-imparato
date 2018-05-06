package it.polimi.se2018.view.gui;

import it.polimi.se2018.events.Message;
import it.polimi.se2018.events.messages.DraftedDieMessage;
import it.polimi.se2018.model.Game;
import it.polimi.se2018.view.View;

public class GUI extends View
{
    public GUI(Game game)
    {
        super(game);
    }

    public void start(){}
    public void showErrorMessage(String message){}
    public void showMessage(String message){}
    @Override
    public void update(Message event)
    {
    }
}
