package ca.hvilledev.www.forsightfood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

class SQLite_Control extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "forsight.db";
    private static final int DATABASE_VERSION = 8;
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

    public static final String FN_W_AND_M_PRIMARY_KEY = "_id";
    private static final int     COL_W_AND_M_PRIMARY_KEY = 0;
    private static final String  TYPE_W_AND_M_PRIMARY_KEY = "integer primary key autoincrement";

    public static final String  FN_W_AND_M_UNIT_A_XREF = "unit_a_xref";
    private static final int     COL_W_AND_M_UNIT_A_XREF = 1;
    private static final String  TYPE_W_AND_M_UNIT_A_XREF = "integer";

    public static final String  FN_W_AND_M_UNIT_B_XREF = "unit_b_xref";
    private static final int     COL_W_AND_M_UNIT_B_XREF = 2;
    private static final String  TYPE_W_AND_M_UNIT_B_XREF = "integer";

    public static final String  FN_W_AND_M_FACTOR = "factor";
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
    private static final String UNITS_TABLE_NAME = "unitTable";

    public static final String FN_UNITS_PRIMARY_KEY = "_id";
    private static final int     COL_UNITS_PRIMARY_KEY = 0;
    private static final String  TYPE_UNITS_PRIMARY_KEY = "integer primary key autoincrement";

    public static final String  FN_UNITS_DESCRIPTION = "description";
    private static final int     COL_UNITS_DESCRIPTION = 1;
    private static final String  TYPE_UNITS_DESCRIPTION = "varchar(25)";

    public static final String  FN_UNITS_SYSTEM = "system";
    private static final int     COL_UNITS_SYSTEM= 2;
    private static final String  TYPE_UNITS_SYSTEM = "varchar(1)";

    private static final String[] UNITS_ALL_KEYS = new String[] {FN_UNITS_PRIMARY_KEY};

    private static final String createUnitsQuery =
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
    private static final String QUALIFIERS_TABLE_NAME = "qualifiersTable";

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

        this.initializeUnitTable_Unit(context,_db);
        this.initializeWandMTable_WandM(context,_db);


    }

    @Override
    public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
        Log.i("SQLite_Control   261", "Upgrading application's database from version " + oldVersion
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

    
//^^^^^^^^^^^^^^^^^^^^^^^^^^^ U N I T S ******************************
    
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
    private boolean deleteRowUnit(long rowId) {
        String where = FN_UNITS_PRIMARY_KEY + "=" + rowId;
        return _db.delete(UNITS_TABLE_NAME, where, null) != 0;
    }

    public void deleteAllUnits() {
        Cursor c = getAllRowsUnit();
        long rowId = c.getColumnIndexOrThrow(FN_UNITS_PRIMARY_KEY);
        if (c.moveToFirst()) {
            do {
                deleteRowUnit(c.getLong((int) rowId));
            } while (c.moveToNext());
        }
        c.close();
    }

    // Return all data in the database.
    private Cursor getAllRowsUnit() {
        String where = null;
        String rawUnitQuery = ("SELECT * FROM " + UNITS_TABLE_NAME + " ORDER BY " + FN_UNITS_DESCRIPTION);

        Cursor c = 	_db.rawQuery(rawUnitQuery, null);
        if (c != null) {
            c.moveToFirst();
        }
        if (c != null) {
            c.close();
        }
        return c;
    }

    // Get a specific row (by rowId)
    public Cursor getRowUnit(long rowId) {
        String where = FN_UNITS_PRIMARY_KEY + "=" + rowId;
        Cursor c = 	_db.query(true, UNITS_TABLE_NAME, UNITS_ALL_KEYS,
                where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        if (c != null) {
            c.close();
        }
        return c;
    }

    // Change an existing row to be equal to new data.
    public boolean updateRowUnit(long rowId, String name, int studentNum, String favColour) {
        String where = FN_UNITS_PRIMARY_KEY + "=" + rowId;

        ContentValues newValues = new ContentValues();
        newValues.put(FN_UNITS_DESCRIPTION, name);
        newValues.put(FN_UNITS_SYSTEM, studentNum);

        // Insert it into the database.
        return _db.update(UNITS_TABLE_NAME, newValues, where, null) != 0;
    }



    private void initializeUnitTable_Unit(Context ctx, SQLiteDatabase _db) {

        String[] unitCol1, unitCol2;

        unitCol1 = ctx.getResources().getStringArray(R.array.unitsCol1);
        unitCol2 = ctx.getResources().getStringArray(R.array.unitsCol2);

        int i;
        for (i = 0; i<unitCol1.length; i++) {

            Log.i("SQLite_Control 367", "Inserting "+ unitCol1[i] + "  " + unitCol2[i]);

            HashMap<String, String> hm = new HashMap<String, String>();

            hm.put(FN_UNITS_DESCRIPTION, unitCol1[i]);
            hm.put(FN_UNITS_SYSTEM, unitCol2[i].toUpperCase());

            insertUnit(_db,hm);

        }
    }

//
//        Insert a Unit.
//
    private void insertUnit(SQLiteDatabase _db, HashMap<String, String> unitQueryValues) {

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

        Log.i("SQLite_Control 410:", DATABASE_NAME +", values, " +
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
    private void unitDelete(String id) {
        _db = this.getWritableDatabase();
        String deleteQuery = "DELETE FROM " + UNITS_TABLE_NAME +
                " WHERE " + FN_UNITS_PRIMARY_KEY + "=' " + id + "'";
        _db.execSQL(deleteQuery);

//            _db.close();

    }


//
//        Read All Units from DB.
//
public ArrayList<HashMap<String, String>> getAllUnits() {

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

                //  getUnits ************ KEY:
                Log.i("SQLite_Control 458 ", cursor.getString(COL_UNITS_PRIMARY_KEY)
                                + "  A: " + cursor.getString(COL_W_AND_M_UNIT_A_XREF)
                                + "  B: " + cursor.getString(COL_W_AND_M_UNIT_B_XREF)
                );

                unitsArrayList.add(unitsMap);


            } while (cursor.moveToNext());

        }

        cursor.close();

        return unitsArrayList;

    }
//
//        Read one Unit from database.
public HashMap<String, String> getUnitInfo(String id) {

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

//^^^^^^^^^^^^^^^^^^^^^^^^^^^ U N I T S ******************************


//^^^^^^^^^^^^^^^^^W E I G H T S AND M E A S U R E S********************


    public void closeWandM() {

//        _db.close();
    }


    // Add a new set of values to the database.
    public long insertRowWandM(String unitA, String unitB, String factor) {


        // Create row's data:
        ContentValues initialValues = new ContentValues();
        initialValues.put(FN_W_AND_M_UNIT_A_XREF, unitA);
        initialValues.put(FN_W_AND_M_UNIT_B_XREF, unitB);
        initialValues.put(FN_W_AND_M_FACTOR, factor);

        // Insert it into the database.
        return _db.insert(W_AND_M_TABLE_NAME, null, initialValues);
    }

    // Delete a row from the database, by rowId (primary key)
    private boolean deleteRowWandM(long rowId) {
        String where = FN_W_AND_M_PRIMARY_KEY + "=" + rowId;
        return _db.delete(W_AND_M_TABLE_NAME, where, null) != 0;
    }

    public void deleteAllWandM() {
        Cursor c = getAllRowsWandM();
        long rowId = c.getColumnIndexOrThrow(FN_W_AND_M_PRIMARY_KEY);
        if (c.moveToFirst()) {
            do {
                deleteRowWandM(c.getLong((int) rowId));
            } while (c.moveToNext());
        }
        c.close();
    }

    // Return all data in the database.
    private Cursor getAllRowsWandM() {
        String where = null;
        String rawWandMQuery = ("SELECT * FROM " + W_AND_M_TABLE_NAME + " ORDER BY " + FN_W_AND_M_UNIT_A_XREF);

        Cursor c = 	_db.rawQuery(rawWandMQuery, null);
        if (c != null) {
            c.moveToFirst();
        }
        if (c != null) {
            c.close();
        }
        return c;
    }

    // Get a specific row (by rowId)
    public Cursor getRowWandM(long rowId) {
        String where = FN_W_AND_M_PRIMARY_KEY + "=" + rowId;
        Cursor c = 	_db.query(true, W_AND_M_TABLE_NAME, W_AND_M_ALL_KEYS,
                where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        if (c != null) {
            c.close();
        }
        return c;
    }

    // Change an existing row to be equal to new data.
    public boolean updateRowWandM(long rowId, String unitA, String unitB, String factor) {
        String where = FN_W_AND_M_PRIMARY_KEY + "=" + rowId;

        ContentValues newValues = new ContentValues();
        newValues.put(FN_W_AND_M_UNIT_A_XREF, unitA);
        newValues.put(FN_W_AND_M_UNIT_B_XREF, unitB);
        newValues.put(FN_W_AND_M_FACTOR, factor);

        // Insert it into the database.
        return _db.update(FN_W_AND_M_PRIMARY_KEY, newValues, where, null) != 0;
    }



    private void initializeWandMTable_WandM(Context ctx,SQLiteDatabase _db) {

        String[] wandmCol1, wandmCol2, wandmCol3;

        wandmCol1 = ctx.getResources().getStringArray(R.array.wandmCol1);
        wandmCol2 = ctx.getResources().getStringArray(R.array.wandmCol2);
        wandmCol3 = ctx.getResources().getStringArray(R.array.wandmCol3);

        int i;
        for (i = 0; i<wandmCol1.length; i++) {

            Log.i("SQLite_Control 611:", "Inserting "+ wandmCol1[i] + "  " + wandmCol2[i] + "  " + wandmCol3[i]);

            HashMap<String, String> hm = new HashMap<String, String>();

            hm.put(FN_W_AND_M_UNIT_A_XREF, wandmCol1[i]);
            hm.put(FN_W_AND_M_UNIT_B_XREF, wandmCol2[i]);
            hm.put(FN_W_AND_M_FACTOR, wandmCol3[i]);

            insertWandM(_db,hm);

        }
    }

    //
//        Insert a WandM.
//
    private void insertWandM(SQLiteDatabase _db, HashMap<String, String> wandmQueryValues) {

        ContentValues values = new ContentValues();

//            _db = this.getWritableDatabase();

        values.put(FN_W_AND_M_UNIT_A_XREF, wandmQueryValues.get(FN_W_AND_M_UNIT_A_XREF));
        values.put(FN_W_AND_M_UNIT_B_XREF, wandmQueryValues.get(FN_W_AND_M_UNIT_B_XREF));
        values.put(FN_W_AND_M_FACTOR, wandmQueryValues.get(FN_W_AND_M_FACTOR));

        _db.insert(W_AND_M_TABLE_NAME, null, values);

//            _db.close();
    }

    //
//        Update a WandM.
//
    public long updateWandM(HashMap<String, String> wandmQueryValues) {

        ContentValues values = new ContentValues();

        _db = this.getWritableDatabase();


        values.put(FN_W_AND_M_PRIMARY_KEY, wandmQueryValues.get(FN_W_AND_M_PRIMARY_KEY));
        values.put(FN_W_AND_M_UNIT_A_XREF, wandmQueryValues.get(FN_W_AND_M_UNIT_A_XREF));
        values.put(FN_W_AND_M_UNIT_B_XREF, wandmQueryValues.get(FN_W_AND_M_UNIT_B_XREF));
        values.put(FN_W_AND_M_FACTOR, wandmQueryValues.get(FN_W_AND_M_FACTOR));

        Log.i("SQLite_Control 657:", DATABASE_NAME +", values, " +
                FN_W_AND_M_PRIMARY_KEY + " = ?,  new String[]{wandmQueryValues.get(FN_W_AND_M_UNIT_A_XREF,FN_W_AND_M_UNIT_B_XREF)");

        long updateResults =  _db.update(W_AND_M_TABLE_NAME, values,
                FN_W_AND_M_PRIMARY_KEY + " = ?",
                new String[]{wandmQueryValues.get(FN_W_AND_M_PRIMARY_KEY)});

//            _db.close();

        return updateResults;

    }
    //
//        Delete a WandM.
//
    public void wandmDelete(String id) {
        _db = this.getWritableDatabase();
        String deleteQuery = "DELETE FROM " + W_AND_M_TABLE_NAME +
                " WHERE " + FN_W_AND_M_PRIMARY_KEY + "=' " + id + "'";
        _db.execSQL(deleteQuery);

//            _db.close();

    }


    //
//        Read All WandM from DB.
//
    public ArrayList<HashMap<String, String>> getAllWandM() {

        ArrayList<HashMap<String, String>> wandmArrayList = new ArrayList<HashMap<String, String>>();

//            String selectQuery = "SELECT * FROM " + W_AND_M_TABLE_NAME +" ORDER BY " + FN_W_AND_M_UNIT_A_XREF;
        String selectQuery = "SELECT * FROM " + W_AND_M_TABLE_NAME;

        SQLiteDatabase gaDatabase = this.getReadableDatabase();

//            _db = this.getWritableDatabase();

        Cursor cursor = gaDatabase.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                HashMap<String, String> wandmMap = new HashMap<String, String>();

                wandmMap.put(FN_W_AND_M_PRIMARY_KEY, cursor.getString(COL_W_AND_M_PRIMARY_KEY));
                wandmMap.put(FN_W_AND_M_UNIT_A_XREF, cursor.getString(COL_W_AND_M_UNIT_A_XREF));
                wandmMap.put(FN_W_AND_M_UNIT_B_XREF, cursor.getString(COL_W_AND_M_UNIT_B_XREF));
                wandmMap.put(FN_W_AND_M_FACTOR, cursor.getString(COL_W_AND_M_FACTOR));

                wandmArrayList.add(wandmMap);

             // getAllWandM ************ KEY:
                Log.i("SQLite_Control 704 ",cursor.getString(COL_W_AND_M_PRIMARY_KEY)
                                + "  A: " + cursor.getString(COL_W_AND_M_UNIT_A_XREF)
                                + "  B: " + cursor.getString(COL_W_AND_M_UNIT_B_XREF)
                                + "  FACTOR: " + cursor.getString(COL_W_AND_M_FACTOR)
                                );

            } while (cursor.moveToNext());

        }

        cursor.close();

        return wandmArrayList;

    }
    //
//        Read one WandM from database.
    public HashMap<String, String> getWandMInfo(String id) {

        HashMap<String, String> wandmMap = new HashMap<String, String>();

        _db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + W_AND_M_TABLE_NAME
                + " WHERE " + FN_W_AND_M_PRIMARY_KEY + " = '" + id + "'";

        Cursor cursor = _db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                wandmMap.put(FN_W_AND_M_PRIMARY_KEY, cursor.getString(COL_W_AND_M_PRIMARY_KEY));
                wandmMap.put(FN_W_AND_M_UNIT_A_XREF, cursor.getString(COL_W_AND_M_UNIT_A_XREF));
                wandmMap.put(FN_W_AND_M_UNIT_B_XREF, cursor.getString(COL_W_AND_M_UNIT_B_XREF));
                wandmMap.put(FN_W_AND_M_FACTOR, cursor.getString(COL_W_AND_M_FACTOR));


            } while (cursor.moveToNext());


        }

        cursor.close();

        return wandmMap;

    }

    //        Get record given 2 Units.
    public HashMap<String, String> getWandMFactor(String unitDesc1, String unitDesc2) {

        HashMap<String, String> wandmMap = new HashMap<String, String>();

        _db = this.getReadableDatabase();


        //  Get the Id for unitDesc1
        String selectQuery = "SELECT * FROM " + UNITS_TABLE_NAME
                + " WHERE " + FN_UNITS_DESCRIPTION + " = '" + unitDesc1 + "'";

        Cursor cursor = _db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            wandmMap.put("unit1Id", cursor.getString(COL_W_AND_M_PRIMARY_KEY));

        }else {
            Toast.makeText(context, context.getString(R.string.error_missing) + unitDesc1 + context.getString(R.string.in) + UNITS_TABLE_NAME,Toast.LENGTH_LONG).show();
            wandmMap.put(FN_W_AND_M_FACTOR, "0");
            return wandmMap;
        }

        //  Get the Id for unitDesc
        selectQuery = "SELECT * FROM " + UNITS_TABLE_NAME
                + " WHERE " + FN_UNITS_DESCRIPTION + " = '" + unitDesc2 + "'";

        cursor = _db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            wandmMap.put("unit2Id", cursor.getString(COL_W_AND_M_PRIMARY_KEY));

        }else {
            Toast.makeText(context, context.getString(R.string.error_missing) + unitDesc2 + context.getString(R.string.in) + UNITS_TABLE_NAME,Toast.LENGTH_LONG).show();
            wandmMap.put(FN_W_AND_M_FACTOR, "0");
            return wandmMap;
        }
