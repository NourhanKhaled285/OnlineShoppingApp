package com.example.onlineshopping;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class ScanQR extends AppCompatActivity {
    SurfaceView surfaceView;
    CameraSource cameraSource;
    TextView textView;
    BarcodeDetector barcodeDetector;
//    ZXingScannerView Scannerview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Scannerview =new ZXingScannerView(Main10Activity.this);
        setContentView(R.layout.activity_scan_qr);



        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        textView = (TextView) findViewById(R.id.textView9);
        barcodeDetector = new BarcodeDetector.Builder(ScanQR.this)
                .setBarcodeFormats(Barcode.QR_CODE).build();


//        if (!barcodeDetector.isOperational()) {
//            textView.setText("can not open camera !");
//        }
        cameraSource = new CameraSource.Builder(this, barcodeDetector)
                .setRequestedPreviewSize(640, 480).build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

                if (ActivityCompat.checkSelfPermission( getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                       return;
                    }
                try {
                    cameraSource.start(holder);
                }
                catch (IOException e){
                    e.printStackTrace();
                }


            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();

            }
        });


        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {

                final SparseArray <Barcode>qrcodes=detections.getDetectedItems();
                if (qrcodes.size()!=0){
                    textView.post(new Runnable() {
                        @Override
                        public void run() {
                            Vibrator vibrator=(Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                          vibrator.vibrate(1000);
                          textView.setText(qrcodes.valueAt(0).displayValue);

                        }
                    });
                }

            }
        });


//        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
//            @Override
//            public void surfaceCreated(SurfaceHolder holder) {
//                try {
//
//                    if (ActivityCompat.checkSelfPermission(Main10Activity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//                        // TODO: Consider calling
//                        //    ActivityCompat#requestPermissions
//                        // here to request the missing permissions, and then overriding
//                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                        //                                          int[] grantResults)
//                        // to handle the case where the user grants the permission. See the documentation
//                        // for ActivityCompat#requestPermissions for more details.
//                        return;
//                    }
//                    cameraSource.start(holder);
//
//               }catch (IOException e)
//               {
//                   e.printStackTrace();
//               }
//
//           }
//
//           @Override
//           public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//
//           }
//
//           @Override
//           public void surfaceDestroyed(SurfaceHolder holder) {
//               cameraSource.stop();
//
//           }
//       });
//      barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
//          @Override
//          public void release() {
//
//          }
//
//          @Override
//          public void receiveDetections(Detector.Detections<Barcode> detections) {
//              final SparseArray<Barcode> qrcode=detections.getDetectedItems();
//              if(qrcode.size()!=0)
//              {
//                  textView.post(new Runnable() {
//                      @Override
//                      public void run() {
//                          Vibrator vibrator=(Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
//                          vibrator.vibrate(1000);
//                          textView.setText(qrcode.valueAt(0).displayValue);
//
//                      }
//                  });
//              }
//          }
//      });
//    }


        }
    }

