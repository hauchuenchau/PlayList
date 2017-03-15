package com.cnlive.video;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView playList;

    private String[] data = {"e0bb4d7404d24bfea93311896e71c55b", "dea73a1a95424d24bec8ff47bec247bb",
            "927a8df65a2a4a408825ece60dd6805b", "afad5c35463e483a94812f1173befa56",
            "31c00d5f222542c99a29c96e2714d534", "88c78563ca7b6149d5a9381e92ad16a58b",
            "9de4e9229a6c4b3da7fa1926d1fdf2e1", "6e8c8ab794ed4e8aaa41078f1340b21e",
            "1b8e212c4d9e41b7a2000a9fb0040f34", "058b13d7ef2f48d2831a880d5ffcac16",
            "2ded7e5c70fc41c29d7dda725da115bd", "497825a824924feaab3cde95700d9c55",
            "054c7dc2406046a18c11d1bce669449c", "ce720cc29b034a89bbb3fbaab33b992f",
            "20d6343bf499448ea9baeecb40756f51", "a1b53e671fba494ab15a9a241cbd8c80",
            "682a5af204fd493590ad87e430cff9dc", "4b3a8011cb004f58877d28d2fa68bbd7",
            "4944933ca9794d63bdb223dbd02e0980", "6f2366d112394ae88d9bb6e8f0126a6c",
            "71c32fb55f2041939c009bbef0bfa1e6", "d11fb9d9adcd4f06a16c9e987ef68b09"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playList = (ListView) findViewById(R.id.list_play);
        PlayListAdapter adapter = new PlayListAdapter(this);
        playList.setAdapter(adapter);

        playList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
        intent.putExtra("path", (String) getData().get(position).get("path"));
        intent.putExtra("title", (String) getData().get(position).get("title"));
        startActivity(intent);
    }


    static class ViewHolder {
        public ImageView imageView;
        public TextView title;
        public TextView subTitle;
    }

    public class PlayListAdapter extends BaseAdapter {
        private LayoutInflater mInflater = null;

        private PlayListAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return getData().size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holer = null;
            if (convertView == null) {
                holer = new ViewHolder();
                convertView = mInflater.inflate(R.layout.playlist_item, null);
                holer.imageView = (ImageView) convertView.findViewById(R.id.videoImg);
                holer.title = (TextView) convertView.findViewById(R.id.videotitle);
                holer.subTitle = (TextView) convertView.findViewById(R.id.subtitle);
                convertView.setTag(holer);
            } else {
                holer = (ViewHolder) convertView.getTag();
            }
            holer.imageView.setBackgroundResource((Integer) getData().get(position).get("img"));
            holer.title.setText((String) getData().get(position).get("title"));
            holer.subTitle.setText((String) getData().get(position).get("subTitle"));
            return convertView;
        }
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("img", R.mipmap.ic_launcher);
            map.put("title", "标题" + i);
            map.put("subTitle", "副标题" + i);
            map.put("path", data[i]);
            list.add(map);
        }
        return list;
    }
}
