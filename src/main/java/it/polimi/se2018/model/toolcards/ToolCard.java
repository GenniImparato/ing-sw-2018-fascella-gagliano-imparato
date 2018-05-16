package it.polimi.se2018.model.toolcards;

import it.polimi.se2018.model.Card;
import it.polimi.se2018.model.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class ToolCard extends Card implements ToolCardVisitable
{
    private int             favorTokens;
    private int             numOfSteps;
    private int             currentStep = 0;
    private Model           model;

    private static int      NUMBER_OF_CARDS=12;

    public ToolCard (String name, String description, int numOfSteps)
    {
        super (name, description);
        this.numOfSteps = numOfSteps;
        favorTokens = 0;
    }

    //copy constructor
    public ToolCard(ToolCard card)
    {
        super(card);
        this.numOfSteps = card.numOfSteps;
        this.favorTokens = card.favorTokens;
    }

    //generate numOfCards different random ToolCards
    public static List<ToolCard> getRandomCards(int numOfCards)
    {
        List<ToolCard> ret = new ArrayList<>();
        List<Integer> randomList = new ArrayList<>();

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
}
