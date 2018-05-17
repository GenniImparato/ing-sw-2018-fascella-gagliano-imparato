package it.polimi.se2018.testcards.testpubliccards;

import it.polimi.se2018.controller.PublicObjectiveCardScorer;
import it.polimi.se2018.files.SagradaSchemeCardFile;
import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;
import it.polimi.se2018.model.publicobjectivecards.DeepShadesCard;
import it.polimi.se2018.utils.Color;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestDeepShadesPublicCards
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

        Die die0 = new Die(Color.BLUE);
        die0.setValue(5);
        Die die1 = new Die(Color.YELLOW);
        die1.setValue(6);
        Die die2 = new Die(Color.RED);
        die2.setValue(5);
        Die die3 = new Die(Color.BLUE);
        die3.setValue(1);


        try                     //try to add one "6" and two "5" and one die of another value
        {
            board.addDie(die0,1,0);
            board.addDie(die1,0,1);
            board.addDie(die2,0,2);
            board.addDie(die3,1,3);


        }
        catch(ChangeModelStateException e)
        {
            fail();
        }

        scorer = new PublicObjectiveCardScorer(board);

        assertEquals(2, new DeepShadesCard().acceptVisitor(scorer));



    }

}
