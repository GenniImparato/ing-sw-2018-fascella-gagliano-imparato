package it.polimi.se2018.view.cli.views;

import it.polimi.se2018.events.clievents.CLIInputEvent;
import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.cli.renderer.CLIRendererMainState;

public class CLIPlayerActionsMainView extends CLIView
{
    public CLIPlayerActionsMainView(CLI cli)
    {
        super(cli);
    }

    public void draw()
    {
        cli.renderGameState(CLIRendererMainState.NO_SELECTION);
        System.out.println("It's your turn!");
        System.out.println("Choose an action:");
        System.out.println("1) Draft a die");
        System.out.println("2) Show cards");
        cli.notify(new CLIInputEvent(cli, this, cli.readInputFromUser()));
    }

    public void control(String input)
    {
        if(input.equals("1"))
            cli.requestView(new CLIPLayerDraftView(cli));
        else if(input.equals("2"))
            cli.requestView(new CLIPlayerActionsCardsView(cli));
        else
        {
            cli.showErrorMessage("Not valid input!");
            cli.requestView(new CLIPlayerActionsMainView(cli));
        }
    }
}
