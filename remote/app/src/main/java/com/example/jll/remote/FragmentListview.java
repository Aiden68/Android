package com.example.jll.remote;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class FragmentListview extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Button search;
    public BluetoothAdapter mbt;
    protected List<String> devices;
    protected ListView lv;
    protected Set<BluetoothDevice> pairedDevice;
    public String address;
    public String status;
    public MyAdapter mAdapter;
    ArrayList<HashMap<String, Object>>deviceList;

    public FragmentListview() {
    }

    public static FragmentListview newInstance(String param1, String param2) {
        FragmentListview fragment = new FragmentListview();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mbt = BluetoothAdapter.getDefaultAdapter();
//        deviceList = new ArrayList<HashMap<String, Object>>();
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
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment_listview, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mbt = BluetoothAdapter.getDefaultAdapter();
        deviceList = new ArrayList<HashMap<String, Object>>();
        pairedDevice = mbt.getBondedDevices();
        if(pairedDevice.size() > 0){
            for (BluetoothDevice d:pairedDevice){
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("ItemTitle", d.getName());
                map.put("ItemText", d.getAddress());
                if(!deviceList.contains(map)) {
                    deviceList.add(map);
                }
            }
        }
//        HashMap<String, Object> map = new HashMap<String, Object>();
//        map.put("ItemTitle", "HC-05");
//        map.put("ItemText", "00:15:83:35:7D:00");
//        deviceList.add(map);
//        map.put("ItemTitle", "HC-06");
//        map.put("ItemText", "00:22:33:A1:76:4B");
//        deviceList.add(map);
//        map.put("ItemTitle", "Mi Phone");
//        map.put("ItemText", "F4:8B:32:7E:D5:58");
//        deviceList.add(map);

//        View view = inflater.inflate(R.layout.fragment_fragment_listview,null);
        devices = new ArrayList<String>();
        mAdapter = new MyAdapter(getActivity());
        lv = (ListView)getActivity().findViewById(R.id.lv);
        search = (Button)getActivity().findViewById(R.id.btnSearch);
        search.setOnClickListener(mylistener);
//        search.performClick();
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        getActivity().registerReceiver(receiver,filter);

        filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        getActivity().registerReceiver(receiver,filter);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    View.OnClickListener mylistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if(!mbt.isEnabled()){
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn, 0);
            }
            Toast.makeText(getActivity().getApplicationContext(),"Start to search" ,
                Toast.LENGTH_LONG).show();
            if(mbt.isDiscovering()){
            mbt.cancelDiscovery();
             }
            mbt.startDiscovery();
            pairedDevice = mbt.getBondedDevices();
            if(pairedDevice.size()> 0){
            for (BluetoothDevice d :pairedDevice){
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("ItemTitle", d.getName());
                map.put("ItemText", d.getAddress());
                if(!deviceList.contains(map)){
                    deviceList.add(map);
                }
            }
        }
        mAdapter = new MyAdapter(getActivity());
        lv.setAdapter(mAdapter);
        }
    };

    protected final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action)){
                BluetoothDevice d = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if(d.getBondState() != BluetoothDevice.BOND_BONDED) {
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("ItemTitle", d.getName());
                    map.put("ItemText", d.getAddress());
                    if(!deviceList.contains(map)){
                        deviceList.add(map);
                    }
                }
//                mAdapter = new MyAdapter(getActivity());
                lv.setAdapter(mAdapter);
            }
        }
    };

    public final class ViewHolder{
        public TextView title;
        public TextView text;
        public Button bt;
    }

    //新建一个类继承BaseAdapter，实现视图与数据的绑定
    public class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater;//得到一个LayoutInfalter对象用来导入布局 /*构造函数*/

        public MyAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            Log.v("MyListViewBase", "getcount: " + deviceList.size());
            return deviceList.size();//返回数组的长度
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        /*书中详细解释该方法*/
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            //观察convertView随ListView滚动情况
            Log.v("MyListViewBase", "getView " + position + " " + convertView);
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item, null);
                holder = new ViewHolder();
                    /*得到各个控件的对象*/
                holder.title = (TextView) convertView.findViewById(R.id.ItemTitle);
                holder.text = (TextView) convertView.findViewById(R.id.ItemText);
                holder.bt = (Button) convertView.findViewById(R.id.ItemButton);
                convertView.setTag(holder);//绑定ViewHolder对象
            } else {
                holder = (ViewHolder) convertView.getTag();//取出ViewHolder对象                  }
            /*设置TextView显示的内容，即我们存放在动态数组中的数据*/
                holder.title.setText(deviceList.get(position).get("ItemTitle").toString());
                holder.text.setText(deviceList.get(position).get("ItemText").toString());

            /*为Button添加点击事件*/
                holder.bt.setText("connect");
                holder.bt.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        //打印Button的点击信息
                    }
                });
            }
            if(convertView != null){
                holder = (ViewHolder) convertView.getTag();//取出ViewHolder对象                  }
            /*设置TextView显示的内容，即我们存放在动态数组中的数据*/
                holder.title.setText(deviceList.get(position).get("ItemTitle").toString());
                holder.text.setText(deviceList.get(position).get("ItemText").toString());
                final Button btn = (Button)convertView.findViewById(R.id.ItemButton);
            /*为Button添加点击事件*/
                status = holder.bt.getText().toString();
                if(status.equals("")){
                    holder.bt.setText("Connect");
                    status = "connect";
                }
                holder.bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.v("点击事件：","外"+status+" .");
                        Log.v("status：",btn.getText().toString());
                        switch (btn.getText().toString()){
                            case ("Connect"):
                                Log.v("intent 执行了intent","Connect");
                                btn.setText("Connecting");
                                address = deviceList.get(position).get("ItemText").toString();
                                Intent intent = new Intent(getActivity(),BluetoothService.class);
                                Bundle data = new Bundle();
                                data.putString("address",address);
                                intent.putExtras(data);
                                getActivity().startService(intent);
                                btn.setText("Connected");
                                break;
                            case ("connect"):
                                Log.v("intent 执行了intent1","connect");
                                btn.setText("Connecting");
                                address = deviceList.get(position).get("ItemText").toString();
                                Intent intent1 = new Intent(getActivity(),BluetoothService.class);
                                Bundle data1 = new Bundle();
                                data1.putString("address",address);
                                intent1.putExtras(data1);
                                getActivity().startService(intent1);
                                btn.setText("Connected");
                                break;
                            case ("Connected"):
                                Log.v("intent 执行了intent2","Connected");
                                Intent intent2 = new Intent(getActivity(),BluetoothService.class);
                                Bundle data2 = new Bundle();
                                data2.putString("disconnect","disconnect");
                                intent2.putExtras(data2);
                                getActivity().startService(intent2);
                                btn.setText("Connect");
                                break;
                            case ("connected"):
                                Log.v("intent 执行了intent3","connected");
                                Intent intent3 = new Intent(getActivity(),BluetoothService.class);
                                Bundle data3 = new Bundle();
                                data3.putString("disconnect","disconnect");
                                intent3.putExtras(data3);
                                getActivity().startService(intent3);
                                btn.setText("Connect");
                                break;
                        }

                    }
                });
            }
            return convertView;
        }
    }
}
