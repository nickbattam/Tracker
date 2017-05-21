package org.nickbattam.tracker.datasource;

import com.github.mikephil.charting.data.Entry;

import java.util.Comparator;

class EntryComparator implements Comparator<Entry>
{
    public int compare(Entry o1, Entry o2) {
        return Float.valueOf(o1.getX()).compareTo(o2.getX());
    }
}