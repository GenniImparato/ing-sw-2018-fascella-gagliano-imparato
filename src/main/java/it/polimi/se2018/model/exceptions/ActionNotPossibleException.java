package it.polimi.se2018.model.exceptions;

/**
 * Class used to represent the Exception that occur when an action is forbidden because it can't happen. For example,
 * the player wants to add a die to his board but there's no cell available
 */
public class ActionNotPossibleException extends Exception
{
}
