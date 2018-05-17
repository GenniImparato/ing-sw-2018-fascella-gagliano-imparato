package it.polimi.se2018.model;

import it.polimi.se2018.model.exceptions.NoElementException;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.mvc_comunication.messages.*;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;
import it.polimi.se2018.model.publicobjectivecards.PublicObjectiveCard;
import it.polimi.se2018.model.toolcards.*;
import it.polimi.se2018.utils.Observable;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

//singleton class
public class Model extends Observable <Message>
{
    private List<Player>                        players;
    private static final int                    MAX_PLAYERS_NUM = 4;
    private static final int                    MIN_PLAYERS_NUM = 1;
    private Player                              currentPlayer;

    private DiceBag                             diceBag;
    private DraftPool                           draftPool;
    private RoundTrack                          roundTrack;

    private List<PublicObjectiveCard>           publicCards;
    private List<ToolCard>                      toolCards;
    private ToolCard                            currentToolCard;

    private boolean                             gameStarted = false;

    private Die                                 draftedDie;
    private Die                                 selectedDie;

    private List<Board>                         schemeCards;

    public Model()
    {
        //init players array
        players = new ArrayList<>();

        //init dice bag, draft pool and round track
        diceBag = new DiceBag();
        draftPool = new DraftPool(diceBag);
        roundTrack = new RoundTrack(draftPool);

        //init cards
        publicCards = PublicObjectiveCard.getRandomCards(3);
        toolCards = ToolCard.getRandomCards(1);
        toolCards.add(new GrozingPliers());
        toolCards.add(new EglomiseBrush());
        toolCards.add(new CopperFoilBurnisher());
        toolCards.add(new Lathekin());
        toolCards.add(new LensCutter());
    }

