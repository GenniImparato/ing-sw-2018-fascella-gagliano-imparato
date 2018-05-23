package it.polimi.se2018.network.exceptions;

import it.polimi.se2018.utils.GeneralMessageException;

public class CannotCreateServerException extends Exception
{
    private String message1;
    private String message2;

    public CannotCreateServerException(String message1, String message2)
    {
        this.message1 = message1;
        this.message2 = message2;
    }

    public String getMessage1()
    {
        return message1;
    }

    public String getMessage2()
    {
        return message2;
    }
}
