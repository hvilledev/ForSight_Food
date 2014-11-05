package ca.hvilledev.www.forsightfood;

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
    private ListView lv;
    private SimpleAdapter simpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapterUnits=new ArrayList<UnitsViewWrapper>();
//
//        UnitsViewWrapper unit1 = new UnitsViewWrapper("Liter", "m");
//        this.adapterUnits.add(unit1);
//         unit1 = new UnitsViewWrapper("Cup-US", "i");
//        this.adapterUnits.add(unit1);
//         unit1 = new UnitsViewWrapper("Pint", "i");
//        this.adapterUnits.adapterUnits.add(unit1);

        setContentView(R.layout.units);

        SQLite_Control unitDB = new SQLite_Control(this);
        unitsArrayHashList = unitDB.getAllUnits();

        lv= (ListView) findViewById(android.R.id.list);



        if (unitsArrayHashList.size()!=0){

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                    lineItemId = (TextView) findViewById(R.id.unitItemId);
                    lineItemId = (TextView) view.findViewById(R.id.unitItemId);
                    String unitIdValue = lineItemId.getText().toString();
//                    Intent theIntent = new Intent(getApplicationContext(), EditUnit.class);
//
//                    theIntent.putExtra(FN_UNITS_PRIMARY_KEY, unitIdValue);
//
//                    startActivity(theIntent);

                    Context ctxt = getApplicationContext();
                    Intent theIntent = new Intent(ctxt, EditUnit.class);

                    theIntent.putExtra(FN_UNITS_PRIMARY_KEY, unitIdValue);

                    startActivityForResult(theIntent, 100);
                }

          });
        }
//**********************
        lvUnitsAdapter = new UnitsListAdapter(
                this,
                unitsArrayHashList);

        lv.setAdapter(lvUnitsAdapter);
//*************************
//        simpAdapter = new SimpleAdapter(
//                this,
//                unitsArrayHashList,
//                R.layout.unit_item,
//                new String[] {FN_UNITS_PRIMARY_KEY,FN_UNITS_DESCRIPTION,FN_UNITS_SYSTEM},
//                new int[] {R.id.unitItemId,R.id.unitItemDesc, R.id.unitItemSys});
//        setListAdapter(simpAdapter);
//*************************
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


//
//
//    class UnitsListAdapter extends BaseAdapter {
//
//        private ArrayList<UnitsViewWrapper> mData;
//        private LayoutInflater mInflater;
//        private int mResource;
//
//
//        public UnitsListAdapter(Context context,
//                                int resource,
//                                ArrayList<UnitsViewWrapper> data) {
//
//            this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//            Context ctx = context;
//            this.mResource = resource;
//            mData = new ArrayList<UnitsViewWrapper>();
//
//            this.mData = data;
//
//        }
//
//        @Override
//        public int getCount() {
//            return this.mData.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return this.mData.get(position);
//        }
//
//        public void addItem(final UnitsViewWrapper item) {
//            mData.add(item);
//            notifyDataSetChanged();
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        public View getView(int position, View convertView, ViewGroup parent) {
//
//////            UnitsViewWrapper wrapper = null;
//            View mView;
//
//            if (convertView == null) {
//                Log.i("getView if ", "*** " + position + "  " + convertView);
//
//                ////            convertView = mInflater.inflate(R.layout.unit_item, null);
//                mView = mInflater.inflate(mResource, parent, false);
//
//            } else {
//                mView = convertView;
//            }
//
//
//            return this.bindData(mView, position);
//
//        }
//
//        public View bindData(View view, int position) {
//
//            if (mData.get(position) == null) {
//                return view;
//            }
//
//////            UnitsViewWrapper item = mData.get(position);
//            UnitsViewWrapper item = this.mData.get(position);
//
//            View viewElement = view.findViewById(R.id.unitItemDesc);
//            TextView tv = (TextView) viewElement;
//            tv.setText(item.description);
//
//            viewElement = view.findViewById(R.id.unitItemSys);
//            tv = (TextView) viewElement;
//            tv.setText(item.system);
//
//            return view;
//        }
//        public void updateAdapter(Cursor cu) {
//            cursor = cu;
//
//            //and call notifyDataSetChanged
//            notifyDataSetChanged();
//        }
//    }

}
