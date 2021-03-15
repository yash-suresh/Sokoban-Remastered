package sample;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import sample.*;
import sample.engine.GameGrid;
import sample.engine.GameObject;
import sample.engine.Level;

import static org.junit.jupiter.api.Assertions.*;

class LevelTest
{

    List<String> list = new ArrayList<>();
    Level level;
    private GameGrid grid;
    private GameObject[][] obj;






    @BeforeEach
    void setUp()
    {

        grid = new GameGrid(20, 20);
        obj = new GameObject[20][20];

        list = new ArrayList<>();
        list.add("WWWWWWWWWWWWWWWWWWWW");
        list.add("W                  W");
        list.add("W                  W");
        list.add("W                  W");
        list.add("W                  W");
        list.add("W                  W");
        list.add("W                  W");
        list.add("W                  W");
        list.add("W                  W");
        list.add("W       S C D      W");
        list.add("W                  W");
        list.add("W                  W");
        list.add("W                  W");
        list.add("W                  W");
        list.add("W                  W");
        list.add("W                  W");
        list.add("W                  W");
        list.add("W                  W");
        list.add("W                  W");
        list.add("WWWWWWWWWWWWWWWWWWWW");


        level = new Level("test", 1, list);
    }


    @Test
    void testGetObjectAt()
    {
        Point source = new Point(1,2);
        Point delta = new Point(2,2);

        assertNotEquals(obj[1][2], level.getObjectAt(source));
        //there is a floor at this coordinate, so we test if the program reads it as a floor
        //this should return false, as we don't expect a floor in our test

    }


    @Test
    void testIsValid()
    {
        assertFalse(level.isValid(21,21));
        //tests if valid

    }

    @Test
    void testGetTargetObject()
    {

        Point source = new Point(1,2);
        Point delta = new Point(2,2);
        assertEquals(GameObject.FLOOR, level.getTargetObject(source, delta));

        //there is a floor at this coordinate, so we test if the program reads it as a floor
    }

    @Test
    void testMoveGameObjectBy()
    {
        Point source = new Point(1,2);
        Point destination = new Point(2,2);
        level.moveGameObjectBy(GameObject.CRATE, source, destination);
        assertNotEquals(GameObject.CRATE, level.getObjectAt(destination));

    }













}
