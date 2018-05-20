package it.polimi.se2018.view.cli.renderer;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.cli.renderer.elements.CLIElementSchemeCard;

public class CLIRendererSchemeCardChoice extends CLIRenderer
{
    private Board[] schemeCards;

    public CLIRendererSchemeCardChoice(CLI cli, Model model, Board[] schemeCards)
    {
        super(cli, model, 250, 28);
        this.schemeCards = schemeCards;
    }

    //helper
    protected void refresh()
    {
        initMatrix();

        new CLIElementSchemeCard(this, schemeCards[0], 0, 0);
        new CLIElementSchemeCard(this, schemeCards[1], 55, 0);
        new CLIElementSchemeCard(this, schemeCards[2], 110, 0);
        new CLIElementSchemeCard(this, schemeCards[3], 165, 0);
    }
}