package it.polimi.se2018.model;

import it.polimi.se2018.events.Message;
import it.polimi.se2018.events.messages.DraftedDieMessage;
import it.polimi.se2018.observer.Observable;


import java.util.ArrayList;

//singleton class
public class Game extends Observable <Message>
{
    private PlayerTurnIterator                  playersIterator;
    private Player                              currentPlayer;

    private ArrayList<PublicObjectiveCard>      publicCards;
    private ArrayList<ToolCard>                 toolCards;
    private DiceBag                             diceBag;
    private DraftPool                           draftPool;
    private RoundTrack                          roundTrack;

    private Die                                 lastDraftedDie;

    private int                                 currentRound = 0;
    private static final int                    TOTAL_ROUNDS = 10;

    public Game() {
        playersIterator = new PlayerTurnIterator();

        try
        {
            addNewPlayer("Renatino");
            addNewPlayer("Kwx");
            addNewPlayer("Ges");
        }
        catch (CannotAddPlayerException e)
        { }

        diceBag = new DiceBag();
        draftPool = new DraftPool(diceBag);
        roundTrack = new RoundTrack(draftPool);

        publicCards = PublicObjectiveCard.getRandomCards(3);
        toolCards = ToolCard.getRandomCards(3);
    }

    //add a new player to the model if the number of player is not at maximum
    //return true if the player is added, false if it's not
    public void addNewPlayer(String nickname) throws  CannotAddPlayerException
    {
        try
        {
            playersIterator.addNewPlayer(nickname);
        }
        catch(CannotAddPlayerException e)
        {
            throw e;
        }
    }

    public Player getCurrentPlayer()
    {
        return currentPlayer;
    }

    public DraftPool getDraftPool()
    {
        return draftPool;
    }

    public RoundTrack getRoundTrack()
    {
        return roundTrack;
    }

    public DiceBag getDiceBag()
    {
        return diceBag;
    }

    //return the number of players in model
    public int getPlayerNum()
    {
        return playersIterator.getPlayerNum();
    }

    //return an int from 0 to 9 that representing the current turn
    public int getCurrentRoundNum()
    {
        return currentRound;
    }

    public Die getLastDraftedDie()
    {
        return lastDraftedDie;
    }

    public ArrayList<Player> getAllPlayers ()
    {
        return playersIterator.getAllPlayers();
    }

    //draw the correct number of dice from DiceBag to the DraftPool
    public void beginRound()
    {
        draftPool.draw(getPlayerNum()*2 +1);
        beginPlayerTurn();
    }

    //add the remaining dice in the DraftPool to the RoundTrack
    public void endRound()
    {
        roundTrack.addLastDice(currentRound);

        if(currentRound+1 < Game.TOTAL_ROUNDS)
        {
            currentRound++;
            beginRound();
        }
        else
            updateAllPlayersScores();
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

    //draft the num die from the draft pool
    //notify the view
    public void draftDie(int num)
    {
        lastDraftedDie = draftPool.draftDie(num);
        notify(new DraftedDieMessage(this, lastDraftedDie));
    }

    //update all the scores
    private void updateAllPlayersScores()
    {
        ArrayList<Player> allPlayers = playersIterator.getAllPlayers();

        for(Player player : allPlayers)
        {
            player.incrementPrivateScore();

            for(PublicObjectiveCard card : publicCards)
                player.incrementScore(card);
        }

    }


}
