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
            throw new CannotCreateServerException("Cannot create RMI server!", "RMI Registry already created!");
        }

        try
        {
            Naming.rebind("//localhost/RMIServer", new RMIServerServices(server));

        }
        catch (MalformedURLException e)
        {
            throw new CannotCreateServerException("Cannot create RMI server!", "Cannot register remote object!");
        }
        catch (RemoteException e)
        {
            throw new CannotCreateServerException("Errore di connessione: ", e.getMessage() + "!");
        }

    }



}
