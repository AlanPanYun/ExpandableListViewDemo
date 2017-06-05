package alan.example.com.expandablelistviewdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public String[] groupStrings = {"西游记", "水浒传", "三国演义", "红楼梦"};
    public String[][] childStrings = {
            {"唐三藏", "孙悟空", "猪八戒", "沙和尚"},
            {"宋江", "林冲", "李逵", "鲁智深"},
            {"曹操", "刘备", "孙权", "诸葛亮", "周瑜"},
            {"贾宝玉", "林黛玉", "薛宝钗", "王熙凤"}
    };
    private ExpandableListView elView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        elView = (ExpandableListView) findViewById(R.id.el_view);
//        elView.setGroupIndicator(null);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        elView.setIndicatorBounds(dm.widthPixels - 40, dm.widthPixels);
        elView.setAdapter(new MyExpandableListAdapter(this));
        //        设置分组项的点击监听事件
        elView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), groupStrings[i], Toast.LENGTH_SHORT).show();
                return false;
            }
        });
//        设置子选项点击监听事件
        elView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
                                        int childPosition, long id) {
                Toast.makeText(getApplicationContext(), childStrings[groupPosition][childPosition], Toast.LENGTH_SHORT).show();
                if (v.isSelected()) {
                    v.setSelected(false);
                    v.setBackgroundColor(Color.WHITE);
                } else {
                    v.setBackgroundColor(Color.RED);
                    v.setSelected(true);
                }
                return true;
            }
        });
    }
}