    //copy constructor
    public Model(Model model)
    {
        this.players = new ArrayList<>();
        //create a copy of every player of the source model to copy
        for(Player player : model.getPlayers())
            players.add(new Player(player));

        //creates a copy current player only if it's not nulòl in the source model
        if(model.currentPlayer !=null)
            this.currentPlayer = new Player(model.currentPlayer);

        this.diceBag = new DiceBag(model.diceBag);
        this.draftPool = new DraftPool(model.draftPool, this.diceBag);
        this.roundTrack = new RoundTrack(model.roundTrack, this.draftPool);

        //copy public cards
        this.publicCards = new ArrayList<>();
        for(PublicObjectiveCard card : model.publicCards)
        {
            try
            {
                //create a copy of the runtime type of every public card
                this.publicCards.add(card.getClass().newInstance());
            }
            catch(InstantiationException | IllegalAccessException e)
            {
            }
        }

        //copy tool cards
        this.toolCards = new ArrayList<>();
        for(ToolCard card : model.toolCards)
        {
            try
            {
                //create a copy of the runtime type of every tool card
                this.toolCards.add(card.getClass().newInstance());
            }
            catch(InstantiationException | IllegalAccessException e)
            {
            }
        }

        //copy the currentToolCard if it's not null
        if(model.currentToolCard != null)
        {
            try
            {
                this.currentToolCard = model.currentToolCard.getClass().newInstance();
            }
            catch(InstantiationException | IllegalAccessException e)
            {
            }
        }

        //copy the lastDrafted if it's not null
        if(model.draftedDie != null)
            this.draftedDie = new Die(model.draftedDie);

        //copy the lastDrafted if it's not null
        if(model.selectedDie != null)
            this.selectedDie = new Die(model.selectedDie);
/*
        if(model.selectedDie !=null)
            this.selectedDie = new Die(model.selectedDie);

        if(model.draftedDie !=null)
            this.draftedDie = new Die(model.draftedDie);

        this.currentToolCard = model.currentToolCard;

        this.currentRound = model.currentRound;
        this.gameStarted = model.gameStarted;*/
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

    public List<ToolCard> getToolCards()
    {
        return toolCards;
    }

    public List<PublicObjectiveCard> getPublicObjectiveCards()
    {
        return publicCards;
    }

    public boolean isGameStarded()
    {
        return gameStarted;
    }

    public Die getDraftedDie()
    {
        return draftedDie;
    }

    public Die getSelectedDie() { return selectedDie;}

    public void setCurrentPlayer(Player player)
    {
        this.currentPlayer = player;
    }

    public Player getCurrentPlayer()
    {
        return currentPlayer;
    }

    public Player findPlayer(String nickname) throws NoElementException
    {
        for(Player player : players)
            if(player.getNickname().equals(nickname))
                return player;

        throw new NoElementException();
    }

    public void setSchemeCards(List<Board> schemeCards)
    {
        this.schemeCards = schemeCards;
    }

    public void setPlayerSchemeCard(Player player, int choice) throws ChangeModelStateException
    {
        if(choice != 1 && choice != 2)
            throw new ChangeModelStateException("Not valid choice");

        if(choice == 1)
            player.setBoard(schemeCards.get(player.getFirstSchemeCardIndex()));
        else
            player.setBoard(schemeCards.get(player.getSecondSchemeCardIndex()));
    }

    public void setCurrentToolCard(int cardNum) throws ChangeModelStateException
    {
        if(cardNum < 0  || cardNum >= toolCards.size())
            throw new ChangeModelStateException("Invalid tool card index!");
        else
        {
            currentToolCard = toolCards.get(cardNum);
            notify(new UsingToolCardMessage(this, currentToolCard, currentPlayer));
        }
    }

    public ToolCard getCurrentToolCard()
    {
        return currentToolCard;
    }

    public void addNewPlayer(String nickname) throws ChangeModelStateException
    {
        if(nickname.length() == 0)
            throw new ChangeModelStateException("Nickname cannot be empty!");

        for(Player player : players)
        {
            if(player.getNickname().equals(nickname))                       //check if another player has the same nickname
                throw new ChangeModelStateException("There is another player with this nickname!");
        }

        if(getPlayerNum() < MAX_PLAYERS_NUM)                                //check if there are already all players
            players.add(new Player(nickname));
        else
            throw new ChangeModelStateException("There are already " + MAX_PLAYERS_NUM + " players!");

        notify(new AddedPlayerMessage(this, players.get(getPlayerNum()-1)));
    }

    public void startGame() throws ChangeModelStateException
    {
        if(getPlayerNum() >= MIN_PLAYERS_NUM)
        {
            gameStarted = true;
            notify(new StartedGameMessage(this));
        }
        else
            throw new ChangeModelStateException("Not enough players to start the game!");
    }

    public void selectRandomSchemeCardsForPlayers() throws ChangeModelStateException
    {
        if(schemeCards.size() < getPlayerNum()*2)
            throw new ChangeModelStateException("Not enough scheme cards loaded for all the players!");

        //generates an array with indices from 0 to the scheme cards loaded number
        List<Integer> schemeCardsIndices = new ArrayList<>();
        for(int i=0; i<schemeCards.size(); i++)
            schemeCardsIndices.add(i);


        for(Player player : players)
        {
            int firstIndex;
            int secondIndex;

            //get 2 random indices
            //every time and index is randomly generated it's removed from the list
            //this ensures that every random index is different from the others
            firstIndex = schemeCardsIndices.remove(new Random().nextInt(schemeCardsIndices.size()));
            secondIndex = schemeCardsIndices.remove(new Random().nextInt(schemeCardsIndices.size()));

            player.setFirstSchemeCardIndex(firstIndex);
            player.setSecondSchemeCardIndex(secondIndex);

            //notify the view that 2 random scheme cards have been selected
            //so the view(user) can choose on of them
            notify(new SelectedPlayerSchemeCardsMessage(this, player, schemeCards.get(firstIndex), schemeCards.get(secondIndex)));
        }
    }

    public void draftDie(int dieNum) throws ChangeModelStateException
    {
        draftedDie = draftPool.draftDie(dieNum);
        notify(new DraftedDieMessage(this, draftedDie, currentPlayer));
    }

    public void drawFromDiceBag()
    {
        draftPool.draw(getPlayerNum()*2 + 1);
    }

    public void addDraftedDieToBoard(Player player, int row, int column) throws ChangeModelStateException
    {
        if(draftedDie == null)
            throw new ChangeModelStateException("No drafted die!");

        player.getBoard().addDie(draftedDie, row, column);
        notify(new AddedDieMessage(this, player, draftedDie, row, column));
    }

    public void selectDieFromBoard(Player player, int row, int column) throws ChangeModelStateException
    {
        selectedDie = player.getBoard().getDie(row, column);

        if(selectedDie == null)
            throw new ChangeModelStateException("The selected cell doesn't contain any die!");
        else
            notify(new SelectedDieMessage(this, selectedDie, currentPlayer));
    }

    public void incrementDraftedDie() throws ChangeModelStateException
    {
        if(draftedDie == null)
            throw new ChangeModelStateException("No drafted die!");

        draftedDie.incrementValue();
        notify(new ChangedDraftedDieMessage(this, draftedDie, currentPlayer));
    }

    public void decrementDraftedDie() throws ChangeModelStateException
    {
        if(draftedDie == null)
            throw new ChangeModelStateException("No drafted die!");

        draftedDie.decrementValue();
        notify(new ChangedDraftedDieMessage(this, draftedDie, currentPlayer));
    }

    public void moveSelectedDie(Player player, int row, int column, boolean ignoreValueRestriction, boolean ignoreColorRestriction) throws ChangeModelStateException
    {
        player.getBoard().moveDie(selectedDie, row, column, ignoreValueRestriction, ignoreColorRestriction);
        notify(new MovedDieMessage(this, selectedDie, currentPlayer, row, column));
    }


    /*public Player getCurrentPlayer()
    {
        return currentPlayer;
    }



    //return an int from 0 to 9 that representing the current turn
    public int getCurrentRoundNum()
    {
        return currentRound;
    }

    public Die getDraftedDie()
    {
        return draftedDie;
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
        draftedDie = draftPool.draftDie(num);
        notify(new DraftedDieMessage(this, draftedDie, currentPlayer));
    }

    public void returnLastDraftedDie()
    {
        draftPool.addDie(draftedDie);
        Die die = draftedDie;
        draftedDie = null;
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
            getCurrentPlayer().getBoard().addDie(draftedDie, row, col);
            notify(new AddedDieMessage(this, currentPlayer, row, col, draftedDie));
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
        notify(new UsingToolCardMessage(this, toolCards.get(currentToolCard)));
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
