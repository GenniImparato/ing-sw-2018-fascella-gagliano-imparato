package it.polimi.se2018.view.cli.views;

import it.polimi.se2018.mvc_comunication.events.DraftDieEvent;
import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.cli.renderer.CLIRendererMainState;

public class CLIDraftDieView extends CLIView
{
    public CLIDraftDieView(CLI cli)
    {
        super(cli);
    }

    public void draw()
    {
        cli.clear();
        cli.renderGameState(CLIRendererMainState.DRAFTPOOL_SELECTED);
        cli.showMessage("Choose a die in the draft pool:");
        cli.showMessage("b) go back");
        cli.readInputFromUser();
    }

    public void parseInput(String input)
    {
        if(input.equals("b"))
            cli.showView(new CLIMainActionsView(cli));
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