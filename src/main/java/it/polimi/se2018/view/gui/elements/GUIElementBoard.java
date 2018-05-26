package it.polimi.se2018.view.gui.elements;

import it.polimi.se2018.model.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GUIElementBoard extends JDesktopPane
{
    private Board               board;
    private GUIElementCell[][]  cell;
    private GUIElementDie[][]   dice;
    private boolean             selectable;
    private GUIBoardActions     actions;

    public GUIElementBoard(Board board)
    {
        this.selectable = false;
        this.setLayout(new GridLayout(Board.ROWS, Board.COLUMNS));

        cell = new GUIElementCell[Board.ROWS][Board.COLUMNS];
        dice = new GUIElementDie[Board.ROWS][Board.COLUMNS];

        for(int i=0; i<Board.ROWS; i++)
        {
            for(int j=0; j<Board.COLUMNS; j++)
            {
                cell[i][j] = new GUIElementCell(board.getCell(i,j));
                cell[i][j].setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
                this.add(cell[i][j]);
            }
        }

        for(int i=0; i<Board.ROWS; i++)
        {
            for(int j=0; j<Board.COLUMNS; j++)
            {
                if(board.getDie(i,j)!=null)
                {
                    dice[i][j] = new GUIElementDie(board.getDie(i,j));
                    cell[i][j].add(dice[i][j]);
                }
            }
        }



        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if(selectable && actions!=null)
                    actions.clicked();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e)
            {
                if(selectable)
                    showSelected();
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                if(selectable)
                    showNormal();
            }
        });

        this.setMaximumSize(new Dimension(350,280));
        this.setMinimumSize(new Dimension(350,280));
        this.setSize(new Dimension(350,280));

    }

    public void setSelectable(boolean selectable)
    {
        if(!selectable)
            showNormal();
        else
        {
            setSelectableCells(false);  //prevents that the two selectable mode be active at the same time
            setSelectableDice(false);
        }


        this.selectable=selectable;
    }

    public void setSelectableCells(boolean selectable)
    {
        if(selectable)
        {
            setSelectable(false);           //prevents that the two selectable mode be active at the same time
            setSelectableDice(false);
        }


        for(int i=0; i<Board.ROWS; i++)
        {
            for(int j=0; j<Board.COLUMNS;j++)
            {
                cell[i][j].setSelectable(selectable);
            }
        }
    }

    public void setSelectableDice(boolean selectable)
    {
        if(selectable)
        {
            setSelectable(false);
            setSelectableCells(false);
        }

        for(int i=0; i<Board.ROWS; i++)
        {
            for(int j=0; j<Board.COLUMNS;j++)
            {
                if(dice[i][j]!=null)
                    dice[i][j].setSelectable(selectable);
            }
        }
    }

    public boolean getSelectable()
    {
        return selectable;
    }


    public void showSelected()
    {
        for(int i=0; i<Board.ROWS; i++)
        {
            for(int j=0; j<Board.COLUMNS;j++)
            {
                cell[i][j].showSelectedIcon();
            }
        }
    }

    public void showNormal()
    {
        for(int i=0; i<Board.ROWS; i++)
        {
            for(int j=0; j<Board.COLUMNS;j++)
            {
                cell[i][j].showNormalIcon();
            }
        }

    }

    public void setActions(GUIBoardActions actions)
    {
        this.actions = actions;
    }

}
