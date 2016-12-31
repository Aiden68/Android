package com.example.jll.remote;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    public TextView connect;
    public TextView control;
    public TextView random;
    public BluetoothAdapter mbt;
    protected List<String> devices;
    protected ListView lv;
    protected Set<BluetoothDevice> pairedDevice;
    protected ArrayAdapter<String> arrayAdapter;
    protected BluetoothSocket clientSocket;
    public BluetoothDevice device;
    public OutputStream os;
    public String address;
//    public MyAdapter mAdapter;
    public FragmentManager fm;
    public FragmentTransaction ft;
    ArrayList<HashMap<String, Object>>deviceList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connect = (TextView) findViewById(R.id.Connect);
        control = (TextView) findViewById(R.id.Control);
        random = (TextView) findViewById(R.id.Random);
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
//        connect.setSelected(true);
        mbt = BluetoothAdapter.getDefaultAdapter();
        deviceList = new ArrayList<HashMap<String, Object>>();
        lv = (ListView)findViewById(R.id.lv);
//        pairedDevice = mbt.getBondedDevices();
//        if(pairedDevice.size() > 0){
//            for (BluetoothDevice d:pairedDevice){
//                HashMap<String, Object> map = new HashMap<String, Object>();
//                map.put("ItemTitle", d.getName());
//                map.put("ItemText", d.getAddress());
//                if(!deviceList.contains(map)) {
//                    deviceList.add(map);
//                }
//            }
//        }
        devices = new ArrayList<String>();
//       mAdapter = new MyAdapter(this);
//        lv.setAdapter(mAdapter);
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String s = mAdapter.getItem(i).toString();
//                address = s.substring(s.indexOf(":") + 1).trim();
//                Intent intent = new Intent(MainActivity.this,BluetoothService.class);
//                Bundle data = new Bundle();
//                data.putString("address",address);
//                intent.putExtras(data);
//                startService(intent);
//            }
//        });

        connect.setOnClickListener(mylistener);
        control.setOnClickListener(mylistener);
        random.setOnClickListener(mylistener);

        connect.performClick();
//        btnAdjust.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View view){
//                Intent intent = new Intent(MainActivity.this,control.class);
//                Bundle data = new Bundle();
//                data.putString("MA",address);
//                intent.putExtras(data);
//                startActivity(intent);
//            }
//        });
//        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
//        this.registerReceiver(receiver,filter);
//
//        filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
//        this.registerReceiver(receiver,filter);
    }

//    protected final BroadcastReceiver receiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String action = intent.getAction();
//            if(BluetoothDevice.ACTION_FOUND.equals(action)){
//                BluetoothDevice d = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
//                if(d.getBondState() != BluetoothDevice.BOND_BONDED) {
//                    HashMap<String, Object> map = new HashMap<String, Object>();
//                    map.put("ItemTitle", d.getName());
//                    map.put("ItemText", d.getAddress());
//                    if(!deviceList.contains(map)){
//                        deviceList.add(map);
//                    }
//                }
//                mAdapter = new MyAdapter(MainActivity.this);
////                lv.setAdapter(mAdapter);
//            }
//        }
//    };

    View.OnClickListener mylistener =new View.OnClickListener(){
        public void onClick(View v){
            ft = fm.beginTransaction();
            switch (v.getId()) {
                case R.id.Connect:
                    control.setSelected(false);
                    random.setSelected(false);
                    connect.setSelected(true);
                    ft.replace(R.id.content,new FragmentListview());
                    break;
                case R.id.Control:
                    connect.setSelected(false);
                    random.setSelected(false);
                    control.setSelected(true);
                    ft.replace(R.id.content,new FragmentControl());
                    break;
                case R.id.Random:
                    connect.setSelected(false);
                    control.setSelected(false);
                    random.setSelected(true);
                    ft.replace(R.id.content,new FragmentRandom());
                    break;
            }
            ft.commit();
        }
    };

