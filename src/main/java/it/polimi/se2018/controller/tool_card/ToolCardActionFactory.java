package it.polimi.se2018.controller.tool_card;


import it.polimi.se2018.controller.tool_card.actions.*;

/**
 * This class is used to create the tool cards' actions. The tool cards' actions are created through the Factory pattern.
 * Each tool card has a set of actions that need to be executed; the execution of an action can involve zero or more parameters.
 */
public class ToolCardActionFactory
{
    /**
     * These are all the possible actions that a tool card can execute.
     */
    private enum Action
    {
        DRAFT_DIE("DraftDie", 0),
        SELECT_DIE_FROM_BOARD("SelectDieFromBoard", 0),
        ADD_DRAFTED_DIE_TO_BOARD("AddDraftedDie", 1),
        MOVE_SELECTED_DIE("MoveSelectedDie", 1),
        ROLL_DIE("RollDie", 1),
        RE_DRAW_DIE("ReDrawDie", 1),
        RE_ROLL_DRAFTPOOL("ReRollDraftPool", 0),
        FLIP_DIE("FlipDie", 1),
        INCREMENT_DECREMENT_DIE("IncrementDecrementDie", 1),
        SWAP_DICE("SwapDice", 0),
        SKIP_NEXT_TURN("SkipNextTurn", 0),
        ASK_TO_STOP("AskToStop", 0),
        CHOOSE_DIE_FROM_ROUNDTRACK("ChooseDieFromRoundTrack", 0),
        SELECT_SAME_COLOR_DIE("SelectSameColorDie", 1),
        SWAP_DRAFTED_CHOSEN_DIE("SwapDraftedChosenDice", 0);

        private String name;
        private int paramNumber;

        /**
         * Constructor of the enum
         * @param name name of the tool card action
         * @param parametersNumber number of parameters associated to the tool card action
         */
        Action(String name, int parametersNumber)
        {
            this.name = name;
            this.paramNumber = parametersNumber;
        }

        /**
         * Returns the number of parameters of a particular action
         * @return number of parameters of a particular action
         */
        public int getParametersNumber()
        {
            return paramNumber;
        }

        /**
         * Returns the name of the tool card action
         * @return name of the tool card action
         */
        public String getName()
        {
            return name;
        }
    }

    /**
     * Constructor
     */
    private ToolCardActionFactory()
    {}

    /**
     * This is the method responsible for creating a tool card action; each tool card action is generated from this method
     * according to the name and to the parameter passed by parameter
     * @param action action that is about to be created
     * @param parameter parameter associated to th action
     * @return tool card action built properly
     * @throws InvalidToolCardActionException when an action is not permitted
     */
    public static ToolCardAction createAction(String action, String parameter) throws InvalidToolCardActionException
    {
        if(action.equals(Action.DRAFT_DIE.getName()))
            return new DraftDieAction(new ToolCardParameterBuilder().build());
        else if(action.equals(Action.SELECT_DIE_FROM_BOARD.getName()))
            return new SelectDieFromBoardAction(new ToolCardParameterBuilder().build());
        else if(action.equals(Action.ADD_DRAFTED_DIE_TO_BOARD.getName()))
            return new AddDieToBoardAction(new ToolCardParameterBuilder().setIgnore(parameter).build());
        else if(action.equals(Action.MOVE_SELECTED_DIE.getName()))
            return new MoveSelectedDieAction(new ToolCardParameterBuilder().setIgnore(parameter).build());
        else if(action.equals(Action.ROLL_DIE.getName()))
            return new RollDieAction(new ToolCardParameterBuilder().setDie(parameter).build());
        else if(action.equals(Action.RE_ROLL_DRAFTPOOL.getName()))
            return new ReRollDraftPoolAction(new ToolCardParameterBuilder().build());
        else if(action.equals(Action.FLIP_DIE.getName()))
            return new FlipDieAction(new ToolCardParameterBuilder().setDie(parameter).build());
        else if(action.equals(Action.INCREMENT_DECREMENT_DIE.getName()))
            return new IncrementDecrementDieAction(new ToolCardParameterBuilder().setDie(parameter).build());
        else if(action.equals(Action.SKIP_NEXT_TURN.getName()))
            return new SkipTurnAction(new ToolCardParameterBuilder().build());
        else if(action.equals(Action.CHOOSE_DIE_FROM_ROUNDTRACK.getName()))
            return new ChooseDieFromRoundTrackAction(new ToolCardParameterBuilder().build());
        else if(action.equals(Action.SELECT_SAME_COLOR_DIE.getName()))
            return new SelectSameColorDieAction(new ToolCardParameterBuilder().setDie(parameter).build());
        else if(action.equals(Action.SWAP_DRAFTED_CHOSEN_DIE.getName()))
            return new SwapDraftedChosenDieAction(new ToolCardParameterBuilder().build());

        throw new InvalidToolCardActionException("Invalid action: " + action + "!");
    }

    /**
     * Returns the number of parameters of the action that matches the name passed by parameter
     * @param action name of the action
     * @return the number of parameters of the action that matches the name passed by parameter
     * @throws InvalidToolCardActionException when an action cannot be found
     */
    public static int getParameterNumber(String action) throws InvalidToolCardActionException
    {
        for(Action possibleAction : Action.values())
            if(action.equals(possibleAction.getName()))
                return possibleAction.getParametersNumber();

        throw new InvalidToolCardActionException("Invalid action: " + action + "!");
    }
}
