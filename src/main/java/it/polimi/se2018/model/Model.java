package it.polimi.se2018.model;

import it.polimi.se2018.controller.PlayerTurnIterator;
import it.polimi.se2018.events.Message;
import it.polimi.se2018.events.messages.*;
import it.polimi.se2018.model.publicobjectivecards.PublicObjectiveCard;
import it.polimi.se2018.model.toolcards.*;
import it.polimi.se2018.utils.Observable;


import java.util.ArrayList;
import java.util.List;

//singleton class
public class Model extends Observable <Message>
{
    private List<Player>                        players;
    private static final int                    MAX_PLAYERS_NUM = 4;

    /*private boolean                             cardUsed = false;
    private boolean                             dieAdded = false;
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

    private boolean                             gameStarted = false;*/

    public Model()
    {
        players = new ArrayList<>();

        /*diceBag = new DiceBag();
        draftPool = new DraftPool(diceBag);
        roundTrack = new RoundTrack(draftPool);

        publicCards = PublicObjectiveCard.getRandomCards(3);
        toolCards = ToolCard.getRandomCards(1);
        toolCards.add(new GlazingHammer());
        toolCards.add(new EglomiseBrush());
        toolCards.add(new CopperFoilBurnisher());
        toolCards.add(new Lathekin());
        toolCards.add(new LensCutter());

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
        roundTrack.addLastDice(5);*/
    }

    //copy constructor
    public Model(Model model)
    {
        /*this.cardUsed = model.cardUsed;
        this.dieAdded = model.dieAdded;

<<<<<<< Updated upstream:src/main/java/it/polimi/se2018/model/Game.java
        this.diceBag = new DiceBag(game.diceBag);
        this.draftPool = new DraftPool(game.draftPool, this.diceBag);
        this.roundTrack = new RoundTrack(game.roundTrack, this.draftPool);
=======
        this.diceBag = new DiceBag(model.diceBag);
        this.draftPool = new DraftPool(model.draftPool, this.diceBag);
        this.roundTrack = new RoundTrack(model.roundTrack, this.draftPool);
>>>>>>> Stashed changes:src/main/java/it/polimi/se2018/model/Model.java

        this.publicCards = new ArrayList<>();
        for(PublicObjectiveCard card : model.publicCards)
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
        for(ToolCard card : model.toolCards)
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

        if(model.selectedDie !=null)
            this.selectedDie = new Die(model.selectedDie);

        if(model.lastDraftedDie !=null)
            this.lastDraftedDie = new Die(model.lastDraftedDie);

        this.currentToolCard = model.currentToolCard;

        this.currentRound = model.currentRound;
        this.gameStarted = model.gameStarted;*/
    }

    public void addNewPlayer(String nickname) throws  CannotAddPlayerException
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

        notify(new AddedPlayerMessage(this, players.get(getPlayerNum()-1)));
    }


    //return the number of players in model
    public int getPlayerNum()
    {
        return players.size();
    }

    public List<Player> getPlayers()
    {
        return players;
    }

    /*public Player getCurrentPlayer()
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

        if(currentRound+1 < Model.TOTAL_ROUNDS)
        {
            currentRound++;
            beginRound();
        }
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
    }*/

}
