package it.polimi.se2018.controller.tool_card;

public class ToolCardParameters
{
    enum DieParameter
    {
        DRAFTED,
        SELECTED,
        CHOSEN
    }

    enum IgnoreParameter
    {
        NOTHING,
        COLOR,
        VALUE,
        ADJACENT
    }

    private DieParameter die;
    private IgnoreParameter ignore;

    ToolCardParameters(DieParameter die, IgnoreParameter ignore)
    {
        this.die = die;
        this.ignore = ignore;
    }

    public boolean isDraftedDie()
    {
        return die == die.DRAFTED;
    }

    public boolean isSelectedDie()
    {
        return die == die.SELECTED;
    }

    public boolean isChosenDie()
    {
        return die == die.CHOSEN;
    }

    public boolean isIgnoreNothing()
    {
        return ignore == IgnoreParameter.NOTHING;
    }

    public boolean isIgnoreColor()
    {
        return ignore == IgnoreParameter.COLOR;
    }

    public boolean isIgnoreValue()
    {
        return ignore == IgnoreParameter.VALUE;
    }

    public boolean isIgnoreAdjacent()
    {
        return ignore == IgnoreParameter.ADJACENT;
    }

}
