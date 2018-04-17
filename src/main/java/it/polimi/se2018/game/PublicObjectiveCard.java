package it.polimi.se2018.game;

public abstract class PublicObjectiveCard extends Card {

    private int points;

    public abstract int score (Matrix matrix);

    public PublicObjectiveCard (String name, String description, int points)
    {
        super (name, description);
        this.points = points;
    }
}
