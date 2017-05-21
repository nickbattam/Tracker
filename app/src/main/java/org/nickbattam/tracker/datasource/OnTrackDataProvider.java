package org.nickbattam.tracker.datasource;

import android.util.Log;

import com.github.mikephil.charting.data.Entry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by nick on 21/05/17.
 */

public class OnTrackDataProvider implements IDataSource {

    private String filename;
    private List<Entry> entries;

    public OnTrackDataProvider(String filename) {
        this.filename = filename;
    }

    @Override
    public List<Entry> getData() {
        if (entries == null) {
            entries = buildData();
        }
        return entries;
    }

    private List<Entry> buildData() {
        List<Entry> entries = null;
        try {
            List<ITrackerData> data = new OnTrackExportParser().parse(filename);
            entries = new ArrayList<>();
            for (ITrackerData d : data) {
                if (d instanceof Blood) {
                    entries.add(createEntry((Blood)d));
                }
            }
            Collections.sort(entries, new EntryComparator());
        } catch(IOException ex) {
            Log.e("OnTrackParse", "Error parsing export file", ex);
        }
        return entries;
    }

    private Entry createEntry(Blood b) {
        return new Entry(b.getShiftedDatetime(), b.getBlood());
    }

}
