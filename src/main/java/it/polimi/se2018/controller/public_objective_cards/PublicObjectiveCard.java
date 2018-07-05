package it.polimi.se2018.controller.public_objective_cards;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class PublicObjectiveCard
{
    protected int               points;
    private String              name;
    private static final int    NUMBER_OF_CARDS = 10;


    public PublicObjectiveCard (String name, int points)
    {
        this.name = name;
        this.points = points;
    }

    public int getPoints()
    {
        return points;
    }

    //generate numOfCards different random PublicObjective cards
    public static List<PublicObjectiveCard> getRandomCards(int numOfCards)
    {
        List<PublicObjectiveCard> ret = new ArrayList<>();
        List<Integer>  randomList = new ArrayList<>();

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
                case 0: ret.add(new ColorDiagonalsCard()); break;
                case 1: ret.add(new ColorVarietyCard()); break;
                case 2: ret.add(new ColumnColorVarietyCard()); break;
                case 3: ret.add(new ColumnShadeVarietyCard()); break;
                case 4: ret.add(new DeepShadesCard()); break;
                case 5: ret.add(new LightShadesCard()); break;
                case 6: ret.add(new MediumShadesCard()); break;
                case 7: ret.add(new RowColorVarietyCard()); break;
                case 8: ret.add(new RowShadeVarietyCard()); break;
                case 9: ret.add(new ShadeVarietyCard()); break;
                default: break;
            }
        }

        return ret;
    }

    public abstract int score(Board board);

    public Card generateCard()
    {
        return new Card(name);
    }
}
