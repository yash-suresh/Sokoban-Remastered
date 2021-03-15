package sample;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sample.IO.writer;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class writerTest
{
    writer test;


    @BeforeEach
    void setUp()
    {
        test = writer.getInstance();
    }

    @Test
    void testGetInstance()
    {
        assertNotNull(test);
        //testing if an instance is being created
    }

    @Test
    void testSingleton()
    {
        assertSame(writer.getInstance(), writer.getInstance());

        //if a singleton is created, both objects should be the same

    }

    @Test
    void testMain()
    {
        //this checks if the correct filepath name has been created according to the level
        //remember the level count deducts 1 for the previous level.

        GameController.setLevelCount(3);
        writer.main(4);//inputting a random value
        assertEquals("src/sample/resources/2highScores.txt", writer.getWritePath());

    }

    @Test
    void testRead() throws FileNotFoundException
    {
        //this checks if the correct filepath name has been created according to the level
        //remember the level count deducts 1 for the previous level.

        GameController.setLevelCount(3);
        writer.read();
        assertEquals("src/sample/resources/2highScores.txt", writer.getReadPath());
    }
}