package it.polimi.se2018.model.publicobjectivecards;

public interface PublicObjectiveCardVisitable
{
    int acceptVisitor(PublicObjectiveCardVisitor visitor);
}
