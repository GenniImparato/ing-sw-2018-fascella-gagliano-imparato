package it.polimi.se2018.view.cli.views;

import it.polimi.se2018.mvc_comunication.events.EndTurnEvent;
import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.cli.renderer.CLIRendererMainState;

public class CLIMainActionsView extends CLIView
{
    public CLIMainActionsView(CLI cli)
    {
        super(cli);
    }

    public void draw()
    {
        cli.clear();
        cli.renderGameState(CLIRendererMainState.NO_SELECTION);
        cli.showMessage("It's your turn " + cli.getAssociatedPlayerNickname() + "!");
        cli.showMessage("Choose an action:");
        cli.showMessage("1) Add a die to your board.");
        cli.showMessage("2) Show cards.");
        cli.showMessage("3) End your turn.");
        parseInput(cli.readInputFromUser());
    }

    public void parseInput(String input)
    {
        if(input.equals("1"))
        {
            cli.clear();
            cli.showView(new CLIDraftDieView(cli));
        }
        else if(input.equals("2"))
        {
            cli.clear();
            cli.showView(new CLICardsActionsView(cli));
        }
        else if(input.equals("3"))
        {
            cli.notify(new EndTurnEvent(cli));
            cli.reShowCurrentView();
        }
        else
        {
            cli.showErrorMessage("Not valid input!");
            cli.reShowCurrentView();
        }
    }
}