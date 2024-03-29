package it.polimi.se2018.view.cli.views;

import it.polimi.se2018.mvc_comunication.events.AddDieToBoardEvent;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.utils.Color;
import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.cli.renderer.CLIRendererMainState;

public class CLIAddDieView extends CLIView
{
    public CLIAddDieView(CLI cli)
    {
        super(cli);
    }

    @Override
    public void draw()
    {
        cli.clear();
        cli.renderGameState(CLIRendererMainState.BOARD_SELECTED);

        Die draftedDie = cli.getModel().getDraftedDie();

        if(draftedDie != null)
            cli.showMessage("Drafted die: (" + draftedDie.getColor().getConsoleString() + draftedDie.getColor() + Color.getResetConsoleString()
                            + ", " + draftedDie.getValue() + ").");
        cli.showMessage("Choose in which cell to add the die: ");

        cli.readInputFromUser();
    }


    @Override
    public void parseInput(String input)
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

                cli.notify(new AddDieToBoardEvent(cli, row, col));

            }
            else        //input is not in range [0,20]
            {
                cli.showErrorMessage("Input must be in range [0, 20]!");
                cli.reShowCurrentView();
            }
        }       //input is not a number
        catch(NumberFormatException e)
        {
            cli.showErrorMessage("Input must be a number!");
            cli.reShowCurrentView();
        }
    }
}