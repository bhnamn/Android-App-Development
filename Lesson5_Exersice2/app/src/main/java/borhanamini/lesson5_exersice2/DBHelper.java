package borhanamini.lesson5_exersice2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by borha on 1.10.2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "myDatabase.db";
    private static final int DB_VERSION = 1;

    public static final String TABLE_SHOPPING = "product";
    public static final String C_ID = "_id";
    public static final String C_NAME = "name";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String sqlCreateTablePeople = "CREATE TABLE "
                + TABLE_SHOPPING + "( " + C_ID
                + " integer primary key autoincrement, " + C_NAME
                + " text not null);";
        db.execSQL(sqlCreateTablePeople);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String sqlDropTablePeople = "DROP TABLE IF EXISTS " + TABLE_SHOPPING + ";";
        db.execSQL(sqlDropTablePeople);
        onCreate(db);
    }
}
