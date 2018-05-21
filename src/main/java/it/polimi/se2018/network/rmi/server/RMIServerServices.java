package it.polimi.se2018.network.rmi.server;

import it.polimi.se2018.mvc_comunication.Event;
import it.polimi.se2018.network.client.NetworkHandler;
import it.polimi.se2018.network.rmi.client.RMIClientInterface;
import it.polimi.se2018.network.server.Server;
import it.polimi.se2018.network.server.VirtualView;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RMIServerServices extends UnicastRemoteObject implements RMIServerInterface
{
    private List<VirtualView>           virtualClients;
    private List<RMIClientInterface>    clients;
    private Server                      server;

    private static final long serialVersionUID = -7098548671967083832L;

    public RMIServerServices(Server server) throws RemoteException
    {
        super(0);
        virtualClients  = new ArrayList<>();
        clients         = new ArrayList<>();
        this.server = server;
    }

    public void addClient(RMIClientInterface client) throws RemoteException
    {
        RMIVirtualView vView = new RMIVirtualView(client);
        virtualClients.add(vView);

        vView.attach(server.getController());   //register the controller as an observer of the created virtual view
        server.getModel().attach((vView));      //register the virtual view as an observer of the model*/

        clients.add(client);
    }

    @Override
    public void notifyController(RMIClientInterface client, Event event) throws RemoteException
    {
        VirtualView vView = virtualClients.get(clients.indexOf(client));

        event.setView(vView);
        vView.notify(event);
    }
}

