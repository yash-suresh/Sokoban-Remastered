package sample;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sample.engine.GameGrid;
import sample.engine.GameObject;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class GameGridTest
{
    private static GameGrid grid;
    private static GameObject[][] object = new GameObject[5][5];

    private final int r = 5;
    private final int c = 5;


    @BeforeEach
    void setUp()
    {
        grid = new GameGrid(c, r);
    }

    @Test
    void ConstructorTest()
    {
        assertNotNull(grid);
        //testing if the game grid is reloaded so null isn't returned.
    }

    @Test
    void testTranslatePoint()
    {
        Point source = new Point(1,2);
        Point delta = new Point(2,2);
        Point destination = new Point(3,4);

        assertEquals(destination, GameGrid.translatePoint(source,delta));

        //testing if the object is moved to the correct coordinates
    }

    @Test
    void testGameObjectAt()
    {
        assertEquals(object[2][3], grid.getGameObjectAt(2,3));
        //testing if the correct object is called when the coordinates are passed.
    }

    @Test
    void testGetTargetfromSource()
    {
        Point source = new Point(1,2);
        Point delta = new Point(2,2);


        assertEquals(object[3][4], grid.getTargetFromSource(source, delta));
        //testing if the function gives us the correct object coordinates.

    }

    @Test
    void testPutGameObjectAt1()
    {

        assertFalse(grid.putGameObjectAt(GameObject.CRATE, r+1, c+1));
        //testing if the 'out of bounds' function is working.
        //since 5+1 is out of bounds, it will return false. so its working
    }
    @Test
    void testPutGameObjectAt2()
    {
        assertTrue(grid.putGameObjectAt(GameObject.CRATE, r-2, c-2));
        //this is within GameGrid limits
    }


}