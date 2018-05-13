package it.polimi.se2018.model;

public class CannotPlaceDieException extends Exception
{
    private String message;

    public CannotPlaceDieException(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }
}
