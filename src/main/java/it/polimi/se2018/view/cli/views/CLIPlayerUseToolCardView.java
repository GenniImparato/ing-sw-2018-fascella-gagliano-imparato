package it.polimi.se2018.view.cli.views;

import it.polimi.se2018.mvc_comunication.events.UseToolCardEvent;
import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.cli.renderer.CLIRendererCardsState;

public class CLIPlayerUseToolCardView extends CLIView
{
    public CLIPlayerUseToolCardView(CLI cli)
    {
        super(cli);
    }

    @Override
    public void draw()
    {
        cli.clear();
        cli.renderGameState(CLIRendererCardsState.TOOL_CARDS_SELECTED);
        cli.showMessage("Choose a tool card to use:");
        cli.showMessage("b) back.");
        parseInput(cli.readInputFromUser());
    }

    @Override
    public void parseInput(String input)
    {
        if(input.equals("b"))
        {
            cli.clear();
            cli.showView(new CLIPlayerCardsActionsView(cli));
        }
        else
        {
            try
            {
                Integer val = Integer.parseInt(input);
                cli.notify(new UseToolCardEvent(cli, val));
            }
            catch(NumberFormatException e)
            {
                cli.showErrorMessage("Input must be a number!");
                cli.reShowCurrentView();
            }
        }
    }
}
