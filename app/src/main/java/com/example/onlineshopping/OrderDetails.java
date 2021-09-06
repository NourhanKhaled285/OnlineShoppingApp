package com.example.onlineshopping;

import android.content.Intent;
import android.database.Cursor;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class OrderDetails extends AppCompatActivity {
    EditText address;
    EditText city;
    EditText country;
    LocationManager locationManager;
    LocationListener locationListener;
       EditText username;
    String [] myLis;
     String [] stringArray;
     int p;

    String mon = new SimpleDateFormat( "yyyy-MM-dd", Locale.getDefault()).format(new Date());
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetails);
        address=(EditText)findViewById(R.id.editText15);
        city=(EditText)findViewById(R.id.editText16);
        country=(EditText)findViewById(R.id.editText17);
       username=(EditText)findViewById(R.id.editText18);
        final Intent intent = getIntent();
        stringArray  = intent.getStringArrayExtra("prodid");
        final TextView yy=(TextView)findViewById(R.id.textView7);
        myLis =  (String[]) getIntent().getSerializableExtra("quant");
        p=getIntent().getIntExtra("key",0);

        Date today = Calendar.getInstance().getTime();//getting date
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");//formating according to my need
       date = formatter.format(today);
        yy.setText(date);
        final ProjectDB database=new ProjectDB(this);
        Button ord=(Button)findViewById(R.id.button15);
        ord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c=database.getid(username.getText().toString());
                if(c.getCount()>0){
                    int id=c.getInt(0);
                    database.insertorders(date,address.getText().toString(),city.getText().toString(),country.getText().toString(),id);
                   int z=1;



                    Intent i=new Intent(OrderDetails.this, OrderData.class);
                    i.putExtra("bbord",z);
                    i.putExtra("bbprod",stringArray);
                    i.putExtra("bbquant",myLis);
                    i.putExtra("bba",p);
                    startActivity(i);
                    z++;
                }
                else {
                    Toast.makeText(getApplicationContext(),"Wrong phone number",Toast.LENGTH_LONG).show();
                }
            }
        });

//
//        locationManager=(LocationManager)this.getSystemService(LOCATION_SERVICE);
//        boolean isGPS_enabled=locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//
//        if (isGPS_enabled){
//            locationListener=new LocationListener() {
//                @Override
//                public void onLocationChanged(Location location) {
//                    double longitude=location.getLongitude();
//                    double latitude=location.getLatitude();
//                    try {
//                        Geocoder geocoder=new Geocoder(OrderDetails.this, Locale.getDefault());
//                        List<Address> addressList=geocoder.getFromLocation(latitude,longitude,1);
//                        address.setText(addressList.get(0).getAddressLine(0));
//                        city.setText(addressList.get(0).getAdminArea());
//                        country.setText(addressList.get(0).getCountryName());
//                    }
//                    catch (IOException e){
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void onStatusChanged(String provider, int status, Bundle extras) {
//
//                }
//
//                @Override
//                public void onProviderEnabled(String provider) {
//
//                }
//
//                @Override
//                public void onProviderDisabled(String provider) {
//
//                }
//            };
//        }
//        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED&&ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
//        }
//        else {
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
//            if(ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED){
//                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
//                address.setText("Getting Location");
//                city.setText("Getting Location");
//                country.setText("Getting Location");
//            }
//            else {
//                address.setText("Access not granted");
//                city.setText("Access not granted");
//                country.setText("Access not granted");
//            }
//        }


    }
}
