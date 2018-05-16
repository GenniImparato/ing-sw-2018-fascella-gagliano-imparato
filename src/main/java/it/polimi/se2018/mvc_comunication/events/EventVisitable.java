package it.polimi.se2018.mvc_comunication.events;

public interface EventVisitable
{
    void acceptVisitor(EventVisitor visitor);
}
