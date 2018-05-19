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

/**
 * Class used to test the methods of the class PublicObjectiveCard
 * @author Carmelo Fascella
 */
public class TestPublicObjectiveCards {

    private PublicObjectiveCard card;
    private List<PublicObjectiveCard> ret;

    /**
     * Sets up the cards that will be used in the following tests
     */
    @Before
    public void setUp()
    {
        card = new ColorDiagonalsCard();
        ret = PublicObjectiveCard.getRandomCards(10);
    }

    /**
     * Test the getter of the publicObjectiveCard
     */
    @Test
    public void testGetter()
    {

        assertEquals("Color Diagonals", card.getName());
        assertEquals("Count of diagonally adjacent same color dice", card.getDescription());
    }

    /**
     * Test the generation of a set of 10 public cards
     */
    @Test
    public void testRandomGeneration()
    {
        assertEquals(10, ret.size());
    }
}
