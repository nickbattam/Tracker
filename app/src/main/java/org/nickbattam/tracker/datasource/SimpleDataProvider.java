package org.nickbattam.tracker.datasource;

import com.github.mikephil.charting.data.Entry;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SimpleDataProvider implements IDataSource {

    private static List<Blood> rawData = new ArrayList<>(Arrays.asList(
            new Blood("4 Feb 2017 11:12:42 p.m.", 5.5f),
            new Blood("4 Feb 2017 9:53:07 p.m.", 4.5f),
            new Blood("4 Feb 2017 8:57:56 p.m.", 4.2f),
            new Blood("4 Feb 2017 4:17:01 p.m.", 5.5f),
            new Blood("4 Feb 2017 3:20:11 p.m.", 3.2f),
            new Blood("4 Feb 2017 8:32:44 a.m.", 3.2f),
            new Blood("4 Feb 2017 1:11:53 a.m.", 7.2f),
            new Blood("4 Feb 2017 12:03:51 a.m.", 3.7f),
            new Blood("3 Feb 2017 9:05:15 p.m.", 8.6f),
            new Blood("3 Feb 2017 2:21:48 p.m.", 14.7f),
            new Blood("3 Feb 2017 12:01:56 p.m.", 8.7f),
            new Blood("3 Feb 2017 9:47:12 a.m.", 2.3f),
            new Blood("3 Feb 2017 9:02:20 a.m.", 4.8f),
            new Blood("3 Feb 2017 6:16:17 a.m.", 7.2f),
            new Blood("2 Feb 2017 11:23:10 p.m.", 3.7f),
            new Blood("2 Feb 2017 10:54:24 p.m.", 4.1f),
            new Blood("2 Feb 2017 8:33:21 p.m.", 4.2f),
            new Blood("2 Feb 2017 5:43:12 p.m.", 9.4f),
            new Blood("2 Feb 2017 3:50:03 p.m.", 8.7f),
            new Blood("2 Feb 2017 12:35:27 p.m.", 8.6f),
            new Blood("2 Feb 2017 9:26:52 a.m.", 4.6f),
            new Blood("2 Feb 2017 7:05:40 a.m.", 2.6f),
            new Blood("1 Feb 2017 9:31:24 p.m.", 12.0f),
            new Blood("1 Feb 2017 9:05:55 p.m.", 12.2f),
            new Blood("1 Feb 2017 4:48:01 p.m.", 8.7f),
            new Blood("1 Feb 2017 3:15:57 p.m.", 7.4f),
            new Blood("1 Feb 2017 2:28:07 p.m.", 3.4f)
    ));

    private List<Entry> entries;


    private List<Entry> buildData(List<Blood> raw) {

        List<Entry> data = new ArrayList<>();

        for (Blood b : raw) {
            data.add(createEntry(b));
        }
        Collections.sort(data, new EntryComparator());
        return data;
    }

    @Override
    public List<Entry> getData() {
        if (entries == null) {
            entries = buildData(rawData);
        }
        return entries;
    }

    Entry createEntry(Blood b) {
        return new Entry(b.getShiftedDatetime(), b.getBlood());
    }

}
