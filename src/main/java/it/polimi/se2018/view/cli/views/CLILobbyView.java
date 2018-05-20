package it.polimi.se2018.view.cli.views;

import it.polimi.se2018.model.Player;
import it.polimi.se2018.mvc_comunication.events.EndTurnEvent;
import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.cli.renderer.CLIRendererMainState;

public class CLILobbyView extends CLIView
{
    public CLILobbyView(CLI cli)
    {
        super(cli);
    }

    public void draw()
    {
        cli.clear();
        cli.showMessage("Server is waiting for players...");
        cli.showMessage("Players connected:");
        cli.showMessage("");

        for(Player connectedPlayer : cli.getModel().getPlayers())
            cli.showMessage("      -) " + connectedPlayer.getNickname());

        cli.readInputFromUser();
    }

    public void parseInput(String input)
    {
    }
}