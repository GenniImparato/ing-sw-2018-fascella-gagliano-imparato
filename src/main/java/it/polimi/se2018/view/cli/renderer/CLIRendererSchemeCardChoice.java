package it.polimi.se2018.view.cli.renderer;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.view.cli.CLI;

import java.util.List;

public class CLIRendererSchemeCardChoice extends CLIRenderer
{
    private Board firstSchemeBoard;
    private Board secondSchemeBoard;

    public CLIRendererSchemeCardChoice(CLI cli, Model model, Board firstSchemeBoard, Board secondSchemeBoard)
    {
        super(cli, model, 150, 23);
        this.firstSchemeBoard = firstSchemeBoard;
        this.secondSchemeBoard = secondSchemeBoard;
    }

    //helper
    protected void refresh()
    {
        initMatrix();

        new CLIElementBoard(this, firstSchemeBoard, 0, 0, false);
        new CLIElementBoard(this, secondSchemeBoard, 55, 0, false);
    }
}