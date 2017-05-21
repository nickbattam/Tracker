package org.nickbattam.tracker.datasource;

import com.github.mikephil.charting.data.Entry;

import java.util.List;

/**
 * Created by nick on 21/05/17.
 */

public interface IDataSource {
    List<Entry> getData();
}
