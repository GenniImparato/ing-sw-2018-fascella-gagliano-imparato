package it.polimi.se2018.files;

import it.polimi.se2018.files.exceptions.CannotReadFileException;
import it.polimi.se2018.files.exceptions.FileNotReadException;
import it.polimi.se2018.files.exceptions.InvalidFileException;
import it.polimi.se2018.files.exceptions.LoadingFilesException;
import it.polimi.se2018.model.Board;
import it.polimi.se2018.utils.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SchemeCardLoader
{
    private String          directoryPath;
    private Logger          logger;

    private List<String>    schemeCardFileNames;
    private List<Board>     generatedBoard;

    public SchemeCardLoader(String directoryPath, Logger logger) throws LoadingFilesException
    {
        schemeCardFileNames = new ArrayList<>();
        generatedBoard = new ArrayList<>();

        this.directoryPath = directoryPath;
        this.logger = logger;

        logger.logMessage("Loading scheme card files...");
        logger.logMessage("Opening directory: " + directoryPath + "...");

        findAllFilesInDirectory();

        logger.logMessage("Found " + schemeCardFileNames.size() + " sagradaschemecard files!");
        logger.logMessage("Parsing files...");
        logger.logMessage("");

        readAllFiles();

        logger.logMessage("");
        logger.logMessage("File loading finished.");
        logger.logMessage("Generated " + generatedBoard.size() + " scheme boards.");
    }

    private void findAllFilesInDirectory() throws LoadingFilesException
    {
        File directory = new File(directoryPath);

        if(!directory.exists())
        {
            logger.logErrorMessage("Error in loading files: " + directoryPath + " doesn't exist!");
            throw new LoadingFilesException("Error in loading files: " + directoryPath + " doesn't exist!");
        }
        if(!directory.isDirectory())
        {
            logger.logErrorMessage("Error in loading files: " + directoryPath + " is not a directory!");
            throw new LoadingFilesException("Error in loading files: " + directoryPath + " is not a directory!");
        }

        String[] files = directory.list();

        for(int i=0; i< files.length; i++)
        {
            if(isSagradaSchemeCardFile(files[i]))
                schemeCardFileNames.add(files[i]);
        }
    }

    private void readAllFiles()
    {
        for(String filename : schemeCardFileNames)
        {
            try
            {
                logger.logMessage("Parsing " + filename + "...");

                SagradaSchemeCardFile schemeCardFile = new SagradaSchemeCardFile(directoryPath + filename);

                logger.logMessage("File is valid.");
                logger.logMessage("File read correctly.");

                generatedBoard.add(schemeCardFile.generateBoard());

                logger.logMessage("Scheme board generated.");
                logger.logMessage("");
            }
            catch(InvalidFileException e)
            {
                logger.logErrorMessage(e.getMessage());
                logger.logMessage("File ignored!");
                logger.logMessage("");
            }
            catch(CannotReadFileException|FileNotReadException e)
            {
                logger.logErrorMessage("Cannot read file!");
                logger.logMessage("File ignored");
                logger.logMessage("");
            }
        }
    }

    private boolean isSagradaSchemeCardFile(String filename)
    {
        String extension = "";

        int i = filename.lastIndexOf('.');
        if (i > 0)
            extension = filename.substring(i+1);

        return extension.equals("sagradaschemecard");
    }
}
