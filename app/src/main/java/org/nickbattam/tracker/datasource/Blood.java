package org.nickbattam.tracker.datasource;

import android.util.Log;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by nick on 21/05/17.
 */

public class Blood implements ITrackerData {
    public static long BASE_DATETIME = 1483228800L;  // Sun  1 Jan 00:00:00 GMT 2017
    private static final SimpleDateFormat mFormat = Blood.buildParser();
    private float blood;
    private long datetime;

    Blood() { }

    public Blood(String datetimeStr, float blood) {
        this.blood = blood;
        try {
            datetime = mFormat.parse(datetimeStr).getTime() / 1000L;
        }
        catch (ParseException e) {
            Log.e("BloodParse", "Error parsing date " + datetimeStr, e);
        }
    }

    private static SimpleDateFormat buildParser() {
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy hh:mm:ss aa");
        DateFormatSymbols symbols = format.getDateFormatSymbols();
        symbols = (DateFormatSymbols) symbols.clone();
        symbols.setAmPmStrings(new String[] { "a.m.", "p.m."});
        format.setDateFormatSymbols(symbols);
        return format;
    }

    @Override
    public String toString() {
        return getDatetime() + ": " + getBlood() + "\n";
    }

    public float getBlood() {
        return blood;
    }

    public long getDatetime() {
        return datetime;
    }

    public float getShiftedDatetime() {
        return (float)(datetime - BASE_DATETIME);
    }

    void setBlood(float blood) {
        this.blood = blood;
    }

    void setDateTime(long datetime) {
        this.datetime = datetime;
    }

}
