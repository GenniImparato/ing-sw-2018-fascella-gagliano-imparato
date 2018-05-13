package it.polimi.se2018.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class used to iterate on the Players of the game.
 * All the logic of the turns is implemented in this class.
 * @author Matteo Gagliano
 * @author Carmelo Fascella
 * @author Generoso Imparato
 */
public class PlayerTurnIterator implements Iterator<Player>
{
    private List<Player>                players;
    private int                         currentTurn = 0;
    private int                         firstPlayer = 0;
    private List<Integer>               turns;                  //used to store the order of player's turns

    private static final int            MAX_PLAYERS_NUM =4;

    /**
     * Constructor that creates a PlayerTurnIterator
     */
    public PlayerTurnIterator()
    {
        players = new ArrayList<>();
        turns = new ArrayList<>();
    }

    /**
     * Copy constructor
     * @param playerTurnIterator source instance to be cloned
     */
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

    /**
     * Tries to add a player; if a Player can't be added, throws an Exception.
     * @param nickname a String representing the nickname of the Player to add to the Game.
     * @throws CannotAddPlayerException if there is another player with same nickname
     * or if the game has reached the maximum number of Players.
     */
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

    /**
     * Gets the instance of the player that takes the turn next
     * @return the Player who's going to play the next turn
     */
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

    /**
     * Tells if there's at least one more player on the queue
     * @return true if there are still players on the queue
     */
    public boolean hasNext()
    {
        return currentTurn < getPlayerNum()*2;
    }

    /**
     * Returns the number of players present in the game
     * @return number of players present in the game
     */
    public int  getPlayerNum()
    {
        return players.size();
    }

    /**
     * Tells if it's the last turn of the round or not
     * @return true if this is the last turn of the round
     */
    public boolean isLastTurn()
    {
        return !hasNext();
    }

    public boolean isCurrentPlayerSecondTurn()
    {
        int currentPlayer = turns.get(currentTurn);
        boolean ret = true;

        for(int i = currentPlayer+1; i<turns.size(); i++)
            if(turns.get(i) == currentPlayer)
                ret = false;

        return ret;
    }
    //return an ArrayList with all the players in the game
    /**
     * Returns an ArrayList with all the players in the game
     * @return ArrayList of all the players present in the game
     */
    public List<Player> getAllPlayers()
    {
        return players;
    }

    /**
     * Refreshes the internal list with the correct queue of the players turns.
     * Each time a new round begins this method needs to be called.
     */
    private void refreshPlayersTurns()
    {
        currentTurn = 0;
        turns.clear();

        int iClockwise;

        for(iClockwise=firstPlayer; iClockwise<getPlayerNum(); iClockwise++)
            turns.add(iClockwise);                       //adds all the players to the turns list in clockwise mode

        for(iClockwise=0; iClockwise<firstPlayer; iClockwise++)
            turns.add(iClockwise);                       //adds all the players to the turns list in clockwise mode

        int iAnticlockwise;

        for(iAnticlockwise=iClockwise-1; iAnticlockwise>=0; iAnticlockwise--)
            turns.add(iAnticlockwise);                       //adds all the players to the turns list in anticlockwise mode

        for(iAnticlockwise=getPlayerNum()-1; iAnticlockwise>=firstPlayer; iAnticlockwise--)
            turns.add(iAnticlockwise);                       //adds all the players to the turns list in anticlockwise mode
    }

    /**
     * Increments the current turn of the game
     */
    private void incrementTurn()
    {
        currentTurn++;
    }

    /**
     * Increments the first Player (i.e. the player who starts and ends the round)
     */
    private void incrementFirstPlayer()
    {
        if(firstPlayer == getPlayerNum()-1)
            firstPlayer=0;
        else
            firstPlayer++;
    }
}
