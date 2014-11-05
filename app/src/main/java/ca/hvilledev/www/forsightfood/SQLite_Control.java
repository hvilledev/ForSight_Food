package ca.hvilledev.www.forsightfood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class SQLite_Control extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "forsight.db";
    private static final int DATABASE_VERSION = 3;
    /*
    Items Table details.
     */
    private static final String ITEMS_TABLE_NAME = "itemsTable";

    private static final String FN_ITEMS_PRIMARY_KEY = "_id";
    private static final int    COL_ITEMS_PRIMARY_KEY = 0;
    private static final String  TYPE_ITEMS_PRIMARY_KEY = "integer primary key autoincrement";

    private static final String  FN_ITEMS_PRODUCT_XREF = "product_xref";
    private static final int     COL_ITEMS_PRODUCT_XREF = 1;
    private static final String  TYPE_ITEMS_PRODUCT_XREF = "integer not null";

    private static final String  FN_ITEMS_LOCATON_XREF = "location_xref";
    private static final int     COL_ITEMS_LOCATION_XREF = 2;
    private static final String  TYPE_ITEMS_LOCATON_XREF = "integer";

    private static final String  FN_ITEMS_NFC_CODE = "nfc_code";
    private static final int     COL_ITEMS_NFC_CODE = 3;
    private static final String  TYPE_ITEMS_NFC_CODE = "integer";

    private static final String  FN_ITEMS_ITEM_COUNT = "item_count";
    private static final int     COL_ITEMS_ITEM_COUNcT = 4;
    private static final String  TYPE_ITEMS_ITEM_COUNT = "smallint not null";

    private static final String  FN_ITEMS_LAST_ADDED = "last_added";
    private static final int     COL_ITEMS_LAST_ADDED = 5;
    private static final String  TYPE_ITEMS_LAST_ADDED = "integer not null";

    private static final String[] ITEMS_ALL_KEYS = new String[] {FN_ITEMS_PRIMARY_KEY,
                                                                FN_ITEMS_PRODUCT_XREF,
                                                                FN_ITEMS_LOCATON_XREF};
    private static final String createItemsQuery =
            "CREATE TABLE "
            + ITEMS_TABLE_NAME
            + "("
            + FN_ITEMS_PRIMARY_KEY +" " + TYPE_ITEMS_PRIMARY_KEY
            + ", " + FN_ITEMS_PRODUCT_XREF + " " + TYPE_ITEMS_PRODUCT_XREF
            + ", " + FN_ITEMS_LOCATON_XREF + " " + TYPE_ITEMS_LOCATON_XREF
            + ", " + FN_ITEMS_NFC_CODE + " " + TYPE_ITEMS_NFC_CODE
            + ", " + FN_ITEMS_ITEM_COUNT + " " + TYPE_ITEMS_ITEM_COUNT
            + ", " + FN_ITEMS_LAST_ADDED + " " + TYPE_ITEMS_LAST_ADDED
            + ");";


    /*
    Products Table details.

     */
    private static final String PRODUCTS_TABLE_NAME = "productsTable";

    private static final String FN_PRODUCTS_PRIMARY_KEY = "_id";
    private static final int     COL_PRODUCTS_PRIMARY_KEY = 0;
    private static final String  TYPE_PRODUCTS_PRIMARY_KEY = "integer primary key autoincrement";

    private static final String  FN_PRODUCTS_DESCRIPTION = "description";
    private static final int     COL_PRODUCTS_DESCRIPTION = 1;
    private static final String  TYPE_PRODUCTS_DESCRIPTION = "varchar(50)";

    private static final String  FN_PRODUCTS_UNITS_XREF = "units_xref";
    private static final int     COL_PRODUCTS_UNITS_XREF = 2;
    private static final String  TYPE_PRODUCTS_UNITS_XREF = "integer";

    private static final String  FN_PRODUCTS_UNITS_PER = "units_per";
    private static final int     COL_PRODUCTS_UNITS_PER = 3;
    private static final String  TYPE_PRODUCTS_UNITS_PER = "smallint";

    private static final String  FN_PRODUCTS_BARCODE = "barcode";
    private static final int     COL_PRODUCTS_BARCODE = 4;
    private static final String  TYPE_PRODUCTS_BARCODE = "integer";

    private static final String  FN_PRODUCTS_REORDER_AT = "reorder_at";
    private static final int     COL_PRODUCTS_REORDER_AT = 5;
    private static final String  TYPE_PRODUCTS_REORDER_AT = "smallint";

    private static final String  FN_PRODUCTS_CATEGORY1_XREF = "cat1_xref";
    private static final int     COL_PRODUCTS_CATEGORY1_XREF = 6;
    private static final String  TYPE_PRODUCTS_CATEGORY1_XREF = "integer";

    private static final String  FN_PRODUCTS_CATEGORY2_XREF = "cat2_xref";
    private static final int     COL_PRODUCTS_CATEGORY2_XREF = 7;
    private static final String  TYPE_PRODUCTS_CATEGORY2_XREF = "integer";

    private static final String  FN_PRODUCTS_CATEGORY3_XREF = "cat3_xref";
    private static final int     COL_PRODUCTS_CATEGORY4_XREF = 8;
    private static final String  TYPE_PRODUCTS_CATEGORY3_XREF = "integer";

    private static final String[] PRODUCTS_ALL_KEYS = new String[] {FN_PRODUCTS_PRIMARY_KEY,
                                                                    FN_PRODUCTS_UNITS_XREF,
                                                                    FN_PRODUCTS_CATEGORY1_XREF,
                                                                    FN_PRODUCTS_CATEGORY2_XREF,
                                                                    FN_PRODUCTS_CATEGORY3_XREF};

    private static final String createProductsQuery =
            "CREATE TABLE "
                    + PRODUCTS_TABLE_NAME
                    + "("
                    + FN_PRODUCTS_PRIMARY_KEY +" " + TYPE_PRODUCTS_PRIMARY_KEY
                    + ", " + FN_PRODUCTS_DESCRIPTION + " " + TYPE_PRODUCTS_DESCRIPTION
                    + ", " + FN_PRODUCTS_UNITS_XREF + " " + TYPE_PRODUCTS_UNITS_XREF
                    + ", " + FN_PRODUCTS_UNITS_PER + " " + TYPE_PRODUCTS_UNITS_PER
                    + ", " + FN_PRODUCTS_BARCODE + " " + TYPE_PRODUCTS_BARCODE
                    + ", " + FN_PRODUCTS_REORDER_AT + " " + TYPE_PRODUCTS_REORDER_AT
                    + ", " + FN_PRODUCTS_CATEGORY1_XREF + " " + TYPE_PRODUCTS_CATEGORY1_XREF
                    + ", " + FN_PRODUCTS_CATEGORY2_XREF + " " + TYPE_PRODUCTS_CATEGORY2_XREF
                    + ", " + FN_PRODUCTS_CATEGORY3_XREF + " " + TYPE_PRODUCTS_CATEGORY3_XREF
                    + ");";


    /*
    Weights and Measures Table details.
     */
    private static final String W_AND_M_TABLE_NAME = "wandmTable";

    private static final String FN_W_AND_M_PRIMARY_KEY = "_id";
    private static final int     COL_W_AND_M_PRIMARY_KEY = 0;
    private static final String  TYPE_W_AND_M_PRIMARY_KEY = "integer primary key autoincrement";

    private static final String  FN_W_AND_M_UNIT_A_XREF = "unit_a_xref";
    private static final int     COL_W_AND_M_UNIT_A_XREF = 1;
    private static final String  TYPE_W_AND_M_UNIT_A_XREF = "integer";

    private static final String  FN_W_AND_M_UNIT_B_XREF = "unit_b_xref";
    private static final int     COL_W_AND_M_UNIT_B_XREF = 2;
    private static final String  TYPE_W_AND_M_UNIT_B_XREF = "integer";

    private static final String  FN_W_AND_M_FACTOR = "factor";
    private static final int     COL_W_AND_M_FACTOR = 3;
    private static final String  TYPE_W_AND_M_FACTOR = "real";


    private static final String[] W_AND_M_ALL_KEYS = new String[] {FN_W_AND_M_PRIMARY_KEY,
                                                                   FN_W_AND_M_UNIT_A_XREF,
                                                                   FN_W_AND_M_UNIT_B_XREF};
    private static final String createWandMQuery =
            "CREATE TABLE "
                    + W_AND_M_TABLE_NAME
                    + "("
                    + FN_W_AND_M_PRIMARY_KEY +" " + TYPE_W_AND_M_PRIMARY_KEY
                    + ", " + FN_W_AND_M_UNIT_A_XREF + " " + TYPE_W_AND_M_UNIT_A_XREF
                    + ", " + FN_W_AND_M_UNIT_B_XREF + " " + TYPE_W_AND_M_UNIT_B_XREF
                    + ", " + FN_W_AND_M_FACTOR + " " + TYPE_W_AND_M_FACTOR
                    + ");";

    /*
        Units Table details.
         */
    public static final String UNITS_TABLE_NAME = "unitTable";

    public static final String FN_UNITS_PRIMARY_KEY = "_id";
    public static final int     COL_UNITS_PRIMARY_KEY = 0;
    public static final String  TYPE_UNITS_PRIMARY_KEY = "integer primary key autoincrement";

    public static final String  FN_UNITS_DESCRIPTION = "description";
    public static final int     COL_UNITS_DESCRIPTION = 1;
    public static final String  TYPE_UNITS_DESCRIPTION = "varchar(25)";

    public static final String  FN_UNITS_SYSTEM = "system";
    public static final int     COL_UNITS_SYSTEM= 2;
    public static final String  TYPE_UNITS_SYSTEM = "varchar(1)";

    public static final String[] UNITS_ALL_KEYS = new String[] {FN_UNITS_PRIMARY_KEY};

    public static final String createUnitsQuery =
            "CREATE TABLE "
                    + UNITS_TABLE_NAME
                    + " ("
                    + FN_UNITS_PRIMARY_KEY +" " + TYPE_UNITS_PRIMARY_KEY
                    + ", " + FN_UNITS_DESCRIPTION + " " + TYPE_UNITS_DESCRIPTION
                    + ", " + FN_UNITS_SYSTEM + " " + TYPE_UNITS_SYSTEM
                    + ");";


    /*
    Qualifiers Table details.
     */
    public static final String QUALIFIERS_TABLE_NAME = "qualifiersTable";

    private static final String FN_QUALIFIERS_PRIMARY_KEY = "_id";
    private static final int     COL_QUALIFIERS_PRIMARY_KEY = 0;
    private static final String  TYPE_QUALIFIERS_PRIMARY_KEY = "integer primary key autoincrement";

    private static final String  FN_QUALIFIERS_TYPE = "type";
    private static final int     COL_QUALIFIERS_TYPE = 1;
    private static final String  TYPE_QUALIFIERS_TYPE = "integer";

    private static final String  FN_QUALIFIERS_DESCRIPTION = "desc";
    private static final int     COL_QUALIFIERS_DESCRIPTION = 2;
    private static final String  TYPE_QUALIFIERS_DESCRIPTION = "varcar(25)";


    private static final String[] QUALIFIERS_ALL_KEYS = new String[] {FN_QUALIFIERS_PRIMARY_KEY};

    private static final String createQualifiersQuery =
            "CREATE TABLE "
                    + QUALIFIERS_TABLE_NAME
                    + "("
                    + FN_QUALIFIERS_PRIMARY_KEY +" " + TYPE_QUALIFIERS_PRIMARY_KEY
                    + ", " + FN_QUALIFIERS_TYPE + " " + TYPE_QUALIFIERS_TYPE
                    + ", " + FN_QUALIFIERS_DESCRIPTION + " " + TYPE_QUALIFIERS_DESCRIPTION
                    + ");";


    private final Context context;
    private SQLiteDatabase _db;



    public SQLite_Control(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = ctx;
//        myDBHelper = new DatabaseHelper(context);
//        myDBHelper = new SQLite_Control(context);

    }


    @Override
    public void onCreate(SQLiteDatabase _db) {


        _db.execSQL(createProductsQuery);

        _db.execSQL(createQualifiersQuery);

        _db.execSQL(createWandMQuery);

        _db.execSQL(createUnitsQuery);

        _db.execSQL(createItemsQuery);

//        _db.close();

//            this.InitializeUnitTable_Unit();
        this.initializeUnitTable_Unit(context,_db);


    }

    @Override
    public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
        Log.i("onUpgrade", "Upgrading application's database from version " + oldVersion
                + " to " + newVersion + ", which will destroy all old data!");

        // Destroy old database:
        _db.execSQL("DROP TABLE IF EXISTS " + ITEMS_TABLE_NAME);
        _db.execSQL("DROP TABLE IF EXISTS " + PRODUCTS_TABLE_NAME);
        _db.execSQL("DROP TABLE IF EXISTS " + W_AND_M_TABLE_NAME);
        _db.execSQL("DROP TABLE IF EXISTS " + UNITS_TABLE_NAME);
        _db.execSQL("DROP TABLE IF EXISTS " + QUALIFIERS_TABLE_NAME);

        // Recreate new database:
        onCreate(_db);
    }

