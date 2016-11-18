package com.example.arexnt.mydaygram;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class selectActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageButton s_del, s_addTimek, s_save;
    private TextView s_txt;
    private NoteDB mNoteDB;
    private SQLiteDatabase dbWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        s_del = (ImageButton) findViewById(R.id.select_page_del_btn);
        s_addTimek = (ImageButton) findViewById(R.id.select_page_addtime);
        s_save = (ImageButton) findViewById(R.id.select_page_save_btn);
        s_txt = (TextView) findViewById(R.id.select_text);

        s_del.setOnClickListener(this);
        s_addTimek.setOnClickListener(this);
        s_save.setOnClickListener(this);
        s_txt.setOnClickListener(this);

        mNoteDB = new NoteDB(this);
        dbWriter = mNoteDB.getWritableDatabase();

        s_txt.setText(getIntent().getStringExtra(NoteDB.CONTENT));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edit_page_addtime :
                SimpleDateFormat sdf=new SimpleDateFormat("HH:mm ");
                String date=sdf.format(new java.util.Date());
                s_txt = (TextView)findViewById(R.id.select_text);
                s_txt.append(date);
                break;
            case R.id.select_page_save_btn:
                addDB();
                onBackPressed();
                break;
            case R.id.select_page_del_btn:
                delDB();
                onBackPressed();
                break;
        }

    }
    public  void addDB(){
        ContentValues cv = new ContentValues();
        cv.put(NoteDB.CONTENT,s_txt.getText().toString());
        cv.put(NoteDB.WEEK,getStr("WEEK"));
        cv.put(NoteDB.DAY,getStr("DAY"));
        dbWriter.insert(NoteDB.TABLE_NAME, null,cv);
    }

    public void delDB(){
        dbWriter.delete(NoteDB.TABLE_NAME, "_id=" + getIntent().getIntExtra(NoteDB.ID,0),null);
    }
    public String getStr(String str) {
        String date;
        if (str == "WEEK") {
            SimpleDateFormat sdf = new SimpleDateFormat("E");
            date = sdf.format(new java.util.Date());
        }else {
            SimpleDateFormat sdf = new SimpleDateFormat("d");
            date = sdf.format(new java.util.Date());
        }
        return date;
    }
}
