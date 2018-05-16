package it.polimi.se2018.model.exceptions;

import it.polimi.se2018.utils.GeneralMessageException;

public class ChangeModelStateException extends GeneralMessageException
{
    public ChangeModelStateException(String message)
    {
        super(message);
    }
}
