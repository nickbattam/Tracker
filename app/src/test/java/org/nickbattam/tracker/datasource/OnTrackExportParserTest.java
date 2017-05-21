package org.nickbattam.tracker.datasource;

import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;
import static org.junit.Assert.assertTrue;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Log.class})
public class OnTrackExportParserTest {

    public OnTrackExportParserTest() {

        // mock all the static methods in a class called "Static"
        PowerMockito.mockStatic(Log.class);
    }

    static String EXPORT_FILEPATH = "/home/nick/Downloads/export_21_05_2017-12_36_56_9650.csv";

    @Test
    public void parser_returns_list_of_Bloods() throws Exception {
        OnTrackExportParser parser = new OnTrackExportParser();
        List<ITrackerData> data = parser.parse(EXPORT_FILEPATH);
        assertTrue(data.size()>0);
    }
}
