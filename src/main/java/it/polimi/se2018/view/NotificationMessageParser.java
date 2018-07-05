package it.polimi.se2018.view;

import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.mvc_comunication.MessageVisitor;
import it.polimi.se2018.mvc_comunication.messages.*;
import it.polimi.se2018.view.gui.views.GUIGameView;

public class NotificationMessageParser implements MessageVisitor
{
    private static final String         CHOSEN_SCHEME_CARD_YOU              = "You have chosen ";
    private static final String         CHOSEN_SCHEME_CARD_OTHER            = " has chosen ";
    private static final String         ADDED_DIE_YOU                       = "You added a ";
    private static final String         ADDED_DIE_OTHER                     = " added a ";
    private static final String         ADDED_PLAYER_YOU                    = "You joined the game!";
    private static final String         ADDED_PLAYER_OTHER                  = " joined the game!";
    private static final String         REMOVED_PLAYER                      = " has disconnected from the game!";
    private static final String         DRAFTED_DIE_YOU                     = "You drafted a ";
    private static final String         DRAFTED_DIE_OTHER                   = " drafted a ";
    private static final String         RETURNED_DIE_YOU                    = "You returned a ";
    private static final String         RETURNED_DIE_OTHER                  = " returned a ";
    private static final String         GAME_STARTED                        = "Game started! ";
    private static final String         PLAYER_READY_YOU                    = "You ";
    private static final String         PLAYER_READY_OTHER                  = " is ";
    private static final String         PLAYER_READY                        = " ready to play!";
    private static final String         PLAYER_NOT_READY                    = " not ready to play!";
    private static final String         USING_TOOLCARD_YOU                  = "You are using a ";
    private static final String         USING_TOOLCARD_OTHER                = " is using a ";
    private static final String         MOVED_DIE_YOU                       = "You moved a ";
    private static final String         MOVEDDIE_OTHER                      = " moved a ";
    private static final String         BEGUN_TURN_YOU                      = "Your turn begun!";
    private static final String         BEGUN_TURN_OTHER                    = " 's turn begun!";
    private static final String         MODIFIED_DIE_YOU                    = "Your changed a die to ";
    private static final String         MODIFIED_DIE_OTHER                  = " changed a die to ";
    private static final String         DISCONNECTED                        = " has been disconnected from the game";

    private static final String         DIE_VALUE                           = " die with value ";
    private static final String         TO_THE_BOARD                        = " to the board!";
    private static final String         ON_THE_BOARD                        = " on the board!";
    private static final String         TO_THE_DRAFTPOOL                    = " to the draftpool!";
    private static final String         TOOL_CARD                           = " tool card!";

    private View     view;
    private String   notification;

    public NotificationMessageParser(Message message, View view)
    {
        this.view = view;

        notification = view.getStartNotificationString();
        message.acceptVisitor(this);
        notification += view.getEndNotificationString();
    }

    @Override
    public void visit(SelectedPlayerSchemeCardsMessage message)
    {
    }

    @Override
    public void visit(ChosenSchemeCardMessage message)
    {
        if(message.getPlayer().getNickname().equals(view.getAssociatedPlayerNickname()))
            notification += CHOSEN_SCHEME_CARD_YOU;
        else
            notification += view.getColorString(message.getPlayer().getColor())
                    + message.getPlayer().getNickname()
                    + view.getColorEndString()
                    + CHOSEN_SCHEME_CARD_OTHER;

        notification += message.getSchemeBoards().getSchemeCardName() + "!";
    }

    @Override
    public void visit(AddedDieMessage message)
    {
        if(message.getPlayer().getNickname().equals(view.getAssociatedPlayerNickname()))
            notification += ADDED_DIE_YOU;
        else
            notification += view.getColorString(message.getPlayer().getColor())
                    + message.getPlayer().getNickname()
                    + view.getColorEndString()
                    + ADDED_DIE_OTHER;

        notification += view.getColorString(message.getDie().getColor())
                + message.getDie().getColor()
                + view.getColorEndString()
                + DIE_VALUE
                + message.getDie().getValue()
                + TO_THE_BOARD;
    }

    @Override
    public void visit(AddedPlayerMessage message)
    {
        if(message.getPlayer().getNickname().equals(view.getAssociatedPlayerNickname()))
            notification += ADDED_PLAYER_YOU;
        else
            notification += view.getColorString(message.getPlayer().getColor())
                    + message.getPlayer().getNickname()
                    + view.getColorEndString()
                    + ADDED_PLAYER_OTHER;
    }

    @Override
    public void visit(RemovedPlayerMessage message)
    {
        notification += view.getColorString(message.getPlayer().getColor())
                + message.getPlayer().getNickname()
                + view.getColorEndString()
                + REMOVED_PLAYER;
    }

