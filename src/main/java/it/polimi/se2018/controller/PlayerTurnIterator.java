package it.polimi.se2018.controller;

import it.polimi.se2018.model.Player;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class is used to manage the player turns.
 */
public class PlayerTurnIterator implements Iterator<Player>
{
    private Controller                  controller;
    private int                         currentTurn = 0;
    private int                         firstPlayer = 0;
    private boolean                     nextCalled = false;

    private List<Integer>               turns ;                  //used to store the order of player's turns

    /**
     * Constructor
     * @param controller controller
     */
    public PlayerTurnIterator(Controller controller)
    {
        this.controller = controller;
        turns = new ArrayList<>();
    }

    /**
     * Returns the instance of the player that takes the turn next
     * @return instance of the player that takes the turn next
     */
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

        while(!controller.getModel().getPlayers().get(turns.get(currentTurn)).isActive())
            currentTurn++;

        if(hasNext())
            ret = controller.getModel().getPlayers().get(turns.get(currentTurn));

        incrementTurn();

        return ret;
    }

    /**
     * Returns true if there are still players on the queue
     * @return true if there are still player on the queue, false otherwise
     */
    public boolean hasNext()
    {
        return currentTurn < turns.size();
    }

    /**
     * Returns the number of players in the model
     * @return number of the players in the model
     */
    private int  getPlayerNum()
    {
        return controller.getModel().getPlayerNum();
    }

    /**
     * Returns true if this is the last turn of the round
     * @return true if this is the last turn of the round, false otherwise
     */
    public boolean isLastTurn()
    {
        return !hasNext();
    }

    /**
     * Tells if it's the second turn of the current player
     * @return true if it's the second turn of the current player, false otherwise
     */
    public boolean isCurrentPlayerSecondTurn()
    {
        int currentPlayer = turns.get(currentTurn-1);
        boolean ret = true;

        for(int i = currentTurn; i<turns.size(); i++)
            if(turns.get(i) == currentPlayer)
                ret = false;

        return ret;
    }

    /**
     * Skips the current player second turn
     */
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

    /**
     * Refreshes the internal list with the correct queue of the players turns
     */
    private void refreshPlayersTurns()      //it's called at the beginning of the round
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

    /**
     * Change the current turn
     */
    private void incrementTurn()
    {
        currentTurn++;
    }

    /**
     * Change the first player of the round
     */
    private void incrementFirstPlayer()
    {
        if(firstPlayer == getPlayerNum()-1)
            firstPlayer=0;
        else
            firstPlayer++;
    }
}
