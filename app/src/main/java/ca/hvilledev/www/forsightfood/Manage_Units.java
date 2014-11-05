package ca.hvilledev.www.forsightfood;

import android.app.Activity;
import android.app.LauncherActivity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_UNITS_DESCRIPTION;
import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_UNITS_PRIMARY_KEY;
import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_UNITS_SYSTEM;


/**
 * Created by miked on 28/10/14.
 */
public class Manage_Units extends ListActivity {


    private UnitsListAdapter lvUnitsAdapter;
    ArrayList<UnitsViewWrapper> adapterUnits;
    EditText inputUnit;
    ArrayList<HashMap<String, String>> unitsArrayHashList;
    String[] unitsList;
    TextView lineItemId;
    private Cursor cursor;
    private static ListView lv;
    private static View row;
    private SimpleAdapter simpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapterUnits=new ArrayList<UnitsViewWrapper>();

        setContentView(R.layout.units);

        SQLite_Control unitDB = new SQLite_Control(this);
        unitsArrayHashList = unitDB.getAllUnits();

        lv= (ListView) findViewById(android.R.id.list);



        if (unitsArrayHashList.size()!=0){

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                    lineItemId = (TextView) findViewById(R.id.unitItemId);
                    row = view;
                    lineItemId = (TextView) view.findViewById(R.id.unitItemId);
                    String unitIdValue = lineItemId.getText().toString();

                    Intent theIntent = new Intent(getApplicationContext(), EditUnit.class);

                    theIntent.putExtra(FN_UNITS_PRIMARY_KEY, unitIdValue);

                    startActivity(theIntent);

                }

          });
        }

        lvUnitsAdapter = new UnitsListAdapter(
                this,
                unitsArrayHashList);

        lv.setAdapter(lvUnitsAdapter);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.i("in  onActivityResult   requestCode :", requestCode + "  resultCode =" + resultCode);
        if (requestCode == 100) {
            if(resultCode == RESULT_OK){
                lvUnitsAdapter.notifyDataSetChanged();
                lv.invalidateViews();
                lv.refreshDrawableState();

            }else {
                Log.i("onActivityResult : ", " resultCode ="+resultCode);
            }
        }
    }//onActivityResult

    // @Override
    public void onListItemClick(ListView l_view, View view, int position, long id) {

//        This puts a checkbox on the right of each item to allow for multiple select
        l_view.setItemChecked(position, l_view.isItemChecked(position));

//        Toast.makeText(getBaseContext(), "You clicked on " + unitsList[position], Toast.LENGTH_LONG).show();

        lineItemId = (TextView) view.findViewById(R.id.unitItemId);

        String unitIdValue = lineItemId.getText().toString();

        Intent theIntent = new Intent(getApplicationContext(), EditUnit.class);

        theIntent.putExtra(FN_UNITS_PRIMARY_KEY, unitIdValue);

        startActivity(theIntent);
    }

    public static void updateUnitRowLv (HashMap<String,String> rowHash){

        HashMap<String ,String> item = rowHash;

        View viewElement = row.findViewById(R.id.unitItemId);
        TextView tv = (TextView) viewElement;
        tv.setText(item.get(FN_UNITS_PRIMARY_KEY));

        viewElement = row.findViewById(R.id.unitItemDesc);
        tv = (TextView) viewElement;
        tv.setText(item.get(FN_UNITS_DESCRIPTION));

        viewElement = row.findViewById(R.id.unitItemSys);
        tv = (TextView) viewElement;
        tv.setText(item.get(FN_UNITS_SYSTEM));

        lv.deferNotifyDataSetChanged();
    }

}