    @Override
    public void visit(DraftedDieMessage message)
    {
        if(message.getPlayer().getNickname().equals(view.getAssociatedPlayerNickname()))
            notification += DRAFTED_DIE_YOU;
        else
            notification += view.getColorString(message.getPlayer().getColor())
                    + message.getPlayer().getNickname()
                    + view.getColorEndString()
                    + DRAFTED_DIE_OTHER;

        notification += view.getColorString(message.getDie().getColor())
                + message.getDie().getColor()
                + view.getColorEndString()
                + DIE_VALUE
                + message.getDie().getValue()
                + "!";
    }

    @Override
    public void visit(ReturnedDieMessage message)
    {
        if(message.getPlayer().getNickname().equals(view.getAssociatedPlayerNickname()))
            notification += RETURNED_DIE_YOU;
        else
            notification += view.getColorString(message.getPlayer().getColor())
                    + message.getPlayer().getNickname()
                    + view.getColorEndString()
                    + RETURNED_DIE_OTHER;

        notification += view.getColorString(message.getDie().getColor())
                + message.getDie().getColor()
                + view.getColorEndString()
                + DIE_VALUE
                + message.getDie().getValue()
                + TO_THE_DRAFTPOOL;
    }

    @Override
    public void visit(SelectedDieMessage message)
    {

    }

    @Override
    public void visit(StartedGameMessage message)
    {
        notification += GAME_STARTED;
    }

    @Override
    public void visit(PlayerReadyMessage message)
    {
        if(message.getPlayer().getNickname().equals(view.getAssociatedPlayerNickname()))
            notification += PLAYER_READY_YOU;
        else
            notification += view.getColorString(message.getPlayer().getColor())
                + message.getPlayer().getNickname()
                + view.getColorEndString()
                + PLAYER_READY_OTHER;

        if(message.isReady())
            notification += PLAYER_READY;
        else
            notification += PLAYER_NOT_READY;
    }

    @Override
    public void visit(UsingToolCardMessage message)
    {
        if(message.getPlayer().getNickname().equals(view.getAssociatedPlayerNickname()))
            notification += USING_TOOLCARD_YOU;
        else
            notification += view.getColorString(message.getPlayer().getColor())
                + message.getPlayer().getNickname()
                + view.getColorEndString()
                + USING_TOOLCARD_OTHER;

        notification += message.getCard().getName()
                + TOOL_CARD;
    }

    @Override
    public void visit(ToolCardEndedMessage message)
    {
    }

    @Override
    public void visit(ChangedDraftedDieMessage message)
    {

    }

    @Override
    public void visit(MovedDieMessage message)
    {
        if(message.getPlayer().getNickname().equals(view.getAssociatedPlayerNickname()))
            notification += MOVED_DIE_YOU;
        else
            notification += view.getColorString(message.getPlayer().getColor())
                    + message.getPlayer().getNickname()
                    + view.getColorEndString()
                    + MOVEDDIE_OTHER;

        notification += view.getColorString(message.getDie().getColor())
                + message.getDie().getColor()
                + view.getColorEndString()
                + DIE_VALUE
                + message.getDie().getValue()
                + ON_THE_BOARD;
    }

    @Override
    public void visit(BegunTurnMessage message)
    {
        if(message.getPlayer().getNickname().equals(view.getAssociatedPlayerNickname()))
            notification += BEGUN_TURN_YOU;
        else
            notification += view.getColorString(message.getPlayer().getColor())
                    + message.getPlayer().getNickname()
                    + view.getColorEndString()
                    + BEGUN_TURN_OTHER;
    }

    @Override
    public void visit(ModifiedDieMessage message)
    {
        if(message.getPlayer().getNickname().equals(view.getAssociatedPlayerNickname()))
            notification += MODIFIED_DIE_YOU;
        else
            notification += view.getColorString(message.getPlayer().getColor())
                    + message.getPlayer().getNickname()
                    + view.getColorEndString()
                    + MODIFIED_DIE_OTHER;

        notification += view.getColorString(message.getDie().getColor())
                + message.getDie().getColor()
                + view.getColorEndString()
                + DIE_VALUE
                + message.getDie().getValue()
                + "!";
    }

    @Override
    public void visit(ReRolledDraftPoolMessage message)
    {
    }

    @Override
    public void visit(UpdatedStartTimerMessage message) {

    }

    @Override
    public void visit(DisconnectedPlayerMessage message)
    {
        notification += view.getColorString(message.getPlayer().getColor())
                + message.getPlayer().getNickname()
                + view.getColorEndString()
                + DISCONNECTED;

        notification += "!";
    }

    @Override
    public void visit(UpdatedTurnTimerMessage message) {

    }

    public void showNotification()
    {
        if(!notification.equals(view.getStartNotificationString()+view.getEndNotificationString()))
            view.showNotification(notification);
    }
}
