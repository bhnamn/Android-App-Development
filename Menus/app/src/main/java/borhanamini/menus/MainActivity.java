package borhanamini.menus;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.ListView;
import java.util.Comparator;

public class MainActivity extends ListActivity {
    private int optionLastClickedId = -1;
    private int optionClickedId = -1;

    public class MyData {
        public String name;
        public boolean clicked;
        public MyData(String name) {
            this.name = name;
            this.clicked = false;
        }
    }

    private MyData[] data = new MyData[] {
            new MyData("Odin"),
            new MyData("Thor"),
            new MyData("Loki"),
            new MyData("Baldr"),
            new MyData("Freyr"),
            new MyData("Heimdallr"),
            new MyData("Ullr"),
            new MyData("Meili"),
            new MyData("Hodr"),
            new MyData("Forseti")
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View textView = findViewById(R.id.text);
        registerForContextMenu(textView);
        MyListAdapter adapter = new MyListAdapter(this, data);
        adapter.sort(new Comparator<MyData>() {
            @Override
            public int compare(MyData arg0, MyData arg1) {
                return arg0.name.compareTo(arg1.name);
            }
        });

        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        MyListAdapter adapter = (MyListAdapter) getListAdapter();
        MyData item = adapter.getItem(position);
        item.clicked = !item.clicked;
        adapter.notifyDataSetInvalidated();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mi_info:
                Toast.makeText(this, "Exterminate!", Toast.LENGTH_LONG).show();
                break;
            case R.id.mi_exit:
                Toast.makeText(this, "You will be upgraded.", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(optionLastClickedId);
        if (item != null) {
            item.setEnabled(true);
        }
        item = menu.findItem(optionClickedId);
        if (item != null) {
            item.setEnabled(false);
        }
        optionLastClickedId = optionClickedId;

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        optionClickedId = item.getItemId();

        switch (optionClickedId) {
            case R.id.mi_info:
                Toast.makeText(this, "Exterminate!", Toast.LENGTH_LONG).show();
                break;
            case R.id.mi_exit:
                Toast.makeText(this, "You will be upgraded.", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}