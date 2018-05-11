package it.polimi.se2018.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlayerTurnIterator implements Iterator<Player>
{
    private List<Player>                players;
    private int                         currentTurn = 0;
    private int                         firstPlayer = 0;
    private List<Integer>               turns ;                  //used to store the order of player's turns

    private static final int            MAX_PLAYERS_NUM =4;

    public PlayerTurnIterator()
    {
        players = new ArrayList<>();
        turns = new ArrayList<>();
    }

    //copy constructor
    public PlayerTurnIterator(PlayerTurnIterator playerTurnIterator)
    {
        this.players = new ArrayList<>();

        for(Player player : playerTurnIterator.players)
            this.players.add(new Player(player));

        this.currentTurn = playerTurnIterator.currentTurn;
        this.firstPlayer = playerTurnIterator.firstPlayer;

        this.turns = new ArrayList<>();

        for(Integer turn : playerTurnIterator.turns)
            this.turns.add(turn);
    }

    //try to add a player, throws an exception if there is another player with same nickname or if there are already all players!
    public void addNewPlayer(String nickname) throws CannotAddPlayerException
    {
        if(nickname.length() == 0)
            throw new CannotAddPlayerException("Nickname cannot be empty!");

        for(Player player : players)
        {
            if(player.getNickname().equals(nickname))                       //check if another player has the same nickname
                throw new CannotAddPlayerException("There is another player with this nickname!");
        }

        if(getPlayerNum() < MAX_PLAYERS_NUM)                                //check if there are already all players
            players.add(new Player(nickname));
        else
            throw new CannotAddPlayerException("There are already " + MAX_PLAYERS_NUM + " players!");

        refreshPlayersTurns();
    }

    //get the instance of the player that takes the turn next
    public Player next()
    {
        Player ret=null;

        if(isLastTurn())
        {
            incrementFirstPlayer();
            refreshPlayersTurns();
        }

        if(hasNext())
            ret = players.get(turns.get(currentTurn));

        incrementTurn();

        return ret;
    }

    //return true if there are still players on the queue
    public boolean hasNext()
    {
        return currentTurn < getPlayerNum()*2;
    }

    //return the number of players in the game
    public int  getPlayerNum()
    {
        return players.size();
    }

    //return true if this is the last turn of the round
    public boolean isLastTurn()
    {
        return !hasNext();
    }

    //return an ArrayList with all the players in the game
    public List<Player> getAllPlayers()
    {
        return players;
    }

    //helper
    //refresh the internal list with the correct queue of the players turns
    //it's called at the beginning of the round
    private void refreshPlayersTurns()
    {
        currentTurn = 0;
        turns.clear();

        int iClockwise;

        for(iClockwise=firstPlayer; iClockwise<getPlayerNum(); iClockwise++)
            turns.add(iClockwise);                       //add all the players to the turns list in clockwise mode

        for(iClockwise=0; iClockwise<firstPlayer; iClockwise++)
            turns.add(iClockwise);                       //add all the players to the turns list in clockwise mode

        int iAnticlockwise;

        for(iAnticlockwise=iClockwise-1; iAnticlockwise>=0; iAnticlockwise--)
            turns.add(iAnticlockwise);                       //add all the players to the turns list in anticlockwise mode

        for(iAnticlockwise=getPlayerNum()-1; iAnticlockwise>=firstPlayer; iAnticlockwise--)
            turns.add(iAnticlockwise);                       //add all the players to the turns list in anticlockwise mode
    }

    //helper
    private void incrementTurn()
    {
        currentTurn++;
    }

    //helper
    private void incrementFirstPlayer()
    {
        if(firstPlayer == getPlayerNum()-1)
            firstPlayer=0;
        else
            firstPlayer++;
    }
}
