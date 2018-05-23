package it.polimi.se2018.network.exceptions;

import it.polimi.se2018.utils.GeneralMessageException;

public class CannotConnectToServerException extends GeneralMessageException
{
    public CannotConnectToServerException(String message)
    {
        super(message);
    }
}
