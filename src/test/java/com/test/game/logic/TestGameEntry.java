package com.test.game.logic;

import com.game.GameEntry;
import com.game.constants.MessageConstants;
import com.game.messages.MessageUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.spy;

@RunWith(PowerMockRunner.class)
@PrepareForTest({GameEntry.class})
public class TestGameEntry {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void testSaveAndContinueGameError() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        spy(GameEntry.class);
        Method saveGame = GameEntry.class.getDeclaredMethod("saveAndContinueGame");
        saveGame.setAccessible(true);
        saveGame.invoke(null);
        assertEquals(MessageUtil.getMessage(MessageConstants.START_GAME_TO_SAVE), systemOutRule.getLog().trim());
    }

    @Test
    public void testSaveAndExitGameError() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        spy(GameEntry.class);
        Method saveGame = GameEntry.class.getDeclaredMethod("saveAndExitGame");
        saveGame.setAccessible(true);
        saveGame.invoke(null);
        assertEquals(MessageUtil.getMessage(MessageConstants.START_GAME_TO_SAVE), systemOutRule.getLog().trim());
    }

    @Test
    public void testLoadGameError() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        spy(GameEntry.class);
        Method loadGame = GameEntry.class.getDeclaredMethod("loadGame");
        loadGame.setAccessible(true);
        loadGame.invoke(null);
        assertEquals(MessageUtil.getMessage(MessageConstants.NO_SAVES), systemOutRule.getLog().trim());
    }

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
        if (file.exists())
            file.renameTo(file2);
    }

}
