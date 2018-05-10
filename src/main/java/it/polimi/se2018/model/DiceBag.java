package it.polimi.se2018.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DiceBag
{
    private List<Die> dice ;

    //create a DiceBag containing 90 dices
    public DiceBag()
    {
        dice = new ArrayList<>();

        for(Color col : Color.values())             //add 90 dice to the list (18 for each color)
        {
            for(int j=0;j<18;j++)
            {
                dice.add(new Die (col));
            }
        }
    }

    //copy constructor
    public DiceBag(DiceBag diceBag)
    {
        this.dice = new ArrayList<>();

        for(Die die : diceBag.dice)
        {
            dice.add(new Die(die));
        }
    }

    //return a list containing num random dice from the DiceBag
    //dice returned are removed from the DiceBag
    public List<Die> pullDice(int num)
    {
        Random random = new Random();
        ArrayList<Die> ret = new ArrayList<>();

        for(int i=0; i<num; i++)
        {
            int index = random.nextInt(dice.size());        //generate a random index between 0 and the number of dice left
            ret.add(dice.get(index));
            dice.remove(index);
        }

        return ret;
    }
}
