package it.polimi.se2018.view.cli.views.tool_cards_views;


import it.polimi.se2018.mvc_comunication.events.tool_cards_events.ToolCardDraftDieEvent;
import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.cli.renderer.CLIRendererMainState;
import it.polimi.se2018.view.cli.views.CLIView;

public class CLIPlayerToolCardDraftDieView extends CLIView
{
    public CLIPlayerToolCardDraftDieView(CLI cli)
    {
        super(cli);
    }

    public void draw()
    {
        cli.clear();
        cli.renderGameState(CLIRendererMainState.DRAFTPOOL_SELECTED);
        cli.showMessage("Choose a die in the draft pool:");
        parseInput(cli.readInputFromUser());
    }

    public void parseInput(String input)
    {
        try
        {
            Integer val = Integer.parseInt(input);
            cli.notify(new ToolCardDraftDieEvent(cli, val));
        }
        catch(NumberFormatException e)
        {
            cli.showErrorMessage("Input must be a number!");
            cli.reShowCurrentView();
        }
    }
}