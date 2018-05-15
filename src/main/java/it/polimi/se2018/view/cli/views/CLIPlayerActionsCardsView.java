package it.polimi.se2018.view.cli.views;

import it.polimi.se2018.events.clievents.CLIInputEvent;
import it.polimi.se2018.model.Game;
import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.cli.renderer.CLIRendererCardsState;

public class CLIPlayerActionsCardsView extends CLIView
{
    public CLIPlayerActionsCardsView(CLI cli)
    {
        super(cli);
    }

    public void draw()
    {
        cli.renderGameState(CLIRendererCardsState.NO_SELECTION);
        cli.showMessage("It's your turn!");
        cli.showMessage("Choose an action:");
        if(!cli.getGameInstance().canCurrentPlayerUseCard())
            cli.showMessage("- (Tool card already used)");
        else
            cli.showMessage("1) Use a tool card");
        cli.showMessage("2) Get back to main view");
        cli.notify(new CLIInputEvent(cli, this, cli.readInputFromUser()));
    }

    public void control(Game game, String input)
    {
        if(input.equals("1") && game.canCurrentPlayerUseCard())
        {
            cli.requestView(new CLIPlayerUseToolCardView(cli));
        }
        else if(input.equals("2"))
            cli.requestView(new CLIPlayerActionsMainView(cli));
        else
        {
            cli.showErrorMessage("Not valid input!");
            cli.requestView(new CLIPlayerActionsCardsView(cli));
        }
    }
}
