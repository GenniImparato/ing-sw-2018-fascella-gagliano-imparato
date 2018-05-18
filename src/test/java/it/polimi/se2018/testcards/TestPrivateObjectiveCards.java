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

public class TestPrivateObjectiveCards
{
    private static SagradaSchemeCardFile sagradaSchemeCardFile;
    private static Board board;
    private Die die0;
    private Die die1;
    private Die die2;
    private PrivateObjectiveCard privateObjectiveCard;
    private PrivateObjectiveCard privateCard;

    @BeforeClass
    public static void setUpClass()
    {
        board = new Board();

        try                         //try to add some dice on the board to verify if the method score returns the right value
        {
            sagradaSchemeCardFile = new SagradaSchemeCardFile("resources/schemecards/Firmitas.sagradaschemecard");
            board = sagradaSchemeCardFile.generateBoard();
        }
        catch(Exception e) {fail();}
    }


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
    @Test
    public void testScore()
    {


        assertEquals(3, new PrivateObjectiveCard(Color.PURPLE).score(board));

        assertEquals(5, new PrivateObjectiveCard(Color.GREEN).score(board));

        assertEquals(0, new PrivateObjectiveCard(Color.YELLOW).score(board));       //test with color of dice not on the board
    }


    @Test
    public void testGetColor()
    {
        assertEquals(Color.PURPLE, new PrivateObjectiveCard(Color.PURPLE).getColor());

        assertNotEquals(Color.GREEN, new PrivateObjectiveCard(Color.PURPLE).getColor());
    }


    @Before
    public void setUp1()
    {
        privateObjectiveCard = new PrivateObjectiveCard(Color.RED);
        privateCard = new PrivateObjectiveCard(privateObjectiveCard);
    }
    @Test
    public void testCloneCard()
    {


        assertEquals(privateObjectiveCard.getName(), privateCard.getName());
        assertEquals(privateObjectiveCard.getDescription(), privateCard.getDescription());
    }
}
