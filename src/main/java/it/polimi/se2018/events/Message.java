package it.polimi.se2018.events;

import it.polimi.se2018.model.Model;
import it.polimi.se2018.view.cli.CLIMessageParser;

import java.io.Serializable;

public abstract class Message implements Serializable
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

    public abstract void beParsed(CLIMessageParser parser);
}
