package it.polimi.se2018.network.rmi.server;

import it.polimi.se2018.network.server.Server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMIServer
{
    private Server server;

    private static final int PORT = 1099;

    public RMIServer(Server server)
    {
        this.server = server;

        try
        {
            LocateRegistry.createRegistry(PORT);
        }
        catch (RemoteException e)
        {
            System.out.println("RMI Registry already created!");
        }

        try
        {
            Naming.rebind("//localhost/RMIServer", new RMIServerServices(server));

        }
        catch (MalformedURLException e)
        {
            System.err.println("Impossibile registrare l'oggetto indicato!");
        }
        catch (RemoteException e)
        {
            System.err.println("Errore di connessione: " + e.getMessage() + "!");
        }

    }



}
