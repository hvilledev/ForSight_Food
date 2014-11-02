package ca.hvilledev.www.forsightfood;

import android.app.LauncherActivity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_UNITS_PRIMARY_KEY;
import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_UNITS_DESCRIPTION;
import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_UNITS_SYSTEM;


/**
 * Created by miked on 28/10/14.
 */
public class Manage_Units extends ListActivity {




    private UnitsListAdapter lvUnitsAdapter;
    ArrayList<UnitsViewWrapper> adapterUnits;
    EditText inputUnit;
    ArrayList<HashMap<String, String>> unitsHashList;
    String[] unitsList;
    TextView lineItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.units);
        setListAdapter(new UnitsListAdapter());



        /////   public void ListRecycledWith3Icon(View view) {
/////       setContentView(R.layout.activity_my);
/////       setListAdapter(new IconicAdapter3());
/////       selection = (TextView) findViewById(R.id.selection);
/////   }


//        Connect to the ListView to see clicks in the list.
        lvUnitsAdapter = getListView();

//        Set to only select one item at a time use 1. 2 is for multiple
        lvUnitsAdapter.setChoiceMode(1);

//        Enable the ability to type first letter(s) to narrow list.
//        lvUnitsAdapter.setTextFilterEnabled(true);



/////    ***REMOVED DUE TO COMPLEX NATURE OF ADDING TO CUSTOM LIST ADAPTER. ***

//        EditText unitDescription = (EditText) findViewById(R.id.unitDesc);

/////
/////      unitDescription.addTextChangedListener(new TextWatcher() {
/////          @Override
/////          public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
/////
/////          }
/////
/////          @Override
/////          public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
/////              Manage_Units.this.adapterUnits.getFilter().filter(charSequence);
/////
/////          }
/////
/////          @Override
/////          public void afterTextChanged(Editable editable) {
/////
/////          }
/////      });


//        Getting array data from Units listview
        SQLite_Control unitDB = new SQLite_Control(this);
//        unitDB.initializeUnitTable_Unit(getBaseContext());

        unitsHashList = unitDB.getAllUnits();

//        if (unitsHashList.size()!=0){
//
////            ListView listView = getListView();
//           lvUnitsAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                    lineItemId = (TextView) view.findViewById(R.id.unitItemId);
//
//                    String unitIdValue = lineItemId.getText().toString();
//
//                    Intent theIntent = new Intent(getApplicationContext(), EditUnit.class);
//
//                    theIntent.putExtra(FN_UNITS_PRIMARY_KEY, unitIdValue);
//
//                    startActivity(theIntent);
//                }
//            });
//        }

//        unitsList = getResources().getStringArray(R.array.dummy_stores_list);
////        For none or single selection
//        adapterUnits =  new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, unitsList);

//
//        ListAdapter adapterUnits = new SimpleAdapter( Manage_Units.this, unitsHashList,
//                R.layout.unit_item,
//                new String[] { FN_UNITS_PRIMARY_KEY,FN_UNITS_DESCRIPTION,FN_UNITS_SYSTEM},a
//                new int[] {R.id.unitItemId, R.id.unitItemDesc, R.id.unitItemSys});
//
//
//
////        For multiple selection.  (It also allow single pic with check box.
////        adapterUnits =  new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, unitsList);
//
//        setListAdapter(adapterUnits);
//
     }

        @Override
        public void onListItemClick (ListView l_view, View view, int position, long id){

//        This puts a checkbox on the right of each item to allow for multiple select
            l_view.setItemChecked(position, l_view.isItemChecked(position));

//        Toast.makeText(getBaseContext(), "You clicked on " + unitsList[position], Toast.LENGTH_LONG).show();

            lineItemId = (TextView) view.findViewById(R.id.unitItemId);

            String unitIdValue = lineItemId.getText().toString();

            Intent theIntent = new Intent(getApplicationContext(), EditUnit.class);

            theIntent.putExtra(FN_UNITS_PRIMARY_KEY, unitIdValue);

            startActivity(theIntent);
        }






    public void ListWith2Icon(View view) {
        setContentView(R.layout.units);
        setListAdapter(new IconicAdapter());

//        don't have this
//        selection=(TextView)findViewById(R.id.selection);
    }

//
//    public void onListItemClick(ListView parent, View v,									int position, long id) {
//        selection.setText(SmartPhones[position]);
//    }

class IconicAdapter extends ArrayAdapter<String> {
    IconicAdapter() {
        super(Manage_Units.this, R.layout.unit_item, SmartPhones);
    }

    public View getView(int position, View convertView,
                        ViewGroup parent) {
        LayoutInflater inflater=getLayoutInflater();
        View row=inflater.inflate(R.layout.row_with_icon, parent,	false);
        TextView label=(TextView)row.findViewById(R.id.label);
        label.setText(SmartPhones[position]);

        ImageView icon=(ImageView)row.findViewById(R.id.icon);

        if (SmartPhones[position].startsWith("Samsung")) {
            icon.setImageResource(R.drawable.ok);
        }
        else {
            icon.setImageResource(R.drawable.radio);
        }

        return(row);
    }
}



//    HOLDER EXAMPLE

class UnitsListAdapter extends BaseAdapter {

    private ArrayList<UnitsViewWrapper> mData;
    private LayoutInflater mInflater;
    private int mResource;


    public UnitsListAdapter(Context context,
                            int resource,
                            ArrayList<UnitsViewWrapper> data) {

        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mResource = resource;
        mData = data;


    }

    public void addItem(final UnitsViewWrapper item){
        mData.add(item);
        notifyDataSetChanged();
    }


    @Override
    public int getCount(){
        return mData.size();
    }

    @Override
    public Object getItem(int position){
        return mData.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

////            UnitsViewWrapper wrapper = null;
        View mView;

        if (convertView == null) {
            Log.i("getView if ", "*** " +position +"  " + convertView);

            ////            convertView = mInflater.inflate(R.layout.unit_item, null);
            mView = mInflater.inflate(mResource,parent,false);

////                wrapper = new UnitsViewWrapper(parent);
////                convertView.setTag(wrapper);
        } else {
            mView = convertView;
////                wrapper = (UnitsViewWrapper) convertView.getTag();
        }

//            wrapper.getDesc().setText(mData(position)));
//
//            wrapper.getDesc().setText(mData.(UnitsViewWrapper)getDesc(position));
//
//            if (SmartPhones[position].startsWith("Samsung")) {
//                wrapper.getIcon().setImageResource(R.drawable.ic_launcher);
//            } else {
//                wrapper.getIcon().setImageResource(R.drawable.radio);
//            }

        return bindData(mView, position);

    }

    public View bindData(View view, int position) {

        if (mData.get(position) == null) {
            return view;
        }

////            UnitsViewWrapper item = mData.get(position);
        UnitsViewWrapper item = mData.get(position);

        View viewElement = view.findViewById(R.id.unitDescriptionEditText);
        TextView tv = (TextView)viewElement;
        tv.setText(item.description);

        viewElement = view.findViewById(R.id.unitSystemEditText);
        tv = (TextView)viewElement;
        tv.setText(item.system);

        return view;
    }

//        public static class ViewHolder {
//            return TextView textView;
//    }



    }
}