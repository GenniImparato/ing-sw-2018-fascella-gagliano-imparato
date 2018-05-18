package it.polimi.se2018.view.cli.renderer;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.view.cli.CLI;

import java.util.List;

public class CLIRendererSchemeCardChoice extends CLIRenderer
{
    private Board[] schemeCards;

    public CLIRendererSchemeCardChoice(CLI cli, Model model, Board[] schemeCards)
    {
        super(cli, model, 150, 50);
        this.schemeCards = schemeCards;
    }

    //helper
    protected void refresh()
    {
        initMatrix();

        new CLIElementBoard(this, schemeCards[0], 0, 0, false);
        new CLIElementBoard(this, schemeCards[1], 57, 0, false);
        new CLIElementBoard(this, schemeCards[2], 0, 25, false);
        new CLIElementBoard(this, schemeCards[3], 57, 25, false);
    }
}