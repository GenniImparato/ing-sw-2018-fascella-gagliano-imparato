package it.polimi.se2018.model.toolcards;

public interface ToolCardVisitable
{
    void acceptVisitor(ToolCardVisitor visitor);
}
