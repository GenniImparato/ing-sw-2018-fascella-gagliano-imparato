package it.polimi.se2018.view.cli;

import it.polimi.se2018.model.Game;
import it.polimi.se2018.model.Player;

import java.util.ArrayList;

//a concrete CLIRenderer used to render the main view of the game (the player's boards, the draftpool, the roundtrack)
public class CLIRendererMain extends CLIRenderer
{

    private CLIRenderMainState state;

    public CLIRendererMain(Game game)
    {
        super(game);
    }


    public void render(CLIRenderMainState state)
    {
        this.state = state;
        render();
    }

    //helper
    protected void refresh()
    {
        initMatrix();



        boolean draftPoolSelected = false;
        if(state == CLIRenderMainState.DRAFTPOOL_SELECTED)
            draftPoolSelected = true;

        CLIElementDraftPool cliDraftPool = new CLIElementDraftPool(this, game.getDraftPool(), 0, 0, draftPoolSelected);

        ArrayList<Player> players = game.getAllPlayers();


        for(int i=0; i<players.size(); i++)
        {
            boolean showObjective = false;

            if(players.get(i) == game.getCurrentPlayer())
                showObjective = true;                   //selects the board of the current player

            CLIElementPlayer elementPlayer = new CLIElementPlayer(this, players.get(i), i*53, cliDraftPool.getHeight()+1, showObjective);

            boolean boardSelected = false;

            if(players.get(i) == game.getCurrentPlayer()  &&  state == CLIRenderMainState.BOARD_SELECTED)
                boardSelected = true;                   //selects the board of the current player

            new CLIElementBoard(this, players.get(i).getBoard(), i*53, cliDraftPool.getHeight()+elementPlayer.getHeight()+1, boardSelected);
        }
    }
}
