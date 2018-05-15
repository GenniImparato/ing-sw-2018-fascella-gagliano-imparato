package it.polimi.se2018.events;

public interface EventVisitable
{
    void acceptVisitor(EventVisitor visitor);
}
