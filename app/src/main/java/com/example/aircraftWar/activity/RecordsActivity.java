package com.example.aircraftWar.activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aircraftWar.R;
import com.example.aircraftWar.dao.MyAdapter;
import com.example.aircraftWar.dao.Record;

import java.util.List;

public class RecordsActivity extends ListActivity {
    private MyAdapter myAdapter = null;

    private List<Record> records;

    private TextView difficulty;
    private Button deleteButton;
    private ListView list;
    private int index = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        //绑定
        difficulty = findViewById(R.id.difficulty);
        deleteButton = findViewById(R.id.deleteButton);
        list = findViewById(android.R.id.list);
        init();
        myAdapter =new MyAdapter(RecordsActivity.this, R.layout.activity_records, records);
        setListAdapter(myAdapter);
        registerForContextMenu(getListView());

        //获取点击目标的位置
        list.setOnItemClickListener((parent, view, position, id) ->
                    index = (int)position
        );

        //删除并更新排行榜
        deleteButton.setOnClickListener(view ->{
            if (records != null && records.size() > 0 ) {
                //System.out.println("1在deleteButton按下后文件中对象的个数："+MainActivity.recordDao.getAllRecords().size());
               //该语句打印对象数为1
                // MainActivity.recordDao.deleteRecord(index);//加了这句话后报错数组越界
                records.remove(index);
                System.out.println("2在deleteButton按下后文件中对象的个数："+MainActivity.recordDao.getAllRecords().size());
                //通过打印语句了解到recordDao此时对象数为0
                myAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(RecordsActivity.this, "无可删除对象！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Record record = records.get(position);
        Toast.makeText(RecordsActivity.this, (position+1)+":"+record.getRecordName()
                +":"+record.getRecordScore()+":"+record.getRecordTime(), Toast.LENGTH_LONG).show();
    }

    private void init() {
        if(!MainActivity.level.equals("")) {
            switch (MainActivity.level) {
                case "easy":
                    difficulty.setText("难度：EASY");
                    break;
                case "hard":
                    difficulty.setText("难度：HARD");
                    break;
                case "normal":
                    difficulty.setText("难度：NORMAL");
                    break;
            }
        }
        records = MainActivity.recordDao.getAllRecords();
    }

}
