package it.polimi.se2018.view.cli.views;

import it.polimi.se2018.events.clievents.CLIInputEvent;
import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.cli.renderer.CLIRendererCardsState;

public class CLIPlayerActionsCardsView extends CLIView
{
    public CLIPlayerActionsCardsView(CLI cli)
    {
        super(cli);
    }

    public void draw()
    {
        cli.renderGameState(CLIRendererCardsState.NO_SELECTION);
        System.out.println("It's your turn!");
        System.out.println("Choose an action:");
        System.out.println("1) Use a tool card");
        System.out.println("2) Get back to main view");
        cli.notify(new CLIInputEvent(cli, this, cli.readInputFromUser()));
    }

    public void control(String input)
    {
        if(input.equals("1"))
        {
            cli.showErrorMessage("Tool cards not yet implemented");
            cli.requestView(new CLIPlayerActionsCardsView(cli));
        }
        else if(input.equals("2"))
            cli.requestView(new CLIPlayerActionsMainView(cli));
        else
        {
            cli.showErrorMessage("Not valid input!");
            cli.requestView(new CLIPlayerActionsCardsView(cli));
        }
    }
}
