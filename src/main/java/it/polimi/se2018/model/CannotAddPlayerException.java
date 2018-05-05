package it.polimi.se2018.model;

public class CannotAddPlayerException extends Exception
{
    private String message;

    public CannotAddPlayerException(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }

}