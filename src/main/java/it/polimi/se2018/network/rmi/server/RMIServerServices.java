package it.polimi.se2018.network.rmi.server;

import it.polimi.se2018.model.Player;
import it.polimi.se2018.mvc_comunication.Event;
import it.polimi.se2018.mvc_comunication.events.AddPlayerEvent;
import it.polimi.se2018.mvc_comunication.events.ClientDisconnectedEvent;
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

    public void removeClient(RMIClientInterface client) throws RemoteException
    {
        int removedClientIndex = clients.indexOf(client);
        VirtualView removedVirtualClient = virtualClients.get(removedClientIndex);

        virtualClients.get(removedClientIndex).notify(new ClientDisconnectedEvent(removedVirtualClient, removedVirtualClient.getAssociatedNickname()));
        server.getModel().detach(removedVirtualClient);      //register the virtual view as an observer of the model
        removedVirtualClient.detach(server.getController());

        clients.remove(removedClientIndex);
        virtualClients.remove(removedClientIndex);

        System.out.println("Removed");
    }

    @Override
    public void notifyController(RMIClientInterface client, Event event) throws RemoteException
    {
        if(clients.indexOf(client) != -1)
        {
            VirtualView vView = virtualClients.get(clients.indexOf(client));

            event.setView(vView);

            //intercept AddPLayerEvent to set the virtual view associated player nickname
            if(event instanceof AddPlayerEvent)
                vView.setAssociatedNickname(((AddPlayerEvent)event).getNickname());

            vView.notify(event);
        }
    }

    @Override
    public synchronized void checkActivePlayers() throws RemoteException
    {
        for(VirtualView virtualClient : virtualClients)
        {
            for(Player player: server.getModel().getPlayers())
            {
                if(virtualClient.getAssociatedNickname() != null
                        &&     !player.isActive()
                        &&     player.getNickname().equals(virtualClient.getAssociatedNickname()))
                    virtualClient.disconnect();
            }
        }
    }
}

