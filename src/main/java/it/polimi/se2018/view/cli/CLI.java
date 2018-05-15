package it.polimi.se2018.view.cli;
import it.polimi.se2018.events.Message;
import it.polimi.se2018.utils.Color;
import it.polimi.se2018.view.*;
import it.polimi.se2018.view.cli.renderer.*;
import it.polimi.se2018.view.cli.views.CLIMenuView;
import it.polimi.se2018.view.cli.views.CLIView;

import java.util.Scanner;


public class CLI extends View
{
    private Scanner             scanner;
    private CLIMessageParser    parser;

    public CLI ()
    {
        scanner = new Scanner(System.in);
        parser = new CLIMessageParser(this);

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
    public void update(Message message)
    {
        setModel(message.getModel());
        message.beParsed(parser);
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
            Thread.sleep(500);
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
        new CLIRendererMain(getModel()).render(renderState);
    }

    public void renderGameState(CLIRendererCardsState renderState)
    {
        new CLIRendererCards(getModel()).render(renderState);
    }

    @Override
    public void showMenu()
    {
        new CLIMenuView(this).draw();
    }
}
