package it.polimi.se2018.view.gui.elements;

import it.polimi.se2018.model.RoundTrack;

import javax.swing.*;
import java.awt.*;

public class GUIElementRoundTrack extends JPanel
{
    private RoundTrack roundTrack;

    public GUIElementRoundTrack(RoundTrack roundTrack)
    {
        this.roundTrack = roundTrack;

        setLayout(new FlowLayout());

        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(new ImageIcon("resources/images/elements/roundtrack/back.png"));
        add(backgroundLabel);

        backgroundLabel.setLayout(new BoxLayout(backgroundLabel, BoxLayout.X_AXIS));

        backgroundLabel.add(Box.createHorizontalStrut(30));

        for(int i=1; i<=10; i++)
        {
            JLabel roundLabel = new JLabel();
            roundLabel.setIcon(new ImageIcon("resources/images/elements/roundtrack/" + i + ".png"));
            roundLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
            backgroundLabel.add(roundLabel);

            if(i<10)
                backgroundLabel.add(Box.createHorizontalGlue());
        }

        backgroundLabel.add(Box.createHorizontalStrut(30));
    }
}
