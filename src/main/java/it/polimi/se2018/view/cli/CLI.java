package it.polimi.se2018.view.cli;
import it.polimi.se2018.events.Message;
import it.polimi.se2018.events.clievents.CLIInputParsedEvent;
import it.polimi.se2018.model.*;
import it.polimi.se2018.view.*;

import java.util.ArrayList;
import java.util.Scanner;


public class CLI extends View
{
    private     CLIState    state;
    private     Scanner     scanner;

    public CLI (Game game)
    {
        super (game);
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
        System.out.println("Select an action from the menu typing it's number.");
        System.out.println();
    }

    @Override
    public void update(Message event)
    {

    }

    public void showErrorMessage(String message)
    {
        System.out.print(Color.RED.getConsoleString());
        System.out.println("Error: " + message);
        System.out.print(Color.getResetConsoleString());
    }

    public void showMessage(String message)
    {
        System.out.println(message);
    }


    public void start()
    {
        menuClientServer();
    }

    private void render ()
    {
        for (Player player : game.getAllPlayers())
            draw(player);
    }

    public void menuClientServer()
    {
        state = CLIState.MENU_CLIENT_SERVER;
        System.out.println("1) Start a New Game (Server):");
        System.out.println("2) Connect to a Game (Client):");
        notify(new CLIInputParsedEvent(this, state, scanner.next()));
    }

    public void askPLayerNickname()
    {
        state = CLIState.MENU_ASKING_PLAYER_NICKNAME;
        System.out.println("Insert your nickname:");
        notify(new CLIInputParsedEvent(this, state, scanner.next()));
    }

    private void draw (Player player)
    {

        System.out.println("Player " +player.getNickname() + " : ");
        for (int i=0; i<Board.ROWS; i++)
        {
            for (int j=0; j<Board.COLUMNS; j++)
            {
                System.out.print ("[ ");

                CellRestriction restriction = player.getBoard().getCell(i,j).getRestriction();
                if (restriction.isColor())
                {
                    System.out.print (restriction.getColor().getConsoleString());
                    System.out.print (restriction.getColor().getFirstChar()+ " ");
                    System.out.print ("\033[0m");
                    System.out.print ("| ");
                }

                else if (restriction.isValue())
                    System.out.print(restriction.getValue() + " | ");

                else
                {
                    System.out.print("- | ");
                }

                Die die = player.getBoard().getDie(i,j);

                if (die == null)
                    System.out.print("- ] ");

                else
                {
                    System.out.print(die.getColor().getConsoleString());
                    System.out.print(die.getValue() + "." + die.getColor().getFirstChar() + " ] ");
                }

            }

            System.out.println();

        }


    }










}
