package it.polimi.se2018.controller;

import it.polimi.se2018.controller.tool_card.actions.ToolCardAction;
import it.polimi.se2018.model.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to represent a generic tool card
 */
public class ToolCard
{
    private String                  name;
    private List<ToolCardAction>    actions;

    private int                     currentAction;

    /**
     * Constructor
     * @param name name of the tool card
     */
    public ToolCard(String name)
    {
        this.name = name;
        this.currentAction = 0;
        actions = new ArrayList<>();
    }

    /**
     * Adds an action to the list of actions that the tool card has to perform
     * @param action action that the tool card has to perform
     */
    public void addAction(ToolCardAction action)
    {
        actions.add(action);
    }

    /**
     * Performs the tool card
     * @param controller controller
     */
    public void use(Controller controller)
    {
        currentAction = 0;

        if(!actions.isEmpty())
            executeNextAction(controller);
    }

    /**
     * Executes the next action of the tool card(the one after the current action)
     * @param controller controller
     */
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

    /**
     * Generates the card
     * @return card
     */
    public Card generateCard()
    {
        return new Card(name);
    }

    /**
     * Returns the action that the tool card is performing (current action)
     * @return action that the tool card is performing (current action)
     */
    public ToolCardAction getCurrentAction()
    {
        return actions.get(currentAction-1);
    }
}
