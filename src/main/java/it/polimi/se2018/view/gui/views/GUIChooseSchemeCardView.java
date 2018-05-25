package it.polimi.se2018.view.gui.views;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.Cell;
import it.polimi.se2018.network.exceptions.CannotCreateServerException;
import it.polimi.se2018.network.server.Server;
import it.polimi.se2018.utils.Color;
import it.polimi.se2018.view.gui.GUI;
import it.polimi.se2018.view.gui.elements.GUIElementBoard;
import it.polimi.se2018.view.gui.elements.GUIElementCell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIChooseSchemeCardView extends GUIView
{
    public GUIChooseSchemeCardView(GUI gui)
    {
        super(gui, 710,600);




    }

    public void draw()
    {
        mainContainer = new Container();
        mainContainer.setLayout(new FlowLayout());

        Container container = new Container();
        container.setLayout(new FlowLayout());


        mainContainer.add(new GUIElementBoard(new Board()));

    }
}

