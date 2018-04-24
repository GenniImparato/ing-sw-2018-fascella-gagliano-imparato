package it.polimi.se2018.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DiceBag
{
    private static DiceBag instance = null;
    ArrayList<Die> dice ;

    public static DiceBag getInstance()     //Singleton
    {
        if (instance==null)
            instance=new DiceBag();
        return instance;
    }

    private DiceBag()
    {
        dice = new ArrayList<Die>();

        for(Color col : Color.values())             //add 90 dice to the list (18 for each color)
        {
            for(int j=0;j<18;j++)
            {
                dice.add(new Die (col));
            }
        }
    }

    public ArrayList<Die> pullDice(int num)
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
