package it.polimi.se2018.model;

import java.util.ArrayList;
import java.util.List;

public class RoundTrack
{
    private List<Die>[] dice;
    private DraftPool draftPool;

    public RoundTrack(DraftPool draftPool)
    {
        dice=new ArrayList[10];

        for (int i=0; i<10; i++)
            dice[i]=new ArrayList<>();

        this.draftPool = draftPool;
    }

    //copy constructor
    public RoundTrack(RoundTrack roundTrack, DraftPool draftPool)
    {
        this.dice = new ArrayList[10];

        for (int i=0; i<10; i++)
        {
            this.dice[i]=new ArrayList<>();

            for(Die die : roundTrack.dice[i])
            {
                this.dice[i].add(new Die(die));
            }
        }

        this.draftPool = draftPool;
    }

    //add the remaining dice from the DraftPool to the RoundTrack at the given round
    public void addLastDice(int round)
    {
        dice[round] = draftPool.pullAllDice();
    }

    public List<Die> getDicesAtRound (int round)
    {
        return dice[round];
    }
}
