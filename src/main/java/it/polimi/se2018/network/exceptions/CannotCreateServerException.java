package it.polimi.se2018.network.exceptions;

import it.polimi.se2018.utils.GeneralMessageException;

public class CannotCreateServerException extends GeneralMessageException
{
    public CannotCreateServerException(String message)
    {
        super(message);
    }
}
