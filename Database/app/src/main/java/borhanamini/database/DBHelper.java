package borhanamini.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by borha on 10/1/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "myDatabase.db";
    private static final int DB_VERSION = 1;

    public static final String TABLE_SHOP_LIST = "people";
    public static final String C_ID = "_id";
    public static final String C_NAME = "name";
    public static final String C_COUNT = "count";
    public static final String C_PRICE = "price";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        final String sqlCreateTableProduct = "CREATE TABLE "
                + TABLE_SHOP_LIST + "( "
                + C_ID + " integer primary key autoincrement, "
                + C_NAME + " text not null, "
                + C_COUNT + " integer not null, "
                + C_PRICE + " double not null);";
        db.execSQL(sqlCreateTableProduct);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String sqlCreateTableProduct = "DROP TABLE IF EXISTS " + TABLE_SHOP_LIST + ";";
        db.execSQL(sqlCreateTableProduct);
        onCreate(db);
    }
}
