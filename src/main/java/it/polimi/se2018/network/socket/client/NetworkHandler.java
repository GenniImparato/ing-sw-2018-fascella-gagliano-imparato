package it.polimi.se2018.network.socket.client;

import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.network.socket.server.ServerInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class NetworkHandler extends Thread implements ServerInterface
{
    private Socket socketToServer;
    private ClientInterface client;
    private BufferedReader in;

    public NetworkHandler(String host, int port, ClientInterface client)
    {
        this.client = client;

        try
        {
            socketToServer = new Socket(host,port);
            this.in = new BufferedReader(new InputStreamReader(socketToServer.getInputStream()));
        }
        catch(IOException e){e.printStackTrace();}
        start();

    }

    @Override
    public void run()
    {
        while(true)
        {
            try
            {

                String inputString = in.readLine();
                client.sendToClient(inputString);
            }
            catch (IOException e) {e.printStackTrace();}
        }
    }

    @Override
    public synchronized void sendToServer(Message message){

    }
}
