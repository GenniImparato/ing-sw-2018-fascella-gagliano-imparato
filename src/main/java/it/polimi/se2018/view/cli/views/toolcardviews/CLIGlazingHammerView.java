package it.polimi.se2018.view.cli.views.toolcardviews;

import it.polimi.se2018.events.clievents.CLIInputEvent;
import it.polimi.se2018.model.Game;
import it.polimi.se2018.model.gameactions.GameAction;
import it.polimi.se2018.model.gameactions.UseToolCardAction;
import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.cli.renderer.CLIRendererMainState;
import it.polimi.se2018.view.cli.views.CLIPlayerActionsMainView;
import it.polimi.se2018.view.cli.views.CLIView;

public class CLIGlazingHammerView extends CLIView
{
    public CLIGlazingHammerView(CLI cli)
    {
        super(cli);
    }

    @Override
    public void draw()
    {
        cli.renderGameState(CLIRendererMainState.NO_SELECTION);
        cli.showMessage("You used a Glazing Hammer tool card:");
        cli.notify(new CLIInputEvent(cli, this, ""));
    }

    @Override
    public void control(Game game, String input)
    {
        GameAction action = new UseToolCardAction(0, 0);
        game.executeAction(action);

        if(!action.hasBeenExecuted())
        {
            cli.showErrorMessage(action.getErrorMessage());
        }

        cli.requestView(new CLIPlayerActionsMainView(cli));
    }
}
