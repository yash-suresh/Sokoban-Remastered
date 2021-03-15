package sample.IO;

import sample.GameController;
import sample.Menu2Controller;
import java.io.*;
import java.util.*;

/**
 * this class creates the functionality which write the
 * score from each level to a separate file
 * @author Yash Suresh
 */

public class writer
{
    private static writer m_single_instance;
    private static ArrayList<String> m_scoresList = new ArrayList<>();
    private static String m_writePath = null;
    private static String m_readPath = null;


    /**
     * creating the singleton class
     * @return m_single_instance: instance of the class
     */
    public static writer getInstance()
    {
        if (m_single_instance == null)
            m_single_instance = new writer();

        return m_single_instance;
    }

    /**
     * getter for the filepath which the function writes to
     * @return m_writePath: stores the file path
     */
    public static String getWritePath()
    {
        return m_writePath;
    }

    /**
     * setter for filepath which function writes to
     * @param writePath the String value
     */
    public static void setWritePath(String writePath) {
        writer.m_writePath = writePath;
    }

    /**
     * getter for the file path which is read from
     * @return m_readPath stores the String value of the path
     */
    public static String getReadPath()
    {
        return m_readPath;
    }

    /**
     * setter for the file path which is read from
     * @param readPath stores the String path
     */
    public static void setReadPath(String readPath)
    {
        writer.m_readPath = readPath;
    }

    /**
     * getter for the arraylist which stores the scores per level
     * @return m_scoresList the arraylist which stores the scores
     */
    public static ArrayList<String> getScoresList()
    {

        return m_scoresList;
    }


    /**
     * this function writes the scores to a file
     * @param x passes the move count from GameController
     */
    public static void main(int x)
    {

        try {
            setWritePath("src/sample/resources/" +
                    (GameController.getLevelCount() - 1)
                    + "highScores.txt");

            FileWriter test = new FileWriter
                    (getWritePath(), true);
            BufferedWriter bw = new BufferedWriter(test);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(x + ", " +
                    Menu2Controller.getName());
            pw.close();

        } catch (IOException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }


    /**
     * this function reads the scores from a txt file and saves them
     * to an ArrayList
     * @throws FileNotFoundException if error loading the txt file
     *                               which stores the scores
     *
     */
    public static void read() throws FileNotFoundException
    {
        setReadPath("src/sample/resources/" +
                (GameController.getLevelCount() - 1) + "highScores.txt");

        BufferedReader br = new BufferedReader(new FileReader(getReadPath()));
        String line;

        try {
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] playerScores = line.split(",");
                getScoresList().add(line);

            }
            Collections.sort(getScoresList());
            System.out.println(getScoresList());

        } catch (IOException e) {

            e.printStackTrace();
        }


    }


}



