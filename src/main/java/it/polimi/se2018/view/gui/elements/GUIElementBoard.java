package it.polimi.se2018.view.gui.elements;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.Cell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GUIElementBoard extends JPanel
{
    private Board board;
    private GUIElementCell[][] cell;

    public GUIElementBoard(Board board)
    {
        super();
        this.setLayout(new GridLayout(Board.ROWS, Board.COLUMNS));

        cell = new GUIElementCell[Board.ROWS][Board.COLUMNS];

        for(int i=0; i<Board.ROWS; i++)
        {
            for(int j=0; j<Board.COLUMNS; j++)
            {
                cell[i][j] = new GUIElementCell(board.getCell(i,j));

                cell[i][j].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
                this.add(cell[i][j]);
            }
        }

    }

}
