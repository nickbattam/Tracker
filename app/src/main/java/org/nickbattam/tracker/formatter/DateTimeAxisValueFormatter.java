package org.nickbattam.tracker.formatter;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.nickbattam.tracker.datasource.Blood.BASE_DATETIME;


public class DateTimeAxisValueFormatter implements IAxisValueFormatter {

    private SimpleDateFormat mFormatter;

    public DateTimeAxisValueFormatter() {
        mFormatter = new SimpleDateFormat("hh:mm:ss");
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return mFormatter.format(new Date((BASE_DATETIME + (long)value) * 1000));
    }
}
