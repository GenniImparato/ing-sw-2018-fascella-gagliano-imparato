package it.polimi.se2018.view.gui.dialogs;

import it.polimi.se2018.view.cli.views.CLIView;
import it.polimi.se2018.view.gui.GUI;
import it.polimi.se2018.view.gui.views.GUIView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIOKDialog extends GUIDialog
{
    public GUIOKDialog(GUI gui, String title, String message)
    {
        super(gui, title);
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

        messageContainer.add(Box.createVerticalStrut(40));

        JTextArea errorLabel = new JTextArea(message);
        errorLabel.setBackground(null);
        errorLabel.setOpaque(false);
        errorLabel.setLineWrap(true);
        errorLabel.setEditable(false);
        errorLabel.setMaximumSize(new Dimension(420, 80));
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageContainer.add(errorLabel);

        messageContainer.add(Box.createVerticalStrut(40));


        Container buttonContainer = new Container();
        buttonContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        backgroundLabel.add(buttonContainer);

        JButton closeButton = new JButton();
        closeButton.setPreferredSize(new Dimension(150, 70));
        closeButton.setIcon(new ImageIcon("resources/images/menu/ok.png"));

        closeButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setVisible(false);
            }
        });

        buttonContainer.add(closeButton);

        validate();
        pack();
        setLocationRelativeTo(gui.getMainWindow());
        setVisible(true);
    }
}
