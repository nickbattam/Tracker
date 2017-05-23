package org.nickbattam.tracker.datasource;

import android.util.Log;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.EntryXComparator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by nick on 21/05/17.
 */

public class OnTrackDataProvider implements IDataSource {

    private File fileDir;
    private String filename;
    private List<Entry> entries;

    public OnTrackDataProvider(File filesDir, String filename) {
        Log.i("OnTrackDataProvider", "FileDir: " + filesDir.getAbsolutePath());
        this.fileDir = filesDir;
        this.filename = filename;
    }

    @Override
    public List<Entry> getData() {
        if (entries == null) {
            Log.i("OnTrackParse", "Building data");
            entries = buildData();
        }
        Log.i("OnTrackParse", "List contains " + entries.size() + "elements");
        return entries;
    }

    private List<Entry> buildData() {
        List<Entry> entries = new ArrayList<>();
        try {
            List<ITrackerData> data = new OnTrackExportParser().parse(fileDir, filename);
            for (ITrackerData d : data) {
                if (d instanceof Blood) {
                    entries.add(createEntry((Blood)d));
                }
                else {
                    Log.w("OnTrackParse", "Ignoring" + d.getClass());
                }

            }
        } catch(IOException ex) {
            Log.e("OnTrackParse", "Error parsing export file", ex);
        }
        Collections.sort(entries, new EntryXComparator());
        return entries;
    }

    private Entry createEntry(Blood b) {
        return new Entry(b.getShiftedDatetime(), b.getBlood());
    }

}
