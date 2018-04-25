package it.polimi.se2018.game;

import java.util.ArrayList;

public class RoundTrack
{
    private ArrayList<Die>[] dice;
    private DraftPool drafPool;

    public RoundTrack(DraftPool draftPool)
    {
        dice=new ArrayList[10];
        this.drafPool = draftPool;
    }

    //add the remaining dice from the DraftPool to the RoundTrack at the given round
    public void addLastDice(int round)
    {
        dice[round] = drafPool.getAllDice();
    }
}
