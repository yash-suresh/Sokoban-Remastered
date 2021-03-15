package sample;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sample.utilities.SoundFX;

import static org.junit.jupiter.api.Assertions.*;

class SoundFXTest
{
    SoundFX test;

    @BeforeEach
    void setUp()
    {
        test = SoundFX.getInstance();

    }

    @Test
    void testInstance()
    {
        assertNotNull(SoundFX.getInstance());
        //testing if an instance is being made
    }

    @Test
    void testSingleton()
    {
        assertSame(SoundFX.getInstance(), SoundFX.getInstance());
        //testing if singleton
    }


}