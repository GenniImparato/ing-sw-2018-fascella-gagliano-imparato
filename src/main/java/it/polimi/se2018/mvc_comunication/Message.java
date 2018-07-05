package it.polimi.se2018.mvc_comunication;

import it.polimi.se2018.model.Model;

import java.io.Serializable;

/**
 * This class is used to represent a generic message in order to respect the MVC pattern.
 */
public abstract class Message implements MessageVisitable, Serializable
{
    private Model model;

    /**
     * Constructor that saves an instance of a Model
     * @param model model to set as attribute
     */
    public Message(Model model)
    {
        this.model = new Model(model);
    }

    /**
     * This method is used to get the model saved in the message
     * @return Model encapsulated into the message
     */
    public Model getModel()
    {
        return model;
    }
}
