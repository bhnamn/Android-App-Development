package borhanamini.lesson2_exercise4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find list view
        ListView listView = (ListView) findViewById(R.id.listView);

        // generate some dummy data
        String[] phones = new String[]{
                "Android", "iOS", "WindowsMobile", "blackberry", "WebOS", "Ubuntu",
                "Android", "iOS", "WindowsMobile", "blackberry", "WebOS", "Ubuntu"
        };

        // add data to array list
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < phones.length; ++i){
            list.add(phones[i]);
        }

        // create custom adapter
        PhoneArrayAdapter adapter = new PhoneArrayAdapter(this, list);

        // add data to ArrayAdapter (own custom layout)
        // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.rowlayout, R.id.textView, list);

        // set data to list view with adapter
        listView.setAdapter(adapter);

        // item listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                // get list row data (now String as a phone name)
                String phone = list.get(position);
                // create an explicit intent
                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                // add data to intent
                intent.putExtra("phone", phone);
                // start a new activity
                startActivity(intent);

            }
        });
    }
}
