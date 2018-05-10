package it.polimi.se2018.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DraftPool
{
    private List <Die> dice;
    private DiceBag diceBag;

    public DraftPool(DiceBag diceBag)
    {
        this.diceBag = diceBag;
        dice = new ArrayList<>();
    }

    //copy constructor
    public DraftPool(DraftPool draftPool, DiceBag diceBag)
    {
        this.diceBag = diceBag;

        this.dice = new ArrayList<>();

        for(Die die : draftPool.dice)
            this.dice.add(new Die(die));
    }

    //draw num dice form the DiceBag
    public void draw(int num)
    {
        dice = diceBag.pullDice(num);
    }

    //return a die from the DraftPool
    //the drafted die is removed from the DraftPool
    public Die draftDie(int num)
    {
        Die die = dice.get(num);
        dice.remove(num);
        return die;
    }

    //get a list containing all dice in the DraftPool
    public List<Die> getAllDice()
    {
        return dice;
    }

    //return a list containing all remaining dice from the DraftPool
    //the returned dice are removed from the DraftPool
    public List<Die> pullAllDice()
    {
        ArrayList<Die> ret = new ArrayList<>(dice);
        dice.clear();
        return ret;
    }

}
