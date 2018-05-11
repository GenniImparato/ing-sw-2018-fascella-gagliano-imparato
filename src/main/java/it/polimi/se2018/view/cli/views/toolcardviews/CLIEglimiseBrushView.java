package it.polimi.se2018.view.cli.views.toolcardviews;

import it.polimi.se2018.model.Game;
import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.cli.views.CLIView;

public class CLIEglimiseBrushView extends CLIView
{
    public CLIEglimiseBrushView(CLI cli)
    {
        super(cli);
    }

    @Override
    public void draw()
    {
        cli.showMessage("CLIEglimiseBrushView");
    }

    @Override
    public void control(Game game, String input) {

    }
}