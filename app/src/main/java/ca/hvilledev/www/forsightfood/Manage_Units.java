package ca.hvilledev.www.forsightfood;

import android.app.LauncherActivity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_UNITS_PRIMARY_KEY;


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

        adapterUnits=new ArrayList<UnitsViewWrapper>();

        UnitsViewWrapper unit1 = new UnitsViewWrapper("Liter", "m");
        this.adapterUnits.add(unit1);
         unit1 = new UnitsViewWrapper("Cup-US", "i");
        this.adapterUnits.add(unit1);
         unit1 = new UnitsViewWrapper("Pint", "i");
        this.adapterUnits.add(unit1);

        setContentView(R.layout.units);

        lvUnitsAdapter = new UnitsListAdapter(this, R.layout.unit_item,adapterUnits);

        setListAdapter(lvUnitsAdapter);

    }

    @Override
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


//    public void ListWith2Icon(View view) {
//        setContentView(R.layout.units);
//        setListAdapter(new IconicAdapter());

//        don't have this
//        selection=(TextView)findViewById(R.id.selection);
    }

//
//    public void onListItemClick(ListView parent, View v,									int position, long id) {
//        selection.setText(SmartPhones[position]);
//    }

//    class IconicAdapter extends ArrayAdapter<String> {
//        IconicAdapter() {
//            super(Manage_Units.this, R.layout.unit_item, SmartPhones);
//        }
//
//        public View getView(int position, View convertView,
//                            ViewGroup parent) {
//            LayoutInflater inflater = getLayoutInflater();
//            View row = inflater.inflate(R.layout.row_with_icon, parent, false);
//            TextView label = (TextView) row.findViewById(R.id.label);
//            label.setText(SmartPhones[position]);
//
//            ImageView icon = (ImageView) row.findViewById(R.id.icon);
//
//            if (SmartPhones[position].startsWith("Samsung")) {
//                icon.setImageResource(R.drawable.ok);
//            } else {
//                icon.setImageResource(R.drawable.radio);
//            }
//
//            return (row);
//        }
//    }


//    HOLDER EXAMPLE

    class UnitsListAdapter extends BaseAdapter {

        private ArrayList<UnitsViewWrapper> mData;
        private LayoutInflater mInflater;
        private int mResource;


        public UnitsListAdapter(Context context,
                                int resource,
                                ArrayList<UnitsViewWrapper> data) {

            this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            this.mResource = resource;
            this.mData = data;

        }

        @Override
        public int getCount() {
            return this.mData.size();
        }

        @Override
        public Object getItem(int position) {
            return this.mData.get(position);
        }

        public void addItem(final UnitsViewWrapper item) {
            mData.add(item);
            notifyDataSetChanged();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

////            UnitsViewWrapper wrapper = null;
            View mView;

            if (convertView == null) {
                Log.i("getView if ", "*** " + position + "  " + convertView);

                ////            convertView = mInflater.inflate(R.layout.unit_item, null);
                mView = mInflater.inflate(mResource, parent, false);

            } else {
                mView = convertView;
            }

            return this.bindData(mView, position);

        }

        public View bindData(View view, int position) {

            if (mData.get(position) == null) {
                return view;
            }

////            UnitsViewWrapper item = mData.get(position);
            UnitsViewWrapper item = this.mData.get(position);

            View viewElement = view.findViewById(R.id.unitItemDesc);
            TextView tv = (TextView) viewElement;
            tv.setText(item.description);

            viewElement = view.findViewById(R.id.unitItemSys);
            tv = (TextView) viewElement;
            tv.setText(item.system);

            return view;
        }

//        public static class ViewHolder {
//            return TextView textView;
//    }
//
//
//    }

}