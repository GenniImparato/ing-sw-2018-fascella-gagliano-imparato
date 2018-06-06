package it.polimi.se2018.controller;

import it.polimi.se2018.controller.tool_card.actions.ToolCardAction;
import it.polimi.se2018.model.Card;

import java.util.ArrayList;
import java.util.List;

public class ToolCard
{
    private String                  name;
    private List<ToolCardAction>    actions;

    private int                     currentAction;

    public ToolCard(String name)
    {
        this.name = name;
        this.currentAction = 0;
        actions = new ArrayList<>();
    }

    public void addAction(ToolCardAction action)
    {
        actions.add(action);
    }

    public void use(Controller controller)
    {
        currentAction = 0;

        if(!actions.isEmpty())
            executeNextAction(controller);
    }

    public void executeNextAction(Controller controller)
    {
        if(currentAction >= actions.size())
        {
            controller.endToolCardActions();
            return;
        }

        ToolCardAction action = actions.get(currentAction);
        action.execute(controller);

        currentAction++;

        if(action.isInstant())
            executeNextAction(controller);
    }

    public Card generateCard()
    {
        return new Card(name);
    }

    public ToolCardAction getCurrentAction()
    {
        return actions.get(currentAction-1);
    }
}
