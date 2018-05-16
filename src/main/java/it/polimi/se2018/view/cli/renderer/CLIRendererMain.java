package it.polimi.se2018.view.cli.renderer;

import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.view.cli.CLI;

import java.util.List;

//a concrete CLIRenderer used to render the main view of the model (the player's boards, the draftpool, the roundtrack)
public class CLIRendererMain extends CLIRenderer
{
    private CLIRendererMainState state;

    public CLIRendererMain(CLI cli, Model model)
    {
        super(cli, model, 220, 37);
    }


    public void render(CLIRendererMainState state)
    {
        this.state = state;
        render();
    }

    //helper
    protected void refresh()
    {
        initMatrix();


        //add the DraftPool
        boolean draftPoolSelected = false;
        if(state == CLIRendererMainState.DRAFTPOOL_SELECTED)
            draftPoolSelected = true;
        CLIElementDraftPool cliDraftPool = new CLIElementDraftPool(this, model.getDraftPool(), 0, 0, draftPoolSelected);

        //add the RoundTrack
        boolean roundTrackSelected = false;
        if(state == CLIRendererMainState.ROUNDTRACK_SELECTED)
            roundTrackSelected = true;
        CLIElementRoundTrack cliRoundTrack = new CLIElementRoundTrack(this, model.getRoundTrack(), cliDraftPool.getWidth()+1, 0, roundTrackSelected);


        List<Player> players = model.getPlayers();

        for(int i=0; i<players.size(); i++)
        {
            //add the players
            boolean showObjective = false;
            if(players.get(i).getNickname().equals(cli.getAssociatedPlayerNickname()))
                showObjective = true;                   //selects the board of the current player

            CLIElementPlayer elementPlayer = new CLIElementPlayer(this, players.get(i), i*53, cliDraftPool.getHeight()+1, showObjective);

            //add the boards
            boolean boardSelected = false;
            if(players.get(i).getNickname().equals(cli.getAssociatedPlayerNickname())  &&  state == CLIRendererMainState.BOARD_SELECTED)
                boardSelected = true;                   //selects the board of the current player

            new CLIElementBoard(this, players.get(i).getBoard(), i*53, cliDraftPool.getHeight()+elementPlayer.getHeight()+1, boardSelected);
        }
    }
}
