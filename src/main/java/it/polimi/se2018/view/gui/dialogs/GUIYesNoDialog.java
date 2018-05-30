package it.polimi.se2018.view.gui.dialogs;

import it.polimi.se2018.view.gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIYesNoDialog extends GUIDialog
{
    public static final int     YES = 0;
    public static final int     NO = 1;

    private int response;

    public GUIYesNoDialog(GUI gui, String title, String message)
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
        errorLabel.setFont(new Font("SansSerif", Font.PLAIN, 23));
        messageContainer.add(errorLabel);

        messageContainer.add(Box.createVerticalStrut(40));


        Container buttonContainer = new Container();
        buttonContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 20));
        backgroundLabel.add(buttonContainer);

        JButton yesButton = new JButton();
        yesButton.setPreferredSize(new Dimension(150, 70));
        yesButton.setIcon(new ImageIcon("resources/images/menu/yes.png"));
        buttonContainer.add(yesButton);

        JButton noButton = new JButton();
        noButton.setPreferredSize(new Dimension(150, 70));
        noButton.setIcon(new ImageIcon("resources/images/menu/no.png"));
        buttonContainer.add(noButton);

        yesButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                response = YES;
                setVisible(false);
            }
        });

        noButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                response = NO;
                setVisible(false);
            }
        });


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
