package it.polimi.se2018.view.gui.elements;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.view.gui.elements.animations.GUICellAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GUIElementBoard extends JDesktopPane
{
    private Board               board;
    private GUIElementCell[][]  guiCells;
    private GUIElementDie[][]   dice;
    private boolean             selectable;
    private GUIBoardActions     actions;

    private GUIElementBoard     thisElement;

    public GUIElementBoard(Board board)
    {
        this.selectable = false;
        this.thisElement = this;
        this.board = board;

        this.setLayout(new GridLayout(Board.ROWS, Board.COLUMNS));

        guiCells = new GUIElementCell[Board.ROWS][Board.COLUMNS];
        dice = new GUIElementDie[Board.ROWS][Board.COLUMNS];

        for(int i=0; i<Board.ROWS; i++)
        {
            for(int j=0; j<Board.COLUMNS; j++)
            {
                guiCells[i][j] = new GUIElementCell(board.getCell(i,j));
                guiCells[i][j].setActions(new GUICellAction()
                {
                    @Override
                    public void clicked(GUIElementCell cell)
                    {
                        if(actions != null)
                            actions.clickedCell(thisElement, getCellRow(cell), getCellColumn(cell));
                    }
                });
                guiCells[i][j].setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
                this.add(guiCells[i][j]);
            }
        }

        placeDice();

        this.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if(selectable && actions!=null)
                    actions.clicked(thisElement);
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
                guiCells[i][j].setSelectable(selectable);
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
                guiCells[i][j].showSelectedIcon();
            }
        }
    }

    public void showNormal()
    {
        for(int i=0; i<Board.ROWS; i++)
        {
            for(int j=0; j<Board.COLUMNS;j++)
            {
                guiCells[i][j].showNormalIcon();
            }
        }

    }

    public void setActions(GUIBoardActions actions)
    {
        this.actions = actions;
    }

    private int getCellRow(GUIElementCell cell)
    {
        for(int row = 0; row < Board.ROWS; row++)
        {
            for(int col = 0; col < Board.COLUMNS; col++)
            {
                if(guiCells[row][col] == cell)
                    return row;
            }
        }

        return -1;
    }

    private int getCellColumn(GUIElementCell cell)
    {
        for(int row = 0; row < Board.ROWS; row++)
        {
            for(int col = 0; col < Board.COLUMNS; col++)
            {
                if(guiCells[row][col] == cell)
                    return col;
            }
        }

        return -1;
    }

    public boolean contains(Die die)
    {
        for(int i=0; i<Board.ROWS; i++)
        {
            for(int j=0; j<Board.COLUMNS; j++)
            {
                if(dice[i][j] != null)
                    if(dice[i][j].getDie().isSameDie(die))
                        return true;
            }
        }

        return false;
    }

    public void refresh(Board board)
    {
        this.board = board;
        placeDice();
    }

    private void placeDice()
    {
        //add new dies to the board
        for(int i=0; i<Board.ROWS; i++)
        {
            for(int j=0; j<Board.COLUMNS; j++)
            {
                if(board.getDie(i,j)!=null  && !contains(board.getDie(i,j)))
                {
                    dice[i][j] = new GUIElementDie(board.getDie(i,j), false);
                    guiCells[i][j].add(dice[i][j]);
                }
            }
        }

        //remove
        for(int i=0; i<Board.ROWS; i++)
        {
            for(int j=0; j<Board.COLUMNS; j++)
            {
                if(dice[i][j] != null && board.getDie(i, j) == null)
                {
                    guiCells[i][j].remove(dice[i][j]);
                    dice[i][j] = null;
                }
            }
        }
    }

}
