package it.polimi.se2018.game;

//singleton class
public class Game
{

    private Player[]                players;
    private PublicObjectiveCard[]   publicCards;
    private ToolCard[]              toolCards;
    private DiceBag                 diceBag;
    private DraftPool               draftPool;
    private RoundTrack              roundTrack;

    public Game()
    {
        players = new Player[1];
        players[0] = new Player();
        diceBag = new DiceBag();
        draftPool = new DraftPool(diceBag);
        roundTrack = new RoundTrack();
    }

    public Player getPlayer(int num)
    {
        return players[num];
    }
}
