package it.polimi.se2018.network.socket.client;

import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.network.socket.ClientInterface;
import it.polimi.se2018.network.socket.server.ServerInterface;
import it.polimi.se2018.view.cli.CLI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class NetworkHandler extends Thread implements ServerInterface
{
    private Socket socketToServer;
    private ClientInterface client;
    private BufferedReader in;

    private ClientNetworkMessageAnalyzer messageAnalyzer;

    public NetworkHandler(String host, int port, ClientInterface client)
    {
        this.client = client;
        messageAnalyzer = new ClientNetworkMessageAnalyzer(this);

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
                messageAnalyzer.analyzeMessage(inputString);
            }
            catch (IOException e) {e.printStackTrace();}
        }
    }

    @Override
    public synchronized void sendToServer(Message message)
    {

    }

    public synchronized ClientInterface getClient() {
        return client;
    }

    private synchronized void parseString(String message)
    {
        try
        {
            Method method = client.getClass().getMethod(message);
            method.invoke(client);
        }
        catch(IllegalAccessException|IllegalArgumentException|InvocationTargetException e)
            {e.printStackTrace();}
        catch(NoSuchMethodException e) {e.printStackTrace();}
    }
}
