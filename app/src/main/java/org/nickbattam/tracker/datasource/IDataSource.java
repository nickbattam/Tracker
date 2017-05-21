package org.nickbattam.tracker.datasource;

import com.github.mikephil.charting.data.Entry;

import java.util.List;

public interface IDataSource {
    List<Entry> getData();
}
