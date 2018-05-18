package it.polimi.se2018.testcards;

import it.polimi.se2018.model.publicobjectivecards.ColorDiagonalsCard;
import it.polimi.se2018.model.publicobjectivecards.PublicObjectiveCard;
import it.polimi.se2018.testcards.testpubliccards.*;
import it.polimi.se2018.utils.Observer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.junit.runners.Suite;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class TestPublicObjectiveCards {

    private PublicObjectiveCard card;
    private List<PublicObjectiveCard> ret;

    @Before
    public void setUp()
    {
        card = new ColorDiagonalsCard();
    }
    @Test
    public void testGetter()
    {

        assertEquals("Color Diagonals", card.getName());
        assertEquals("Count of diagonally adjacent same color dice", card.getDescription());
    }

    @Before
    public void setUp1()
    {
        ret = PublicObjectiveCard.getRandomCards(10);
    }
    @Test
    public void testRandomGeneration()
    {
        assertEquals(10, ret.size());
    }
}
