package it.polimi.se2018.view.gui.dialogs;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.view.gui.GUI;
import it.polimi.se2018.view.gui.elements.GUIElementDie;

import javax.swing.*;
import java.awt.*;

public class GUIDrawDieDialog extends GUIDialog
{
    private int response;

    public GUIDrawDieDialog(GUI gui, Die die)
    {
        super(gui, "Choose a value for the die!");
        Container mainContainer = new Container();
        mainContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        setContentPane(mainContainer);
        setResizable(false);

        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setPreferredSize(new Dimension(500, 200));
        backgroundLabel.setIcon(new ImageIcon("resources/images/menu/dialogback.png"));
        mainContainer.add(backgroundLabel);

        backgroundLabel.setLayout(new GridLayout(2, 1));

        Container messageContainer = new Container();
        messageContainer.setLayout(new BoxLayout(messageContainer, BoxLayout.Y_AXIS));
        backgroundLabel.add(messageContainer);

        messageContainer.add(Box.createVerticalStrut(5));

        Container diceContainer = new Container();
        diceContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 20));
        messageContainer.add(diceContainer);

        for(int i=1; i<=7; i++)
        {
            Die shownDie = new Die(die.getColor());
            shownDie.setValue(i);
            GUIElementDie guiDie = new GUIElementDie(shownDie, false, gui);
            guiDie.setSelectable(true);
            diceContainer.add(guiDie);
        }

        messageContainer.add(Box.createVerticalStrut(5));


        Container buttonContainer = new Container();
        buttonContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 0));
        backgroundLabel.add(buttonContainer);

        JRadioButton addButton = new JRadioButton("Add To Board");
        addButton.setOpaque(false);
        addButton.setFont(new Font("SansSerif", Font.PLAIN, 23));
        buttonContainer.add(addButton);

        JRadioButton returnButton = new JRadioButton("Return to the DraftPool");
        returnButton.setOpaque(false);
        returnButton.setFont(new Font("SansSerif", Font.PLAIN, 23));
        buttonContainer.add(returnButton);

        ButtonGroup group = new ButtonGroup();
        group.add(addButton);
        group.add(returnButton);


        validate();
        pack();
        setLocationRelativeTo(gui.getMainWindow());
        setVisible(true);
    }

    public int getResponse()
    {
        return response;
    }
}
