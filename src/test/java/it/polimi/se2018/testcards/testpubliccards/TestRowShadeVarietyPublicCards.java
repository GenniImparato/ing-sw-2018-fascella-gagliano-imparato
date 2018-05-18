package it.polimi.se2018.testcards.testpubliccards;

import it.polimi.se2018.controller.PublicObjectiveCardScorer;
import it.polimi.se2018.files.SagradaSchemeCardFile;
import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;
import it.polimi.se2018.model.publicobjectivecards.RowShadeVarietyCard;
import it.polimi.se2018.utils.Color;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestRowShadeVarietyPublicCards
{
    private static PublicObjectiveCardScorer scorer;
    private static SagradaSchemeCardFile sagradaSchemeCardFile;
    private static Board board;
    private static Die die0;
    private static Die die1;
    private static Die die2;
    private static Die die3;
    private static Die die4;
    private static Die die5;
    private static Die die6;
    private static Die die7;
    private static Die die8;
    private static Die die9;
    private static Die die10;

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
        die0 = new Die(Color.PURPLE);
        die0.setValue(2);
        die1 = new Die(Color.GREEN);
        die1.setValue(6);
        die2 = new Die(Color.YELLOW);
        die2.setValue(4);
        die3 = new Die(Color.PURPLE);
        die3.setValue(1);
        die4 = new Die(Color.RED);
        die4.setValue(3);

        die5 = new Die(Color.RED);
        die5.setValue(5);
        die6 = new Die(Color.PURPLE);
        die6.setValue(4);
        die7 = new Die(Color.RED);
        die7.setValue(3);
        die8 = new Die(Color.YELLOW);
        die8.setValue(5);
        die9 = new Die(Color.GREEN);
        die9.setValue(6);

        die10 = new Die(Color.PURPLE);
        die10.setValue(4);

        try {
            board.addDie(die0, 0, 0);
            board.addDie(die1, 0, 1);
            board.addDie(die2, 0, 2);
            board.addDie(die3, 0, 3);
            board.addDie(die4, 0, 4);

            board.addDie(die5, 1, 0);
            board.addDie(die6, 1, 1);
            board.addDie(die7, 1, 2);
            board.addDie(die8, 1, 3);
            board.addDie(die9, 1, 4);

            board.addDie(die10,2,0);


        } catch (ChangeModelStateException e) {
            fail();
        }
    }

    @Before
    public void setUp()
    {
        scorer = new PublicObjectiveCardScorer(board);
    }

    @Test
    public void testScore()
    {
        assertEquals(5, new RowShadeVarietyCard().acceptVisitor(scorer));
    }

}
