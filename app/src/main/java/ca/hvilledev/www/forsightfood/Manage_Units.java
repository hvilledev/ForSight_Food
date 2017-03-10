package ca.hvilledev.www.forsightfood;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_UNITS_DESCRIPTION;
import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_UNITS_PRIMARY_KEY;
import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_UNITS_SYSTEM;


/**
 * Created by miked on 28/10/14.
 */
public class Manage_Units extends ListActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.units_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.units_AddNew:
                Toast.makeText(this, "Add selected!!!", Toast.LENGTH_SHORT)
                        .show();
                break;
            // action with ID action_settings was selected
            case R.id.action_settings:
                Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            default:

                break;
        }

        return true;
    }

    private UnitsListAdapter lvUnitsAdapter;
    EditText inputUnit;
    String[] unitsList;
    private TextView lineItemId;
    private Cursor cursor;
    private static ListView lv;
    private static View row;
    private SimpleAdapter simpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<UnitsViewWrapper> adapterUnits = new ArrayList<UnitsViewWrapper>();

        setContentView(R.layout.units);

        SQLite_Control unitDB = new SQLite_Control(this);
        ArrayList<HashMap<String, String>> unitsArrayHashList = unitDB.getAllUnits();

        lv = (ListView) findViewById(android.R.id.list);



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
            lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Toast.makeText(getBaseContext(), "Long Click of Item to MODIFY/DELETE", Toast.LENGTH_LONG).show();

                    return true;
                }
            });
        }

        lvUnitsAdapter = new UnitsListAdapter(
                this,
                unitsArrayHashList);

        lv.setAdapter(lvUnitsAdapter);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.i("Manage_Units 135:", requestCode + "  resultCode =" + resultCode);
        if (requestCode == 100) {
            if(resultCode == RESULT_OK){
                lvUnitsAdapter.notifyDataSetChanged();
                lv.invalidateViews();
                lv.refreshDrawableState();

            }else {
                Log.i("Manage_Units 143 : ", " resultCode ="+resultCode);
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

    public static void updateUnitRowLv(HashMap<String, String> rowHash){

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
