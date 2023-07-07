package com.example.aircraftWar.dao;

import java.util.ArrayList;

/**
 * @author hitsz200110211
 */
public interface RecordDao {
    /**
     * 打印得分排行榜
     * @return 记录列表
     */
    ArrayList<Record> getAllRecords();

    /**
     * 增加一条新的得分记录
     * @param record 记录信息
     */
    void addRecord(Record record);

    /**
     * 删除一条已存在的得分记录
     * @param index 需删除的记录的标号
     */
    void deleteRecord(int index);
}