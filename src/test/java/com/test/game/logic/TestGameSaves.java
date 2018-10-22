package com.test.game.logic;

import com.game.GameEntry;
import com.game.components.maps.GameMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({GameEntry.class})
public class TestGameSaves {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Before
    public void setup(){
        File file = new File("game.sav");
        File file2 = new File("game.sav1");
        if (file.exists())
            file.renameTo(file2);
    }

    @After
    public void cleanup(){
        File file = new File("game.sav1");
        File file2 = new File("game.sav");
        if(file2.exists()){
            file2.delete();
        }
        if (file.exists())
            file.renameTo(file2);
    }

   @Test
    public void testSave() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
       // GameMap gameMap = new GameMap(null, null);
       GameMap gameMap = mock(GameMap.class);
       when(gameMap.isPlayerAlive()).thenReturn(true);    // Mock implementation
       when(gameMap.tasksLeft()).thenReturn(true);
        Whitebox.setInternalState(GameEntry.class, "activeGameGameMap", gameMap);
        spy(GameEntry.class);
        Method saveGame = GameEntry.class.getDeclaredMethod("saveAndExitGame");
        saveGame.setAccessible(true);
        saveGame.invoke(null);

       File file = new File("game.sav");
       assert(file.exists());

    }

}
