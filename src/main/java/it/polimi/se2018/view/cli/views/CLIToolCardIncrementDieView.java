package it.polimi.se2018.view.cli.views;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.mvc_comunication.events.ToolCardIncrementDraftedDieEvent;
import it.polimi.se2018.utils.Color;
import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.cli.renderer.CLIRendererMainState;

public class CLIToolCardIncrementDieView extends CLIView
{
    public CLIToolCardIncrementDieView(CLI cli)
    {
        super(cli);
    }

    public void draw()
    {
        cli.clear();
        cli.renderGameState(CLIRendererMainState.NO_SELECTION);
        Die draftedDie = cli.getModel().getDraftedDie();

        cli.showMessage("Drafted die: (" + draftedDie.getColor().getConsoleString() + draftedDie.getColor() + Color.getResetConsoleString()
                + ", " + draftedDie.getValue() + ").");
        cli.showMessage("Choose an action: ");
        cli.showMessage("1)Increment value by 1.");
        cli.showMessage("1)Decrement value by 1.");

        parseInput(cli.readInputFromUser());
    }

    public void parseInput(String input)
    {
        if(input.equals("1"))
        {
            cli.notify(new ToolCardIncrementDraftedDieEvent(cli, ToolCardIncrementDraftedDieEvent.INCREMENT));
        }
        else if(input.equals("2"))
        {
            cli.notify(new ToolCardIncrementDraftedDieEvent(cli, ToolCardIncrementDraftedDieEvent.DECREMENT));
        }
        else
        {
            cli.showErrorMessage("Not valid input!");
            cli.reShowCurrentView();
        }
    }
}