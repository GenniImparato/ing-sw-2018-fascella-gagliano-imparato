package it.polimi.se2018.model;

import it.polimi.se2018.controller.Controller;
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

    private int                                 startTimer;

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
        if(model.currentToolCard != null  && model.toolCards != null)
            this.currentToolCard = this.toolCards.get(model.toolCards.indexOf(model.currentToolCard));

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

    /**
     * Getter of the current Player
     * @return the current player
     */
    public Player getCurrentPlayer()
    {
        return currentPlayer;
    }

    /**
     * Method that find the Player whose name matches with the string passed by parameter
     * @param nickname the name of the player that we are searching
     * @return the player searched
     * @throws NoElementException if there isn't the player searched
     */
    public Player findPlayer(String nickname) throws NoElementException
    {
        for(Player player : players)
            if(player.getNickname().equals(nickname))
                return player;

        throw new NoElementException();
    }

    /**
     * Method that find the Player with the color passed by parameter
     * @param color the color of the player that we are searching
     * @return the player searched
     * @throws NoElementException if there isn't the player searched
     */
    public Player findPlayer(Color color) throws NoElementException
    {
        for(Player player : players)
            if(player.getColor() == color)
                return player;

        throw new NoElementException();
    }

    /**
     * Setter of all the Boards selected by the Players
     * @param schemeCards is a list of boards of the Players who join the game
     */
    public void setSchemeCards(List<Board> schemeCards)
    {
        this.schemeCards = schemeCards;
    }

    /**
     * Setter of the list of ToolCards to use in the game
     * @param toolCards is the list of the cards to save as reference
     */
    public void setToolCards(List<Card> toolCards)
    {
        this.toolCards = toolCards;
    }

    /**
     * Setter of the Board selected by each player
     * @param player who is setting the board
     * @param choice number of the board chosen. Each player can choose between four boards, from choice = 0 to choice = 4
     * @throws ChangeModelStateException this exception is thrown if the choice of the board is not valid
     */
    public void setPlayerSchemeCard(Player player, int choice) throws ChangeModelStateException
    {
        if(choice < 0 || choice > 3)
            throw new ChangeModelStateException("Not valid choice!");

        player.setBoard(schemeCards.get(player.getSchemeCardIndex(choice)));
        notify(new ChosenSchemeCardMessage(this, player, player.getBoard()));
    }

    /**
     * Setter of the current ToolCard
     * @param cardNum it's the number of the chosen ToolCard. Each player can choose between three cards, from cardNum = 0 to cardNum = 3
     * @throws ChangeModelStateException if the index of the card is not valid
     */
    public void setCurrentToolCard(int cardNum) throws ChangeModelStateException
    {
        if(cardNum < 0  || cardNum >= toolCards.size())
            throw new ChangeModelStateException("Invalid tool card index!");

        currentToolCard = toolCards.get(cardNum);
        notify(new UsingToolCardMessage(this, currentToolCard, currentPlayer));
    }

    public void setNoCurrentToolCard()
    {
        Card card = currentToolCard;
        currentToolCard = null;
        notify(new ToolCardEndedMessage(this, card));
    }

    /**
     * Getter of the current ToolCard number
     * @return the current ToolCard number or -1 in case of tool card not being used
     */
    public int getCurrentToolCardNumber()
    {
        return toolCards.indexOf(currentToolCard);
    }


    /**
     * Method that add a new Player with the name passed by parameter.
     * If the player is added, it notifies the view with the AddedPlayerMessage
     * @param nickname is the name of the player to add
     * @throws ChangeModelStateException if the nickname is empty or if there is a player with the same nickname
     */
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

    public void rollDraftPool()
    {
        for(Die die: draftPool.getAllDice())
            die.roll();

        notify(new ReRolledDraftPoolMessage(this, currentPlayer));
    }

    /**
     * Setter of the player who is ready to play. It notifies the View with the PlayerReadyMessage
     * @param player is the player we want to set ready
     * @param ready is a boolean that if it's true, it sets the player in a ready state
     */
    public void setPlayerReady(Player player, boolean ready)
    {
        player.setReady(ready);
        notify(new PlayerReadyMessage(this, player, ready));
    }

    /**
     * Method that returns the Die just drafted .
     * If there is a drafted Die, it notifies the view with a ReturnedDieMessage
     * @throws ChangeModelStateException if there isn't a drafted Die
     */
    public void returnDraftedDie() throws ChangeModelStateException
    {
        if(draftedDie == null)
            throw new ChangeModelStateException("No drafted die!");

        Die die = draftedDie;
        draftPool.addDie(die);
        draftedDie = null;

        notify(new ReturnedDieMessage(this, die, currentPlayer));
    }

    /**
     * Method that says if all the players are ready
     * @return true if every player is ready, false if not
     */
    public boolean isEveryPlayerReady()
    {
        for(Player player : players)
            if(!player.isReady())
                return false;

        return true;
    }

    /**
     * Method that removes the Player with the string passed by parameter
     * @param nickname of the player to remove
     * @throws ChangeModelStateException if there is not player with the name passed by parameter
     */
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

    /**
     * Method that says if every player have chosen the scheme card
     * @return true if every player have chosen the scheme card, false if not
     */
    public boolean hasEveryPlayerChosenSchemeCard()
    {
        for(Player player : players)
        {
            if(!player.hasChoosenSchemeCard())
                return false;
        }

        return true;
    }

    /**
     * Method that starts the game and then it notifies the view with the StartedGameMessage
     * @throws ChangeModelStateException if there aren't enough players to start the game, or not every player has chosen a scheme card
     */
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

    /**
     * Method that generates four random scheme cards for each player. Then it notifies the view
     * with the SelectedPlayerSchemeCardsMessage
     * @throws ChangeModelStateException if there aren't too many cards to load for each player
     */
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
            //so the view(user) can choose one of them
            notify(new SelectedPlayerSchemeCardsMessage(this, player, selectedSchemeCards));
        }
    }

    public void setStartTimer(int timer)
    {
        startTimer = timer;
        notify(new UpdatedStartTimerMessage(this, startTimer));
    }

    /**
     * Method that drafts a die from the DraftPool.
     * Then it notifies the View with the DraftedDieMessage.
     * @param dieNum is the number of the drafted Die
     * @throws ChangeModelStateException if there isn't the selected Die
     */
    public void draftDie(int dieNum) throws ChangeModelStateException
    {
        draftedDie = draftPool.draftDie(dieNum);
        notify(new DraftedDieMessage(this, draftedDie, currentPlayer));
    }

    /**
     * Method that draws the right number of dice from the Dicebag to the Draftpool
     * at the beginning of each round.
     */
    public void drawFromDiceBag()
    {
        draftPool.draw(getPlayerNum()*2 + 1);
    }

    /**
     * Method that add the last die or dice remaining on the DraftPool at the end of the turn,
     * on the position of the current round of the RoundTrack
     */
    public void addLastDiceToRoundTrack()
    {
        roundTrack.addLastDice(currentRound);
    }

    /**
     * Method that adds the drafted Die in the chosen coordinates.
     * Then it notifies the view with the AddedDieMessage.
     * @param row first coordinate
     * @param column second coordinante
     * @param ignoreValueRestriction if true ignore the value restriction of the placing of the Die
     * @param ignoreColorRestriction if true, ignore the color restriction of the placing of the Die
     * @param ignoreAdjacentRestriction if true, ignore the adjacent restriction of the placing of the Die
     * @throws ChangeModelStateException if there isn't any drafted Die to add on the board
     * @throws ActionNotPossibleException
     */
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

    /**
     * Method that select a Die at the given coordinates from the Board of a Player
     * @param player who wants to select the Die
     * @param row first coordinate
     * @param column second coordinate
     * @throws ChangeModelStateException if the selected cell doesn't contain any Die
     */
    public void selectDieFromBoard(Player player, int row, int column) throws ChangeModelStateException
    {
        selectedDie = player.getBoard().getDie(row, column);

        if(selectedDie == null)
            throw new ChangeModelStateException("The selected cell doesn't contain any die!");
        else
            notify(new SelectedDieMessage(this, selectedDie, currentPlayer));
    }

    public void selectSameColorDieFromBoard(Player player, int row, int column, int dieType) throws ChangeModelStateException
    {
        Die tempDie = player.getBoard().getDie(row, column);
        Die compareDie = getDie(dieType);

        if(tempDie == null)
            throw new ChangeModelStateException("The selected cell doesn't contain any die!");

        if(tempDie.getColor() != compareDie.getColor())
            throw new ChangeModelStateException("The selected die is not " + compareDie.getColor() + " !");

        selectedDie = tempDie;
        notify(new SelectedDieMessage(this, selectedDie, currentPlayer));
    }

    public void chooseDieFromRoundTrack(int round) throws ChangeModelStateException
    {
        if(round < 0 || round >= Controller.TOTAL_ROUNDS)
            throw new ChangeModelStateException("Round out of bound!");

        if(roundTrack.getDiceAtRound(round).isEmpty())
            throw new ChangeModelStateException("No dice at selected round!");

        chosenDie = roundTrack.getDiceAtRound(round).get(0);
    }

    /**
     * Method that increments the value of the Drafted Die.
     * Then it notifies the View with the ChangedDraftedDieMessage
     * @throws ChangeModelStateException if there isn't a drafted Die
     */
    public void incrementDraftedDie() throws ChangeModelStateException
    {
        if(draftedDie == null)
            throw new ChangeModelStateException("No drafted die!");

        draftedDie.incrementValue();
        notify(new ChangedDraftedDieMessage(this, draftedDie, currentPlayer));
    }

    /**
     * Method that decrements the Drafted Die.
     * Then it notifies the View with the ChangedDraftedDie
     * @throws ChangeModelStateException if there isn't a drafted Die
     */
    public void decrementDraftedDie() throws ChangeModelStateException
    {
        if(draftedDie == null)
            throw new ChangeModelStateException("No drafted die!");

        draftedDie.decrementValue();
        notify(new ChangedDraftedDieMessage(this, draftedDie, currentPlayer));
    }

    /**
     * Method that moves the selected Die at the given position.
     * Then it notifies the View with the MovedDieMessage
     * @param row first coordinate
     * @param column second coordinate
     * @param ignoreValueRestriction if true, ignore the value restriction of the chosen position
     * @param ignoreColorRestriction if true, ignore the color restriction of the chosen position
     * @param ignoreAdjacentRestriction if true, ignore the adjacent restriction of the chosen position
     * @throws ChangeModelStateException if there isn't a selected Die
     */
    public void moveSelectedDie(int row, int column, boolean ignoreValueRestriction, boolean ignoreColorRestriction, boolean ignoreAdjacentRestriction) throws ChangeModelStateException
    {
        if(selectedDie == null)
            throw new ChangeModelStateException("No selected die!");

        currentPlayer.getBoard().moveDie(selectedDie, row, column, ignoreValueRestriction, ignoreColorRestriction, ignoreAdjacentRestriction);
        notify(new MovedDieMessage(this, selectedDie, currentPlayer, row, column));
    }

    /**
     * Method that rolls a Die chosen by his type, passed by parameter
     * @param dieType type of the Die to roll. 0 = drafted, 1 = selected, 2 = chosen
     * @throws ChangeModelStateException if there isn't a Die to Roll
     */
    public void rollDie(int dieType) throws ChangeModelStateException
    {
        Die die = getDie(dieType);
        if(die == null)
            throw new ChangeModelStateException("No die to roll!");

        die.roll();
        notify(new ModifiedDieMessage(this, die, currentPlayer));
    }

    public void invertDie(int dieType) throws ChangeModelStateException
    {
        Die die = getDie(dieType);
        if(die == null)
            throw new ChangeModelStateException("No die to invert!");

        die.invert();
        notify(new ModifiedDieMessage(this, die, currentPlayer));
    }

    /**
     * Method that saves the reference to the current round
     * @param currentRound is the number that indicates the current round. It can be >0 && <9
     * @throws ChangeModelStateException if the value of the parameter is invalid
     */
    public void setCurrentRound(int currentRound) throws ChangeModelStateException
    {
        if(currentRound <=0 || currentRound >9)
            throw new ChangeModelStateException("Invalid round number!");
        this.currentRound = currentRound;
    }

    /**
     * Getter of the 4 schemeCard that a player can choose
     * @return the array of the boards that the player can choose
     */
    public List<Board> getSchemeCards()
    {
        return schemeCards;
    }


}
