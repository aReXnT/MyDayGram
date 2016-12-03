package com.example.arexnt.mydaygram;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class selectActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageButton s_del, s_addTimek, s_save;
    private TextView s_title;
    private EditText s_txt;
    private NoteDB mNoteDB;
    private SQLiteDatabase dbWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        s_title = (TextView) findViewById(R.id.select_page_title);
        s_del = (ImageButton) findViewById(R.id.select_page_del_btn);
        s_addTimek = (ImageButton) findViewById(R.id.select_page_addtime);
        s_save = (ImageButton) findViewById(R.id.select_page_save_btn);
        s_txt = (EditText) findViewById(R.id.select_text);

        s_del.setOnClickListener(this);
        s_addTimek.setOnClickListener(this);
        s_save.setOnClickListener(this);

        mNoteDB = new NoteDB(this);
        dbWriter = mNoteDB.getWritableDatabase();

        String title = getIntent().getStringExtra(NoteDB.WEEK) + " / "
                    + getIntent().getStringExtra(NoteDB.MONTH) + " "
                    + getIntent().getStringExtra(NoteDB.DAY) + " / "
                    + getIntent().getStringExtra(NoteDB.YEAR);
        s_title.setText(title);
        s_txt.setText(getIntent().getStringExtra(NoteDB.CONTENT));
//        boolean b = true;
//        if (getIntent().getStringExtra(NoteDB.CONTENT) = null){
//
//        }
        String str = getIntent().getStringExtra(NoteDB.CONTENT);
        if (str == " "){
            Log.i("contentIsNull","null");
        }else {
            Log.i("contentIsNull","not null");
            Log.i("contentMsg",Integer.toString(str.length()));
        }

        s_txt.requestFocus();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.select_page_addtime :
                SimpleDateFormat sdf=new SimpleDateFormat("HH:mm ");
                String date=sdf.format(new java.util.Date());
                s_txt = (EditText)findViewById(R.id.select_text);
                s_txt.append(date);
                break;
            case R.id.select_page_save_btn:
                updateDB();
                onBackPressed();
                break;
            case R.id.select_page_del_btn:
                AlertDialog.Builder dialog = new AlertDialog.Builder(selectActivity.this);
                dialog.setTitle(R.string.app_name);
                dialog.setMessage("确认删除此日记？");
                dialog.setCancelable(true);
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delDB();
                        finish();
                    }
                });
                dialog.show();
                break;
        }

    }
    public  void updateDB(){
        ContentValues cv = new ContentValues();
        cv.put(NoteDB.CONTENT,s_txt.getText().toString());
        dbWriter.update(NoteDB.TABLE_NAME, cv, "_id=" + getIntent().getIntExtra(NoteDB.ID, 0), null);
    }

    public void delDB(){
        dbWriter.delete(NoteDB.TABLE_NAME, "_id=" + getIntent().getIntExtra(NoteDB.ID, 0), null);
    }
    public String getStr(String str) {
        String date;
        if (str == "WEEK") {
            SimpleDateFormat sdf = new SimpleDateFormat("E", Locale.ENGLISH);
            date = sdf.format(new java.util.Date());
        }else {
            SimpleDateFormat sdf = new SimpleDateFormat("d");
            date = sdf.format(new java.util.Date());
        }
        return date;
    }


}
