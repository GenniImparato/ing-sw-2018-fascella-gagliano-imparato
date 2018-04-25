package it.polimi.se2018.game;

import javafx.print.PageLayout;

//singleton class
public class Game
{
    private PlayerTurnIterator      playersIterator;
    private Player                  currentPlayer;

    private PublicObjectiveCard[]   publicCards;
    private ToolCard[]              toolCards;
    private DiceBag                 diceBag;
    private DraftPool               draftPool;
    private RoundTrack              roundTrack;

    private int                     currentRound = 0;

    public Game()
    {
        playersIterator = new PlayerTurnIterator();

        addNewPlayer("Renatino");
        addNewPlayer("Kwx");
        addNewPlayer("Ges");

        diceBag = new DiceBag();
        draftPool = new DraftPool(diceBag);
        roundTrack = new RoundTrack(draftPool);
    }

    //add a new player to the game if the number of player is not at maximum
    //return true if the player is added, false if it's not
    public boolean addNewPlayer(String nickname)
    {
        try
        {
            playersIterator.addNewPlayer(nickname);
            return true;
        }
        catch(TooManyPlayersException e)
        {
            return false;
        }
    }

    public Player getCurrentPlayer()
    {
        return currentPlayer;
    }

    //return the number of players in game
    public int getPlayerNum()
    {
        return playersIterator.getPlayerNum();
    }

    //return an int from 0 to 9 that representing the current turn
    public int getCurrentRoundNum()
    {
        return currentRound;
    }

    //draw the correct number of dice from DiceBag to the DraftPool
    public void beginRound()
    {
        draftPool.draw(getPlayerNum()*2 +1);
    }

    //add the remaining dice in the DraftPool to the RoundTrack
    public void endRound()
    {
        roundTrack.addLastDice(currentRound);
        currentRound++;
        beginRound();
    }

    public void beginPlayerTurn()
    {
        currentPlayer = playersIterator.next();
    }

    public void endPlayerTurn()
    {
        if(playersIterator.isLastTurn())
            endRound();
    }
}
