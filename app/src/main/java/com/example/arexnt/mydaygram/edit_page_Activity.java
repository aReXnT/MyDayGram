package com.example.arexnt.mydaygram;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class edit_page_Activity extends AppCompatActivity implements View.OnClickListener{

    private TextView tv;
    private Intent it;
    private EditText mEditText;
    private ImageButton addTimebtn, backbtn, savebtn;
    private NoteDB mNoteDB;
    private SQLiteDatabase dbWriter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_page_);
        //响应添加日记时，标题时间为当日时间
        it = getIntent();
        tv = (TextView) findViewById(R.id.edit_page_title);
        tv.setText(it.getStringExtra("date").toUpperCase());

        mEditText = (EditText) findViewById(R.id.edit_text);
        addTimebtn = (ImageButton) findViewById(R.id.edit_page_addtime);
        backbtn = (ImageButton) findViewById(R.id.edit_page_back_btn);
        savebtn = (ImageButton) findViewById(R.id.edit_page_save_btn);

        mEditText.setOnClickListener(this);
        addTimebtn.setOnClickListener(this);
        backbtn.setOnClickListener(this);
        savebtn.setOnClickListener(this);

        mNoteDB = new NoteDB(this);
        dbWriter = mNoteDB.getWritableDatabase();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edit_page_addtime :
                SimpleDateFormat sdf=new SimpleDateFormat("HH:mm ");
                String date=sdf.format(new java.util.Date());
                tv = (TextView)findViewById(R.id.edit_text);
                tv.append(date);
                break;
            case R.id.edit_page_back_btn:
                onBackPressed();
                break;
            case R.id.edit_page_save_btn:
                addDB();
                Log.i("writedata","writing");
                onBackPressed();
                break;
        }
    }
    public  void addDB(){
        ContentValues cv = new ContentValues();
        cv.put(NoteDB.CONTENT,mEditText.getText().toString());
        cv.put(NoteDB.YEAR,getStr("YEAR"));
        cv.put(NoteDB.MONTH,getStr("MONTH"));
        cv.put(NoteDB.WEEK,getStr("WEEK"));
        cv.put(NoteDB.DAY,getStr("DAY"));
        Log.i("insertInfo",cv.toString());
        dbWriter.insert(NoteDB.TABLE_NAME, null,cv);
    }
//    public String getTime(){
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        String date = sdf.format(new java.util.Date());
//        return date;
//    }
    public String getStr(String str) {
        String date = null;

        switch (str){
            case "YEAR":
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                date = sdf.format(new java.util.Date());
                break;
            case "MONTH":
                sdf = new SimpleDateFormat("MMMM", Locale.ENGLISH);
                date = sdf.format(new java.util.Date());
                break;
            case "WEEK":
                sdf = new SimpleDateFormat("E", Locale.ENGLISH);
                date = sdf.format(new java.util.Date());
                break;
            case "DAY":
                sdf = new SimpleDateFormat("dd");
                date = sdf.format(new java.util.Date());
                break;
        }

        return date;
    }
}
