package com.example.arexnt.mydaygram;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private NoteDB noteDB;
    private SQLiteDatabase dbReader;
    private ListView lv;
    private TextView addDiary;
    private Intent it;
    private Context mContext;
    private dataAdapter mAdapter = null;
    private ListView mListView;



    public String getTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String date = sdf.format(new java.util.Date());
        return date;
    }

    //载入页面时显示ListView
    public void initView(){
        lv = (ListView) findViewById(R.id.diary_list);
        addDiary = (TextView) findViewById(R.id.new_Diary_btn);
        addDiary.setOnClickListener(this);

        noteDB = new NoteDB(this);
        dbReader = noteDB.getReadableDatabase();


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //数据库
//        noteDB = new NoteDB(this);
//        dbWriter = noteDB.getWritableDatabase();
        initView();

   }

    @Override
    public void onClick(View v) {
        it = new Intent(MainActivity.this,edit_page_Activity.class);
        switch (v.getId()){
            case R.id.new_Diary_btn:
                SimpleDateFormat sdf=new SimpleDateFormat("EEEE / MMMM dd / yyyy ");
                String date=sdf.format(new java.util.Date());
                it.putExtra("date",date);
                startActivity(it);
        }

    }
    public void selectDB(){
        Cursor cursor = dbReader.query(NoteDB.TABLE_NAME, null,null,null,null,null,null,null);
        mAdapter = new dataAdapter(this, cursor);
        mListView = (ListView) findViewById(R.id.diary_list);
        mListView.setAdapter(mAdapter);
    }
    @Override
    protected void onResume(){
        super.onResume();
        selectDB();
    }
}
