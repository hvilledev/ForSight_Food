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

import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_W_AND_M_FACTOR;
import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_W_AND_M_PRIMARY_KEY;
import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_W_AND_M_UNIT_A_XREF;
import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_W_AND_M_UNIT_B_XREF;

/**
 * Created by miked on 05/11/14.
 */
public class W_and_M_Manage extends ListActivity{
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.wandm_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.wandm_AddNew:
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

    private W_and_MListAdapter lvWandMAdapter;
    ArrayList<W_and_MViewWrapper> adapterWandM;
    EditText inputUnit;
    ArrayList<HashMap<String, String>> w_and_mArrayHashList;
    String[] w_and_mList;
    TextView lineItemId;
    private Cursor cursor;
    private static ListView lv;
    private static View row;
    private SimpleAdapter simpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapterWandM=new ArrayList<W_and_MViewWrapper>();

        setContentView(R.layout.w_and_m);

        SQLite_Control w_and_mDB = new SQLite_Control(this);
        w_and_mArrayHashList = w_and_mDB.getAllWandM();

        lv= (ListView) findViewById(android.R.id.list);



        if (w_and_mArrayHashList.size()!=0){

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                    lineItemId = (TextView) findViewById(R.id.w_and_mItemId);
                    row = view;
                    lineItemId = (TextView) view.findViewById(R.id.w_and_mItemId);
                    String w_and_mIdValue = lineItemId.getText().toString();

                    Intent theIntent = new Intent(getApplicationContext(), W_and_M_Edit.class);

                    theIntent.putExtra(FN_W_AND_M_PRIMARY_KEY, w_and_mIdValue);

                    startActivity(theIntent);

                }

            });
            lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Toast.makeText(getBaseContext(), "Long Click of Item to MODIFY/DELETE",Toast.LENGTH_LONG).show();

                    return true;
                }
            });
        }

        lvWandMAdapter = new W_and_MListAdapter(this, w_and_mArrayHashList);

        lv.setAdapter(lvWandMAdapter);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.i("in  onActivityResult   requestCode :", requestCode + "  resultCode =" + resultCode);
        if (requestCode == 100) {
            if(resultCode == RESULT_OK){
                lvWandMAdapter.notifyDataSetChanged();
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

//        Toast.makeText(getBaseContext(), "You clicked on " + w_and_mList[position], Toast.LENGTH_LONG).show();

        lineItemId = (TextView) view.findViewById(R.id.w_and_mItemId);

        String w_and_mIdValue = lineItemId.getText().toString();

        Intent theIntent = new Intent(getApplicationContext(), W_and_M_Edit.class);

        theIntent.putExtra(FN_W_AND_M_PRIMARY_KEY, w_and_mIdValue);

        startActivity(theIntent);
    }

    public static void updateWandMRowLv (HashMap<String,String> rowHash){

        HashMap<String ,String> item = rowHash;
    
        View viewElement = row.findViewById(R.id.w_and_mItemId);
        TextView tv = (TextView) viewElement;
        tv.setText(item.get(FN_W_AND_M_PRIMARY_KEY));

        viewElement = row.findViewById(R.id.w_and_m_ItemUnitA);
        tv = (TextView) viewElement;
        tv.setText(item.get(FN_W_AND_M_UNIT_A_XREF));

        viewElement = row.findViewById(R.id.w_and_m_ItemUnitB);
        tv = (TextView) viewElement;
        tv.setText(item.get(FN_W_AND_M_UNIT_B_XREF));

        viewElement = row.findViewById(R.id.w_and_m_ItemFactor);
        tv = (TextView) viewElement;
        tv.setText(item.get(FN_W_AND_M_FACTOR));

        lv.deferNotifyDataSetChanged();
    }

}
