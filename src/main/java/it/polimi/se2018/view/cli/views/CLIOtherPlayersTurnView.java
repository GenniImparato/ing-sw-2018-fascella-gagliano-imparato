package it.polimi.se2018.view.cli.views;

import it.polimi.se2018.mvc_comunication.events.EndTurnEvent;
import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.cli.renderer.CLIRendererMainState;

public class CLIOtherPlayersTurnView extends CLIView
{
    public CLIOtherPlayersTurnView(CLI cli)
    {
        super(cli);
    }

    public void draw()
    {
        cli.clear();
        cli.renderGameState(CLIRendererMainState.NO_SELECTION);
    }

    public void parseInput(String input)
    {
    }
}