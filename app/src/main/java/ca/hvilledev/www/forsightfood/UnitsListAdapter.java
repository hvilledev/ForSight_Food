package ca.hvilledev.www.forsightfood;


import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_UNITS_DESCRIPTION;
import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_UNITS_PRIMARY_KEY;
import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_UNITS_SYSTEM;

class UnitsListAdapter extends BaseAdapter {

    private ArrayList<HashMap<String, String>> mData;
    private LayoutInflater mInflater;
    Context ctx;

    public UnitsListAdapter(Context context,
                            ArrayList<HashMap<String, String>> data) {

        ctx = context;

        this.mInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mData = new ArrayList<HashMap<String, String>>();
        mData = data;

    }

    @Override
    public int getCount() {
        return this.mData.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mData.get(position);
    }

    public void addItem(final int position) {
//       ToDo  Something to insert a view at "position".

        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }




    public View getView(int position, View convertView, ViewGroup parent) {

////            UnitsViewWrapper wrapper = null;
        View row;


        if (convertView == null) {
            Log.i("getView if ", "*** " + position + "  " + convertView);

            ////            convertView = mInflater.inflate(R.layout.unit_item, null);
//******************
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.unit_item, parent,false);


//******************
//            mView = mInflater.inflate(mResource, parent, false);
//******************

        } else {
            row = convertView;
        }
//        mView = convertView;
        return this.bindData(row, position);

    }

    public View bindData(View row, int position) {

        if (mData.get(position) == null) {
            return row;
        }

        View mRow = row;
////            UnitsViewWrapper item = mData.get(position);
        HashMap<String,String > item = mData.get(position);

        View viewElement = mRow.findViewById(R.id.unitItemId);
        TextView tv = (TextView) viewElement;
        tv.setText(item.get(FN_UNITS_PRIMARY_KEY));

        viewElement = mRow.findViewById(R.id.unitItemDesc);
        tv = (TextView) viewElement;
        tv.setText(item.get(FN_UNITS_DESCRIPTION));

        viewElement = mRow.findViewById(R.id.unitItemSys);
        tv = (TextView) viewElement;
        tv.setText(item.get(FN_UNITS_SYSTEM));

        return mRow;
    }
    public void updateAdapter() {

        //and call notifyDataSetChanged
        notifyDataSetChanged();
    }
}
