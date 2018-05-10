package it.polimi.se2018.controller;

import it.polimi.se2018.controller.gameactions.*;
import it.polimi.se2018.events.Event;
import it.polimi.se2018.events.clievents.*;
import it.polimi.se2018.model.*;
import it.polimi.se2018.view.cli.CLI;

public class CLIController extends Controller
{
    private CLI         cli;

    public CLIController(CLI cli, Game game)
    {
        super(cli, game);
        this.cli = cli;
        view.attach(this);
    }

    @Override
    //handle events from the view
    public void update(Event event)
    {
        if(event instanceof CLIInputEvent)
            handleEvent((CLIInputEvent)event);
        else if(event instanceof CLIBeginGameEvent)
        {
            saveAction(new BeginTurnAction(game));
            cli.askPlayerForAction();
        }
    }

    private void handleEvent(CLIInputEvent event)
    {
        switch(event.getState())
        {
            case MENU_CLIENT_SERVER:
                controlClientServerView(event);
                break;

            /*case MENU_NEW_PLAYER_ASKING_NICKNAME:
                controlNewPlayerAskingNicknameView(event);
                break;*/

            case MENU_CLIENT_ASKING_IP:
                controlClientAskingIPView(event);
                break;

            case MENU_CLIENT_ASKING_PORT:
                controlClientAskingPortView(event);
                break;

            case MENU_CLIENT_ASKING_NICKNAME:
                controlClientAskingNicknameView(event);
                break;

            case GAME_ASK_PLAYER_FOR_ACTION:
                controlAskPlayerForActionView(event);
                break;

            case GAME_ASK_PLAYER_FOR_ACTION_CARDS:
                controlAskPlayerForActionCardsView(event);
                break;

            case GAME_ASK_PLAYER_FOR_DRAFTING:
                controlAskPlayerForDraftingView(event);
                break;

            case GAME_ASK_PLAYER_FOR_ADDING_DICE:
                controlAskPlayerForAddingDiceView(event);
                break;
        }
    }

    private void controlClientServerView(CLIInputEvent event)
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
                saveAction(new DraftDieAction(game, val));
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
                try
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

                    saveAction(new AddDieToBoardAction(game, row, col));
                    cli.askPlayerForAction();
                }
                catch (CannotAddDieException e)
                {
                    cli.showErrorMessage(e.getMessage());
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
    }
}
