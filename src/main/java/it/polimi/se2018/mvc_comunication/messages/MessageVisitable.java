package it.polimi.se2018.mvc_comunication.messages;

public interface MessageVisitable
{
    void acceptVisitor(MessageVisitor visitor);
}
