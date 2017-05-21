package org.nickbattam.tracker.datasource;


import com.github.mikephil.charting.data.Entry;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.nickbattam.tracker.datasource.Blood.BASE_DATETIME;

public class SimpleDataProviderTest {
    @Test
    public void entry_created_with_correct_blood() throws Exception {
        Blood b = new Blood();
        b.setBlood(1.5f);
        b.setDateTime(BASE_DATETIME + 12345L);

        SimpleDataProvider provider = new SimpleDataProvider();

        Entry e = provider.createEntry(b);
        assertEquals(e.getY(), 1.5f, 1e-6);
    }

    @Test
    public void entry_created_with_correct_datetime() throws Exception {
        long delta = 12345L;

        Blood b = new Blood();
        b.setBlood(1.5f);
        b.setDateTime(BASE_DATETIME + delta);

        SimpleDataProvider provider = new SimpleDataProvider();

        Entry e = provider.createEntry(b);
        assertEquals((long)e.getX(), delta);
    }

    @Test
    public void getData_returns_correct_length_list() throws Exception {
        SimpleDataProvider provider = new SimpleDataProvider();
        List<Entry> data = provider.getData();
        assertEquals(data.size(), 27);

        for (Entry e : data) {
            System.out.println(e.toString());
        }
    }
}