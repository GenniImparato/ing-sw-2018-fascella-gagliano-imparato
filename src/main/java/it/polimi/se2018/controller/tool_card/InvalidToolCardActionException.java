package it.polimi.se2018.controller.tool_card;

import it.polimi.se2018.utils.GeneralMessageException;

public class InvalidToolCardActionException extends GeneralMessageException
{
    public InvalidToolCardActionException(String message)
    {
        super(message);
    }
}
