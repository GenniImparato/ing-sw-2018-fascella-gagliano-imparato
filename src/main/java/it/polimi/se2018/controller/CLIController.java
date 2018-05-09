package it.polimi.se2018.controller;

import it.polimi.se2018.events.Event;
import it.polimi.se2018.events.clievents.CLIBeginRoundEvent;
import it.polimi.se2018.events.clievents.CLIInputEvent;
import it.polimi.se2018.events.clievents.CLIRequestGameInstanceEvent;
import it.polimi.se2018.model.*;
import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.network.CannotConnectToServerException;
import it.polimi.se2018.network.CannotCreateServerException;
import it.polimi.se2018.network.Server;

public class CLIController extends Controller
{
    private CLI         cli;
    private String      serverIP;
    private int         serverPort;

    public CLIController(CLI cli, Game game)
    {
        super(cli, game);
        this.cli = cli;
        view.attach(this);
        cli.start();
    }

    @Override
    //handle events from the view
    public void update(Event event)
    {
        if(event instanceof CLIInputEvent)
            handleEvent((CLIInputEvent)event);
        else if(event instanceof CLIBeginRoundEvent)
        {
            game.beginRound();
            cli.askPlayerForAction();
        }
        else if(event instanceof CLIRequestGameInstanceEvent)
        {
            cli.showErrorMessage("Game download requested");
        }
    }

    private void handleEvent(CLIInputEvent event)
    {
        switch(event.getState())
        {
            case MENU_CLIENT_SERVER:
                controlClientServerView(event);
                break;

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
            Server server;

            try
            {
                server = new Server();
                cli.showMessage("A server has been created!");
                cli.showMessage("Server IP: " + server.getIP());
                cli.showMessage("Server port: " + server.getPort());
                cli.menuClientServer();
            }
            catch (CannotCreateServerException e)
            {
                cli.showErrorMessage("Server cannot be created!\n");
            }

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
                game.draftDie(val);
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
            if(val >= 0 && val < 20)
            {
                try
                {
                    if(val<=4)
                        game.getCurrentPlayer().getBoard().addDie(game.getLastDraftedDie(),0, val);
                    else if(val<=9)
                        game.getCurrentPlayer().getBoard().addDie(game.getLastDraftedDie(),1, val%5);
                    else if(val<=14)
                        game.getCurrentPlayer().getBoard().addDie(game.getLastDraftedDie(),2, val%10);
                    else
                        game.getCurrentPlayer().getBoard().addDie(game.getLastDraftedDie(),3, val%15);

                    cli.askPlayerForAction();
                }
                catch (CannotAddDieException e)
                {
                    cli.showErrorMessage(e.getMessage());
                    cli.askPlayerForAddingDie(game.getLastDraftedDie());
                }
            }
            else
            {
                cli.showErrorMessage("Not valid input!");
                cli.askPlayerForAddingDie(game.getLastDraftedDie());
            }
        }
        catch(NumberFormatException e)
        {
            cli.showErrorMessage("Not valid input!");
            cli.askPlayerForAddingDie(game.getLastDraftedDie());
        }
    }

    private void controlClientAskingIPView(CLIInputEvent event)
    {
        serverIP = event.getInput();
        cli.menuClientAskingPort();
    }

    private void controlClientAskingPortView(CLIInputEvent event)
    {
        try
        {
            serverPort = Integer.parseInt(event.getInput());
        }
        catch(NumberFormatException e)
        {
            cli.showErrorMessage("Port must be a number!");
            cli.menuClientServer();
        }
        cli.menuClientAskingNickname();
    }

    private void controlClientAskingNicknameView(CLIInputEvent event)
    {
        try
        {
            cli.connectToServer(serverIP, serverPort, event.getInput());
            try
            {
                game.addNewPlayer(event.getInput());
            }
            catch(CannotAddPlayerException e)
            {
                cli.showErrorMessage(e.getMessage());
            }

            cli.beginRound();
        }
        catch(CannotConnectToServerException e)
        {
            view.showErrorMessage("Cannot connect to server!");
            cli.menuClientServer();
        }
        catch(CannotAddPlayerException e)
        {
            view.showErrorMessage(e.getMessage());
            cli.menuClientServer();
        }
    }
}
