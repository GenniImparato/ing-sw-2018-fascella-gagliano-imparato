package it.polimi.se2018.files.exceptions;

import it.polimi.se2018.utils.GeneralMessageException;

public class InvalidFileException extends GeneralMessageException
{
    public InvalidFileException(String message)
    {
        super(message);
    }
}
