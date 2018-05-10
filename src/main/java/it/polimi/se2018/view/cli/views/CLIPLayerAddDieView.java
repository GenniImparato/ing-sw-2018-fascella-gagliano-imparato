package it.polimi.se2018.view.cli.views;

import it.polimi.se2018.events.clievents.CLIInputEvent;
import it.polimi.se2018.model.Color;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.gameactions.AddDieToBoardAction;
import it.polimi.se2018.model.gameactions.GameAction;
import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.cli.renderer.CLIRendererMainState;

public class CLIPLayerAddDieView extends CLIView
{
    public CLIPLayerAddDieView(CLI cli)
    {
        super(cli);
    }

    @Override
    public void draw()
    {
        cli.renderGameState(CLIRendererMainState.BOARD_SELECTED);

        Die draftedDie = cli.getGameInstance().getLastDraftedDie();

        System.out.print("You drafted a " + draftedDie.getColor().getConsoleString() + draftedDie.getColor() +" "+ Color.getResetConsoleString());
        System.out.println("die with value: " + draftedDie.getValue());
        System.out.println("Choose in which cell to add the die: ");

        cli.notify(new CLIInputEvent(cli,this, cli.readInputFromUser()));
    }


    @Override
    public void control(String input)
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

                GameAction action = new AddDieToBoardAction(row, col);
                cli.getGameInstance().executeAction(action);

                if(action.hasBeenExecuted())
                    cli.requestView(new CLIEndTurnView(cli));
                else
                {
                    cli.showErrorMessage(action.getErrorMessage());
                    cli.requestView(new CLIPLayerAddDieView(cli));
                }

            }
            else        //input is not in range [0,20]
            {
                cli.showErrorMessage("Not valid input!");
                cli.requestView(new CLIPLayerAddDieView(cli));
            }
        }       //input is not a number
        catch(NumberFormatException e)
        {
            cli.showErrorMessage("Not valid input!");
            cli.requestView(new CLIPLayerAddDieView(cli));
        }
    }
}
