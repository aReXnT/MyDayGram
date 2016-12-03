package com.example.arexnt.mydaygram;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private NoteDB noteDB;
    private SQLiteDatabase dbReader;
    private ListView lv;
    private TextView addDiary;
    private Intent it;
    private Context mContext;
    private dataAdapter mAdapter = null;
    private ListView mListView;
    private Cursor cursor;
    private TextView btn_month;
    private TextView btn_year;

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

        btn_month = (TextView) findViewById(R.id.monthbtn);
        btn_year = (TextView) findViewById(R.id.yearbtn);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        CharSequence date = sdf.format(new java.util.Date());
        btn_year.setText(date);
        sdf = new SimpleDateFormat("MMMM",Locale.ENGLISH);
        date = sdf.format(new java.util.Date());
        btn_month.setText(date);

        noteDB = new NoteDB(this);
        dbReader = noteDB.getReadableDatabase();


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
   }

    @Override
    public void onClick(View v) {
        it = new Intent(MainActivity.this,edit_page_Activity.class);
        switch (v.getId()){
            case R.id.new_Diary_btn:
                SimpleDateFormat sdf=new SimpleDateFormat("E / MMMM dd / yyyy ", Locale.ENGLISH);
                String date=sdf.format(new java.util.Date());
                Bundle bundle = new Bundle();
                it.putExtra("date",date);
                startActivity(it);
        }

    }
    public void selectDB(){
        cursor = dbReader.query(NoteDB.TABLE_NAME, null,null,null,null,null,null,null);
        mAdapter = new dataAdapter(this, cursor);
        mListView = (ListView) findViewById(R.id.diary_list);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(itemClickListener);
        mListView.setOnItemLongClickListener(itemLongClickListener);

    }
    @Override
    protected void onResume(){
        super.onResume();
        selectDB();
    }
    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            cursor.moveToPosition(position);
            Intent i = new Intent(MainActivity.this,selectActivity.class);
            i.putExtra(NoteDB.ID,cursor.getInt(cursor.getColumnIndex(NoteDB.ID)));
            i.putExtra(NoteDB.CONTENT,cursor.getString(cursor.getColumnIndex(NoteDB.CONTENT)));
            i.putExtra(NoteDB.YEAR,cursor.getString(cursor.getColumnIndex(NoteDB.YEAR)));
            i.putExtra(NoteDB.MONTH,cursor.getString(cursor.getColumnIndex(NoteDB.MONTH)));
            i.putExtra(NoteDB.WEEK,cursor.getString(cursor.getColumnIndex(NoteDB.WEEK)));
            i.putExtra(NoteDB.DAY,cursor.getString(cursor.getColumnIndex(NoteDB.DAY)));
            startActivity(i);

        }
    };
    private AdapterView.OnItemLongClickListener itemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            return false;
        }
    };
    private void checkExist(){

    }
    private void fillData(){

    }
}
