package it.polimi.se2018.model;

import it.polimi.se2018.model.exceptions.ActionNotPossibleException;
import it.polimi.se2018.model.exceptions.NoElementException;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.mvc_comunication.messages.*;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;
import it.polimi.se2018.model.publicobjectivecards.PublicObjectiveCard;
import it.polimi.se2018.utils.Color;
import it.polimi.se2018.utils.Observable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This is the Model of the Game.
 * It contains the state of the game.
 * It's observable by the View, and it can be modified by the Controller.
 * @author Matteo Gagliano
 * @author Carmelo Fascella
 * @author Generoso Imparato
 */
public class Model extends Observable <Message> implements Serializable
{
    public static final int                     DRAFTET_DIE     = 0;
    public static final int                     SELECTED_DIE    = 1;
    public static final int                     CHOOSEN_DIE     = 2;

    private List<Player>                        players;
    private static final int                    MAX_PLAYERS_NUM = 4;
    private static final int                    MIN_PLAYERS_NUM = 1;
    private Player                              currentPlayer;

    private DiceBag                             diceBag;
    private DraftPool                           draftPool;
    private RoundTrack                          roundTrack;

    private List<PublicObjectiveCard>           publicCards;
    private List<Card>                          toolCards;
    private Card                                currentToolCard;

    private boolean                             gameStarted = false;

    private Die                                 draftedDie;
    private Die                                 selectedDie;
    private Die                                 chosenDie;

    private List<Board>                         schemeCards;

    private int                                 currentRound;

