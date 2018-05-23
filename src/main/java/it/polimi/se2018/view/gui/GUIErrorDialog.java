package it.polimi.se2018.view.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIErrorDialog
{
    public GUIErrorDialog(GUI gui, String title, String message)
    {
        JDialog dialog = new JDialog(gui.getMainWindow(), title, true);
        Container mainContainer = new Container();
        mainContainer.setLayout(new FlowLayout(FlowLayout.CENTER));

        dialog.setContentPane(mainContainer);
        dialog.setResizable(false);

        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setPreferredSize(new Dimension(300, 200));
        backgroundLabel.setIcon(new ImageIcon("resources/images/menu/dialogback.png"));
        mainContainer.add(backgroundLabel);

        backgroundLabel.setLayout(new FlowLayout(FlowLayout.CENTER,0, 40));

        JLabel errorLabel = new JLabel(message, JLabel.CENTER);
        errorLabel.setPreferredSize(new Dimension(200, 30));
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        backgroundLabel.add(errorLabel);

        backgroundLabel.add(Box.createHorizontalGlue());

        JButton closeButton = new JButton();
        closeButton.setPreferredSize(new Dimension(150, 70));
        closeButton.setIcon(new ImageIcon("resources/images/menu/ok.png"));

        closeButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dialog.setVisible(false);
            }
        });

        backgroundLabel.add(closeButton);

        dialog.validate();
        dialog.pack();
        dialog.setLocationRelativeTo(gui.getMainWindow());
        dialog.setVisible(true);
    }
}
