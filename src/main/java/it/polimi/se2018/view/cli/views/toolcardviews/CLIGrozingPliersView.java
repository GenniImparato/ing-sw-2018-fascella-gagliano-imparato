package it.polimi.se2018.view.cli.views.toolcardviews;

import it.polimi.se2018.events.clievents.CLIInputEvent;
import it.polimi.se2018.model.Color;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Game;
import it.polimi.se2018.model.gameactions.DraftDieAction;
import it.polimi.se2018.model.gameactions.UseToolCardAction;
import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.cli.renderer.CLIRendererMainState;
import it.polimi.se2018.view.cli.views.*;

public class CLIGrozingPliersView extends CLIView
{
    int state;

    public CLIGrozingPliersView(CLI cli)
    {
        super(cli);
        state = 0;
    }

    @Override
    public void draw()
    {
        if(state == 0)
        {
            cli.renderGameState(CLIRendererMainState.DRAFTPOOL_SELECTED);
            cli.showMessage("You are using a Grozing Pliers tool card:");
            cli.showMessage("Choose a die from the drafting pool:");
            cli.showMessage("b) go back");
            cli.notify(new CLIInputEvent(cli, this, cli.readInputFromUser()));
        }
        else if(state == 1)
        {
            cli.renderGameState(CLIRendererMainState.NO_SELECTION);
            Die draftedDie = cli.getGameInstance().getLastDraftedDie();

            cli.showMessage("You drafted a " + draftedDie.getColor().getConsoleString() + draftedDie.getColor() +" "+ Color.getResetConsoleString());
            cli.showMessage("die with value: " + draftedDie.getValue());
            cli.showMessage("Choose an action");
            cli.showMessage("1) Increment value by 1");
            cli.showMessage("2) Decrement value by 1");
            cli.notify(new CLIInputEvent(cli, this, cli.readInputFromUser()));
        }
    }

    @Override
    public void control(Game game, String input)
    {
        if(state == 0)
        {
            if(input.equals("b"))
                cli.requestView(new CLIPlayerActionsCardsView(cli));
            else try
            {
                Integer val = Integer.parseInt(input);
                if(val >= 0 && val < cli.getGameInstance().getDraftPool().getAllDice().size())
                {
                    game.executeAction(new DraftDieAction(val));
                    state = 1;
                    cli.requestView(this);
                }
                else
                {
                    cli.showErrorMessage("Not valid input!");
                    cli.requestView(this);
                }
            }
            catch(NumberFormatException e)
            {
                cli.showErrorMessage("Not valid input!");
                cli.requestView(this);
            }
        }
        else if(state == 1)
        {
            if(input.equals("1"))
            {
                game.executeAction(new UseToolCardAction(1, 0));
                cli.requestView(new CLIPlayerActionsMainView(cli));
            }
            else if(input.equals("2"))
            {
                game.executeAction(new UseToolCardAction(0, 0));
                cli.requestView(new CLIPlayerActionsMainView(cli));
            }
            else
            {
                cli.showErrorMessage("Not valid input");
                cli.requestView(this);
            }

        }
    }
}
