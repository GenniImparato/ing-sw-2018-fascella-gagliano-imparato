package it.polimi.se2018.model;


import java.io.Serializable;

/**
 * Abstract class used to represent a generic card.
 * @author Matteo Gagliano
 * @author Carmelo Fascella
 * @author Generoso Imparato
 */
public abstract class Card implements Serializable
{
    private String name;
    private String description;

    /**
     * Constructor that saves in the subclass instance the name and the description of the Card.
     * @param name name of the Card
     * @param description describe how to use a Card
     */
    public Card (String name, String description)
    {
        this.name=name;
        this.description=description;
    }

    /**
     * Copy constructor
     * @param card source instance to be cloned
     */
    public Card(Card card)
    {
        this.name = card.getName();
        this.description = card.getDescription();
    }

    /**
     * Returns a String that is the name of the Card
     * @return name of the Card
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns a String that is the description of the Card
     * @return
     */
    public String getDescription()
    {
        return description;
    }

}
