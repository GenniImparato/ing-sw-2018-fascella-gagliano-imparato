package it.polimi.se2018.model;

import it.polimi.se2018.utils.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class used to represent the DiceBag
 * @author Matteo Gagliano
 * @author Carmelo Fascella
 * @author Generoso Imparato
 */
public class DiceBag implements Serializable
{
    private List<Die> dice ;

    /**
     * Constructor that creates a DiceBag containing 90 dice (15 for each color)
     */
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

    /**
     * Copy constructor
     * @param diceBag soruce instance to be cloned
     */
    public DiceBag(DiceBag diceBag)
    {
        this.dice = new ArrayList<>();

        for(Die die : diceBag.dice)
        {
            dice.add(new Die(die));
        }
    }

    /**
     * Returns a list containing num random dice from the DiceBag,
     * dice returned are removed from the DiceBag
     * @param num number random dice pulled from the DiceBag
     */
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

    public Die drawDie()
    {
        Random random = new Random();
        int index = random.nextInt(dice.size());
        Die ret = dice.get(index);
        dice.remove(index);

        return ret;
    }

    public void returnDie(Die die)
    {
        dice.add(die);
    }
}
