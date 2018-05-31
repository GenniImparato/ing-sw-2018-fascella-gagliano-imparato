package it.polimi.se2018.files;

import it.polimi.se2018.controller.ToolCard;
import it.polimi.se2018.controller.tool_card.InvalidToolCardActionException;
import it.polimi.se2018.controller.tool_card.ToolCardActionFactory;
import it.polimi.se2018.files.exceptions.CannotReadFileException;
import it.polimi.se2018.files.exceptions.InvalidFileException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SagradaToolCardFile extends File
{
    private ToolCard toolCard;

    public SagradaToolCardFile(String filename) throws InvalidFileException, CannotReadFileException
    {
        super(filename);

        toolCard = new ToolCard(getCardName(filename));

        read();
    }

    private void read() throws InvalidFileException, CannotReadFileException
    {
        try(Scanner scanner = new Scanner(this))
        {
            //read until it reaches the end of file
            while(scanner.hasNext())
            {
                String          actionName = scanner.next();
                int             parametersNumber;
                String          param = "";

                try
                {
                    //if the action read is not valid the file is not valid too
                    parametersNumber = ToolCardActionFactory.getParameterNumber(actionName);
                }
                catch(InvalidToolCardActionException e)
                {
                    throw new InvalidFileException("File not valid: " + e.getMessage());
                }

                if(parametersNumber != 0)
                {
                    //check if the file ends before the parameters required for the current action
                    if(!scanner.hasNext())
                        throw new InvalidFileException("File not valid: " + actionName + " requires a parameter");

                    param =  scanner.next();
                }

                //action name and parameters read
                //tries too create a ToolCardAction
                try
                {
                    toolCard.addAction(ToolCardActionFactory.createAction(actionName, param));
                }
                catch(InvalidToolCardActionException e)
                {
                    throw new InvalidFileException("File not valid: " + e.getMessage());
                }
            }
        }
        catch(IOException e)
        {
            throw new CannotReadFileException();
        }
    }

    private String getCardName(String filename)
    {
        return filename.replaceAll(".*[\\\\/]|\\.[^\\.]*$","");
    }

    public ToolCard getToolCard()
    {
        return toolCard;
    }
}
