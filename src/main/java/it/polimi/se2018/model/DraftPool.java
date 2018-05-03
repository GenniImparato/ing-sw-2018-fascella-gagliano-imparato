package it.polimi.se2018.model;

import java.util.ArrayList;

public class DraftPool
{
    private ArrayList <Die> dice;
    private DiceBag diceBag;

    public DraftPool(DiceBag diceBag)
    {
        this.diceBag = diceBag;
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

    //return a list containing all remaining dice from the DraftPool
    //the returned dice are removed from the DraftPool
    public ArrayList<Die> getAllDice()
    {
        ArrayList<Die> ret = new ArrayList<>(dice);
        dice.clear();
        return ret;
    }

}
