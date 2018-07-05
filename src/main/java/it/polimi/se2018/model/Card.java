package it.polimi.se2018.model;


import java.io.Serializable;

/**
 * Abstract class used to represent a generic card.
 * @author Matteo Gagliano
 * @author Carmelo Fascella
 * @author Generoso Imparato
 */
public class Card implements Serializable
{
    private String  name;
    private int     favorTokens;

    /**
     * Constructor that saves in the subclass instance the name of the Card.
     * @param name name of the Card
     */
    public Card (String name)
    {
        this.name = name;
        this.favorTokens = 0;
    }

    /**
     * Copy constructor
     * @param card source instance to be cloned
     */
    public Card(Card card)
    {
        this.name = card.getName();
        this.favorTokens = card.favorTokens;
    }

    /**
     * Returns a String that is the name of the Card
     * @return name of the Card
     */
    public String getName()
    {
        return name;
    }

    public int getFavorTokens()
    {
        return favorTokens;
    }

    public void addFavorTokens(int num)
    {
        favorTokens += num;
    }
}
