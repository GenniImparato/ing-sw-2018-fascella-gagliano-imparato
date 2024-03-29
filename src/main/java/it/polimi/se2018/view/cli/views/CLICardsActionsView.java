package it.polimi.se2018.view.cli.views;

import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.cli.renderer.CLIRendererCardsState;

public class CLICardsActionsView extends CLIView
{
    public CLICardsActionsView(CLI cli)
    {
        super(cli);
    }

    public void draw()
    {
        cli.clear();
        cli.renderGameState(CLIRendererCardsState.NO_SELECTION);
        cli.showMessage("It's your turn " + cli.getAssociatedPlayerNickname() + "!");
        cli.showMessage("Choose an action:");
        cli.showMessage("1) Use a tool card.");
        cli.showMessage("2) Return to main view.");
        cli.readInputFromUser();
    }

    public void parseInput(String input)
    {
        if(input.equals("1"))
        {
            cli.clear();
            cli.showView(new CLIUseToolCardView(cli));
        }
        else if(input.equals("2"))
        {
            cli.clear();
            cli.showView(new CLIMainActionsView(cli));
        }
        else
        {
            cli.showErrorMessage("Not valid input!");
            cli.reShowCurrentView();
        }
    }
}