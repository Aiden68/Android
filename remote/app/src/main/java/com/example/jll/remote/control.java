package com.example.jll.remote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class control extends AppCompatActivity {

    public Button btnUp;
    public Button btnDown;
    public Button btnLeft;
    public Button btnRight;
    private Button btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
//        Intent intent = getIntent();
//        address = intent.getExtras().getString("MA","null");
//        try{
//            if(mbt.isDiscovering()){
//                mbt.cancelDiscovery();
//            }
//            try{
//                if(device == null){
//                    device = mbt.getRemoteDevice(address);
//                }
//                if(clientSocket == null){
//                    clientSocket = device.createRfcommSocketToServiceRecord(UUID.fromString
//                            ("00001101-0000-1000-8000-00805F9B34FB"));
//                    clientSocket.connect();
//                    os =clientSocket.getOutputStream();
//                }
//                if(clientSocket != null){
//                    Toast.makeText(getApplicationContext(),"please press button to adjust position" ,
//                            Toast.LENGTH_LONG).show();
//                }
//            }
//            catch (Exception e){
//            }
//        }
//        catch (Exception e){
//
//        }
        btnUp = (Button)findViewById(R.id.buttonU);
        btnUp.setOnTouchListener(new Button.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                switch(action) {
                    case MotionEvent.ACTION_DOWN:
                        Intent intent = new Intent(control.this,BluetoothService.class);
                        Bundle data = new Bundle();
                        data.putString("control","u");
                        intent.putExtras(data);
                        startService(intent);
                        break;
                    case MotionEvent.ACTION_UP:
                        Intent intent1 = new Intent(control.this,BluetoothService.class);
                        Bundle data1 = new Bundle();
                        data1.putString("control","s");
                        intent1.putExtras(data1);
                        startService(intent1);
                        break;
                }
                return false;
            }
        });

        btnLeft = (Button)findViewById(R.id.L);
        btnLeft.setOnTouchListener(new Button.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                switch(action) {
                    case MotionEvent.ACTION_DOWN:
                        Intent intent = new Intent(control.this,BluetoothService.class);
                        Bundle data = new Bundle();
                        data.putString("control","l");
                        intent.putExtras(data);
                        startService(intent);
                        break;
                    case MotionEvent.ACTION_UP:
                        Intent intent1 = new Intent(control.this,BluetoothService.class);
                        Bundle data1 = new Bundle();
                        data1.putString("control","s");
                        intent1.putExtras(data1);
                        startService(intent1);
                        break;
                }
                return false;
            }
        });

        btnRight = (Button)findViewById(R.id.R);
        btnRight.setOnTouchListener(new Button.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                switch(action) {
                    case MotionEvent.ACTION_DOWN:
                        Intent intent = new Intent(control.this,BluetoothService.class);
                        Bundle data = new Bundle();
                        data.putString("control","r");
                        intent.putExtras(data);
                        startService(intent);
                        break;
                    case MotionEvent.ACTION_UP:
                        Intent intent1 = new Intent(control.this,BluetoothService.class);
                        Bundle data1 = new Bundle();
                        data1.putString("control","s");
                        intent1.putExtras(data1);
                        startService(intent1);
                        break;
                }
                return false;
            }
        });

        btnDown = (Button)findViewById(R.id.D);
        btnDown.setOnTouchListener(new Button.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                switch(action) {
                    case MotionEvent.ACTION_DOWN:
                        Intent intent = new Intent(control.this,BluetoothService.class);
                        Bundle data = new Bundle();
                        data.putString("control","d");
                        intent.putExtras(data);
                        startService(intent);
                        break;
                    case MotionEvent.ACTION_UP:
                        Intent intent1 = new Intent(control.this,BluetoothService.class);
                        Bundle data1 = new Bundle();
                        data1.putString("control","s");
                        intent1.putExtras(data1);
                        startService(intent1);
                        break;
                }
                return false;
            }
        });

        btnStop = (Button)findViewById(R.id.btnStop);
        btnStop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(control.this,BluetoothService.class);
                Bundle data1 = new Bundle();
                data1.putString("control","s");
                intent1.putExtras(data1);
                startService(intent1);
            }
        });
    }

}
