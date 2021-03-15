package sample.engine;

import java.awt.*;
import java.util.Iterator;

/**
 * this class creates a grid for the game and all its functionalities
 * @author Yash Suresh-modified
 */

public class GameGrid implements Iterable
{
    private final int m_COLUMNS;
    private final int m_ROWS;
    private final GameObject[][] m_gameObjects;

    /**
     * constructor for the grid
     * @param columns col number
     * @param rows row number
     */
    public GameGrid(int columns, int rows)
    {
        m_COLUMNS = columns;
        m_ROWS = rows;
        m_gameObjects = new GameObject[getCOLUMNS()][getROWS()];
    }

    /**
     * getter for columns
     * @return the column size
     */
    public int getCOLUMNS()
    {
        return m_COLUMNS;
    }


    /**
     * getter for rows
     * @return the row size
     */
    public int getROWS()
    {
        return m_ROWS;
    }

    /**
     * getter for game object grids
     * @return instance of GameObject
     */
    public GameObject[][] getGameObjects()
    {
        return m_gameObjects;
    }


    /**
     * moves the point by a specified path along the grid
     * @param sourceLocation current position
     * @param delta directions to new point
     * @return translatedPoint new coordinates
     */
    public static Point translatePoint(Point sourceLocation, Point delta)
    {
        Point translatedPoint = new Point(sourceLocation);
        translatedPoint.translate((int) delta.getX(), (int) delta.getY());
        return translatedPoint;
    }

    /**
     * getter for the object at the new position
     * @param source current point
     * @param delta path to the new point
     * @return the gameobject at the new point
     */
    public GameObject getTargetFromSource(Point source, Point delta)
    {
        return getGameObjectAt(translatePoint(source, delta));
    }

    /**
     * getter for the game object which is at a specified point
     * @param col column
     * @param row row
     * @return the object at the specified point
     * @throws ArrayIndexOutOfBoundsException if attempts to access points
     * beyond the array size
     *
     */
    public GameObject getGameObjectAt(int col, int row)
            throws ArrayIndexOutOfBoundsException
    {
        if (isPointOutOfBounds(col, row))
        {
            if (StartMeUp.isDebugActive())
            {
                System.out.printf("Trying to get null " +
                        "GameObject from COL: %d  ROW: %d", col, row);
            }
            throw new ArrayIndexOutOfBoundsException
                    ("The point [" + col + ":" + row + "] is outside the map.");
        }

        return getGameObjects()[col][row];
    }

    /**
     * getter for the game object at a point
     * @param p the current point
     * @return the game object at the point)
     */
    public GameObject getGameObjectAt(Point p)
    {
        if (p == null)
        {
            throw new IllegalArgumentException("Point cannot be null.");
        }

        return getGameObjectAt((int) p.getX(), (int) p.getY());
    }

    /**
     * getter for boolean if the gameobject is at a certain point
     * @param gameObject any object
     * @param x the x coord of the point
     * @param y the y coord of the point
     * @return boolean if the object is at the point
     */
    public boolean putGameObjectAt(GameObject gameObject, int x, int y)
    {
        if (isPointOutOfBounds(x, y))
        {
            return false;
        }

        getGameObjects()[x][y] = gameObject;
        return getGameObjects()[x][y] == gameObject;
    }

    /**
     * getter for if the gameobject is at a point
     * @param gameObject the game object
     * @param p the point to put at
     * @return if the object has been placed
     */
    public boolean putGameObjectAt(GameObject gameObject, Point p)
    {
        return p != null && putGameObjectAt(gameObject,
                (int) p.getX(), (int) p.getY());
    }

    /**
     * checks if a point is out of bounds
     * @param x x coord
     * @param y y coord
     * @return true if out of bounds
     *
     */
    private boolean isPointOutOfBounds(int x, int y)
    {
        return (x < 0 || y < 0 || x >= getCOLUMNS() || y >= getROWS());
    }


    /**
     * converts the object to a string to be used when reading the levels
     * @return the string value of the gameobject
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder(getGameObjects().length);

        for (GameObject[] gameObject : getGameObjects()) {
            for (GameObject aGameObject : gameObject) {
                if (aGameObject == null) {
                    aGameObject = GameObject.DEBUG_OBJECT;
                }
                sb.append(aGameObject.getCharSymbol());
            }

            sb.append('\n');
        }

        return sb.toString();
    }

    /**
     * iterates thru the Gameobject
     * @return new GridIterator()
     */
    @Override
    public Iterator<GameObject> iterator()
    {
        return new GridIterator();
    }


    /**
     * iterates through GameObject
     */
    public class GridIterator implements Iterator<GameObject>
    {
        int row = 0;
        int column = 0;

        @Override
        public boolean hasNext() {
            return !(row == getROWS() && column == getCOLUMNS());
        }

        @Override
        public GameObject next() {
            if (column >= getCOLUMNS()) {
                column = 0;
                row++;
            }
            return getGameObjectAt(column++, row);
        }
    }
}