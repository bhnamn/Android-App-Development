package borhanamini.lesson5_exercise2_shoppinglist;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends ListActivity {

    private static final int ADD_DIALOG = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView list = getListView();
        list.setItemsCanFocus(false);
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // initialize the adapter
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_single_choice,
                null,
                new String[]{DBHelper.C_NAME},
                new int[]{android.R.id.text1});
        setListAdapter(adapter);

        updateAdapterData();
    }

    public void updateAdapterData() {
        // re-query the data
        SQLiteDatabase db = new DBHelper(this).getReadableDatabase();
        Cursor c = db.query(DBHelper.TABLE_PEOPLE,
                null, null, null, null, null, null);
        ((SimpleCursorAdapter)getListAdapter()).changeCursor(c);
        db.close();
    }

    public void onAddClicked(View view) {
        showDialog(ADD_DIALOG);
    }

    public void onDeleteClicked(View view) {
        int position = getListView().getCheckedItemPosition();
        if (position >= 0) {
            long itemId = getListAdapter().getItemId(position);
            SQLiteDatabase db = new DBHelper(this).getWritableDatabase();
            int rowsAffected = db.delete(DBHelper.TABLE_PEOPLE, DBHelper.C_ID + " = " + itemId, null);
            db.close();
            if (rowsAffected > 0)
                updateAdapterData();
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog d;
        switch (id) {
            case ADD_DIALOG:
                d = new Dialog(this);
                d.setContentView(R.layout.add_person_dialog);
                d.setTitle("Add a Person");
                final TextView nameText = (TextView) d.findViewById(R.id.name);
                d.findViewById(R.id.okay_btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addPerson(nameText.getText().toString());
                        dismissDialog(MainActivity.ADD_DIALOG);
                    }
                });
                d.findViewById(R.id.cancel_btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismissDialog(MainActivity.ADD_DIALOG);
                    }
                });
                break;
            default:
                d = super.onCreateDialog(id);
                break;
        }
        return d;
    }

    public void addPerson(String name) {
        // add the new data to the db
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.C_NAME, name);
        db.insert(DBHelper.TABLE_PEOPLE, null, cv);
        db.close();

        // update the view
        updateAdapterData();
    }
}

