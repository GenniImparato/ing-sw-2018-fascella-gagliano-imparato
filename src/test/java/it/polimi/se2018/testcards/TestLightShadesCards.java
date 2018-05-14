package it.polimi.se2018.testcards;

import it.polimi.se2018.files.SagradaSchemeCardFile;
import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.CannotPlaceDieException;
import it.polimi.se2018.model.Color;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.publicobjectivecards.DeepShadesCard;
import it.polimi.se2018.model.publicobjectivecards.LightShadesCard;
import it.polimi.se2018.model.publicobjectivecards.PublicObjectiveCard;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestLightShadesCards
{
    @Test
    public void score()
    {
        PublicObjectiveCard card = new LightShadesCard();
        SagradaSchemeCardFile sagradaSchemeCardFile;
        Board board = new Board();
        try
        {
            sagradaSchemeCardFile = new SagradaSchemeCardFile("resources/schemecards/1-Firmitas.sagradaschemecard");
            board = sagradaSchemeCardFile.generateBoard();
        }
        catch(Exception e)
        {
            fail();
        }

        Die die0 = new Die(Color.PURPLE);
        die0.setValue(2);
        Die die1 = new Die(Color.PURPLE);
        die1.setValue(1);
        Die die2 = new Die(Color.PURPLE);
        die2.setValue(2);
        Die die3 = new Die(Color.PURPLE);
        die3.setValue(6);



        try                     //try to add one "1" and two "2" and one die of another value
        {
            board.addDie(die0,0,0);
            board.addDie(die1,1,1);
            board.addDie(die2,2,2);
            board.addDie(die3,3,3);


        }
        catch(CannotPlaceDieException e)
        {
            fail();
        }

        assertEquals(2,card.score(board));



    }
}
