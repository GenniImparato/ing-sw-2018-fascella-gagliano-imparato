package it.polimi.se2018;

import it.polimi.se2018.files.SagradaSchemeCardFile;
import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.CannotPlaceDieException;
import it.polimi.se2018.model.Color;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.publicobjectivecards.ColorDiagonalsCard;
import it.polimi.se2018.model.publicobjectivecards.PublicObjectiveCard;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestColorDiagonalCard
{
    @Test
    public void testScore()
    {
        SagradaSchemeCardFile sagradaSchemeCardFile;
        Board board = new Board();
        PublicObjectiveCard card = new ColorDiagonalsCard();


        try                         //try to add some dice on the board to verify if the method score returns the right value
        {
            sagradaSchemeCardFile = new SagradaSchemeCardFile("resources/schemecards/1-Firmitas.sagradaschemecard");
            board = sagradaSchemeCardFile.generateBoard();
        }
        catch(Exception e)
        {
            fail();
        }

        Die die0 = new Die(Color.PURPLE);
        die0.setValue(6);
        try
        {
            board.addDie(die0, 0, 0);
        }
        catch(CannotPlaceDieException e)
        {
            fail();
        }


        Die die1 = new Die(Color.PURPLE);
        die1.setValue(5);
        try
        {
            board.addDie(die1, 1, 1);
        }
        catch(CannotPlaceDieException e)
        {
            fail();
        }

        Die die2 = new Die(Color.PURPLE);
        die2.setValue(4);
        try
        {
            board.addDie(die2, 2, 2);
        }
        catch(CannotPlaceDieException e)
        {
            fail();
        }

        Die die3 = new Die(Color.PURPLE);
        die3.setValue(3);
        try
        {
            board.addDie(die3, 3, 3);
        }
        catch(CannotPlaceDieException e)
        {
            fail();
        }

        Die die4 = new Die(Color.PURPLE);
        die4.setValue(1);
        try
        {
            board.addDie(die4, 3, 1);
        }
        catch(CannotPlaceDieException e)
        {
            fail();
        }

        Die die5 = new Die(Color.RED);                      //try to add a die with a different color of the others
        die5.setValue(2);                                   //it won't be counted in the score method
        try
        {
            board.addDie(die5, 2, 4);
        }
        catch(CannotPlaceDieException e)
        {
            fail();
        }

        assertEquals(5,card.score(board));



    }

    @Test
    public void testGetter()
    {
        PublicObjectiveCard card = new ColorDiagonalsCard();
        assertEquals("Color Diagonals", card.getName());
        assertEquals("Count of diagonally adjacent same color dice", card.getDescription());
    }
}
