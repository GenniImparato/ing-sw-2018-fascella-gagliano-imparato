package it.polimi.se2018.files;

import it.polimi.se2018.controller.ToolCard;
import it.polimi.se2018.files.exceptions.CannotReadFileException;
import it.polimi.se2018.files.exceptions.InvalidFileException;
import it.polimi.se2018.files.exceptions.LoadingFilesException;
import it.polimi.se2018.model.Board;
import it.polimi.se2018.utils.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ToolCardsLoader
{
    private String          directoryPath;
    private Logger          logger;

    private List<String>    toolCardsFileNames;
    private List<ToolCard>  toolCards;

    public ToolCardsLoader(String directoryPath, Logger logger) throws LoadingFilesException
    {
        toolCardsFileNames = new ArrayList<>();
        toolCards = new ArrayList<>();

        this.directoryPath = directoryPath;
        this.logger = logger;

        logger.logMessage("Loading tool card files...");
        logger.logMessage("Opening directory: " + directoryPath + "...");

        findAllFilesInDirectory();

        logger.logMessage("Found " + toolCardsFileNames.size() + " tool card files!");
        logger.logMessage("Parsing files...");
        logger.logMessage("");

        readAllFiles();

        logger.logMessage("");
        logger.logMessage("File loading finished.");
        logger.logMessage("Loaded " + toolCards.size() + " tool cards.");
    }

    private void findAllFilesInDirectory() throws LoadingFilesException
    {
        File directory = new File(directoryPath);

        if(!directory.exists())
        {
            logger.logErrorMessage("Error in loading files: " + directoryPath + " doesn't exist!");
            throw new LoadingFilesException();
        }
        if(!directory.isDirectory())
        {
            logger.logErrorMessage("Error in loading files: " + directoryPath + " is not a directory!");
            throw new LoadingFilesException();
        }

        String[] files = directory.list();

        for(int i=0; i< files.length; i++)
        {
            if(isToolCardCardFile(files[i]))
                toolCardsFileNames.add(files[i]);
        }
    }

    private void readAllFiles()
    {
        for(String filename : toolCardsFileNames)
        {
            try
            {
                logger.logMessage("Parsing " + filename + "...");

                SagradaToolCardFile toolCardFile = new SagradaToolCardFile(directoryPath + filename);

                logger.logMessage("File is valid.");
                logger.logMessage("File read correctly.");

                toolCards.add(toolCardFile.getToolCard());

                logger.logMessage("Tool card loaded.");
                logger.logMessage("");
            }
            catch(InvalidFileException e)
            {
                logger.logErrorMessage(e.getMessage());
                logger.logMessage("File ignored!");
                logger.logMessage("");
            }
            catch(CannotReadFileException e)
            {
                logger.logErrorMessage("Cannot read file!");
                logger.logMessage("File ignored");
                logger.logMessage("");
            }
        }
    }

    private boolean isToolCardCardFile(String filename)
    {
        String extension = "";

        int i = filename.lastIndexOf('.');
        if (i > 0)
            extension = filename.substring(i+1);

        return extension.equals("sagradatoolcard");
    }

    public List<ToolCard> getToolCards()
    {
        return toolCards;
    }
}