//    public SQLite_Control openUnit() {
//        db = myDBHelper.getWritableDatabase();
//        return this;
//    }

    public void closeUnit() {

//        _db.close();
    }


    // Add a new set of values to the database.
    public long insertRowUnit(String desc, int system) {


        // Create row's data:
        ContentValues initialValues = new ContentValues();
        initialValues.put(FN_UNITS_DESCRIPTION, desc);
        initialValues.put(FN_UNITS_SYSTEM, system);

        // Insert it into the database.
        return _db.insert(UNITS_TABLE_NAME, null, initialValues);
    }

    // Delete a row from the database, by rowId (primary key)
    public boolean deleteRow(long rowId) {
        String where = FN_UNITS_PRIMARY_KEY + "=" + rowId;
        return _db.delete(UNITS_TABLE_NAME, where, null) != 0;
    }

    public void deleteAll() {
        Cursor c = getAllRows();
        long rowId = c.getColumnIndexOrThrow(FN_UNITS_PRIMARY_KEY);
        if (c.moveToFirst()) {
            do {
                deleteRow(c.getLong((int) rowId));
            } while (c.moveToNext());
        }
        c.close();
    }

    // Return all data in the database.
    public Cursor getAllRows() {
        String where = null;
        String rawUnitQuery = ("SELECT * FROM " + UNITS_TABLE_NAME + " ORDER BY " + FN_UNITS_DESCRIPTION);

        Cursor c = 	_db.rawQuery(rawUnitQuery, null);
        if (c != null) {
            c.moveToFirst();
        }
        c.close();
        return c;
    }

    // Get a specific row (by rowId)
    public Cursor getRow(long rowId) {
        String where = FN_UNITS_PRIMARY_KEY + "=" + rowId;
        Cursor c = 	_db.query(true, UNITS_TABLE_NAME, UNITS_ALL_KEYS,
                where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        c.close();
        return c;
    }

    // Change an existing row to be equal to new data.
    public boolean updateRow(long rowId, String name, int studentNum, String favColour) {
        String where = FN_UNITS_PRIMARY_KEY + "=" + rowId;

        ContentValues newValues = new ContentValues();
        newValues.put(FN_UNITS_DESCRIPTION, name);
        newValues.put(FN_UNITS_SYSTEM, studentNum);

        // Insert it into the database.
        return _db.update(UNITS_TABLE_NAME, newValues, where, null) != 0;
    }



    public void initializeUnitTable_Unit(Context ctx,SQLiteDatabase _db) {

        String[] unitCol1, unitCol2;

        unitCol1 = ctx.getResources().getStringArray(R.array.unitsCol1);
        unitCol2 = ctx.getResources().getStringArray(R.array.unitsCol2);

        int i;
        for (i = 0; i<unitCol1.length; i++) {

            Log.i("unit for:", "Inserting "+ unitCol1[i] + "  " + unitCol2[i]);

            HashMap<String, String> hm = new HashMap<String, String>();

            hm.put(FN_UNITS_DESCRIPTION, unitCol1[i]);
            hm.put(FN_UNITS_SYSTEM, unitCol2[i].toUpperCase());

            insertUnit(_db,hm);

        }
    }

//
//        Insert a Unit.
//
    public void insertUnit(SQLiteDatabase _db, HashMap<String, String> unitQueryValues) {

        ContentValues values = new ContentValues();

//            _db = this.getWritableDatabase();

        values.put(FN_UNITS_DESCRIPTION, unitQueryValues.get(FN_UNITS_DESCRIPTION));
        values.put(FN_UNITS_SYSTEM, unitQueryValues.get(FN_UNITS_SYSTEM));

        _db.insert(UNITS_TABLE_NAME, null, values);

//            _db.close();
    }

//
//        Update a Unit.
//
    public long updateUnit(HashMap<String, String> unitQueryValues) {

        ContentValues values = new ContentValues();

        _db = this.getWritableDatabase();


        values.put(FN_UNITS_PRIMARY_KEY, unitQueryValues.get(FN_UNITS_PRIMARY_KEY));
        values.put(FN_UNITS_DESCRIPTION, unitQueryValues.get(FN_UNITS_DESCRIPTION));
        values.put(FN_UNITS_SYSTEM, unitQueryValues.get(FN_UNITS_SYSTEM));

        Log.i("unit updateUnit:", DATABASE_NAME +", values, " +
                FN_UNITS_PRIMARY_KEY + " = ?,  new String[]{unitQueryValues.get(FN_UNITS_DESCRIPTION)");

        long updateResults =  _db.update(UNITS_TABLE_NAME, values,
                FN_UNITS_PRIMARY_KEY + " = ?",
                new String[]{unitQueryValues.get(FN_UNITS_PRIMARY_KEY)});

//            _db.close();

        return updateResults;

    }
//
//        Delete a Unit.
//
    public void unitDelete(String id) {
        _db = this.getWritableDatabase();
        String deleteQuery = "DELETE FROM " + DATABASE_NAME +
                " WHERE " + FN_UNITS_PRIMARY_KEY + "=' " + id + "'";
        _db.execSQL(deleteQuery);

//            _db.close();

    }
//
//    public void resetListView(){
//        notifyDataSetChanged();
//    }

//
//        Read All Units from DB.
//
    public  ArrayList<HashMap<String, String>> getAllUnits() {

        ArrayList<HashMap<String, String>> unitsArrayList = new ArrayList<HashMap<String, String>>();

//            String selectQuery = "SELECT * FROM " + UNITS_TABLE_NAME +" ORDER BY " + FN_UNITS_DESCRIPTION;
        String selectQuery = "SELECT * FROM " + UNITS_TABLE_NAME;

        SQLiteDatabase gaDatabase = this.getReadableDatabase();

//            _db = this.getWritableDatabase();

        Cursor cursor = gaDatabase.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                HashMap<String, String> unitsMap = new HashMap<String, String>();

                unitsMap.put(FN_UNITS_PRIMARY_KEY, cursor.getString(COL_UNITS_PRIMARY_KEY));
                unitsMap.put(FN_UNITS_DESCRIPTION, cursor.getString(COL_UNITS_DESCRIPTION));
                unitsMap.put(FN_UNITS_SYSTEM, cursor.getString(COL_UNITS_SYSTEM));

                unitsArrayList.add(unitsMap);


            } while (cursor.moveToNext());

        }

        cursor.close();

        return unitsArrayList;

    }
//
//        Read one Unit from database.
//
//    public HashMap<String, String> getUnitInfo(String id) {
    public HashMap<String, String> getUnitInfo(String id) {

//            Don't need ArrayList<HashMap... like above because only returning 1 Unit.
            HashMap<String, String> unitsMap = new HashMap<String, String>();

            _db = this.getReadableDatabase();

            String selectQuery = "SELECT * FROM " + UNITS_TABLE_NAME
                    + " WHERE " + FN_UNITS_PRIMARY_KEY + " = '" + id + "'";

            Cursor cursor = _db.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()) {

                do {

                    unitsMap.put(FN_UNITS_PRIMARY_KEY, cursor.getString(COL_UNITS_PRIMARY_KEY));
                    unitsMap.put(FN_UNITS_DESCRIPTION, cursor.getString(COL_UNITS_DESCRIPTION));
                    unitsMap.put(FN_UNITS_SYSTEM, cursor.getString(COL_UNITS_SYSTEM));


                } while (cursor.moveToNext());


            }

            cursor.close();

            return unitsMap;

        }


}
