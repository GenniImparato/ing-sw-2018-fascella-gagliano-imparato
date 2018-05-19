package it.polimi.se2018.mvc_comunication;

import it.polimi.se2018.model.Model;

import java.io.Serializable;

public abstract class Message implements MessageVisitable, Serializable
{
    private Model model;

    public Message(Model model)
    {
        this.model = new Model(model);
    }

    public Model getModel()
    {
        return model;
    }
}
