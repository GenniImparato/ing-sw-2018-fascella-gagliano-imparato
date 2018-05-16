package it.polimi.se2018.mvc_comunication;

public interface EventVisitable
{
    void acceptVisitor(EventVisitor visitor);
}
