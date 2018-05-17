package it.polimi.se2018.testcards;

import it.polimi.se2018.model.publicobjectivecards.ColorDiagonalsCard;
import it.polimi.se2018.model.publicobjectivecards.PublicObjectiveCard;
import it.polimi.se2018.testcards.testpubliccards.*;
import it.polimi.se2018.utils.Observer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.junit.runners.Suite;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestColorDiagonalPublicCards.class,
        TestColorVarietyPublicCards.class,
        TestColumnColorVarietyPublicCards.class,
        TestColumnShadeVarietyPublicCards.class,
        TestDeepShadesPublicCards.class,
        TestLightShadesCards.class,
        TestMediumShadesPublicCards.class,
        TestRowColorVarietyPublicCards.class,
        TestRowShadeVarietyPublicCards.class,
        TestShadeVarietyPublicCards.class
})

public class TestPublicObjectiveCards {

    @Test
    public void testGetter()
    {
        PublicObjectiveCard card = new ColorDiagonalsCard();
        assertEquals("Color Diagonals", card.getName());
        assertEquals("Count of diagonally adjacent same color dice", card.getDescription());
    }

    /*@Test
    public void testRandomGeneration()
    {

        List<PublicObjectiveCard> ret = new ArrayList<>();
        /*ret = PublicObjectiveCard.getRandomCards(3);

        assertEquals(3, ret.size());


    }*/
}
