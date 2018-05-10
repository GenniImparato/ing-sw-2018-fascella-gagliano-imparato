package it.polimi.se2018.model;

import java.io.Serializable;

public abstract class Card implements Serializable
{
    private String name;
    private String description;

    public Card (String name, String description)
    {
        this.name=name;
        this.description=description;
    }

    //copy constructor
    public Card(Card card)
    {
        this.name = card.getName();
        this.description = card.getDescription();
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

}
