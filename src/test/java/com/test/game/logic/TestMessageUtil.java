package com.test.game.logic;

import com.game.constants.MessageConstants;
import com.game.messages.MessageUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestMessageUtil {


    private MessageUtil messageUtil;

    @Before
    public void setUp() {
        messageUtil = new MessageUtil();
    }

    @Test
    public void testMessages() throws Exception {
        Assert.assertNotNull(MessageUtil.getMessage(MessageConstants.START_GAME_TO_SAVE));
        Assert.assertNotNull(MessageUtil.getMessage(MessageConstants.SAVE_ERROR));
        Assert.assertNotNull(MessageUtil.getMessage(MessageConstants.LOAD_ERROR));
        Assert.assertNotNull(MessageUtil.getMessage(MessageConstants.NO_SAVES));
    }

    @Test
    public void testMessagesError() throws Exception {
        Assert.assertNull(MessageUtil.getMessage(""));
    }

}
