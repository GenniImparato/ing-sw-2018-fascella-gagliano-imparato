package it.polimi.se2018.testcards;

import it.polimi.se2018.controller.PublicObjectiveCardScorer;
import it.polimi.se2018.files.SagradaSchemeCardFile;
import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.PrivateObjectiveCard;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;
import it.polimi.se2018.utils.Color;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class used to test the methods of the class PrivateObjectiveCard
 * @author Carmelo Fascella
 */
public class TestPrivateObjectiveCards
{
    private static SagradaSchemeCardFile sagradaSchemeCardFile;
    private static Board board;
    private Die die0;
    private Die die1;
    private Die die2;
    private PrivateObjectiveCard privateObjectiveCard;
    private PrivateObjectiveCard privateCard;

    /**
     * Tries to creats a board in order to be used in the test methods
     */
    @BeforeClass
    public static void setUpClass()
    {
        board = new Board();

        try
        {
            sagradaSchemeCardFile = new SagradaSchemeCardFile("resources/schemecards/Firmitas.sagradaschemecard");
            board = sagradaSchemeCardFile.generateBoard();
        }
        catch(Exception e) {fail();}
    }


    /**
     * Tries to add some dice on the board to test the method score of the PrivateObjectiveCard.
     */
    @Before
    public void setUp()
    {
        die0 = new Die(Color.PURPLE);
        die0.setValue(2);

        die1 = new Die(Color.GREEN);
        die1.setValue(5);

        die2 = new Die(Color.PURPLE);
        die2.setValue(1);

        try
        {
            board.addDie(die0,0,0);
            board.addDie(die1,1,0);
            board.addDie(die2,2,0);
        }
        catch(ChangeModelStateException e)
        {
        }


    }

    /**
     * Tests if the method score of the PrivateObjectiveCard calculates the sum of the values of the dice added on the board that match with the color of the card
     */
    @Test
    public void testScore()
    {


        assertEquals(3, new PrivateObjectiveCard(Color.PURPLE).score(board));

        assertEquals(5, new PrivateObjectiveCard(Color.GREEN).score(board));

        assertEquals(0, new PrivateObjectiveCard(Color.YELLOW).score(board));       //test with color of dice not on the board
    }


    /**
     * Test if the getter of the PrivateObjectiveCard returns its proper color
     */
    @Test
    public void testGetColor()
    {
        assertEquals(Color.PURPLE, new PrivateObjectiveCard(Color.PURPLE).getColor());

        assertNotEquals(Color.GREEN, new PrivateObjectiveCard(Color.PURPLE).getColor());
    }


    /**
     * Sets up two cards in order to be tested in testCloneCard()
     * */
    @Before
    public void setUp1()
    {
        privateObjectiveCard = new PrivateObjectiveCard(Color.RED);
        privateCard = new PrivateObjectiveCard(privateObjectiveCard);
    }

    /**
     * Test the generation of cloned card
     */
    @Test
    public void testCloneCard()
    {


        assertEquals(privateObjectiveCard.getName(), privateCard.getName());
        assertEquals(privateObjectiveCard.getDescription(), privateCard.getDescription());
    }
}
