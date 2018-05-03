package it.polimi.se2018.model;

import it.polimi.se2018.model.toolcards.*;

import java.util.ArrayList;
import java.util.Random;

public abstract class ToolCard extends Card
{
    private int             num;
    private int             tokens;

    private static int      NUMBER_OF_CARDS=12;

    public ToolCard (String name, String description, int num)
    {
        super (name, description);
        this.num = num;
        tokens = 0;
    }

    //generate numOfCards different random ToolCards
    public static ArrayList<ToolCard> getRandomCards(int numOfCards)
    {
        ArrayList<ToolCard> ret = new ArrayList<>();
        ArrayList<Integer>  randomList = new ArrayList<>();

        for(int i=0; i<numOfCards; i++)
        {
            int rand;
            do                                                          //make sure that the random number generated
            {                                                           //is different from the others in order to have different cards
                rand = new Random().nextInt(NUMBER_OF_CARDS);
                if(!randomList.contains(rand))                          //add the random number only if it's not been already generated
                    randomList.add(rand);
            }
            while(randomList.size() < i+1);                              //make sure that a different random number is added


            switch(rand)                                                //generate a random PublicObjectiveCard
            {
                case 0: ret.add(new CopperFoilBurnisher()); break;
                case 1: ret.add(new CorkBackedStraightedge()); break;
                case 2: ret.add(new EglomiseBrush()); break;
                case 3: ret.add(new FluxBrush()); break;
                case 4: ret.add(new FluxRemover()); break;
                case 5: ret.add(new GlazingHammer()); break;
                case 6: ret.add(new GrindingStone()); break;
                case 7: ret.add(new GrozingPliers()); break;
                case 8: ret.add(new Lathekin()); break;
                case 9: ret.add(new LensCutter()); break;
                case 10: ret.add(new RunningPliers()); break;
                case 11: ret.add(new TapWheel()); break;
                default: break;
            }
        }

        return ret;
    }

    public abstract void use (Player player);
}
