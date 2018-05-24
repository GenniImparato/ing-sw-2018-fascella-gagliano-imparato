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
        backgroundLabel.setPreferredSize(new Dimension(300, 200));
        backgroundLabel.setIcon(new ImageIcon("resources/images/menu/dialogback.png"));
        mainContainer.add(backgroundLabel);

        backgroundLabel.setLayout(new GridLayout(2, 1));

        JLabel  errorLabel = new JLabel (message, JLabel.CENTER);
        errorLabel.setBackground(null);
        errorLabel.setOpaque(false);
        backgroundLabel.add(errorLabel);


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
