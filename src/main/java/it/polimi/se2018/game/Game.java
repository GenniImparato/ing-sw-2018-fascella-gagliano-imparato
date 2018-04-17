package it.polimi.se2018.game;

public class Game
{
    private static Game instance = null;

    private Player[]                player;
    private PublicObjectiveCard[]   publicCards;
    private ToolCard[]              toolCards;

    private Game()
    {
        player = new Player[1];
        player[0] = new Player();
    }

    public static Game getInstance()
    {
        if(instance == null)
            instance = new Game();

        return instance;
    }

    public Player getPlayer(int num)
    {
        return player[num];
    }
}
