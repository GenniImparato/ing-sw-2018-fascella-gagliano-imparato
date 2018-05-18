package it.polimi.se2018.network.socket.server;

import it.polimi.se2018.network.socket.ClientInterface;

import java.io.*;
import java.net.Socket;

public class VirtualView extends Thread implements ClientInterface
{
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public VirtualView(Socket clientSocket)
    {
        this.clientSocket = clientSocket;
        try
        {
            this.out = new PrintWriter(clientSocket.getOutputStream(), true);
        }
        catch(IOException e) { e.printStackTrace();}

        start();

        showMessage("MESSAGGIO DI ERRORE");
        showMessage("MESSAGGIO DI ERRORE 2");
        showMessage("MESSAGGIO DI ERRORE 3");
        showMessage("MESSAGGIO DI ERRORE 4");
        showMessage("MESSAGGIO DI ERRORE 5");
        showMessage("MESSAGGIO DI ERRORE 6");
        showMessage("MESSAGGIO DI ERRORE 7");
        showMessage("MESSAGGIO DI ERRORE 8");
    }

    @Override
    //This method waits for messages from Server
    public void run()
    {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            while (true)
            {
                in.readLine();

            }
        }catch(IOException e){e.printStackTrace();}
    }


    @Override
    public synchronized void showErrorMessage(String message)
    {
        sendToClient("StartMethodInvocation");
        sendToClient("showErrorMessage");
        sendToClient(message);
        sendToClient("EndMethodInvocation");
    }

    @Override
    public synchronized void showMessage(String message)
    {
        sendToClient("StartMethodInvocation");
        sendToClient("showMessage");
        sendToClient(message);
        sendToClient("EndMethodInvocation");


    }

    @Override
    public synchronized void showMenu()
    {
        sendToClient("showMenu");
    }

    @Override
    public synchronized void showTurn()
    {
        sendToClient("showTurn");
    }

    @Override
    public synchronized void showDraft()
    {
        sendToClient("showDraft");
    }

    @Override
    public synchronized void showAddDie()
    {
        sendToClient("showAddDie");
    }

    @Override
    public synchronized  void showSelectDieFromBoard()
    {
        sendToClient("showSelectDieFromBoard");
    }

    @Override
    public synchronized  void showMoveDie()
    {
        sendToClient("showMoveDie");
    }

    @Override
    public synchronized void showIncrementDie()
    {
        sendToClient("showIncrementDie");
    }

    @Override
    public synchronized void reShowCurrentView()
    {
        sendToClient("reShowCurrentView");
    }

    //Writes a message to the client socket
    private synchronized void sendToClient(String message)
    {
        out.println(message);
    }
}
