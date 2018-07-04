package it.polimi.se2018.controller;

import it.polimi.se2018.model.Player;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlayerTurnIterator implements Iterator<Player>
{
    private Controller                  controller;
    private int                         currentTurn = 0;
    private int                         firstPlayer = 0;
    private boolean                     nextCalled = false;

    private List<Integer>               turns ;                  //used to store the order of player's turns

    public PlayerTurnIterator(Controller controller)
    {
        this.controller = controller;
        turns = new ArrayList<>();
    }

    //get the instance of the player that takes the turn next
    public Player next()
    {
        Player ret=null;

        if(isFirstNextCall())
            refreshPlayersTurns();

        if(isLastTurn())
        {
            incrementFirstPlayer();
            refreshPlayersTurns();
        }

        if(hasNext())
            ret = controller.getModel().getPlayers().get(turns.get(currentTurn));

        incrementTurn();

        return ret;
    }

    //return true if there are still players on the queue
    public boolean hasNext()
    {
        return currentTurn < turns.size();
    }

    //return the number of players in the model
    private int  getPlayerNum()
    {
        return controller.getModel().getPlayerNum();
    }

    //return true if this is the last turn of the round
    public boolean isLastTurn()
    {
        return !hasNext();
    }

    public boolean isCurrentPlayerSecondTurn()
    {
        int currentPlayer = turns.get(currentTurn-1);
        boolean ret = true;

        for(int i = currentTurn; i<turns.size(); i++)
            if(turns.get(i) == currentPlayer)
                ret = false;

        return ret;
    }

    public void skipSecondPlayerTurn()
    {
        int currentPlayer = turns.get(currentTurn-1);

        for(int i = currentTurn; i<turns.size(); i++)
            if(turns.get(i) == currentPlayer)
                turns.remove(i);
    }

    private boolean isFirstNextCall()
    {
        if(!nextCalled)
        {
            nextCalled = true;
            return true;
        }
        else
            return false;
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
