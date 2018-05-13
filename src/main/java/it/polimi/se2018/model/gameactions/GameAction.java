package it.polimi.se2018.model.gameactions;

import it.polimi.se2018.model.Game;

/**
 * Abstract class used to represent a generic action of the Game.
 * @author Generoso Imparato
 */
public abstract class GameAction
{
    protected String  errorMessage = "";
    protected boolean executed = false;

    /**
     * Abstract method that will be overridden by subclasses to effectively execute a single action.
     * @param game Context where the action will be executed
     */
    public abstract void execute(Game game);

    /**
     * Returns the error message associated to the Game action
     * @return String representing an error message
     */
    public String getErrorMessage()
    {
        return errorMessage;
    }

    /**
     * Tells if the action has been executed or not.
     * @return If the action is executed returns true, else otherwise.
     */
    public boolean hasBeenExecuted()
    {
        return executed;
    }
}
