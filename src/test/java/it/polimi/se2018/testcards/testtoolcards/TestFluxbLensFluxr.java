package it.polimi.se2018.testcards.testtoolcards;

import it.polimi.se2018.TestView;
import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.files.SagradaSchemeCardFile;
import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.mvc_comunication.events.*;
import it.polimi.se2018.view.View;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Class used to test three Tool Card: "Flux Brush", "Lens Cutter" and "Flux Remover"
 * @author carmelofascella
 */
public class TestFluxbLensFluxr
{
    private Model model;
    private Controller controller;
    private View view;
    private Board board;
    private SagradaSchemeCardFile sagradaSchemeCardFile;
    private int [] numCards;
    private Die die0;
    private Die die1;

    @Before
    public void setUp()
    {

        model = new Model();
        controller = new Controller(model, "./test_resources/toolcards/fourthdirectory/");
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
            if(model.getToolCards().get(i).getName().equals("Lens Cutter"))
                numCards[0]=i;

            if(model.getToolCards().get(i).getName().equals("Flux Brush"))
                numCards[1]=i;

            if(model.getToolCards().get(i).getName().equals("Flux Remover"))
                numCards[2]=i;
        }

    }

    /**
     * Test the use of the Tool Card "Lens Cutter".
     * After two rounds, we use the tool card during the turn of the first player.
     * The first die on the Draftpool is swapped with the first one on the Roundtrack,
     * and this last one is added on the board.
     */
    @Test
    public void testLensCutter()
    {
        view.notify(new EndTurnEvent(view));
        view.notify(new EndTurnEvent(view));
        view.notify(new EndTurnEvent(view));
        view.notify(new EndTurnEvent(view));

        view.notify(new EndTurnEvent(view));
        view.notify(new EndTurnEvent(view));
        view.notify(new EndTurnEvent(view));
        view.notify(new EndTurnEvent(view));


        view.notify(new UseToolCardEvent(view, numCards[0]));

        view.notify(new DraftDieEvent(view,0));


        die0 = model.getRoundTrack().getDiceAtRound(0).get(0);

        view.notify(new ChooseDieEvent(view, 0));


        view.notify(new AddDieToBoardEvent(view,0,0));


        assertEquals(model.getPlayers().get(0).getBoard().getDie(0,0).getValue(), die0.getValue());
        assertEquals(model.getPlayers().get(0).getBoard().getDie(0,0).getColor(), die0.getColor());

    }

    /**
     * Test the use of the Tool Card "FlushBrush".
     * It drafts a Die and then this die is rolled.
     * Then this Die is added on the Board.
     */
    @Test
    public void testFlushBrush()
    {
        view.notify(new UseToolCardEvent(view, numCards[1]));

        die0 = model.getDraftPool().getAllDice().get(0);

        view.notify(new DraftDieEvent(view, 0));

        die1 = model.getDraftedDie();


        view.notify(new AddDieToBoardEvent(view,0,0));

        assertEquals(die1.getValue(), model.getPlayers().get(0).getBoard().getDie(0,0).getValue());
    }

    @Test
    public void testFlushRemover()
    {

    }
}
