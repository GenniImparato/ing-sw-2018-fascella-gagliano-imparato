package network;

import java.io.Serializable;

public abstract class ServerRequest implements Serializable
{
    public abstract void executeOnServer(Server server, ServerRequestHandler serverHandler);
    public abstract void executeOnClient(Client client);
}
