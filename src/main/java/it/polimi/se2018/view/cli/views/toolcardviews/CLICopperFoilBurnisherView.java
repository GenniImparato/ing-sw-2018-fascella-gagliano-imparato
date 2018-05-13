package it.polimi.se2018.view.cli.views.toolcardviews;

import it.polimi.se2018.events.clievents.CLIInputEvent;
import it.polimi.se2018.model.Color;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Game;
import it.polimi.se2018.model.gameactions.GameAction;
import it.polimi.se2018.model.gameactions.SelectDieFromBoardAction;
import it.polimi.se2018.model.gameactions.UseToolCardAction;
import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.cli.renderer.CLIRendererMainState;
import it.polimi.se2018.view.cli.views.CLIPlayerActionsMainView;
import it.polimi.se2018.view.cli.views.CLIView;

public class CLICopperFoilBurnisherView extends CLIView
{
    private int state;

    public CLICopperFoilBurnisherView(CLI cli)
    {
        super(cli);
        state = 0;
    }

    @Override
    public void draw()
    {
        if(state == 0)
        {
            cli.renderGameState(CLIRendererMainState.BOARD_SELECTED);
            cli.showMessage("You are using a Copper Foil Burnisher tool card:");
            cli.showMessage("Choose a die from your board:");
            cli.showMessage("b) go back");
            cli.notify(new CLIInputEvent(cli, this, cli.readInputFromUser()));
        }
        else if(state == 1)
        {
            cli.renderGameState(CLIRendererMainState.BOARD_SELECTED);
            Die selectedDie = cli.getGameInstance().getSelectedDie();

            cli.showMessage("You selected a " + selectedDie.getColor().getConsoleString() + selectedDie.getColor() +" "+ Color.getResetConsoleString()
                    + "die with value: " + selectedDie.getValue());
            cli.showMessage("Choose where to move the die");
            cli.notify(new CLIInputEvent(cli, this, cli.readInputFromUser()));
        }
    }

    @Override
    public void control(Game game, String input)
    {
        if(state == 0)
        {
            try
            {
                Integer val = Integer.parseInt(input);
                if(val >= 0 && val < 20) //checks if the input is in the range to represent a cell of the matrix
                {
                    int row, col;

                    if(val<=4)                              //converts the input (integer in [0,20] range)
                    {row  =0;   col = val;}                 //to 2 coordinates (row, col)
                    else if(val<=9)                         //representing the selected cell coordinates
                    {row  =1;   col = val%5;}
                    else if(val<=14)
                    {row  =2;   col = val%10;}
                    else
                    {row  =3;   col = val%15;}

                    GameAction action = new SelectDieFromBoardAction(row, col);
                    game.executeAction(action);

                    if(action.hasBeenExecuted()) {
                        state = 1;
                        cli.requestView(this);
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
        else if(state == 1)
        {
            try
            {
                Integer val = Integer.parseInt(input);
                if(val >= 0 && val < 20) //checks if the input is in the range to represent a cell of the matrix
                {
                    int row, col;

                    if(val<=4)                              //converts the input (integer in [0,20] range)
                    {row  =0;   col = val;}                 //to 2 coordinates (row, col)
                    else if(val<=9)                         //representing the selected cell coordinates
                    {row  =1;   col = val%5;}
                    else if(val<=14)
                    {row  =2;   col = val%10;}
                    else
                    {row  =3;   col = val%15;}

                    GameAction action = new UseToolCardAction(row, col);
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
    }
}
