package it.polimi.se2018.view.cli.views;

import it.polimi.se2018.model.Player;
import it.polimi.se2018.mvc_comunication.events.DraftDieEvent;
import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.cli.renderer.CLIRendererMainState;

public class CLIFinalScoreView extends CLIView
{
    public CLIFinalScoreView(CLI cli)
    {
        super(cli);
    }

    public void draw()
    {
        cli.clear();
        cli.showMessage("Final scores:");
        cli.showMessage("");

        for(Player player:cli.getModel().getPlayers())
        {
            cli.showMessage(player.getColor().getConsoleString()
                                + player.getNickname()
                                + player.getColor().getConsoleString()
                                + "\t\t\t\t\t" +
                                + player.getScore());

        }
    }

    public void parseInput(String input)
    {
    }
}
