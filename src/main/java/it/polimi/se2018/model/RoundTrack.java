package it.polimi.se2018.model;

import java.util.ArrayList;

public class RoundTrack
{
    private ArrayList<Die>[] dice;
    private DraftPool drafPool;

    public RoundTrack(DraftPool draftPool)
    {
        dice=new ArrayList[10];
        for (int i=0; i<10; i++)
            dice[i]=new ArrayList<>();
        this.drafPool = draftPool;
    }

    //add the remaining dice from the DraftPool to the RoundTrack at the given round
    public void addLastDice(int round)
    {
        dice[round] = drafPool.pullAllDice();
    }

    public ArrayList<Die> getDicesAtRound (int round)
    {
        return dice[round];
    }
}
