package it.polimi.se2018.testcards.testpubliccards;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Class used to test the suite of all the public cards
 * @author Carmelo Fascella
 */
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

public class TestSuite
{
}