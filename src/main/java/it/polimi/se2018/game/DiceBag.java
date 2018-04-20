package it.polimi.se2018.game;

import java.util.ArrayList;
import java.util.List;

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
        dice=new ArrayList<Die>();
        for(Color col : Color.values())
        {
            for(int j=0;j<18;j++)
            {
                dice.add(new Die (col));
            }
        }
    }
    public List <Die> pullDice()
    {
        return null;
    }
}
