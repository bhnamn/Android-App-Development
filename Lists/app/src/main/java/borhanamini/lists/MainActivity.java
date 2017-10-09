package borhanamini.lists;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ListActivity;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Comparator;

public class MainActivity extends ListActivity {

    public class MyData{
        public String name;
        public boolean clicked;
        public MyData(String name){
            this.name = name;
            this.clicked = false;
        }
    }

    private MyData[] data = new MyData[]{
            new MyData("Odin"),
            new MyData("Thor"),
            new MyData("Loki"),
            new MyData("Balder"),
            new MyData("Freyr"),
            new MyData("Heimdallr"),
            new MyData("Ullr"),
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyListAdapter adapter = new MyListAdapter(this, data);
        adapter.sort(new Comparator<MyData>(){
            @Override
            public int compare(MyData arg0, MyData arg1){
                return arg0.name.compareTo(arg1.name);
            }
        });
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        MyListAdapter adapter = (MyListAdapter) getListAdapter();
        MyData item = adapter.getItem(position);
        item.clicked = !item.clicked;
        adapter.notifyDataSetInvalidated();
    }
}
