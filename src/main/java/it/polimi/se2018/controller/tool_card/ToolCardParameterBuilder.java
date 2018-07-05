package it.polimi.se2018.controller.tool_card;


/**
 * This class is used to create the tool card action parameters. The parameters are created through the Builder pattern.
 */
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

    /**
     * Constructor
     */
    public ToolCardParameterBuilder()
    {
    }

    /**
     * Sets the kind of die needed
     * @param parameter specifies if the color or the value of the involved die (which can be drafted, selected or chosen)
     *  or adjacent dice should be ignored
     * @return this builder, that can be further modified through the setter methods
     * @throws InvalidToolCardActionException if the parameter is not valid
     */
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

    /**
     * Sets if an action with this parameter has to ignore something (color, value or adjacent dice) or not
     * @param parameter specifies if the color or the value of the involved die (which can be drafted, selected or chosen)
     *  or adjacent dice should be ignored
     * @return this builder, that can further modified through the setter methods
     * @throws InvalidToolCardActionException if the parameter is not valid
     */
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

    /**
     * It effectively builds the tool card action parameters
     * @return tool card parameters
     */
    public ToolCardParameters build()
    {
        return new ToolCardParameters(die, ignore);
    }
}
