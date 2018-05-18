package it.polimi.se2018.testcards.testpubliccards;

import it.polimi.se2018.controller.PublicObjectiveCardScorer;
import it.polimi.se2018.files.SagradaSchemeCardFile;
import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;
import it.polimi.se2018.model.publicobjectivecards.ColumnColorVarietyCard;
import it.polimi.se2018.utils.Color;
import org.junit.Test;
import static org.junit.Assert.*;


public class TestColumnColorVarietyPublicCards
{
    @Test
    public void testScore()
    {

        PublicObjectiveCardScorer scorer;
        SagradaSchemeCardFile sagradaSchemeCardFile;
        Board board = new Board();

        try                         //try to add some dice on the board to verify if the method score returns the right value
        {
            sagradaSchemeCardFile = new SagradaSchemeCardFile("resources/schemecards/1-Firmitas.sagradaschemecard");
            board = sagradaSchemeCardFile.generateBoard();
        }
        catch(Exception e) {fail();}

        Die die0 = new Die(Color.PURPLE);
        die0.setValue(1);
        Die die1 = new Die(Color.YELLOW);
        die1.setValue(5);
        Die die2 = new Die(Color.RED);
        die2.setValue(3);
        Die die3 = new Die(Color.BLUE);
        die3.setValue(5);

        Die die4 = new Die(Color.YELLOW);
        die4.setValue(6);
        Die die5 = new Die(Color.PURPLE);
        die5.setValue(3);
        Die die6 = new Die(Color.YELLOW);
        die6.setValue(2);
        Die die7 = new Die(Color.RED);
        die7.setValue(1);


        try
        {
            board.addDie(die0,0,0);
            board.addDie(die1,1,0);
            board.addDie(die2,2,0);
            board.addDie(die3,3,0);

            board.addDie(die4,0,1);
            board.addDie(die5,1,1);
            board.addDie(die6,2,1);
            board.addDie(die7,3,1);
        }
        catch(ChangeModelStateException e)
        {
            fail();
        }

        scorer = new PublicObjectiveCardScorer(board);

        assertEquals(5, new ColumnColorVarietyCard().acceptVisitor(scorer));
    }
}