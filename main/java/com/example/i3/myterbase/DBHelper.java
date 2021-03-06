package com.example.i3.myterbase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override

    public  void onCreate(SQLiteDatabase db){

        StringBuffer sb = new StringBuffer();

        sb.append(" CREATE TABLE TEST_TABLE ( ");
        sb.append(" _ID INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append(" NAME TEXT, ");
        sb.append(" AGE INTEGER, ");
        sb.append(" ID TEXT ) ");


        db.execSQL(sb.toString());

        Toast.makeText(context, "Table 생성완료", Toast.LENGTH_SHORT).show();

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Toast.makeText(context, "update... version upgrade!!", Toast.LENGTH_SHORT).show();

    }

    public void testDB() {
        SQLiteDatabase db = getReadableDatabase();
    }

    public void addPerson(Person person){

        SQLiteDatabase db = getWritableDatabase();

        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO TEST_TABLE ( ");
        sb.append(" NAME, AGE, PHONE ) ");
        sb.append(" VALUES ( ?, ?, ? ) ");


        db.execSQL(sb.toString(),
                new Object[]{
                        person.getName(),
                        Integer.parseInt(person.getAge()),
                        person.getPhone()});
        Toast.makeText(context, "Insert 완료", Toast.LENGTH_SHORT).show();




    }
    public void delPerson(String name){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM TEST_TABLE WHERE name='" + name + "';");
    }
    public void allDelPerson(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM TEST_TABLE;");
    }

    public List getAllPersonData() {

        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT _ID, NAME, AGE, PHONE FROM TEST_TABLE ");

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(sb.toString(), null);

        List people = new ArrayList();

        Person person = null;

        while( cursor.moveToNext() ) {
            person = new Person();
            person.set_id(cursor.getInt(0));
            person.setName(cursor.getString(1));
            person.setAge(cursor.getString(2));
            person.setPhone(cursor.getString(3));
            people.add(person);
        }

            return people;


    }






}
