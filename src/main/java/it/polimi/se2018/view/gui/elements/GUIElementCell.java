package it.polimi.se2018.view.gui.elements;

import it.polimi.se2018.model.Cell;
import it.polimi.se2018.utils.Color;

import javax.swing.*;

public class GUIElementCell extends JLabel
{
    private Cell cell;

    public GUIElementCell(Cell cell)
    {
        super();
        this.cell=cell;

        String path = "resources/images/elements/cell/";

        //sets the image without restrictions
        if(!cell.getRestriction().isValue() && !cell.getRestriction().isColor())
            this.setIcon(new ImageIcon(path+"empty.png"));

        //sets the image of the cell with its proper color restriction
        if(cell.getRestriction().isColor())
            this.setIcon(new ImageIcon(path+cell.getRestriction().getColor().toString().toLowerCase()+".png"));

        //sets the image of the cell with its proper value restriction
        if(cell.getRestriction().isValue())
            this.setIcon(new ImageIcon(path+cell.getRestriction().getValue()+".png"));

    }




}
