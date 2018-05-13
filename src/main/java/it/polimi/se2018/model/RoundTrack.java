package it.polimi.se2018.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to represent the RoundTrack
 * @author Matteo Gagliano
 * @author Carmelo Fascella
 * @author Generoso Imparato
 */
public class RoundTrack
{
    private List<Die>[] dice;
    private DraftPool draftPool;

    /**
     * Constructor that creates a RoundTrack
     * @param draftPool of the game
     */
    public RoundTrack(DraftPool draftPool)
    {
        dice=new ArrayList[10];

        for (int i=0; i<10; i++)
            dice[i]=new ArrayList<>();

        this.draftPool = draftPool;
    }

    /**
     * Copy constructor
     * @param roundTrack source instance to be cloned
     * @param draftPool of the game
     */
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

    public Die pullDie(int round, int num)
    {
        if(dice[round].size() <= num)
            return null;
        else
        {
            Die ret = dice[round].get(num);
            dice[round].remove(num);
            return ret;
        }
    }

    public void addDie(Die die, int round)
    {
        dice[round].add(die);
    }

    /**
     * Adds the remaining dice from the DraftPool to the RoundTrack at the given round
     * @param round is the index of the RoundTrack where the dice are added
     */
    public void addLastDice(int round)
    {
        dice[round] = draftPool.pullAllDice();
    }

    /**
     * Returns a list of Dice from the RoundTrack at given round
     * @param round is the index of the RoundTrack where the dice are returned from
     * @return list of Dice from the RoundTrack at given round
     */
    public List<Die> getDiceAtRound (int round)
    {
        return dice[round];
    }
}
