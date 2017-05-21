package org.nickbattam.tracker;

import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nickbattam.tracker.datasource.Blood;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Log.class})
public class BloodTest {

    @Test
    public void parses_valid_am_dateString() throws Exception {
        String dateString = "3 Feb 2017 6:16:17 a.m.";
        Blood b = new Blood(dateString, 1.0f);
        assertEquals(b.getDatetime(),1486102577L);
    }

    @Test
    public void parses_valid_pm_dateString() throws Exception {
        String dateString = "2 Feb 2017 11:23:10 p.m.";
        Blood b = new Blood(dateString, 1.0f);
        assertEquals(b.getDatetime(), 1486077790L);
    }

}