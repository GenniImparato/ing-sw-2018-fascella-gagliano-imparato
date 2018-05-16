package it.polimi.se2018.view.cli.views;

import it.polimi.se2018.mvc_comunication.events.DraftDieEvent;
import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.cli.renderer.CLIRendererMainState;

public class CLIPlayerDraftDieView extends CLIView
{
    public CLIPlayerDraftDieView(CLI cli)
    {
        super(cli);
    }

    public void draw()
    {
        cli.clear();
        cli.renderGameState(CLIRendererMainState.DRAFTPOOL_SELECTED);
        cli.showMessage("Choose a die in the draft pool:");
        cli.showMessage("b) go back");
        parseInput(cli.readInputFromUser());
    }

    public void parseInput(String input)
    {
        if(input.equals("b"))
            cli.showView(new CLIPlayerMainActionsView(cli));
        else try
        {
            Integer val = Integer.parseInt(input);
            cli.notify(new DraftDieEvent(cli, val));
        }
        catch(NumberFormatException e)
        {
            cli.showErrorMessage("Not valid input!");
            cli.reShowCurrentView();
        }
    }
}