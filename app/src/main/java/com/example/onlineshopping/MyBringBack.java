package com.example.onlineshopping;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;

public class MyBringBack extends View {
    Bitmap account,add_cart,camera1,cancel_by,logo1,mic,pas,phone,proc,sear,shop_cart;
    public MyBringBack(Context context){
        super(context);
        account=BitmapFactory.decodeResource(getResources(),R.drawable.accountperson);
        add_cart= BitmapFactory.decodeResource(getResources(),R.drawable.addcart);
        camera1=BitmapFactory.decodeResource(getResources(),R.drawable.camera);
        cancel_by=BitmapFactory.decodeResource(getResources(),R.drawable.cancel);
        logo1=BitmapFactory.decodeResource(getResources(),R.drawable.logo);
        mic=BitmapFactory.decodeResource(getResources(),R.drawable.microphone);
        pas=BitmapFactory.decodeResource(getResources(),R.drawable.pass);
        phone=BitmapFactory.decodeResource(getResources(),R.drawable.phonecall);
        proc=BitmapFactory.decodeResource(getResources(),R.drawable.proceed);
        sear=BitmapFactory.decodeResource(getResources(),R.drawable.search);
        shop_cart=BitmapFactory.decodeResource(getResources(),R.drawable.shoppingcart);



    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(account,canvas.getWidth()/2,0,null);
        canvas.drawBitmap(pas,canvas.getWidth()/2,0,null);
        canvas.drawBitmap(add_cart,canvas.getWidth()/2,0,null);
        canvas.drawBitmap(camera1,canvas.getWidth()/2,0,null);
        canvas.drawBitmap(cancel_by,canvas.getWidth()/2,0,null);
        canvas.drawBitmap(logo1,canvas.getWidth()/2,0,null);
        canvas.drawBitmap(mic,canvas.getWidth()/2,0,null);
        canvas.drawBitmap(phone,canvas.getWidth()/2,0,null);
        canvas.drawBitmap(sear,canvas.getWidth()/2,0,null);
        canvas.drawBitmap(shop_cart,canvas.getWidth()/2,0,null);
        canvas.drawBitmap(proc,canvas.getWidth()/2,0,null);
    }
}
