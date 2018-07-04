package it.polimi.se2018.testcards.testtoolcards;

import it.polimi.se2018.TestView;
import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.files.SagradaSchemeCardFile;
import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.exceptions.ActionNotPossibleException;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;
import it.polimi.se2018.mvc_comunication.events.*;
import it.polimi.se2018.utils.Color;
import it.polimi.se2018.view.View;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Class used to test three cards: "Tap Wheel", "Running Pliers", "Grozing Pliers"
 */
public class TestTapGrozingRunning
{
    private Model model;
    private Controller controller;
    private View view;
    private int [] numCards;
    private Board board;
    private SagradaSchemeCardFile sagradaSchemeCardFile;
    private Die die0;
    private Die die1;


    @Before
    public void setUp()
    {
        model = new Model();
        controller = new Controller(model, "./test_resources/toolcards/thirddirectory/");
        view = new TestView();
        view.attach(controller);
        model.attach(view);

        view.notify(new AddPlayerEvent(view, "Player1"));
        view.notify(new AddPlayerEvent(view, "Player2"));

        view.notify(new PlayerReadyEvent(view, "Player1",true));
        view.notify(new PlayerReadyEvent(view, "Player2",true));

        view.notify(new SelectSchemeCardEvent(view,"Player1",1));
        view.notify(new SelectSchemeCardEvent(view,"Player2",1));

        try                                             //try to open a known board
        {
            sagradaSchemeCardFile = new SagradaSchemeCardFile("resources/scheme_cards/Sun Catcher.sagradaschemecard");
            board = sagradaSchemeCardFile.generateBoard();
        }
        catch (Exception e)
        {
            fail();
        }

        model.getPlayers().get(0).setBoard(board);

        numCards = new int[3];


        for(int i=0; i<3; i++)
        {
            if(model.getToolCards().get(i).getName().equals("Tap Wheel"))
                numCards[0]=i;

            if(model.getToolCards().get(i).getName().equals("Grozing Pliers"))
                numCards[1]=i;

            if(model.getToolCards().get(i).getName().equals("Running Pliers"))
                numCards[2]=i;
        }



    }

    /**
     * Test that uses the toolCard "Tap Wheel". The test passes two round without doing anything else.
     * On the RoundTrack the first two round cell aren't empty.
     * The first player, when it's his turn at the third round,
     * he adds on the board two dice of the same color of the first die on the first cell of the RoundTrack,
     * and then he uses the card.
     * Then he can move the two dice just added in two other positions.
     */
    @Test
    public void useTapWheel()
    {
        Die die0;
        Die die1;

        view.notify(new EndTurnEvent(view));
        view.notify(new EndTurnEvent(view));
        view.notify(new EndTurnEvent(view));
        view.notify(new EndTurnEvent(view));            //pass the first turn
        view.notify(new EndTurnEvent(view));
        view.notify(new EndTurnEvent(view));
        view.notify(new EndTurnEvent(view));
        view.notify(new EndTurnEvent(view));            //pass the second turn


        Color roundTrackColor = model.getRoundTrack().getDiceAtRound(0).get(0).getColor();


        die0 = new Die(roundTrackColor);            //dice with the same color of the first of the first cell of the roundtrack
        die1 = new Die(roundTrackColor);
        die0.setValue(3);
        die1.setValue(2);


        try
        {
            model.getPlayers().get(0).getBoard().addDie(die0,1,0,false,false,false);
            model.getPlayers().get(0).getBoard().addDie(die1,2,1,false,false,false);
        }
        catch (ChangeModelStateException|ActionNotPossibleException e)
        {

        }


        view.notify(new UseToolCardEvent(view, numCards[0]));


        view.notify(new ChooseDieEvent(view,0));

        view.notify(new SelectSameColorDieEvent(view,1,0));
        view.notify(new MoveSelectedDieEvent(view,1,2));



        view.notify(new SelectSameColorDieEvent(view, 2,1));
        view.notify(new MoveSelectedDieEvent(view,0,3));

        assertEquals(model.getPlayers().get(0).getBoard().getDie(1,2).getValue(), die0.getValue());
        assertEquals(model.getPlayers().get(0).getBoard().getDie(1,2).getColor(), roundTrackColor);

        assertEquals(model.getPlayers().get(0).getBoard().getDie(0,3).getValue(), die1.getValue());
        assertEquals(model.getPlayers().get(0).getBoard().getDie(0,3).getColor(), roundTrackColor);

    }

    @Test
    public void useGrozingPliersIncrement()
    {
        view.notify(new UseToolCardEvent(view, numCards[1]));

        view.notify(new DraftDieEvent(view, 0));

        int draftedVal = model.getDraftedDie().getValue();
        System.out.println(draftedVal);

        assertNotEquals(null, model.getDraftedDie());

        view.notify(new IncrementDraftedDieEvent(view, IncrementDraftedDieEvent.INCREMENT));

        int afterDraftedVal = model.getDraftedDie().getValue();
        System.out.println(afterDraftedVal);


        if(draftedVal == 6)
            assertEquals(draftedVal, afterDraftedVal);
        else
            assertEquals(draftedVal+1, afterDraftedVal);
    }

    @Test
    public void useGrozingPliersDecrement()
    {
        view.notify(new UseToolCardEvent(view, numCards[1]));

        view.notify(new DraftDieEvent(view, 0));

        int draftedVal = model.getDraftedDie().getValue();
        System.out.println(draftedVal);

        assertNotEquals(null, model.getDraftedDie());

        view.notify(new IncrementDraftedDieEvent(view, IncrementDraftedDieEvent.DECREMENT));

        int afterDraftedVal = model.getDraftedDie().getValue();
        System.out.println(afterDraftedVal);


        if(draftedVal == 1)
            assertEquals(draftedVal, afterDraftedVal);
        else
            assertEquals(draftedVal-1, afterDraftedVal);
    }

    @Test
    public void useRunningPliers()
    {

    }
}
