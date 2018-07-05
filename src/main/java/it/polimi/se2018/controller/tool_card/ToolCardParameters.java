package it.polimi.se2018.controller.tool_card;

/**
 * This class is used to represent the tool card action parameters
 */
public class ToolCardParameters
{
    /**
     * Possible types of die
     */
    enum DieParameter
    {
        DRAFTED,
        SELECTED,
        CHOSEN
    }

    /**
     * Possible elements to ignore
     */
    enum IgnoreParameter
    {
        NOTHING,
        COLOR,
        VALUE,
        ADJACENT
    }

    private DieParameter die;
    private IgnoreParameter ignore;

    /**
     * Constructor. It sets the kind of die and the ignore value (nothing, color, value or adjacent)
     * @param die type of die (drafted, selected or chosen)
     * @param ignore tells what needs to be ignored
     */
    ToolCardParameters(DieParameter die, IgnoreParameter ignore)
    {
        this.die = die;
        this.ignore = ignore;
    }

    /**
     * Tells if it's a drafted die
     * @return true if it's a drafted die, false otherwise
     */
    public boolean isDraftedDie()
    {
        return die == die.DRAFTED;
    }

    /**
     * Tells if it's a selected die
     * @return true if it's a drafted die false otherwise
     */
    public boolean isSelectedDie()
    {
        return die == die.SELECTED;
    }

    /**
     * Tells if it's a chosen die
     * @return true if it's a chosen die, false otherwise
     */
    public boolean isChosenDie()
    {
        return die == die.CHOSEN;
    }

    /**
     * Tells if anything needs to be ignored
     * @return true if nothing must be ignored, false if color, value or adjacent dice need to be ignored
     */
    public boolean isIgnoreNothing()
    {
        return ignore == IgnoreParameter.NOTHING;
    }

    /**
     * Tells if the color of the die needs to be ignored
     * @return true if the color of the die needs to be ignored, false otherwise
     */
    public boolean isIgnoreColor()
    {
        return ignore == IgnoreParameter.COLOR;
    }

    /**
     * Tells if the value of the die needs to be ignored
     * @return true if the value of the die needs to be ignored, false otherwise
     */
    public boolean isIgnoreValue()
    {
        return ignore == IgnoreParameter.VALUE;
    }

    /**
     * Tells if adjacent dice can be ignored
     * @return true if adjacent dice can be ignored, false otherwise
     */
    public boolean isIgnoreAdjacent()
    {
        return ignore == IgnoreParameter.ADJACENT;
    }

}
