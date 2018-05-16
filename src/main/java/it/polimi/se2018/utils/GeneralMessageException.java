package it.polimi.se2018.utils;

public abstract class GeneralMessageException extends Exception
{
    private String message;

    public GeneralMessageException(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }
}
