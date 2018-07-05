package it.polimi.se2018.model.exceptions;

import it.polimi.se2018.utils.GeneralMessageException;

/**
 * Class used to represent the Exception that occur when the Model cannot change its state
 */
public class ChangeModelStateException extends GeneralMessageException
{
    /**
     * Constructor that calls the constructor of the superclass (that sets the message)
     * @param message string that describes the kind of exception
     */
    public ChangeModelStateException(String message)
    {
        super(message);
    }
}
