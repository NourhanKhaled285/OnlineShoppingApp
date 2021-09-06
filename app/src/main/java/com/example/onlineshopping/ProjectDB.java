package com.example.onlineshopping;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ProjectDB extends SQLiteOpenHelper {
    SQLiteDatabase onlineDatabase2;

    public ProjectDB(@Nullable Context context) {
        super(context, "database9", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table category(catid integer primary key autoincrement,catname text)");


        db.execSQL("create table product(proid integer primary key autoincrement," +
                "proname text not null,price text not null, quantity text not null," +
                "cat_id integer, foreign key(cat_id) REFERENCES category(catid))");

        db.execSQL("create table custmers(cusid integer primary key autoincrement, username text ,email text,phone text,gender text ,password text ,securityqn text ,birthdate text,jop text  )");

        db.execSQL("create table orders(orderid integer primary key autoincrement," +
                "orderdate text not null,adress text not null,city text not null,country text not null, " +
                "cast_id integer, foreign key(cast_id) REFERENCES custmers(catid))");

        db.execSQL("create table orderdetails(ordid integer not null," +
                "proid integer not null,quantity text not null, " +
                " primary key(ordid,proid), foreign key(ordid) REFERENCES orders(orderid),foreign key(proid) REFERENCES product(proid))");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists category");
        db.execSQL("drop table if exists product");
        db.execSQL("drop table if exists custmers");
        db.execSQL("drop table if exists orders");
        db.execSQL("drop table if exists orderdetails");
        onCreate(db);
    }


    public void insertcustmer( String username, String email,String phone, String gender, String password,String sec_qn,String birthdate, String jop) {
        ContentValues row = new ContentValues();
        onlineDatabase2 = getWritableDatabase();

        row.put("username", username);
        row.put("email", email);
        row.put("phone", phone);
        row.put("gender", gender);
        row.put("password", password);
        row.put("securityqn", sec_qn);
        row.put("birthdate", birthdate);
        row.put("jop", jop);
        onlineDatabase2.insert("custmers", null, row);
//        sqLiteDatabase3.close();

    }
    public boolean checkthelogin(String phone , String pass) {
        onlineDatabase2 = getReadableDatabase();
        String[] arr = {phone, pass};
        Cursor cursor = onlineDatabase2.rawQuery(" SELECT *  FROM custmers WHERE phone like ? And password like ? ",arr);
        cursor.moveToFirst();
        int cursorCount = cursor.getCount();

        cursor.close();
//        sqLiteDatabase3.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }
    public Cursor Forgotfethdata(String emal,String seq_ans){
        onlineDatabase2 = getReadableDatabase();
        String[]arr= new String[]{emal,seq_ans};
        Cursor cursor = onlineDatabase2.rawQuery(" SELECT *  FROM custmers WHERE email like ? and securityqn like ? ",arr);
        if(cursor!=null)
            cursor.moveToFirst();
        return  cursor;
    }

    public void insert1()
    {
        ContentValues row=new ContentValues();
        onlineDatabase2 =getWritableDatabase();
        //cat1
        row.put("catname","clothes");
        onlineDatabase2.insert("category",null,row);

        //cat2
        row.put("catname","Houseware");
        onlineDatabase2.insert("category",null,row);

        //cat3
//        row.put("catname","MakeUp");
//        sqLiteDatabase6.insert("category",null,row);


        //product1
        ContentValues contentValues=new ContentValues();
        contentValues.put("proname","plouse");
        contentValues.put("price","300");
        contentValues.put("quantity","9");
        contentValues.put("cat_id",1);
        onlineDatabase2.insert("product",null,contentValues);

        //product2
        contentValues.put("proname","belt");
        contentValues.put("price","70");
        contentValues.put("quantity","7");
        contentValues.put("cat_id",1);
        onlineDatabase2.insert("product",null,contentValues);

        //product3
        contentValues.put("proname","skirt");
        contentValues.put("price","120");
        contentValues.put("quantity","8");
        contentValues.put("cat_id",1);
        onlineDatabase2.insert("product",null,contentValues);

        contentValues.put("proname","dress");
        contentValues.put("price","350");
        contentValues.put("quantity","9");
        contentValues.put("cat_id",1);
        onlineDatabase2.insert("product",null,contentValues);

        contentValues.put("proname","shoes");
        contentValues.put("price","200");
        contentValues.put("quantity","12");
        contentValues.put("cat_id",1);
        onlineDatabase2.insert("product",null,contentValues);

        //product3
        contentValues.put("proname","Table");
        contentValues.put("price","250");
        contentValues.put("quantity","8");
        contentValues.put("cat_id",2);
        onlineDatabase2.insert("product",null,contentValues);

        contentValues.put("proname","chair");
        contentValues.put("price","80");
        contentValues.put("quantity","9");
        contentValues.put("cat_id",2);
        onlineDatabase2.insert("product",null,contentValues);

        contentValues.put("proname","bed");
        contentValues.put("price","1250");
        contentValues.put("quantity","12");
        contentValues.put("cat_id",2);
        onlineDatabase2.insert("product",null,contentValues);



//        contentValues.put("proname","RedLipstick");
//        contentValues.put("price","250");
//        contentValues.put("quantity","150");
//        contentValues.put("cat_id",3);
//        sqLiteDatabase6.insert("product",null,contentValues);
//
//        contentValues.put("proname","MayBellineFoundation");
//        contentValues.put("price","300");
//        contentValues.put("quantity","8");
//        contentValues.put("cat_id",3);
//        sqLiteDatabase6.insert("product",null,contentValues);
//
//
//
//        contentValues.put("proname","EssenceMascara");
//        contentValues.put("price","200");
//        contentValues.put("quantity","8");
//        contentValues.put("cat_id",3);
//        sqLiteDatabase6.insert("product",null,contentValues);
//
//
//        contentValues.put("proname","EssenceEyeBrowPowder");
//        contentValues.put("price","100");
//        contentValues.put("quantity","8");
//        contentValues.put("cat_id",3);
//        sqLiteDatabase6.insert("product",null,contentValues);
//
//        contentValues.put("proname","SephoraLipBalm");
//        contentValues.put("price","80");
//        contentValues.put("quantity","8");
//        contentValues.put("cat_id",3);
//        sqLiteDatabase6.insert("product",null,contentValues);


    }

    public Cursor getspesificcategory(int cid){
        onlineDatabase2 =getReadableDatabase();
        Cursor c= onlineDatabase2.rawQuery("select * from product where cat_id like '%"+cid+"%'",null);
        c.moveToFirst();
//        sqLiteDatabase3.close();
        return c;

    }
    public Cursor getdetailes(int id){
        onlineDatabase2 =getReadableDatabase();
        Cursor cursor= onlineDatabase2.rawQuery("select * from product where proid like "+String.valueOf(id),null);
        cursor.moveToFirst();

        return cursor;
    }
    public Cursor getprouduct(String name)
    {
        onlineDatabase2 =getReadableDatabase();

        Cursor cursor= onlineDatabase2.rawQuery("select * from product where proname like '%"+name+"%'",null);
        cursor.moveToNext();
        return cursor;
    }
    public String getproductprice(String name)
    {
        onlineDatabase2 =getReadableDatabase();
        String[]arr={name};
        Cursor cursor= onlineDatabase2.rawQuery("select price from product where proname like ?",arr);
        cursor.moveToNext();
        return cursor.getString(0);
    }
    public Cursor get(String name)
    {
        onlineDatabase2 =getReadableDatabase();
        String[]arr={name};
        Cursor cursor= onlineDatabase2.rawQuery("select * from product where proname like ?",arr);
        cursor.moveToNext();
        return cursor;
    }
    public void insertorders(String date,String address,String city,String country,int id){
        ContentValues row=new ContentValues();
        onlineDatabase2 =getWritableDatabase();
        row.put("orderid",1);
        row.put("orderdate",date);
        row.put("adress",address);
        row.put("city",country);
        row.put("country",city);
        row.put("cast_id",id);

        onlineDatabase2.insert("orders",null,row);
//        sqLiteDatabase3.close();

    }




    public void insertorderdetails(String name,String quantity, int orid){
        ContentValues row=new ContentValues();
        onlineDatabase2 =getWritableDatabase();
        row.put("ordID",orid);
        row.put("proid",name);
        row.put("quantity",quantity);



        onlineDatabase2.insert("order_details",null,row);
//        sqLiteDatabase3.close();

    }

    public Cursor getid(String name){
        onlineDatabase2 =getReadableDatabase();
        Cursor c= onlineDatabase2.rawQuery("select * from custmers where phone = ?  ",new String[]{name});
        c.moveToFirst();
        return c;
    }

    public String prodid(String name)
    {
        onlineDatabase2 =getReadableDatabase();
        String[]arr={name};
        Cursor cursor= onlineDatabase2.rawQuery("select proid from product where proname like ?",arr);
        cursor.moveToNext();
        return cursor.getString(0);
    }
    public String orderid(int cid){
        onlineDatabase2 =getReadableDatabase();
        String[] arr = new String[] {String.valueOf(cid)};
        Cursor c= onlineDatabase2.rawQuery("select orderid from orders where cast_id like ?",arr);
        c.moveToNext();
        return c.getString(0);

    }

}