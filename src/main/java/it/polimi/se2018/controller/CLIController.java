package it.polimi.se2018.controller;

import it.polimi.se2018.model.gameactions.*;
import it.polimi.se2018.events.Event;
import it.polimi.se2018.events.clievents.*;
import it.polimi.se2018.model.*;
import it.polimi.se2018.view.cli.CLI;

public class CLIController extends Controller<CLIEvent>
{
    private CLI                 cli;
    private CLIEventParser      parser;

    public CLIController(CLI cli, Game game)
    {
        super(cli, game);
        this.cli = cli;
        parser = new CLIEventParser(this);
    }

    @Override
    //handle events from the view
    public void update(CLIEvent event)
    {
        event.beParsed(parser);
    }

    /*private void controlClientServerView(CLIInputEvent event)
    {
        if(event.getInput().equals("1"))
        {
            cli.showErrorMessage("Not yet implemented!");
        }
        else if(event.getInput().equals("2"))
            cli.menuClientAskingIP();
        else
        {
            cli.showErrorMessage("Input not valid!");
            cli.menuClientServer();
        }
    }

    private void controlAskPlayerForActionView(CLIInputEvent event)
    {
        if(event.getInput().equals("1"))
            cli.askPlayerForDrafting();
        else if(event.getInput().equals("2"))
            cli.askPlayerForActionCards();
        else
        {
            cli.showErrorMessage("Not valid input!");
            cli.askPlayerForAction();
        }
    }

    private void controlAskPlayerForActionCardsView(CLIInputEvent event)
    {
        if(event.getInput().equals("1"))
        {
            cli.showErrorMessage("Tool cards not yet implemeted!");
            cli.askPlayerForActionCards();
        }
        else if(event.getInput().equals("2"))
            cli.askPlayerForAction();
        else
        {
            cli.showErrorMessage("Not valid input!");
            cli.askPlayerForAction();
        }
    }

    private void controlAskPlayerForDraftingView(CLIInputEvent event)
    {
        if(event.getInput().equals("b"))
            cli.askPlayerForAction();
        else try
        {
            Integer val = Integer.parseInt(event.getInput());
            if(val >= 0 && val < game.getDraftPool().getAllDice().size())
                game.executeAction(new DraftDieAction(val));
            else
            {
                cli.showErrorMessage("Not valid input!");
                cli.askPlayerForDrafting();
            }
        }
        catch(NumberFormatException e)
        {
            cli.showErrorMessage("Not valid input!");
            cli.askPlayerForDrafting();
        }
    }

    private void controlAskPlayerForAddingDiceView(CLIInputEvent event)
    {
        try
        {
            Integer val = Integer.parseInt(event.getInput());
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

                GameAction action = new AddDieToBoardAction(game, row, col);
                game.executeAction(action);

                if(action.hasBeenExecuted())
                    cli.askPlayerForAction();
                else
                {
                    cli.showErrorMessage(action.getErrorMessage());
                    cli.askPlayerForAddingDie(game.getLastDraftedDie());
                }

            }
            else        //input is not in range [0,20]
            {
                cli.showErrorMessage("Not valid input!");
                cli.askPlayerForAddingDie(game.getLastDraftedDie());
            }
        }       //input is not a number
        catch(NumberFormatException e)
        {
            cli.showErrorMessage("Not valid input!");
            cli.askPlayerForAddingDie(game.getLastDraftedDie());
        }
    }

    private void controlClientAskingIPView(CLIInputEvent event)
    {
        cli.menuClientAskingPort();
    }

    private void controlClientAskingPortView(CLIInputEvent event)
    {
        cli.menuClientAskingNickname();
    }

    private void controlClientAskingNicknameView(CLIInputEvent event)
    {
        try
        {
            game.addNewPlayer(event.getInput());
        }
        catch(CannotAddPlayerException e)
        {
            cli.showErrorMessage(e.getMessage());
        }

        cli.beginGame();
    }*/
}