    /**
     * Constructor that instantiates the game field.
     * It creates the ArrayList of the players initially empty, the Dicebag, the Draftpool, the Roundtrack,
     * and three random PublicObjectiveCard
     */
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
    }

    /**
     * It's the copy constructor
     * @param model that will be copied
     */
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
        if(model.toolCards != null)
        {
            this.toolCards = new ArrayList<>();
            for (Card card : model.toolCards)
                this.toolCards.add(new Card(card));
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
    }

    /**
     * Getter of the number of the players
     * @return the numbers of the players in the model
     */
    public int getPlayerNum()
    {
        return players.size();
    }

    /**
     * Getter of the List of the Player
     * @return all the players
     */
    public List<Player> getPlayers()
    {
        return players;
    }

    /**
     * Getter of the DraftPool
     * @return the DraftPool of the Game
     */
    public DraftPool getDraftPool()
    {
        return draftPool;
    }

    /**
     * Getter of the RoundTrack
     * @return the RoundTrack of the Game
     */
    public RoundTrack getRoundTrack()
    {
        return roundTrack;
    }

    /**
     * Getter of the DiceBag
     * @return the DiceBag of the Game
     */
    public DiceBag getDiceBag()
    {
        return diceBag;
    }

    /**
     * Getter of the ToolCards
     * @return the list of the three ToolCard of the Game
     */
    public List<Card> getToolCards()
    {
        return toolCards;
    }

    /**
     * Getter of the PublicObjectiveCard
     * @return the list of the three PublicObjectiveCards of the Game
     */
    public List<PublicObjectiveCard> getPublicObjectiveCards()
    {
        return publicCards;
    }

    /**
     * Method that says if the game is started or not
     * @return a boolean that is true if the game is started, false if not.
     */
    public boolean isGameStarded()
    {
        return gameStarted;
    }

    /**
     * Getter of the last drafted Die
     * @return the last drafted Die or null if there is not a drafted Die
     */
    public Die getDraftedDie()
    {
        return draftedDie;
    }

    /**
     * Getter of the selected Die
     * @return the last selected Die
     */
    public Die getSelectedDie() { return selectedDie;}

    /**
     * Getter of the chosed Die
     * @return the Die just chosen
     */
    public Die getChosenDie() { return chosenDie;}

    /**
     * Getter of the Die, that could be a selected, a drafted or a chosen Die
     * @param dieType it's the type of the Die that you want to get. 0 = Drafted, 1 = Selected, 2 = Chosen
     * @return the chosen Die
     */
    public Die getDie(int dieType)
    {
        if(dieType == DRAFTET_DIE)
            return getDraftedDie();
        else if(dieType == SELECTED_DIE)
            return getSelectedDie();
        else if(dieType == CHOOSEN_DIE)
            return getChosenDie();

        return null;
    }

    /**
     * Getter of the current Round
     * @return the current Round
     */
    public int getCurrentRound()
    {
        return currentRound;
    }

    /**
     * Setter of the current Player
     * @param player that will be set as current one
     */
    public void setCurrentPlayer(Player player)
    {
        this.currentPlayer = player;
        notify(new BegunTurnMessage(this, currentPlayer));
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

    public Player findPlayer(Color color) throws NoElementException
    {
        for(Player player : players)
            if(player.getColor() == color)
                return player;

        throw new NoElementException();
    }

    public void setSchemeCards(List<Board> schemeCards)
    {
        this.schemeCards = schemeCards;
    }

    public void setToolCards(List<Card> toolCards)
    {
        this.toolCards = toolCards;
    }

    public void setPlayerSchemeCard(Player player, int choice) throws ChangeModelStateException
    {
        if(choice < 0 || choice > 3)
            throw new ChangeModelStateException("Not valid choice!");

        player.setBoard(schemeCards.get(player.getSchemeCardIndex(choice)));
        notify(new ChosenSchemeCardMessage(this, player, player.getBoard()));
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

    public Card getCurrentToolCard()
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

        if(getPlayerNum() < MAX_PLAYERS_NUM)   //check if there are already all players
        {
            //assing to the new player the first color available
            Color playerColor = Color.RED;

            for(Color color : Color.values())
            {
                try
                {
                    findPlayer(color);
                }
                catch(NoElementException e)
                {
                    playerColor = color;
                    break;
                }
            }

            //adds the player
            players.add(new Player(nickname, playerColor));
        }

        else
            throw new ChangeModelStateException("There are already " + MAX_PLAYERS_NUM + " players!");

        notify(new AddedPlayerMessage(this, players.get(getPlayerNum()-1)));
    }

    public void setPlayerReady(Player player, boolean ready)
    {
        player.setReady(ready);
        notify(new PlayerReadyMessage(this, player, ready));
    }

    public void returnDraftedDie() throws ChangeModelStateException
    {
        if(draftedDie == null)
            throw new ChangeModelStateException("No drafted die!");

        Die die = draftedDie;
        draftPool.addDie(die);
        draftedDie = null;

        notify(new ReturnedDieMessage(this, die, currentPlayer));
    }

    public boolean isEveryPlayerReady()
    {
        for(Player player : players)
            if(!player.isReady())
                return false;

        return true;
    }
    public void removePlayer(String nickname) throws ChangeModelStateException
    {
        try
        {
            Player removedPlayer = findPlayer(nickname);
            players.remove(removedPlayer);
            notify(new RemovedPlayerMessage(this, removedPlayer));
        }
        catch (NoElementException e)
        {
            throw new ChangeModelStateException("Cannot remove player:" + nickname);
        }
    }

    public boolean hasEveryPlayerChosenSchemeCard()
    {
        for(Player player : players)
        {
            if(!player.hasChoosenSchemeCard())
                return false;
        }

        return true;
    }

    public void startGame() throws ChangeModelStateException
    {
        if(getPlayerNum() < MIN_PLAYERS_NUM)
            throw new ChangeModelStateException("Not enough players to start the game!");

        if(!hasEveryPlayerChosenSchemeCard())
            throw new ChangeModelStateException("Not every player has chosen a scheme card!");


        gameStarted = true;
        currentRound = 0;
        notify(new StartedGameMessage(this));

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
            Board selectedSchemeCards[] = new Board[4];

            //get 4 random indices
            //every time and index is randomly generated it's removed from the list
            //this ensures that every random index is different from the others
            for(int i=0; i<4; i++)
            {
                int randomIndex = schemeCardsIndices.remove(new Random().nextInt(schemeCardsIndices.size()));
                player.setSchemeCardIndex(i, randomIndex);
                selectedSchemeCards[i] = schemeCards.get(randomIndex);
            }


            //notify the view that 4 random scheme cards have been selected for a player
            //so the view(user) can choose on of them
            notify(new SelectedPlayerSchemeCardsMessage(this, player, selectedSchemeCards));
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

    public void addLastDiceToRoundTrack()
    {
        roundTrack.addLastDice(currentRound);
    }

    public void addDraftedDieToBoard(int row, int column, boolean ignoreValueRestriction, boolean ignoreColorRestriction, boolean ignoreAdjacentRestriction) throws ChangeModelStateException, ActionNotPossibleException
    {
        if(draftedDie == null)
            throw new ChangeModelStateException("No drafted die to add!");

        Die die = draftedDie;

        currentPlayer.getBoard().addDie(die, row, column, ignoreValueRestriction, ignoreColorRestriction, ignoreAdjacentRestriction);

        //if the die is added to the board the drafted die is not present anymore
        draftedDie = null;

        notify(new AddedDieMessage(this, currentPlayer, die, row, column));
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

    public void moveSelectedDie(int row, int column, boolean ignoreValueRestriction, boolean ignoreColorRestriction, boolean ignoreAdjacentRestriction) throws ChangeModelStateException
    {
        if(selectedDie == null)
            throw new ChangeModelStateException("No selected die!");

        currentPlayer.getBoard().moveDie(selectedDie, row, column, ignoreValueRestriction, ignoreColorRestriction, ignoreAdjacentRestriction);
        notify(new MovedDieMessage(this, selectedDie, currentPlayer, row, column));
    }

    public void rollDie(int dieType) throws ChangeModelStateException
    {
        Die die = getDie(dieType);
        if(die == null)
            throw new ChangeModelStateException("No die to roll!");

        die.roll();
        notify(new ModifiedDieMessage(this, die, currentPlayer));
    }

    public void setCurrentRound(int currentRound) throws ChangeModelStateException
    {
        if(currentRound <=0 || currentRound >9)
            throw new ChangeModelStateException("Invalid round number!");
        this.currentRound = currentRound;
    }

    //returns the array of the boards that the player can choose
    public List<Board> getSchemeCards()
    {
        return schemeCards;
    }


}
