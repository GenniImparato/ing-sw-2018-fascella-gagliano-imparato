package it.polimi.se2018.view.gui.elements;


import it.polimi.se2018.view.gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GUIElementRoundCell extends JLabel
{
    private List<GUIElementDie> guiDice;
    private GUIRoundTrackPopup  popup;
    private GUI                 gui;

    public GUIElementRoundCell(int roundNumber, GUI gui)
    {
        this.gui = gui;
        guiDice = new ArrayList<>();

        popup = new GUIRoundTrackPopup(this, gui);

        setIcon(new ImageIcon("resources/images/elements/roundtrack/" + roundNumber + ".png"));
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
    }

    public void addDie(GUIElementDie guiDie)
    {
        guiDice.add(guiDie);

        if(guiDice.size() == 1)
        {
            add(guiDie);
        }
        else if(guiDice.size() > 1)
        {
            guiDice.get(0).setActions(new GUIDieActions()
            {
                @Override
                public void clicked(GUIElementDie die)
                {}

                @Override
                public void mouseEntered()
                {
                    popup.show();
                }

                @Override
                public void mouseExited()
                {
                    popup.hide();
                }
            });
        }

        System.out.println(guiDice.size());
    }

    public List<GUIElementDie> getDice()
    {
        return guiDice;
    }
}
