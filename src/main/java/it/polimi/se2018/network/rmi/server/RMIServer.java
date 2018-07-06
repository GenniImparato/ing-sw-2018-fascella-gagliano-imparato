package it.polimi.se2018.network.rmi.server;

import it.polimi.se2018.network.exceptions.CannotCreateServerException;
import it.polimi.se2018.network.server.Server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMIServer
{
    private Server server;
    RMIServerInterface serverServices;

    private static final int PORT = 1099;

    public RMIServer(Server server) throws CannotCreateServerException
    {
        this.server = server;

        try
        {
            LocateRegistry.createRegistry(PORT);
        }
        catch (RemoteException e)
        {
            throw new CannotCreateServerException("RMI Registry already created!");
        }

        try
        {
            serverServices = new RMIServerServices(server);
            Naming.rebind("//localhost/RMIServer", serverServices);

        }
        catch (MalformedURLException e)
        {
            throw new CannotCreateServerException("Cannot register remote object!");
        }
        catch (RemoteException e)
        {
            throw new CannotCreateServerException(e.getMessage());
        }

        new RMIClientRemover(this);
    }


    public void checkActivePlayers()
    {
        try
        {
            serverServices.checkActivePlayers();
        }
        catch(RemoteException e)
        {
            e.printStackTrace();
        }
    }



}
