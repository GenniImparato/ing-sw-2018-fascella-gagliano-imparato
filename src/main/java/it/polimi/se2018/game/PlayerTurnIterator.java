package it.polimi.se2018.game;

import java.util.ArrayList;
import java.util.Iterator;

public class PlayerTurnIterator implements Iterator<Player>
{
    private ArrayList<Player>           players;
    private int                         currentTurn = 0;
    private int                         firstPlayer = 0;
    private ArrayList<Integer>          turns;                  //used to store the order of player's turns

    private static final int            MAX_PLAYERS_NUM =4;

    public PlayerTurnIterator()
    {
        players = new ArrayList<>();
        turns = new ArrayList<>();
    }

    public void addNewPlayer(String nickname) throws TooManyPlayersException
    {
        if(getPlayerNum() < MAX_PLAYERS_NUM)
            players.add(new Player(nickname));
        else
            throw new TooManyPlayersException();

        refreshPlayersTurns();
    }

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

    public boolean hasNext()
    {
        return currentTurn < getPlayerNum()*2;
    }

    public int  getPlayerNum()
    {
        return players.size();
    }

    public boolean isLastTurn()
    {
        return !hasNext();
    }

    //helper
    private void refreshPlayersTurns()      //refresh the list with the corrects players turn for a new round
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
            turns.add(iAnticlockwise);                       //add all the players to the turns list in clockwise mode

        for(iAnticlockwise=getPlayerNum()-1; iAnticlockwise>=firstPlayer; iAnticlockwise--)
            turns.add(iAnticlockwise);                       //add all the players to the turns list in clockwise mode
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


class TooManyPlayersException extends Exception
{

}