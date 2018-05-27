package it.polimi.se2018.model;

import it.polimi.se2018.model.exceptions.ChangeModelStateException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class used to represent the DraftPool
 * @author Matteo Gagliano
 * @author Carmelo Facella
 * @author Generoso Imparato
 */
public class DraftPool implements Serializable
{
    private List <Die> dice;
    private DiceBag diceBag;

    /**
     * Constructor that creates a DraftPool able to draw to pull dice from the DiceBag passed by parameter
     * @param diceBag of the model
     */
    public DraftPool(DiceBag diceBag)
    {
        this.diceBag = diceBag;
        dice = new ArrayList<>();
    }

    /**
     * Copy constructor
     * @param draftPool source instance to be cloned
     * @param diceBag associated to the new instance of the DraftPool
     */
    public DraftPool(DraftPool draftPool, DiceBag diceBag)
    {
        this.diceBag = diceBag;

        this.dice = new ArrayList<>();

        for(Die die : draftPool.dice)
            this.dice.add(new Die(die));
    }

    /**
     * Pull num dice from the associated DiceBag; the pulled dice are removed from the DiceBag
     * and stored in this DraftPool
     */
    public void draw(int num)
    {
        dice = diceBag.pullDice(num);
    }

    /**
     * Returns a die from the DraftPool; the drafted die is removed from the DraftPool
     * @param num is an index of the Die in the DraftPool
     * @return the selected Die
     * @throws ChangeModelStateException if num is negative or greater than the total number of dice in the DraftPool
     */
    public Die draftDie(int num) throws ChangeModelStateException
    {
        if(num < 0  || num >= dice.size())
            throw new ChangeModelStateException("There are less then " + num + " dice in the draft pool!");
        Die die = dice.get(num);
        dice.remove(num);
        return die;
    }

    public void addDie(Die die)
    {
        dice.add(die);
    }

    /**
     * Returns a list of all dice present in the DraftPool
     * @return list of all dice present in the DraftPool
     */
    public List<Die> getAllDice()
    {
        return dice;
    }

    /**
     * Returns  a list containing all remaining dice from the DraftPool;
     * the returned dice are removed from the DraftPool
     * @return list containing all remaining dice from the DraftPool;
     * the returned dice are removed from the DraftPool
     */
    public List<Die> pullAllDice()
    {
        ArrayList<Die> ret = new ArrayList<>(dice);
        dice.clear();
        return ret;
    }

    public boolean contains(Die die)
    {
        for(Die draftPoolDie : dice)
            if(draftPoolDie.isSameDie(die))
                return true;

        return false;
    }

}
