package sample.engine;

import sample.GameController;

import java.awt.*;
import java.util.Iterator;
import java.util.List;

import static sample.engine.GameGrid.translatePoint;


/**
 * This handles the parsing of the .skb file and putting the object in the 2d array.
 * @author Yash Suresh-modified
 */

public final class Level implements Iterable<GameObject>
{
    private final GameGrid m_objectsGrid;
    private final GameGrid m_diamondsGrid;
    private final int m_index;
    private int m_numberOfDiamonds = 0;
    private Point m_keeperPosition = new Point(0, 0);


    /**
     * getters the index for the level
     * @return thelevel index
     */
    public int getM_index()
    {
        return m_index;
    }

    /**
     * getter for number of diamonds
     * @return number of diamonds
     */
    public int getNumberOfDiamonds()
    {
        return m_numberOfDiamonds;
    }

    /**
     * getter for the diamondsGrid populated with only diamonds
     * @return the grid of diamonds which may be updated
     */
    public GameGrid getDiamondsGrid()
    {
        return m_diamondsGrid;
    }

    /**
     * setter for number of diamonds
     * @param m_numberOfDiamonds the number of diamonds
     */
    public void setNumberOfDiamonds(int m_numberOfDiamonds)
    {
        this.m_numberOfDiamonds = m_numberOfDiamonds;
    }

    /**
     * getter for Objects Grid
     * this doesn't have diamonds in it
     * @return the instance of Objects grid, when updated
     */
    public GameGrid getObjectsGrid()
    {
        return m_objectsGrid;
    }


    /**
     * this is the contructor for Level, where the levelName is the first parameter,
     * followed by the level number and raw level
     * @param levelName the level name
     * @param levelIndex the level number
     * @param raw_level raw level used as data
     */
    public Level(String levelName, int levelIndex, List<String> raw_level)
    {
        if (StartMeUp.isDebugActive())
        {
            System.out.printf("[ADDING LEVEL] LEVEL [%d]: %s\n", levelIndex, levelName);
        }

        m_index = levelIndex;

        int rows = raw_level.size();
        int columns = raw_level.get(0).trim().length();

        m_objectsGrid = new GameGrid(rows, columns);
        m_diamondsGrid = new GameGrid(rows, columns);

        for (int row = 0; row < raw_level.size(); row++)
        {
            for (int col = 0; col < raw_level.get(row).length(); col++)
            {
                // The game object is null when the we're adding a floor or a diamond
                GameObject curTile = GameObject.fromChar(raw_level.get(row).charAt(col));

                if (curTile == GameObject.DIAMOND)
                {
                    setNumberOfDiamonds(getNumberOfDiamonds() + 1);
                    getDiamondsGrid().putGameObjectAt(curTile, row, col);
                    curTile = GameObject.FLOOR;
                } else if (curTile == GameObject.KEEPER)
                {
                    m_keeperPosition = new Point(row, col);
                }
                getObjectsGrid().putGameObjectAt(curTile, row, col);
            }
        }
        /* This function is one of the key features in this game.
           It checks each wall grid, and if it surrounded by a wall on each 8 sides,
           it leaves it empty, and helps with making this game more like the original Sokoban.
         */
        for (int row = 0; row < raw_level.size(); row++)
        {
            for (int col = 0; col < raw_level.get(row).length(); col++)
            {
                if (surroundChecker(row, col))
                {
                    getObjectsGrid().putGameObjectAt(GameObject.EMPTY, row, col);
                }
            }
        }
    }

    /**
     * checks if the surrounding 8 grids for any grid is a wall
     * @param row the row number
     * @param col the col number
     * @return m_isVoid true or not
     */
    public boolean surroundChecker(int row, int col) //yash
    {
        /* This is the function which checks for wall grids which are surrounded
         walls on all 8 sides. If it does, it signals the Level
         constructor to leave it empty.
         */
        boolean m_isVoid = true;

        for (int x = row - 1; x < row + 2; x++)
        {
            for (int b = col - 1; b < col + 2; b++)
            {
                if (isValid(x, b) && (getObjectsGrid().getGameObjectAt(x, b) != GameObject.WALL
                        && getObjectsGrid().getGameObjectAt(x, b) != GameObject.EMPTY))
                {
                    m_isVoid = false;
                }
            }
        }
        return m_isVoid;
    }

