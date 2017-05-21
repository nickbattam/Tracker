package org.nickbattam.tracker.datasource;

import android.util.Log;

import com.github.mikephil.charting.data.Entry;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Parser for csv export files from the OnTrack monitoring tool:
 *   https://play.google.com/store/apps/details?id=com.gexperts.ontrack
 *
 *      ID, DateTimeStr, Type, SubType, ToD, Value, Comment
 *      3797,"10 Mar 2017 8:00:59 a.m.",Exercise,Cycling,Breakfast,45.0,""
 *      3010,"1 Feb 2017 7:00:33 p.m.",Medication,Humalog,Breakfast,10.0,""
 *      3007,"1 Feb 2017 2:36:37 p.m.",Food,,Breakfast,20.0,"Apple"
 *      3006,"1 Feb 2017 2:28:07 p.m.",Glucose,,Breakfast,3.4,""
 */
public class OnTrackExportParser {

    List<ITrackerData> parse(String filename) throws IOException {

        List<ITrackerData> data = new ArrayList<>();

        try (
            FileReader fr = new FileReader(filename);
            CSVReader reader = new CSVReader(fr, ',')
        ) {
            // read line by line
            String[] record;

            while ((record = reader.readNext()) != null) {
                String recordType = record[2];
                switch (recordType) {
                    case "Glucose":
                        data.add(new Blood(record[1], Float.valueOf(record[5])));
                        break;
                    default:
                        Log.i("OnTrack Parse", "Ignoring " + recordType);
                }
            }

            System.out.println(data);
        }

        return data;
    }

}
