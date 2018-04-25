package it.polimi.se2018.game;

import it.polimi.se2018.game.publicobjectivecards.ColorDiagonalsCard;
import it.polimi.se2018.game.publicobjectivecards.ColumnColorVarietyCard;
import it.polimi.se2018.game.publicobjectivecards.MediumShadesCard;
import javafx.print.PageLayout;

import java.util.ArrayList;

//singleton class
public class Game
{
    private PlayerTurnIterator                  playersIterator;
    private Player                              currentPlayer;

    private ArrayList<PublicObjectiveCard>      publicCards;
    private ArrayList<ToolCard>                 toolCards;
    private DiceBag                             diceBag;
    private DraftPool                           draftPool;
    private RoundTrack                          roundTrack;

    private int                                 currentRound = 0;
    private static final int                    TOTAL_ROUNDS = 10;

    public Game()
    {
        playersIterator = new PlayerTurnIterator();

        addNewPlayer("Renatino");
        addNewPlayer("Kwx");
        addNewPlayer("Ges");

        diceBag = new DiceBag();
        draftPool = new DraftPool(diceBag);
        roundTrack = new RoundTrack(draftPool);

        publicCards = PublicObjectiveCard.getRandomCards(3);
        toolCards = ToolCard.getRandomCards(3);
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

    //update all the scores
    private void updateAllPlayersScores()
    {
        ArrayList<Player> allPlayers = playersIterator.getAllPlyers();

        for(Player player : allPlayers)
        {
            player.incrementPrivateScore();

            for(PublicObjectiveCard card : publicCards)
                player.incrementScore(card);
        }

    }
}
