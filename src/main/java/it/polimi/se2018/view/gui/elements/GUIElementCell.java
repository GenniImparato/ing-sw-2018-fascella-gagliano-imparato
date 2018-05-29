package it.polimi.se2018.view.gui.elements;

import it.polimi.se2018.model.Cell;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GUIElementCell extends JLabel
{
    private     Cell            cell;
    protected   String          path;
    private     boolean         selectable;

    private     GUICellAction   actions;
    private     GUIElementCell  thisElement;

    public GUIElementCell(Cell cell)
    {
        this.cell=cell;
        this.selectable=false;

        thisElement = this;

        path = "resources/images/elements/cell/";

        //sets the image without restrictions
        if(!cell.getRestriction().isValue() && !cell.getRestriction().isColor())
            path+="empty";

        //sets the image of the cell with its proper color restriction
        if(cell.getRestriction().isColor())
            path+=cell.getRestriction().getColor().toString().toLowerCase();

        //sets the image of the cell with its proper value restriction
        if(cell.getRestriction().isValue())
            path+=cell.getRestriction().getValue();

        showNormalIcon();

        this.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
            }

            @Override
            public void mousePressed(MouseEvent e)
            {
                if(selectable)
                {
                    if(actions != null)
                        actions.clicked(thisElement);
                }
                else
                    e.getComponent().getParent().dispatchEvent(e);
            }
            @Override
            public void mouseReleased(MouseEvent e)
            {

            }

            @Override
            public void mouseEntered(MouseEvent e)
            {
                if(selectable)
                    showSelectedIcon();
                else
                    e.getComponent().getParent().dispatchEvent(e);
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                if(selectable)
                    showNormalIcon();
                else
                    e.getComponent().getParent().dispatchEvent(e);

            }

            });
    }

    public void setSelectable(boolean selectable)
    {
        if(!selectable)
        {
            showNormalIcon();
        }
        else
            this.selectable=selectable;
    }

    public boolean getSelectable()
    {
        return selectable;
    }

    public void showSelectedIcon()
    {
        this.setIcon(new ImageIcon(path+"selected.png"));
    }

    public void showNormalIcon()
    {
        this.setIcon(new ImageIcon(path+".png"));
    }

    public void setActions(GUICellAction actions)
    {
        this.actions = actions;
    }

}




