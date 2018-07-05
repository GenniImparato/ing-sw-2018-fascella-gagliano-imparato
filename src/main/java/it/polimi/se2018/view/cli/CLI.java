package it.polimi.se2018.view.cli;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.utils.Color;
import it.polimi.se2018.view.*;
import it.polimi.se2018.view.cli.renderer.*;
import it.polimi.se2018.view.cli.views.*;
import it.polimi.se2018.view.cli.views.CLIIncrementDieView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;


public class CLI extends View
{
    protected   transient   Scanner             scanner;
    protected               String              input;
    private     transient   CLIReaderTask       readerTask;

    private     transient   CLIMessageParser    parser;
    protected   transient   CLIView             currentView;



    public CLI()
    {
        parser = new CLIMessageParser(this);
        scanner = new Scanner(System.in);
    }

    @Override
    public String getStartNotificationString()
    {
        return "";
    }

    @Override
    public String getEndNotificationString()
    {
        return "";
    }

    @Override
    public String getColorString(Color color)
    {
        return color.getConsoleString();
    }

    @Override
    public String getColorEndString()
    {
        return Color.getResetConsoleString();
    }

    @Override
    public void update(Message message)
    {
        setModel(message.getModel());
        message.acceptVisitor(parser);
    }

    public void showErrorMessage(String message)
    {
        System.out.print(Color.RED.getConsoleString());
        System.out.println("Error: " + message);
        System.out.print(Color.getResetConsoleString());
        try
        {
            Thread.sleep(2000);
        }
        catch(InterruptedException e)
        {}
    }

    public void showMessage(String message)
    {
        System.out.println(message);
    }

    public void showMessage(String message, Color color)
    {
        System.out.print(color.getConsoleString());
        System.out.println(message);
        System.out.print(Color.getResetConsoleString());
    }


    public void showNotification(String message)
    {
        cancelInputReading();

        System.out.println(message);

        try
        {
            Thread.sleep(2000);
        }
        catch(InterruptedException e)
        {}
    }

    public void start()
    {
        System.out.println();
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

        showView(new CLIMenuView(this));
    }

    public void clear()
    {
        for(int i=0; i<50; i++)
            System.out.print("\n");
        System.out.print("\u001b[2J"+"\u001b[H");
        System.out.flush();
    }

    public void readInputFromUser()
    {
        cancelInputReading();
        readerTask = new CLIReaderTask(this);
    }

    private void cancelInputReading()
    {
        if(readerTask != null)
        {
            readerTask.cancelReading();
        }
    }

    public void showView(CLIView cliView)
    {
        currentView = cliView;
        cliView.draw();
    }

    public void renderGameState(CLIRendererMainState renderState)
    {
        new CLIRendererMain(this, getModel()).render(renderState);
    }

    public void renderGameState(CLIRendererCardsState renderState)
    {
        new CLIRendererCards(this, getModel()).render(renderState);
    }

    @Override
    public void showMenu()
    {
        showView(new CLIMenuView(this));
    }

    @Override
    public void showLobby()
    {
        showView(new CLILobbyView(this));
    }

    @Override
    public void showDraft()
    {
        showView(new CLIDraftDieView(this));
    }

    @Override
    public void showTurn()
    {
        showView(new CLIMainActionsView(this));
    }

    @Override
    public void showAddDie()
    {
        showView(new CLIAddDieView(this));
    }

    @Override
    public void showIncrementDie()
    {
        showView(new CLIIncrementDieView(this));
    }

    @Override
    public void showSelectSameColorDie() {

    }

    @Override
    public void showSelectDieFromBoard()
    {
        showView(new CLISelectDieFromBoard(this));
    }

    @Override
    public void showSelectDieFromRoundTrack() {

    }

    @Override
    public void showReDrawDie() {

    }

    @Override
    public void showMoveDie()
    {
        showView(new CLIMoveDieView(this));
    }

    @Override
    public void reShowCurrentView()
    {
        currentView.draw();
    }

    @Override
    public void showFinalScore() {

    }

    @Override
    public void disconnect()
    {}




}
