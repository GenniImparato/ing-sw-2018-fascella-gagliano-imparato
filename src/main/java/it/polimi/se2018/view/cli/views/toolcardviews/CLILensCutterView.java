package it.polimi.se2018.view.cli.views.toolcardviews;

import it.polimi.se2018.events.clievents.CLIInputEvent;
import it.polimi.se2018.model.Color;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Game;
import it.polimi.se2018.model.gameactions.DraftDieAction;
import it.polimi.se2018.model.gameactions.GameAction;
import it.polimi.se2018.model.gameactions.SelectDieFromBoardAction;
import it.polimi.se2018.model.gameactions.UseToolCardAction;
import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.cli.renderer.CLIRendererMainState;
import it.polimi.se2018.view.cli.views.CLIPlayerActionsCardsView;
import it.polimi.se2018.view.cli.views.CLIPlayerActionsMainView;
import it.polimi.se2018.view.cli.views.CLIView;

public class CLILensCutterView extends CLIView
{
    private int state;
    private int selectedRound;

    public CLILensCutterView(CLI cli)
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
            cli.showMessage("You are using a Lenz Cutter tool card:");
            cli.showMessage("Choose a die from the drafting pool:");
            cli.showMessage("b) go back");
            cli.notify(new CLIInputEvent(cli, this, cli.readInputFromUser()));
        }
        else if(state == 1)
        {
            cli.renderGameState(CLIRendererMainState.ROUNDTRACK_SELECTED);
            Die draftedDie = cli.getGameInstance().getLastDraftedDie();

            cli.showMessage("You selected a " + draftedDie.getColor().getConsoleString() + draftedDie.getColor() +" "+ Color.getResetConsoleString()
                    + "die with value: " + draftedDie.getValue());
            cli.showMessage("Choose a position in the round track to swap the dice:");
            cli.notify(new CLIInputEvent(cli, this, cli.readInputFromUser()));
        }
        else if(state == 2)
        {
            cli.renderGameState(CLIRendererMainState.ROUNDTRACK_SELECTED);
            Die draftedDie = cli.getGameInstance().getLastDraftedDie();

            cli.showMessage("You drafted a " + draftedDie.getColor().getConsoleString() + draftedDie.getColor() +" "+ Color.getResetConsoleString()
                    + "die with value: " + draftedDie.getValue());
            cli.showMessage("Choose a die in the round track at position " + selectedRound + " to swap the dice:");
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
            try
            {
                Integer val = Integer.parseInt(input);
                if(val >= 0 && val < 10) //checks if the input is in the range to represent a cell of the matrix
                {
                    if(game.getRoundTrack().getDiceAtRound(val).size() > 1)
                    {
                        state = 2;
                        selectedRound = val;
                        cli.requestView(this);
                    }

                    GameAction action = new UseToolCardAction(val, 0);
                    game.executeAction(action);

                    if(action.hasBeenExecuted())
                    {
                        cli.requestView(new CLIPlayerActionsMainView(cli));
                    }
                    else
                    {
                        cli.showErrorMessage(action.getErrorMessage());
                        cli.requestView(this);
                    }

                }
                else        //input is not in range [0,20]
                {
                    cli.showErrorMessage("Not valid input!");
                    cli.requestView(this);
                }
            }       //input is not a number
            catch(NumberFormatException e)
            {
                cli.showErrorMessage("Not valid input!");
                cli.requestView(this);
            }
        }
        else if(state == 2)
        {
            try
            {
                Integer val = Integer.parseInt(input);
                if(val >= 0 && val < game.getRoundTrack().getDiceAtRound(selectedRound).size())
                {
                    GameAction action = new UseToolCardAction(selectedRound, val);
                    game.executeAction(action);

                    if(action.hasBeenExecuted())
                    {
                        cli.requestView(new CLIPlayerActionsMainView(cli));
                    }
                    else
                    {
                        cli.showErrorMessage(action.getErrorMessage());
                        cli.requestView(this);
                    }
                }
                else        //input is not in range
                {
                    cli.showErrorMessage("Not valid input!");
                    cli.requestView(this);
                }
            }       //input is not a number
            catch(NumberFormatException e)
            {
                cli.showErrorMessage("Not valid input!");
                cli.requestView(this);
            }
        }
    }
}
