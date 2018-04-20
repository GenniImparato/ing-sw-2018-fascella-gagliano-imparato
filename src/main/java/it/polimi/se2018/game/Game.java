package it.polimi.se2018.game;

//singleton class
public class Game
{
    private static Game instance = null;                            //used to store the only reference to the class

    private Player[]                players;
    private PublicObjectiveCard[]   publicCards;
    private ToolCard[]              toolCards;
    private DiceBag                 diceBag;
    private DraftPool               draftPool;
    private RoundTrack              roundTrack;

    private Game()
    {
        players = new Player[1];
        players[0] = new Player();
        diceBag.getInstance();
        draftPool.getInstance();
        roundTrack.getInstance();
    }

    public static Game getInstance()
    {
        if(instance == null)
            instance = new Game();

        return instance;
    }

    public Player getPlayer(int num)
    {
        return players[num];
    }
}
