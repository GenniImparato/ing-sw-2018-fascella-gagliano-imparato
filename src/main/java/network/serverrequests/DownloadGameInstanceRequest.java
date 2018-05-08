package network.serverrequests;

import network.Client;
import network.Server;
import network.ServerRequest;
import network.ServerRequestHandler;

import java.io.Serializable;

public class DownloadGameInstanceRequest extends ServerRequest implements Serializable
{
    public void executeOnServer(Server server, ServerRequestHandler serverHandler)
    {
        serverHandler.sendGameInstance();
    }

    public void executeOnClient(Client client)
    {
       client.downloadGameInstanceFromServer();
    }
}
