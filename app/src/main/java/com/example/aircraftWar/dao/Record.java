package com.example.aircraftWar.dao;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author hitsz200110211
 */
public class Record implements Serializable {

    private String recordName;
    private int recordScore;
    private String recordTime;

    SimpleDateFormat ft = new SimpleDateFormat (" MM-dd ' ' HH:mm");

    public Record (String recordName, int recordScore) {
        this.recordName = recordName;
        this.recordScore = recordScore;
        this.recordTime = ft.format(new Date());
    }

    public String getRecordName() {
        return recordName;
    }

    public int getRecordScore() {
        return recordScore;
    }

    public String getRecordTime() {
        return recordTime;
    }

}
