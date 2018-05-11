package it.polimi.se2018.view.cli.views;

import it.polimi.se2018.controller.CLIToolCardVisitor;
import it.polimi.se2018.events.clievents.CLIInputEvent;
import it.polimi.se2018.model.Game;
import it.polimi.se2018.model.gameactions.GameAction;
import it.polimi.se2018.model.gameactions.UseToolCardAction;
import it.polimi.se2018.model.toolcards.ToolCardVisitor;
import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.cli.renderer.CLIRendererCardsState;

public class CLIPlayerUseToolCardView extends CLIView
{
    public CLIPlayerUseToolCardView(CLI cli)
    {
        super(cli);
    }

    public void draw()
    {
        cli.renderGameState(CLIRendererCardsState.TOOL_CARDS_SELECTED);
        cli.showMessage("Choose a tool card to use:");
        cli.showMessage("b) go back");
        cli.notify(new CLIInputEvent(cli, this, cli.readInputFromUser()));
    }

    public void control(Game game, String input)
    {
        if(input.equals("b"))
        {
            cli.requestView(new CLIPlayerActionsCardsView(cli));
        }
        else
        {
            try //tries to convert the input to an integer
            {
                int val = Integer.parseInt(input);

                if(val >= 0 && val < game.getAllToolCards().size())  //checks if the input is in range of the cards number
                {
                    CLIToolCardVisitor visitor = new CLIToolCardVisitor(cli);

                    GameAction action = new UseToolCardAction(val, visitor);
                    game.executeAction(action);

                    cli.requestView(visitor.getCliView());
                }
                else        //input is a number but not in range
                {
                    cli.showErrorMessage("Not valid input!");
                    cli.requestView(new CLIPlayerActionsCardsView(cli));
                }
            }
            catch(NumberFormatException e) //if input is not a number
            {
                cli.showErrorMessage("Input must be a number!");
                cli.requestView(new CLIPlayerActionsCardsView(cli));
            }
        }
    }
}
