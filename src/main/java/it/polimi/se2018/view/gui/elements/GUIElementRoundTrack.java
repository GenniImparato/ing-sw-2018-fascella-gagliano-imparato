package it.polimi.se2018.view.gui.elements;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.RoundTrack;
import it.polimi.se2018.view.gui.GUI;
import it.polimi.se2018.view.gui.elements.animations.GUIAnimationActions;

import javax.swing.*;
import java.awt.*;

public class GUIElementRoundTrack extends JPanel
{
    private RoundTrack roundTrack;
    private GUIElementRoundCell roundCells[];
    private GUIElementDraftPool guiDraftPool;
    private GUI gui;

    public GUIElementRoundTrack(RoundTrack roundTrack, GUIElementDraftPool guiDraftPool, GUI gui)
    {
        this.gui = gui;
        this.roundTrack = roundTrack;
        this.guiDraftPool = guiDraftPool;

        setLayout(new FlowLayout());

        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(new ImageIcon("resources/images/elements/roundtrack/back.png"));
        add(backgroundLabel);

        backgroundLabel.setLayout(new BoxLayout(backgroundLabel, BoxLayout.X_AXIS));

        backgroundLabel.add(Box.createHorizontalStrut(30));

        roundCells = new GUIElementRoundCell[10];

        for(int i=0; i<10; i++)
        {
            roundCells[i] = new GUIElementRoundCell(i+1, gui);
            roundCells[i].setAlignmentY(Component.CENTER_ALIGNMENT);
            backgroundLabel.add(roundCells[i]);
            backgroundLabel.add(Box.createHorizontalGlue());
        }

        backgroundLabel.add(Box.createHorizontalStrut(30));
    }

    public void refresh(RoundTrack roundTrack)
    {
        this.roundTrack = roundTrack;

        for(GUIElementDie guiDie : guiDraftPool.getGUIDice())
        {
            for(int round=0; round<10; round++)
            {
                for (Die roundTrackDie : roundTrack.getDiceAtRound(round))
                {
                    if (guiDie.getDie().isSameDie(roundTrackDie))
                        guiDie.playMoveToRoundTrackAnimation(roundCells[round]);
                }
            }
        }
    }
}
