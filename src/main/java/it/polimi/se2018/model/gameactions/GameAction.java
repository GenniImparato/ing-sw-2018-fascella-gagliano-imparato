package it.polimi.se2018.model.gameactions;

import it.polimi.se2018.model.Game;

public abstract class GameAction
{
    protected String  errorMessage = "";
    protected boolean executed = false;

    public abstract void execute(Game game);

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public boolean hasBeenExecuted()
    {
        return executed;
    }
}
