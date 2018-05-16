package it.polimi.se2018.mvc_comunication;

public interface MessageVisitable
{
    void acceptVisitor(MessageVisitor visitor);
}
