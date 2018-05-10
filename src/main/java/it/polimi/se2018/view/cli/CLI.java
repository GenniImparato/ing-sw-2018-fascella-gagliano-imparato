package it.polimi.se2018.view.cli;
import it.polimi.se2018.events.Message;
import it.polimi.se2018.events.clievents.*;
import it.polimi.se2018.events.messages.*;
import it.polimi.se2018.model.*;
import it.polimi.se2018.view.*;
import it.polimi.se2018.view.cli.renderer.*;
import it.polimi.se2018.view.cli.views.CLIMenuView;
import it.polimi.se2018.view.cli.views.CLIView;

import java.util.Scanner;


public class CLI extends View<CLIEvent>
{
    private     CLIState            state;
    private     Scanner             scanner;

    public CLI (Game game)
    {
        super(game);

        scanner = new Scanner(System.in);

        System.out.println();
        System.out.print(Color.RED.getConsoleString());
        System.out.print("S ");
        System.out.print(Color.BLUE.getConsoleString());
        System.out.print("A ");
        System.out.print(Color.YELLOW.getConsoleString());
        System.out.print("G ");
        System.out.print(Color.GREEN.getConsoleString());
        System.out.print("R ");
        System.out.print(Color.PURPLE.getConsoleString());
        System.out.print("A ");
        System.out.print(Color.BLUE.getConsoleString());
        System.out.print("D ");
        System.out.print(Color.RED.getConsoleString());
        System.out.print("A ");
        System.out.println(Color.getResetConsoleString());
        System.out.println("____________________");
        System.out.println();
        System.out.println();
    }

    @Override
    public void update(Message event)
    {
        setGameInstance(event.getGame());

        if(event instanceof PlayerAddedMessage)
            showNotification(((PlayerAddedMessage) event).getPlayer().getNickname() + " has joined the game!", Color.BLUE);
        /*if(event instanceof DraftedDieMessage)
            askPlayerForAddingDie(((DraftedDieMessage)event).getDie());
        if(event instanceof GameStartedMessage)
            askPlayerForAction();*/
    }

    public void showErrorMessage(String message)
    {
        System.out.print(Color.RED.getConsoleString());
        System.out.println("Error: " + message);
        System.out.print(Color.getResetConsoleString());
        try
        {
            Thread.sleep(3000);
        }
        catch(InterruptedException e)
        {}
    }

    public void showMessage(String message)
    {
        System.out.println(message);
    }

    public void showNotification(String message, Color color)
    {

        System.out.print(color.getConsoleString());
        System.out.println(message);
        System.out.print(Color.getResetConsoleString());
        try
        {
            Thread.sleep(3000);
        }
        catch(InterruptedException e)
        {}
    }

    public void start()
    {
        new CLIMenuView(this).draw();
    }

    public void clear()
    {
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.print("\u001b[2J"+"\u001b[H");
        System.out.flush();
    }

    public String readInputFromUser()
    {
        return scanner.next();
    }

    public void requestView(CLIView cliView)
    {
        clear();
        cliView.draw();
    }

    public void renderGameState(CLIRendererMainState renderState)
    {
        new CLIRendererMain(getGameInstance()).render(renderState);
    }

    public void renderGameState(CLIRendererCardsState renderState)
    {
        new CLIRendererCards(getGameInstance()).render(renderState);
    }


   /* public void menuClientServer()
    {
        state = CLIState.MENU_CLIENT_SERVER;

        notify(new CLIInputEvent(this, state, scanner.next()));
    }

    public void menuClientAskingIP()
    {
        state = CLIState.MENU_CLIENT_ASKING_IP;
        System.out.println("Insert IP of the Server:");
        notify(new CLIInputEvent(this, state, scanner.next()));
    }

    public void menuClientAskingPort()
    {
        state = CLIState.MENU_CLIENT_ASKING_PORT;
        System.out.println("Insert the port of the Server:");
        notify(new CLIInputEvent(this, state, scanner.next()));
    }

    public void menuClientAskingNickname()
    {
        state = CLIState.MENU_CLIENT_ASKING_NICKNAME;
        System.out.println("Insert your nickname:");
        scanner.nextLine();
        notify(new CLIInputEvent(this, state, scanner.nextLine()));
    }

    //notify the controller to tell that a new round has begun
    public void beginGame()
    {
        notify(new CLIStartGameEvent(this, state));
    }

    public void askPlayerForAction()
    {
        renderGameState(CLIRendererMainState.NO_SELECTION);
        state = CLIState.GAME_ASK_PLAYER_FOR_ACTION;
        System.out.println("It's your turn!");
        System.out.println("Choose an action:");
        System.out.println("1) Draft a die");
        System.out.println("2) Show cards");
        notify(new CLIInputEvent(this, state, scanner.next()));
    }

    public void askPlayerForActionCards()
    {
        renderGameState(CLIRendererCardsState.NO_SELECTION);
        state = CLIState.GAME_ASK_PLAYER_FOR_ACTION_CARDS;
        System.out.println("Choose an action:");
        System.out.println("1) Use a tool card");
        System.out.println("2) Return to main view");
        notify(new CLIInputEvent(this, state, scanner.next()));
    }

    public void askPlayerForDrafting()
    {
        renderGameState(CLIRendererMainState.DRAFTPOOL_SELECTED);
        state = CLIState.GAME_ASK_PLAYER_FOR_DRAFTING;
        System.out.println("Choose a die in the draft pool:");
        System.out.println("b) go back");
        notify(new CLIInputEvent(this, state, scanner.next()));
    }

    public void askPlayerForAddingDie(Die die)
    {
        renderGameState(CLIRendererMainState.BOARD_SELECTED);
        state = CLIState.GAME_ASK_PLAYER_FOR_ADDING_DICE;
        System.out.print("You drafted a " + die.getColor().getConsoleString() + die.getColor() +" "+ Color.getResetConsoleString());
        System.out.println("die with value: " + die.getValue());
        System.out.println("Choose in which cell to add the die: ");
        notify(new CLIInputEvent(this, state, scanner.next()));
    }*/
}
