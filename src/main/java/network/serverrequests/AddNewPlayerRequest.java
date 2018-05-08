package network.serverrequests;

import it.polimi.se2018.model.CannotAddPlayerException;
import it.polimi.se2018.observer.Observer;
import network.Client;
import network.Server;
import network.ServerRequest;
import network.ServerRequestHandler;

public class AddNewPlayerRequest extends ServerRequest
{
    private String nickname;

    public AddNewPlayerRequest(String nickname)
    {
        this.nickname = nickname;
    }

    public void executeOnServer(Server server, ServerRequestHandler serverHandler)
    {
        try
        {
            server.getGameInstance().addNewPlayer(nickname);
            for(Observer o : server.getGameInstance().getAllObservers())
                System.out.println("obs"+o);
        }
        catch (CannotAddPlayerException e)
        {

        }
    }

    public void executeOnClient(Client client)
    {
    }
}
