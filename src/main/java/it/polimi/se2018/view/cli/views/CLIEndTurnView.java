package it.polimi.se2018.view.cli.views;

import it.polimi.se2018.events.clievents.CLIEndTurnEvent;
import it.polimi.se2018.events.clievents.CLIInputEvent;
import it.polimi.se2018.model.Game;
import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.cli.renderer.CLIRendererMainState;

public class CLIEndTurnView extends CLIView
{
    public CLIEndTurnView(CLI cli)
    {
        super(cli);
    }

    public void draw()
    {
        cli.renderGameState(CLIRendererMainState.NO_SELECTION);

        System.out.println("Your turn ended!");

        cli.notify(new CLIEndTurnEvent(cli, this));
    }

    public void control(Game game, String input)
    {
    }
}
