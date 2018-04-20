package it.polimi.se2018.game;

public abstract class ToolCard extends Card {

    private int num;
    private int tokens;

    public ToolCard (String name, String description, int num)
    {
        super (name, description);
        this.num = num;
        tokens = 0;
    }
}