//    public void btnSearch(View view){
//        if(!mbt.isEnabled()){
//            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//            startActivityForResult(turnOn, 0);
//        }
//        Toast.makeText(getApplicationContext(),"Start to search" ,
//                Toast.LENGTH_LONG).show();
//        if(mbt.isDiscovering()){
//            mbt.cancelDiscovery();
//        }
//        mbt.startDiscovery();
//        pairedDevice = mbt.getBondedDevices();
//        if(pairedDevice.size()> 0){
//            for (BluetoothDevice d :pairedDevice){
////                devices.add(d.getName() + ":" + d.getAddress()+"\n");
//                HashMap<String, Object> map = new HashMap<String, Object>();
//                map.put("ItemTitle", d.getName());
//                map.put("ItemText", d.getAddress());
//                if(!deviceList.contains(map)){
//                    deviceList.add(map);
//                }
//            }
//        }
//        mAdapter = new MyAdapter(MainActivity.this);
//        lv.setAdapter(mAdapter);
//    }

//    public final class ViewHolder{
//        public TextView title;
//        public TextView text;
//        public Button bt;
//    }
//
//    //新建一个类继承BaseAdapter，实现视图与数据的绑定
//    public class MyAdapter extends BaseAdapter {
//        private LayoutInflater mInflater;//得到一个LayoutInfalter对象用来导入布局 /*构造函数*/
//
//        public MyAdapter(Context context) {
//            this.mInflater = LayoutInflater.from(context);
//        }
//
//        @Override
//        public int getCount() {
//            Log.v("MyListViewBase", "getcount: " + deviceList.size());
//            return deviceList.size();//返回数组的长度
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return 0;
//        }
//
//        /*书中详细解释该方法*/
//        @Override
//        public View getView(final int position, View convertView, ViewGroup parent) {
//            ViewHolder holder = null;
//            //观察convertView随ListView滚动情况
//            Log.v("MyListViewBase", "getView " + position + " " + convertView);
//            if (convertView == null) {
//                convertView = mInflater.inflate(R.layout.item, null);
//                holder = new ViewHolder();
//                    /*得到各个控件的对象*/
//                holder.title = (TextView) convertView.findViewById(R.id.ItemTitle);
//                holder.text = (TextView) convertView.findViewById(R.id.ItemText);
//                holder.bt = (Button) convertView.findViewById(R.id.ItemButton);
//                convertView.setTag(holder);//绑定ViewHolder对象
//            } else {
//                holder = (ViewHolder) convertView.getTag();//取出ViewHolder对象                  }
//            /*设置TextView显示的内容，即我们存放在动态数组中的数据*/
//                holder.title.setText(deviceList.get(position).get("ItemTitle").toString());
//                holder.text.setText(deviceList.get(position).get("ItemText").toString());
//
//            /*为Button添加点击事件*/
//                holder.bt.setText("connect");
//                holder.bt.setOnClickListener(new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//                        Log.v("MyListViewBase", "你点击了按钮" + position);
//                        //打印Button的点击信息
//                    }
//                });
//            }
//            if(convertView != null){
//                holder = (ViewHolder) convertView.getTag();//取出ViewHolder对象                  }
//            /*设置TextView显示的内容，即我们存放在动态数组中的数据*/
//                holder.title.setText(deviceList.get(position).get("ItemTitle").toString());
//                holder.text.setText(deviceList.get(position).get("ItemText").toString());
//
//            /*为Button添加点击事件*/
//                holder.bt.setText("connect");
//                holder.bt.setOnClickListener(new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//                        Log.v("MyListViewBase", "你点击了按钮" + position);
//                        address = deviceList.get(position).get("ItemText").toString();
//                        Intent intent = new Intent(MainActivity.this,BluetoothService.class);
//                        Bundle data = new Bundle();
//                        data.putString("address",address);
//                        intent.putExtras(data);
//                        startService(intent);
//                        //打印Button的点击信息
//                    }
//                });
//            }
//            return convertView;
//        }
//    }
}
