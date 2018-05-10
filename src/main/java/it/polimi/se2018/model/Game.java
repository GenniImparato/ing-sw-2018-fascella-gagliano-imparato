package it.polimi.se2018.model;

import it.polimi.se2018.events.Message;
import it.polimi.se2018.events.messages.DraftedDieMessage;
import it.polimi.se2018.events.messages.GameStartedMessage;
import it.polimi.se2018.events.messages.PlayerAddedMessage;
import it.polimi.se2018.observer.Observable;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//singleton class
public class Game extends Observable <Message> implements Serializable
{
    private PlayerTurnIterator                  playersIterator;
    private Player                              currentPlayer;

    private List<PublicObjectiveCard>           publicCards;
    private List<ToolCard>                      toolCards;
    private DiceBag                             diceBag;
    private DraftPool                           draftPool;
    private RoundTrack                          roundTrack;

    private Die                                 lastDraftedDie;

    private int                                 currentRound = 0;
    private static final int                    TOTAL_ROUNDS = 10;

    private boolean                             gameStarted = false;

    public Game()
    {
        playersIterator = new PlayerTurnIterator();

        diceBag = new DiceBag();
        draftPool = new DraftPool(diceBag);
        roundTrack = new RoundTrack(draftPool);

        publicCards = PublicObjectiveCard.getRandomCards(3);
        toolCards = ToolCard.getRandomCards(3);
    }

    //copy constructor
    public Game(Game game)
    {
        this.playersIterator = new PlayerTurnIterator(game.playersIterator);
        if(game.currentPlayer !=null)
            this.currentPlayer = new Player(game.currentPlayer);

        this.diceBag = new DiceBag(game.diceBag);
        this.draftPool = new DraftPool(game.draftPool, this.diceBag);
        this.roundTrack = new RoundTrack(game.roundTrack, this.draftPool);

        this.publicCards = new ArrayList<>();
        for(PublicObjectiveCard card : game.publicCards)
        {
            try
            {
                this.publicCards.add(card.getClass().newInstance());
            }
            catch(InstantiationException e)
            {
            }
            catch(IllegalAccessException e)
            {
            }
        }

        this.toolCards = new ArrayList<>();
        for(ToolCard card : game.toolCards)
        {
            try
            {
                this.toolCards.add(card.getClass().newInstance());
            }
            catch(InstantiationException e)
            {
            }
            catch(IllegalAccessException e)
            {
            }
        }

        this.lastDraftedDie = game.lastDraftedDie;
        this.currentRound = game.currentRound;
        this.gameStarted = game.gameStarted;
    }

    //add a new player to the model if the number of player is not at maximum
    //return true if the player is added, false if it's not
    public void addNewPlayer(String nickname) throws  CannotAddPlayerException
    {
        try
        {
            playersIterator.addNewPlayer(nickname);
            notify(new PlayerAddedMessage(this, playersIterator.getAllPlayers().get(getPlayerNum()-1))); //notify the view that a new player has been added
        }
        catch (CannotAddPlayerException e)
        {
            throw e;
        }

    }

    public void startGame()
    {
        gameStarted = true;
        beginRound();
        notify(new GameStartedMessage(new Game((this))));
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

    public List<Player> getAllPlayers ()
    {
        return playersIterator.getAllPlayers();
    }

    public List<PublicObjectiveCard> getAllPublicObjectiveCards ()
    {
        return publicCards;
    }

    public List<ToolCard> getAllToolCards ()
    {
        return toolCards;
    }

    //draw the correct number of dice from DiceBag to the DraftPool
    private void beginRound()
    {
        draftPool.draw(getPlayerNum()*2 +1);
        beginPlayerTurn();
    }

    //add the remaining dice in the DraftPool to the RoundTrack
    private void endRound()
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
        List<Player> allPlayers = playersIterator.getAllPlayers();

        for(Player player : allPlayers)
        {
            player.incrementPrivateScore();

            for(PublicObjectiveCard card : publicCards)
                player.incrementScore(card);
        }

    }


}
