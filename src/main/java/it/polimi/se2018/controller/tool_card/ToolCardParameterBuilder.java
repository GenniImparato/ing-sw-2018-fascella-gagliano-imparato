package it.polimi.se2018.controller.tool_card;


public class ToolCardParameterBuilder
{
    private static final  String    DRAFTED_DIE =           "drafted_die";
    private static final  String    SELECTED_DIE =          "selected_die";
    private static final  String    CHOSEN_DIE =            "chosen_die";

    private static final  String    IGNORE_NOTHING =        "ignore_nothing";
    private static final  String    IGNORE_COLOR =          "ignore_color";
    private static final  String    IGNORE_VALUE  =         "ignore_value";
    private static final  String    IGNORE_ADJACENT =       "ignore_adjacent";

    private ToolCardParameters.DieParameter die;
    private ToolCardParameters.IgnoreParameter ignore;

    public ToolCardParameterBuilder()
    {
    }

    public ToolCardParameterBuilder setDie(String parameter) throws InvalidToolCardActionException
    {
        if(parameter.equals(DRAFTED_DIE))
            die = ToolCardParameters.DieParameter.DRAFTED;
        else if(parameter.equals(SELECTED_DIE))
            die = ToolCardParameters.DieParameter.SELECTED;
        else if(parameter.equals(CHOSEN_DIE))
            die = ToolCardParameters.DieParameter.CHOSEN;
        else
            throw new InvalidToolCardActionException("Invalid parameter: " + parameter);

        return this;
    }

    public ToolCardParameterBuilder setIgnore(String parameter) throws InvalidToolCardActionException
    {
        if(parameter.equals(IGNORE_NOTHING))
            ignore = ToolCardParameters.IgnoreParameter.NOTHING;
        else if(parameter.equals(IGNORE_ADJACENT))
            ignore = ToolCardParameters.IgnoreParameter.ADJACENT;
        else if(parameter.equals(IGNORE_COLOR))
            ignore = ToolCardParameters.IgnoreParameter.COLOR;
        else if(parameter.equals(IGNORE_VALUE))
            ignore = ToolCardParameters.IgnoreParameter.VALUE;
        else
            throw new InvalidToolCardActionException("Invalid parameter: " + parameter);

        return this;
    }

    public ToolCardParameters build()
    {
        return new ToolCardParameters(die, ignore);
    }
}
