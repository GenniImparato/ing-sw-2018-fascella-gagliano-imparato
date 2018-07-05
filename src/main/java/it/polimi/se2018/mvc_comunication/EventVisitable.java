package it.polimi.se2018.mvc_comunication;

/**
 * This interface is required to use the Visitor pattern. It is needed to permit a parser  to parse a generic Event.
 */
public interface EventVisitable
{
    void acceptVisitor(EventVisitor visitor);
}
