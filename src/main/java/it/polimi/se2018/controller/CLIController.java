package it.polimi.se2018.controller;

import it.polimi.se2018.events.clievents.CLIBeginRoundEvent;
import it.polimi.se2018.events.clievents.CLIEvent;
import it.polimi.se2018.events.clievents.CLIInputEvent;
import it.polimi.se2018.model.*;
import it.polimi.se2018.view.cli.CLI;

public class CLIController extends Controller<CLIEvent>
{
    CLI view;

    public CLIController(Game game, CLI view)
    {
        super(game);
        this.view = view;
    }

    @Override
    public void update(CLIEvent event)
    {
        if(event instanceof CLIInputEvent)
            handleEvent((CLIInputEvent)event);
        else if(event instanceof CLIBeginRoundEvent) {
            game.beginRound();
            view.askPlayerForAction();
        }
    }

    private void handleEvent(CLIInputEvent event)
    {
        switch(event.getState())
        {
            case MENU_CLIENT_SERVER:
                if(event.getInput().equals("1"))
                {
                    view.showMessage("New game started!\n");
                    view.askPLayerNickname();
                }
                else if(event.getInput().equals("2"))
                {
                    view.showErrorMessage("Cannot connect to a game!");
                    view.menuClientServer();
                }
                else
                {
                    view.showErrorMessage("Input not valid!");
                    view.menuClientServer();
                }
                break;

            case MENU_ASKING_PLAYER_NICKNAME:
                try
                {
                    game.addNewPlayer(event.getInput());
                    view.showMessage("Player " + event.getInput() + " added!");
                    view.askPLayerNickname();
                }
                catch(CannotAddPlayerException e)
                {
                    view.showErrorMessage(e.getMessage());
                }
                break;

            case GAME_ASK_PLAYER_FOR_ACTION:
                if(event.getInput().equals("1"))
                    view.askPlayerForDrafting();
                else if(event.getInput().equals("2"))
                    view.askPlayerForAction();
                else
                {
                    view.showErrorMessage("Not valid input!");
                    view.askPlayerForAction();
                }
                break;

            case GAME_ASK_PLAYER_FOR_DRAFTING:
                if(event.getInput().equals("b"))
                    view.askPlayerForAction();
                else try
                {
                    Integer val = Integer.parseInt(event.getInput());
                    if(val >= 0 && val < game.getDraftPool().getAllDice().size())
                        game.draftDie(val);
                    else
                    {
                        view.showErrorMessage("Not valid input!");
                        view.askPlayerForDrafting();
                    }
                }
                catch(NumberFormatException e)
                {
                    view.showErrorMessage("Not valid input!");
                    view.askPlayerForDrafting();
                }
                break;

            case GAME_ASK_PLAYER_FOR_ADDING_DICE:
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

                            view.askPlayerForAction();
                        }
                        catch (CannotAddDieException e)
                        {
                            view.showErrorMessage(e.getMessage());
                            view.askPlayerForAddingDie(game.getLastDraftedDie());
                        }
                    }
                    else
                    {
                        view.showErrorMessage("Not valid input!");
                        view.askPlayerForAddingDie(game.getLastDraftedDie());
                    }
                }
                catch(NumberFormatException e)
                {
                    view.showErrorMessage("Not valid input!");
                    view.askPlayerForAddingDie(game.getLastDraftedDie());
                }
                break;
        }
    }
}
