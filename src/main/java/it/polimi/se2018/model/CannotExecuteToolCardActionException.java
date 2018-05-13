package it.polimi.se2018.model;

public class CannotExecuteToolCardActionException extends Exception
{
    private String message;

    public CannotExecuteToolCardActionException(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }
}
