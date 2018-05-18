package it.polimi.se2018.testcards.testpubliccards;

import it.polimi.se2018.controller.PublicObjectiveCardScorer;
import it.polimi.se2018.files.SagradaSchemeCardFile;
import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;
import it.polimi.se2018.model.publicobjectivecards.ColorDiagonalsCard;
import it.polimi.se2018.utils.Color;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestColorDiagonalPublicCards
{
    @Test
    public void testScore()
    {
        PublicObjectiveCardScorer scorer;
        SagradaSchemeCardFile sagradaSchemeCardFile;
        Board board = new Board();

        try                         //try to add some dice on the board to verify if the method score returns the right value
        {
            sagradaSchemeCardFile = new SagradaSchemeCardFile("resources/schemecards/Firmitas.sagradaschemecard");
            board = sagradaSchemeCardFile.generateBoard();
        }
        catch(Exception e) {fail();}

        Die die0 = new Die(Color.PURPLE);
        die0.setValue(6);
        Die die1 = new Die(Color.PURPLE);
        die1.setValue(5);
        Die die2 = new Die(Color.PURPLE);
        die2.setValue(4);
        Die die3 = new Die(Color.PURPLE);
        die3.setValue(3);
        Die die4 = new Die(Color.PURPLE);
        die4.setValue(1);
        Die die5 = new Die(Color.RED);                      //try to add a die with a different color of the others
        die5.setValue(2);                                   //it won't be counted in the score method

        try
        {
            board.addDie(die0, 0, 0);
            board.addDie(die1, 1, 1);
            board.addDie(die2, 2, 2);
            board.addDie(die3, 3, 3);
            board.addDie(die4, 3, 1);
            board.addDie(die5, 2, 4);
        }
        catch(ChangeModelStateException e)
        {
            fail();
        }

        scorer = new PublicObjectiveCardScorer(board);          //creates the scorer with the board just created before

        assertEquals(5, new ColorDiagonalsCard().acceptVisitor(scorer));




    }

}
