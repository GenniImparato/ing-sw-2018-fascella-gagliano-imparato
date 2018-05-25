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
}
