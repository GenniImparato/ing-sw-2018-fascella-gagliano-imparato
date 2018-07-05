package it.polimi.se2018.controller.tool_card;

import it.polimi.se2018.utils.GeneralMessageException;

/**
 * Class that represents the Exception thrown when a tool card is not valid (for example it doesn't exists)
 */
public class InvalidToolCardActionException extends GeneralMessageException
{
    public InvalidToolCardActionException(String message)
    {
        super(message);
    }
}
