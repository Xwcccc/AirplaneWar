package com.example.aircraftWar.dao;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.util.ArrayList;

/**
 * @author hitsz200110211
 */
public class RecordImpl implements RecordDao {

    /**
     * records 从文件中读取得到的Rrcord列表
     * file 存储序列化对象record的文件
     */
    private ArrayList<Record> records;
    private String fileName;
    private Context context;

    public RecordImpl(String fileName, Context context) {
        this.context = context;
        this.fileName = fileName;
        this.records = new ArrayList<>();
        readFile();
    }

    private void writeFile () {
        FileOutputStream fos = null;
        try {
            //String text = "hello world";
            fos = context.openFileOutput(fileName, MODE_PRIVATE);

            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Record record:records) {
                oos.writeObject(record);
            }

            //fos.write(text.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        /**原先的
        try {
            FileOutputStream fos = openFileInput();
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Record record:records) {
                oos.writeObject(record);
            }
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
         */
        //System.out.println("写文件，此时对象数："+records.size());
    }

    private void readFile () {
        FileInputStream fis = null;
        Reader reader = null;
        BufferedReader bufferedReader = null;
        try {
            fis = context.openFileInput(fileName);
           // reader = new InputStreamReader(fis);
            //bufferedReader = new BufferedReader(reader);
           // StringBuilder result = new StringBuilder();
           // String temp;
           // while ((temp = bufferedReader.readLine()) != null) {
           //     result.append(temp);
           // }

            ObjectInputStream ois = new ObjectInputStream(fis);
            while (fis.available() > 0) {
                Record record = (Record) ois.readObject();
                records.add(record);
            }

            //Log.i("MainActivity", "result:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        /**原先的
        System.out.println("是否执行读文件操作");
        //这条语句读写不打印
        if (file.exists()) {
            System.out.println("文件存在");
            ObjectInputStream ois;
            try {
                FileInputStream fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);
                while (fis.available() > 0) {
                    Record record = (Record) ois.readObject();
                    records.add(record);
                }
                ois.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("读文件，此时对象数："+records.size());
        } else {
            System.out.println("文件不存在");
        }
         */
    }

    @Override
    public ArrayList<Record> getAllRecords () {
        //System.out.println("文件中存储对象的数目"+records.size());
        return records;
    }

    @Override
    public void addRecord (Record record) {
        int index = 0;
        int score = record.getRecordScore();
        if (records.isEmpty()) {
            records.add(record);
        } else {
            for (Record recordi : records) {
                if (score > recordi.getRecordScore()) {
                    index = records.indexOf(recordi);
                    break;
                } else if (records.indexOf(recordi) == records.size()-1) {
                    records.add(record);
                    writeFile();
                    return;
                }
            }
            records.add(index,record);
        }
        writeFile();
    }

    @Override
    public void deleteRecord (int index) {
        records.remove(index);
        writeFile();
    }

}
