package it.polimi.se2018.view.gui.elements;

public interface GUIBoardActions
{
    void clicked(GUIElementBoard board);
    void clickedCell(GUIElementBoard board, int row, int column);
    void clickedDie(GUIElementDie die, int row, int column);
}
