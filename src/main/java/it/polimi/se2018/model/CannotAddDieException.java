package it.polimi.se2018.model;

public class CannotAddDieException extends Exception
{
    private String message;

    public CannotAddDieException(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }
}
