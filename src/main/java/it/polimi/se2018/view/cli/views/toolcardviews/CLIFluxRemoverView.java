package it.polimi.se2018.view.cli.views.toolcardviews;

import it.polimi.se2018.model.Game;
import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.cli.views.CLIView;

public class CLIFluxRemoverView  extends CLIView
{
    public CLIFluxRemoverView(CLI cli)
    {
        super(cli);
    }

    @Override
    public void draw()
    {
        cli.showMessage("CLIFluxRemoverView");
    }

    @Override
    public void control(Game game, String input) {

    }
}