//Can't us this until change w and m to us id not desc
        /*
        selectQuery = "SELECT * FROM " + W_AND_M_TABLE_NAME
                                    + " WHERE "
                                    + FN_W_AND_M_UNIT_A_XREF + " = " + wandmMap.get("unit1Id").toString()
                                    + " AND "
                                    + FN_W_AND_M_UNIT_B_XREF + " = " + wandmMap.get("unit2Id").toString()
                                    + "'";
*/
        selectQuery = "SELECT * FROM " + W_AND_M_TABLE_NAME
                + " WHERE "
                + FN_W_AND_M_UNIT_A_XREF + " = '" + unitDesc1
                + "' AND "
                + FN_W_AND_M_UNIT_B_XREF + " = '" + unitDesc2
                + "'";
        cursor = _db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            wandmMap.put("factor", cursor.getString(COL_W_AND_M_FACTOR));

        }else {
            //   Look for unit2Id/unit1Id
/*            selectQuery = "SELECT * FROM " + W_AND_M_TABLE_NAME
                    + " WHERE "
                    + FN_W_AND_M_UNIT_A_XREF + " = " + wandmMap.get("unit2Id").toString()
                    + " AND "
                    + FN_W_AND_M_UNIT_B_XREF + " = " + wandmMap.get("unit1Id").toString()
                    + "'";
                    */

            selectQuery = "SELECT * FROM " + W_AND_M_TABLE_NAME
                    + " WHERE "
                    + FN_W_AND_M_UNIT_A_XREF + " = '" + unitDesc2
                    + "' AND "
                    + FN_W_AND_M_UNIT_B_XREF + " = '" + unitDesc1
                    + "'";

            cursor = _db.rawQuery(selectQuery, null);

            Double factorD =0.0;
            if (cursor.moveToFirst()) {
                factorD = Double.parseDouble(cursor.getString(COL_W_AND_M_FACTOR));
                factorD = 1/factorD;

                wandmMap.put("factor", factorD.toString());
            }else {
                ArrayList<HashMap<String,String>> unit1Hash = new ArrayList<HashMap<String, String>>();
                ArrayList<HashMap<String,String>> unit2Hash = new ArrayList<HashMap<String, String>>();
                HashMap<String,String> working1Hash;
                HashMap<String,String> working2Hash;
                Double workingFactor;

                selectQuery = "SELECT * FROM " + W_AND_M_TABLE_NAME
                        + " WHERE "
                        + FN_W_AND_M_UNIT_A_XREF + " = '" + unitDesc1
                        + "'";

                cursor = _db.rawQuery(selectQuery, null);
                if (cursor.moveToFirst()) {

                    do {
                        working1Hash = new HashMap<String, String>();
                        working1Hash.put("UNIT", cursor.getString(COL_W_AND_M_UNIT_B_XREF));
                        working1Hash.put("FACTOR", cursor.getString(COL_W_AND_M_FACTOR));

//                        Log.i("getAllUnitConversions ************ ","  UNIT:" + unit + " Unit--X: " + cursor.getString(0));

                        unit1Hash.add(working1Hash);

                    } while (cursor.moveToNext());

                }


                selectQuery = "SELECT * FROM " + W_AND_M_TABLE_NAME
                        + " WHERE "
                        + FN_W_AND_M_UNIT_B_XREF + " = '" + unitDesc1
                        + "'";

                cursor = _db.rawQuery(selectQuery, null);
                if (cursor.moveToFirst()) {

                    do {
                        working1Hash = new HashMap<String, String>();
                        working1Hash.put("UNIT", cursor.getString(COL_W_AND_M_UNIT_A_XREF));

                        workingFactor = Double.parseDouble(cursor.getString(COL_W_AND_M_FACTOR));
                        workingFactor = 1/workingFactor;
                        working1Hash.put("FACTOR", workingFactor.toString());

                        unit1Hash.add(working1Hash);

//                        Log.i("getAllUnitConversions ************ ","  UNIT:" + unit + " Unit--X: " + cursor.getString(0));

                    } while (cursor.moveToNext());

                }


                selectQuery = "SELECT * FROM " + W_AND_M_TABLE_NAME
                        + " WHERE "
                        + FN_W_AND_M_UNIT_A_XREF + " = '" + unitDesc2
                        + "'";

                cursor = _db.rawQuery(selectQuery, null);
                if (cursor.moveToFirst()) {

                    do {
                        working2Hash = new HashMap<String, String>();
                        working2Hash.put("UNIT", cursor.getString(COL_W_AND_M_UNIT_B_XREF));
                        workingFactor = Double.parseDouble(cursor.getString(COL_W_AND_M_FACTOR));
                        workingFactor = 1/workingFactor;
                        working2Hash.put("FACTOR", workingFactor.toString());

//                        Log.i("getAllUnitConversions ************ ","  UNIT:" + unit + " Unit--X: " + cursor.getString(0));

                        unit2Hash.add(working2Hash);

                    } while (cursor.moveToNext());

                }


                selectQuery = "SELECT * FROM " + W_AND_M_TABLE_NAME
                        + " WHERE "
                        + FN_W_AND_M_UNIT_B_XREF + " = '" + unitDesc2
                        + "'";

                cursor = _db.rawQuery(selectQuery, null);
                if (cursor.moveToFirst()) {

                    do {
                        working2Hash = new HashMap<String, String>();
                        working2Hash.put("UNIT", cursor.getString(COL_W_AND_M_UNIT_A_XREF));
                        working2Hash.put("FACTOR", cursor.getString(COL_W_AND_M_FACTOR));


                        unit2Hash.add(working2Hash);

//                        Log.i("getAllUnitConversions ************ ","  UNIT:" + unit + " Unit--X: " + cursor.getString(0));

                    } while (cursor.moveToNext());

                }
//          GOT THEM ALL, NOW LOOK FOR INTERSECT.
                String string1,string2;
                for (int ix=0; ix<unit1Hash.size();ix++){
                    working1Hash = new HashMap<String, String>();
                    working1Hash = unit1Hash.get(ix);
                    for (int iy=0; iy < unit2Hash.size();iy++){
                        working2Hash = new HashMap<String, String>();
                        working2Hash = unit2Hash.get(iy);
                        string1 = working2Hash.get("UNIT");
                        string2 = working1Hash.get("UNIT");
                        if (string1.equals(string2)){
                            workingFactor = Double.parseDouble(working1Hash.get("FACTOR"));
                            factorD = workingFactor*Double.parseDouble(working2Hash.get("FACTOR"));
                            // In UNITA AND UNITB INTERSECT
                            Log.i("SQLite_Control 945 ", "Common element is:"+ working1Hash.get("UNIT"));

                            iy=unit2Hash.size();
                            ix=unit1Hash.size();
                        }
                    }
                }

                wandmMap.put("factor", factorD.toString());

                if (factorD == 0.0) {
                    Toast.makeText(context,"The conversion for " + unitDesc1 +" to "+ unitDesc2
                                                + " does not exist.",Toast.LENGTH_LONG).show();
                }

            }
        }

        cursor.close();

        return wandmMap;

    }

    public ArrayList getAllUnitConversions(String unit) {

        ArrayList<String> unitConversionAlts = new ArrayList<String>();
        _db= this.getReadableDatabase();

        String selectQuery = "SELECT "+ FN_W_AND_M_UNIT_B_XREF +" FROM " + W_AND_M_TABLE_NAME
                + " WHERE " + FN_W_AND_M_UNIT_A_XREF + " = '" + unit + "'";

        Cursor cursor = _db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {

            do {

                unitConversionAlts.add(cursor.getString(0));

                //  getAllUnitConversions ************
                Log.i("SQLite_Control 987 ","  UNIT:" + unit + " Unit--X: " + cursor.getString(0));

            } while (cursor.moveToNext());

        }

        selectQuery = "SELECT "+ FN_W_AND_M_UNIT_A_XREF +" FROM " + W_AND_M_TABLE_NAME
                + " WHERE " + FN_W_AND_M_UNIT_B_XREF + " = '" + unit + "'";

        cursor = _db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {

            do {

                unitConversionAlts.add(cursor.getString(0));

                //   getAllUnitConversions ************
                Log.i("SQLite_Control 1006 ","  UNIT:" + unit + " X--Unit " + cursor.getString(0));

            } while (cursor.moveToNext());

        }

        cursor.close();

        return unitConversionAlts;

    }


//^^^^^^^^^^^^^^^^^W E I G H T S AND M E A S U R E S********************




}
