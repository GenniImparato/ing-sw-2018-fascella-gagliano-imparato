package it.polimi.se2018.testcards;

import it.polimi.se2018.controller.PublicObjectiveCardScorer;
import it.polimi.se2018.files.SagradaSchemeCardFile;
import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.PrivateObjectiveCard;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;
import it.polimi.se2018.utils.Color;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestPrivateObjectiveCards
{
    @Test
    public void testScore()
    {
        SagradaSchemeCardFile sagradaSchemeCardFile;
        Board board = new Board();

        try                         //try to add some dice on the board to verify if the method score returns the right value
        {
            sagradaSchemeCardFile = new SagradaSchemeCardFile("resources/schemecards/1-Firmitas.sagradaschemecard");
            board = sagradaSchemeCardFile.generateBoard();
        }
        catch(Exception e) {fail();}

        Die die0 = new Die(Color.PURPLE);
        die0.setValue(2);

        Die die1 = new Die(Color.GREEN);
        die1.setValue(5);

        Die die2 = new Die(Color.PURPLE);
        die2.setValue(1);

        try
        {
            board.addDie(die0,0,0);
            board.addDie(die1,1,0);
            board.addDie(die2,2,0);
        }
        catch(ChangeModelStateException e)
        {
            fail();
        }

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


    @Test
    public void testCloneCard()
    {
        PrivateObjectiveCard privateObjectiveCard = new PrivateObjectiveCard(Color.RED);
        PrivateObjectiveCard privateCard = new PrivateObjectiveCard(privateObjectiveCard);

        assertEquals(privateObjectiveCard.getName(), privateCard.getName());
        assertEquals(privateObjectiveCard.getDescription(), privateCard.getDescription());
    }
}
