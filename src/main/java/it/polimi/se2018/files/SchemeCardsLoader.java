package it.polimi.se2018.files;

import it.polimi.se2018.files.exceptions.CannotReadFileException;
import it.polimi.se2018.files.exceptions.InvalidFileException;
import it.polimi.se2018.files.exceptions.LoadingFilesException;
import it.polimi.se2018.model.Board;
import it.polimi.se2018.utils.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Class used to load the scheme cards from file
 */
public class SchemeCardsLoader
{
    private String          directoryPath;
    private Logger          logger;

    private List<String>    schemeCardFileNames;
    private List<Board>     generatedBoards;

    /**
     * Constructor.
     * @param directoryPath path of the directory of the files
     * @param logger logger
     * @throws LoadingFilesException if files cannot be loaded
     */
    public SchemeCardsLoader(String directoryPath, Logger logger) throws LoadingFilesException
    {
        schemeCardFileNames = new ArrayList<>();
        generatedBoards = new ArrayList<>();

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
        logger.logMessage("Generated " + generatedBoards.size() + " scheme boards.");
    }

    /**
     * Finds all the files in a directory
     * @throws LoadingFilesException if files cannot be loaded
     */
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
            if(isSagradaSchemeCardFile(files[i]))
                schemeCardFileNames.add(files[i]);
        }
    }

    /**
     * Read all files that are found in the directory
     */
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

                generatedBoards.add(schemeCardFile.generateBoard());

                logger.logMessage("Scheme board generated.");
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

    /**
     * Tells if a file has a sagradaschemecard extension
     * @param filename name of the file
     * @return true if it's a sagradaschemecard file, false otherwise
     */
    private boolean isSagradaSchemeCardFile(String filename)
    {
        String extension = "";

        int i = filename.lastIndexOf('.');
        if (i > 0)
            extension = filename.substring(i+1);

        return extension.equals("sagradaschemecard");
    }

    /**
     * Return the list of scheme cards
     * @return list of scheme cards
     */
    public List<Board> getGeneratedBoards()
    {
        List<Board> retBoards = new ArrayList<>();

        for(Board board : generatedBoards)
            retBoards.add(new Board(board));

        return retBoards;
    }
}
