package it.polimi.se2018.game;

public class PrivateObjectiveCard extends Card {

    private Color color;

    public PrivateObjectiveCard (String name, String description, Color color)
    {
        super (name, description );

        this.color=color;
    }
}