    /**
     * checks if the 2d grid is within the limits
     * @param x x coord
     * @param y y coord
     * @return true or false
     */
    public boolean isValid(int x, int y)
    {
        return x >= 0 && x < getObjectsGrid().getROWS() && y >= 0 && y < getObjectsGrid().getCOLUMNS();
    }

    /**
     * checks if level is completed or not
     * @return true or false
     */
    public boolean IsComplete()
    {
        //checks if the level is complete
        int cratedDiamondsCount = 0;
        for (int row = 0; row < getObjectsGrid().getROWS(); row++)
        {
            for (int col = 0; col < getObjectsGrid().getCOLUMNS(); col++)
            {
                if (getObjectsGrid().getGameObjectAt(col, row) == GameObject.CRATE && getDiamondsGrid().getGameObjectAt(col, row) == GameObject.DIAMOND)
                {
                    cratedDiamondsCount++;
                }
            }
        }


        if (cratedDiamondsCount >= getNumberOfDiamonds())
        {

            StartMeUp.setPopUpMovesCount(StartMeUp.getM_LevelMovesCount());
            StartMeUp.setLevelMovesCount(0);
            GameController.setLevelCount(GameController.getLevelCount() + 1);
            GameController.setLevelComplete(true);

        }

        return cratedDiamondsCount >= getNumberOfDiamonds();
    }

    /**
     * getter of index of level
     * @return the index of the level
     */
    public int getIndex()
    {

        return getM_index();
    }

    /**
     * returns the current keeper position
     * @return keeper position
     */
    public Point getKeeperPosition()
    {

        return m_keeperPosition;
    }


    /**
     * Returns the object at distance delta from source
     * @param source original point
     * @param delta distance from source
     * @return object at delta from source
     */
    public GameObject getTargetObject(Point source, Point delta)
    {

        return getObjectsGrid().getTargetFromSource(source, delta);
    }


    /**
     * returns object located at point p
     * @param p point where object is located
     * @return object located at point p
     */
    public GameObject getObjectAt(Point p)
    {
        return getObjectsGrid().getGameObjectAt(p);
    }

    /**
     * moves game object by a specified distance to from a source location
     * @param object the game object
     * @param source the source point, original location
     * @param delta the distance to destination
     */
    public void moveGameObjectBy(GameObject object, Point source, Point delta)
    {
        moveGameObjectTo(object, source, translatePoint(source, delta));
    }


    /**
     * moves a game object by a from a certain location by a specified distance
     * @param object the game object
     * @param source the source location of the object
     * @param destination the distance to the destination
     */
    public void moveGameObjectTo(GameObject object, Point source, Point destination) {
        getObjectsGrid().putGameObjectAt(getObjectAt(destination), source);
        getObjectsGrid().putGameObjectAt(object, destination);
    }


    /**
     * converts the grid to a string for parsing
     * @return the string value
     */
    @Override
    public String toString()
    {
        return getObjectsGrid().toString();
    }


    /**
     * returns an iterator
     * @return an iterator
     */
    @Override
    public Iterator<GameObject> iterator()
    {
        return new LevelIterator();
    }




    /**
     * provided the interface to iterate through the GameObject
     */
    public class LevelIterator implements Iterator<GameObject>
    {

        int column = 0;
        int row = 0;

        @Override
        public boolean hasNext()
        {
            return !(row == getObjectsGrid().getROWS() - 1 && column == getObjectsGrid().getCOLUMNS());
        }

        @Override
        public GameObject next()
        {
            if (column >= getObjectsGrid().getCOLUMNS())
            {
                column = 0;
                row++;
            }

            GameObject object = getObjectsGrid().getGameObjectAt(column, row);
            GameObject diamond = getDiamondsGrid().getGameObjectAt(column, row);
            GameObject retObj = object;

            column++;

            if (diamond == GameObject.DIAMOND)
            {
                if (object == GameObject.CRATE)
                {
                    retObj = GameObject.CRATE_ON_DIAMOND;
                } else if (object == GameObject.FLOOR)
                {
                    retObj = diamond;
                }
            }

            return retObj;
        }

        public Point GetCurrentPosition()
        {
            return new Point(column, row);
        }
    }
}