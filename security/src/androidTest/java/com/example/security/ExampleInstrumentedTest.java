/*
 * Copyright (C) 2018 SEAT, S.A - All Rights Reserved
 *
 * This file is part of MySeat App.
 *
 * Unauthorized reproduction, copying, or modification, of this file, via
 * any medium is strictly prohibited.
 *
 * This code is Proprietary and Confidential.
 *
 * All the 3rd parties libraries included in the project are regulated by
 * their own licenses.
 */

package com.example.security;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        Assert.assertEquals("com.example.security.test", appContext.getPackageName());
    }
}
