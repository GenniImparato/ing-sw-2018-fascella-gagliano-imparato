package it.polimi.se2018.view.cli.views;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.mvc_comunication.events.SelectSchemeCardEvent;
import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.cli.renderer.CLIRendererSchemeCardChoice;

public class CLIChooseSchemeCardView extends CLIView
{
    private Board firstSchemeBoard;
    private Board secondSchemeBoard;

    public CLIChooseSchemeCardView(CLI cli, Board firstSchemeBoard, Board secondSchemeBoard)
    {
        super(cli);
        this.firstSchemeBoard = firstSchemeBoard;
        this.secondSchemeBoard = secondSchemeBoard;
    }

    public void draw()
    {
        cli.clear();
        new CLIRendererSchemeCardChoice(cli, cli.getModel(), firstSchemeBoard, secondSchemeBoard).render();
        cli.showMessage("Choose a scheme card to play with:");
        cli.showMessage("1) Select first scheme.");
        cli.showMessage("2) Select second scheme.");
        parseInput(cli.readInputFromUser());
    }

    public void parseInput(String input)
    {
        if(input.equals("1"))
        {
            cli.notify(new SelectSchemeCardEvent(cli, cli.getAssociatedPlayerNickname(), 1));
        }
        else if(input.equals("2"))
        {
            cli.notify(new SelectSchemeCardEvent(cli, cli.getAssociatedPlayerNickname(), 2));
        }
        else
        {
            cli.showErrorMessage("Not valid input!");
            cli.reShowCurrentView();
        }
    }
}