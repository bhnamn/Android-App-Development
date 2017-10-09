package borhanamini.database;

import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends ListActivity {

    private static final int ADD_DIALOG = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // finding list view
        final ListView list = getListView();
        list.setItemsCanFocus(false);
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // initialize the adapter
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.list_item,
                null,
                new String[]{DBHelper.C_NAME, DBHelper.C_COUNT, DBHelper.C_PRICE},
                new int[]{R.id.productName, R.id.productCount, R.id.productPrice});
        setListAdapter(adapter);

        updateAdapterData();
    }

    public void updateAdapterData() {
        // re-query the data
        SQLiteDatabase db = new DBHelper(this).getReadableDatabase();
        Cursor c = db.query(DBHelper.TABLE_SHOP_LIST, null, null, null, null, null, DBHelper.C_NAME);
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
            int rowsAffected = db.delete(DBHelper.TABLE_SHOP_LIST, DBHelper.C_ID + " = " + itemId, null);
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
                d.setContentView(R.layout.add_product_dialog);
                d.setTitle("Add a Product");
                final TextView name = (TextView) d.findViewById(R.id.name);
                final TextView countt = (TextView) d.findViewById(R.id.count);
                final String countText = countt.getText().toString();
                final int count = Integer.parseInt(countText);
                final TextView pricee = (TextView) d.findViewById(R.id.price);
                final String priceText = pricee.getText().toString();
                final double price = Integer.parseInt(priceText);
                d.findViewById(R.id.okay_btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addProduct(name.getText().toString(), count, price);
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

    public void addProduct(String name, int count, double price) {
        // add the new data to the db
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.C_NAME, name);
        cv.put(DBHelper.C_COUNT, count);
        cv.put(DBHelper.C_PRICE, price);
        db.insert(DBHelper.TABLE_SHOP_LIST, null, cv);
        db.close();

        // update the view
        updateAdapterData();
    }
}
