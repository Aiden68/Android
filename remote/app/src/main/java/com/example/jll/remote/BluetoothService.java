package com.example.jll.remote;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class BluetoothService extends Service {
    public BluetoothService() {
    }

    public BluetoothAdapter mbt= BluetoothAdapter.getDefaultAdapter();;
    public BluetoothDevice device;
    public BluetoothSocket clientSocket;
    public OutputStream os;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String tmpAddress="",control="",disconnect="";
        if(intent.getExtras().getString("address","null")!=null) {
            tmpAddress = intent.getExtras().getString("address","null");
        }
        if(intent.getExtras().getString("address","null")!=null) {
            control = intent.getExtras().getString("control","null");
        }
        if(intent.getExtras().getString("disconnect","null")!=null) {
            disconnect = intent.getExtras().getString("disconnect","null");
        }
        Log.v("intent address:",tmpAddress);
        Log.v("intent control:",control);
        Log.v("intent disconnect:",disconnect);
//        String tmpAddress = intent.getExtras().getString("address","null");
//        String control = intent.getExtras().getString("control","null");
        if(tmpAddress.length() > 3) {
            String address = tmpAddress;

            try{
                if(mbt.isDiscovering()){
                    mbt.cancelDiscovery();
                }
                try{
                    if(device == null){
                        device = mbt.getRemoteDevice(address);
                    }
//                    if(clientSocket!=null){
//                        String tmp = clientSocket.getRemoteDevice().getAddress();
//                        if(tmp.equals(address)){
//                            clientSocket.close();
//                            clientSocket=null;
//                            clientSocket = device.createRfcommSocketToServiceRecord(UUID.fromString
//                                    ("00001101-0000-1000-8000-00805F9B34FB"));
//                            clientSocket.connect();
//                            os =clientSocket.getOutputStream();
//                            if(clientSocket != null){
//                                Toast.makeText(getApplicationContext(),"Connect success" ,
//                                        Toast.LENGTH_LONG).show();
//                            }
//                        }
//                    }
                    if(clientSocket == null){

                        clientSocket = device.createRfcommSocketToServiceRecord(UUID.fromString
                                ("00001101-0000-1000-8000-00805F9B34FB"));
                        clientSocket.connect();
                        os =clientSocket.getOutputStream();
                        if(clientSocket != null){
                            Toast.makeText(getApplicationContext(),"Connect success" ,
                                    Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Connect failed" ,
                                    Toast.LENGTH_LONG).show();
                        }
                        Log.v("intent Clientsocket",clientSocket.toString());
                    }
                }
                catch (Exception e){
                }
            }
            catch (Exception e){
            }
        }
        if(disconnect.equals("disconnect")&&clientSocket!=null){
            Log.v("clientsocket:","close");
            try {
                clientSocket.close();
                clientSocket=null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        switch (control){
            case "a":
                sendMessageHandle("a");
                break;
            case "p":
                sendMessageHandle("p");
                break;
            case "l":
                sendMessageHandle("l");
                break;
            case "r":
                sendMessageHandle("r");
                break;
            case "u":
                sendMessageHandle("u");
                break;
            case "d":
                sendMessageHandle("d");
                break;
            case "s":
                sendMessageHandle("s");
                break;
            default:
                break;
        }
        return START_STICKY;
    }


    private void sendMessageHandle(String msg)
    {
        if (clientSocket == null)
        {
            Toast.makeText(getApplicationContext(), "没有连接", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            OutputStream os = clientSocket.getOutputStream();
            os.write(msg.getBytes());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
