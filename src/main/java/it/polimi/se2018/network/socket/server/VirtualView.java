package it.polimi.se2018.network.socket.server;

import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.network.socket.client.ClientInterface;

import java.io.*;
import java.net.Socket;

public class VirtualView extends Thread implements ClientInterface
{
    private Socket clientSocket;

    public VirtualView(Socket clientSocket)
    {
        this.clientSocket = clientSocket;
        sendToClient("messaggio inviato");
        start();
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
                sendToClient("messaggio inviato");
            }
        }catch(IOException e){e.printStackTrace();}
    }

    @Override
    public synchronized void sendToClient(String message)
    {
        try
        {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(message);
            out.flush();
        }
        catch(IOException e){e.printStackTrace();}

    }

}
