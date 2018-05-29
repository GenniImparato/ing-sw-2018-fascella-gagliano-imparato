package it.polimi.se2018;

import it.polimi.se2018.model.*;
import it.polimi.se2018.utils.Color;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class TestRoundTrack
{
    private List<Die>[] dice;
    private DraftPool draftPool;
    private RoundTrack roundTrack;

    @Before
    public void setUp()
    {
        draftPool = new DraftPool(new DiceBag());
        roundTrack = new RoundTrack(draftPool);
    }

    @Test
    public void testRoundTrack()
    {
        for(int i=0; i<10;i++)
            assertEquals(0, roundTrack.getDiceAtRound(i).size());
    }

    @Test
    public void testClonedRoundTrack()
    {
        roundTrack.addDie(new Die(Color.getRandomColor()), 1);
        RoundTrack clonedRoundTrack = new RoundTrack(roundTrack, draftPool);



        for(int j=0; j<10; j++)
        {
            assertEquals(clonedRoundTrack.getDiceAtRound(j).size(), roundTrack.getDiceAtRound(j).size());
        }
    }

    @Test
    public void testPullDie()
    {
        assertEquals(null, roundTrack.pullDie(0,1));

        Die die = new Die(Color.getRandomColor());
        roundTrack.addDie(die,0);
        assertEquals(die,roundTrack.pullDie(0,0));
    }

    @Test
    public void testAddLastDice()
    {
        draftPool = new DraftPool(new DiceBag());
        int draftSize = draftPool.getAllDice().size();
        roundTrack.addLastDice(0);
        assertEquals(draftSize,roundTrack.getDiceAtRound(0).size());
    }
}
