package it.polimi.se2018.model;

import it.polimi.se2018.events.Message;
import it.polimi.se2018.events.messages.*;
import it.polimi.se2018.model.gameactions.GameAction;
import it.polimi.se2018.model.publicobjectivecards.PublicObjectiveCard;
import it.polimi.se2018.model.toolcards.*;
import it.polimi.se2018.observer.Observable;


import java.util.ArrayList;
import java.util.List;

//singleton class
public class Game extends Observable <Message>
{
    private PlayerTurnIterator                  playersIterator;
    private Player                              currentPlayer;

    private List<PublicObjectiveCard>           publicCards;
    private List<ToolCard>                      toolCards;
    private int                                 currentToolCard = -1;  //null means no tool card is being used

    private DiceBag                             diceBag;
    private DraftPool                           draftPool;
    private RoundTrack                          roundTrack;

    private Die                                 lastDraftedDie;
    private Die                                 selectedDie;

    private int                                 currentRound = 0;
    private static final int                    TOTAL_ROUNDS = 10;

    private boolean                             gameStarted = false;

    private List<GameAction>                    actionChronology;

    public Game()
    {
        playersIterator = new PlayerTurnIterator();

        diceBag = new DiceBag();
        draftPool = new DraftPool(diceBag);
        roundTrack = new RoundTrack(draftPool);

        publicCards = PublicObjectiveCard.getRandomCards(3);
        toolCards = ToolCard.getRandomCards(1);
        toolCards.add(new GlazingHammer());
        toolCards.add(new EglomiseBrush());
        toolCards.add(new CopperFoilBurnisher());
        toolCards.add(new Lathekin());
        toolCards.add(new LensCutter());

        actionChronology = new ArrayList<>();


        draftPool.draw(1);
        roundTrack.addLastDice(0);
        draftPool.draw(3);
        roundTrack.addLastDice(1);
        draftPool.draw(2);
        roundTrack.addLastDice(2);
        draftPool.draw(5);
        roundTrack.addLastDice(3);
        draftPool.draw(1);
        roundTrack.addLastDice(4);
        draftPool.draw(2);
        roundTrack.addLastDice(5);
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

        if(game.selectedDie !=null)
            this.selectedDie = new Die(game.selectedDie);

        if(game.lastDraftedDie !=null)
            this.lastDraftedDie = new Die(game.lastDraftedDie);

        this.currentToolCard = game.currentToolCard;

        this.currentRound = game.currentRound;
        this.gameStarted = game.gameStarted;

        this.actionChronology = new ArrayList<>(game.actionChronology);
    }

    public void executeAction(GameAction action)
    {
        action.execute(this);
        if(action.hasBeenExecuted())
            actionChronology.add(action);
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

    public Die getSelectedDie(){ return selectedDie;}

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

    public boolean isCurrentPlayerSecondTurn()
    {
        return playersIterator.isCurrentPlayerSecondTurn();
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
        catch(CannotAddPlayerException e)
        {
            throw e;
        }
    }

    public void startGame()
    {
        gameStarted = true;
        beginRound();
        notify(new GameStartedMessage(this));
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
        notify(new DraftedDieMessage(this, lastDraftedDie, currentPlayer));
    }

    public void returnLastDraftedDie()
    {
        draftPool.addDie(lastDraftedDie);
        Die die = lastDraftedDie;
        lastDraftedDie = null;
        notify(new ReturnedDieMessage(this, die, currentPlayer));
    }

    public boolean selectDieFromCurrentPlayerBoard(int row, int column)
    {
        selectedDie = currentPlayer.getBoard().getDie(row, column);
        if(selectedDie != null)
        {
            notify(new SelectedDieMessage(this, selectedDie, currentPlayer));
            return true;
        }
        else
            return false;
    }

    public boolean selectDieFromRoundTrack(int round, int dieNum)
    {
        if(roundTrack.getDiceAtRound(round).size()<=dieNum)
            return false;
        else
        {
            selectedDie = roundTrack.getDiceAtRound(round).get(dieNum);
            notify(new SelectedDieMessage(this, selectedDie, currentPlayer));
            return true;
        }
    }

    public void addDraftedDieToBoard(int row, int col) throws CannotPlaceDieException
    {
        try
        {
            getCurrentPlayer().getBoard().addDie(lastDraftedDie, row, col);
            notify(new AddedDieMessage(this, currentPlayer, row, col, lastDraftedDie));
        }
        catch (CannotPlaceDieException e)
        {
            throw e;
        }
    }

    public void startUsingToolCard(int cardNum, ToolCardVisitor visitor)
    {
        currentToolCard =  cardNum;
        toolCards.get(currentToolCard).acceptVisitor(visitor);
        notify(new StartedUsingToolCardMessage(this, toolCards.get(currentToolCard)));
    }

    public void executeCurrentToolCardAction(int param1, int param2) throws CannotExecuteToolCardActionException
    {
        try
        {
            String message;
            message = toolCards.get(currentToolCard).action(this, param1, param2);
            notify(new ToolCardActionExecutedMessage(this, message));
        }
        catch (CannotExecuteToolCardActionException e)
        {
            throw e;
        }
    }

    //update all the scores
    public void updateAllPlayersScores()
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
