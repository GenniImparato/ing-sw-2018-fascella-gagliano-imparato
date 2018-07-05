package it.polimi.se2018.testcards.testpubliccards;

import it.polimi.se2018.files.SagradaSchemeCardFile;
import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.exceptions.ActionNotPossibleException;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;
import it.polimi.se2018.controller.public_objective_cards.LightShadesCard;
import it.polimi.se2018.utils.Color;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class used to test the methods of the class LightShadesCard
 * @author Carmelo Fascella
 */
public class TestLightShadesCards
{
    private static SagradaSchemeCardFile sagradaSchemeCardFile;
    private static Board board;
    private static Die die0;
    private static Die die1;
    private static Die die2;
    private static Die die3;

    /**
     * Tries to add some dice on the board to verify if the scorer calculates the right value
     */
    @BeforeClass
    public static void setUpClass()
    {
        board = new Board();

        try
        {
            sagradaSchemeCardFile = new SagradaSchemeCardFile("resources/scheme_cards/Firmitas.sagradaschemecard");
            board = sagradaSchemeCardFile.generateBoard();
        }
        catch(Exception e) {fail();}

        die0 = new Die(Color.PURPLE);
        die0.setValue(2);
        die1 = new Die(Color.PURPLE);
        die1.setValue(1);
        die2 = new Die(Color.PURPLE);
        die2.setValue(2);
        die3 = new Die(Color.PURPLE);
        die3.setValue(6);


        try                     //try to add one "1" and two "2" and one die of another value
        {
            board.addDie(die0,0,0, false, false, false);
            board.addDie(die1,1,1, false, false, false);
            board.addDie(die2,2,2, false, false, false);
            board.addDie(die3,3,3, false, false, false);


        }
        catch(ChangeModelStateException|ActionNotPossibleException e)
        {
            fail();
        }
    }

    /**
     * Test if the scorer calculates the score related to one single set formed by one 1-die and one 2-die
     */
    @Test
    public void testScore()
    {
        assertEquals(2, new LightShadesCard().score(board));
    }
}
