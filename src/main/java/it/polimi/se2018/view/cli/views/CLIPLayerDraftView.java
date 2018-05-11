package it.polimi.se2018.view.cli.views;

import it.polimi.se2018.events.clievents.CLIInputEvent;
import it.polimi.se2018.model.Game;
import it.polimi.se2018.model.gameactions.DraftDieAction;
import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.cli.renderer.CLIRendererMainState;

public class CLIPLayerDraftView extends CLIView
{
    public CLIPLayerDraftView(CLI cli)
    {
        super(cli);
    }

    public void draw()
    {
        cli.renderGameState(CLIRendererMainState.DRAFTPOOL_SELECTED);
        System.out.println("Choose a die in the draft pool:");
        System.out.println("b) go back");
        cli.notify(new CLIInputEvent(cli, this, cli.readInputFromUser()));
    }

    public void control(Game game, String input)
    {
        if(input.equals("b"))
            cli.requestView(new CLIPlayerActionsMainView(cli));
        else try
        {
            Integer val = Integer.parseInt(input);
            if(val >= 0 && val < cli.getGameInstance().getDraftPool().getAllDice().size())
            {
                game.executeAction(new DraftDieAction(val));
                cli.requestView(new CLIPLayerAddDieView(cli));
            }
            else
            {
                cli.showErrorMessage("Not valid input!");
                cli.requestView(new CLIPLayerDraftView(cli));
            }
        }
        catch(NumberFormatException e)
        {
            cli.showErrorMessage("Not valid input!");
            cli.requestView(new CLIPLayerDraftView(cli));
        }
    }
}