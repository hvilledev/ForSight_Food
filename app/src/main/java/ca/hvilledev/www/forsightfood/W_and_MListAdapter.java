package ca.hvilledev.www.forsightfood;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_W_AND_M_UNIT_A_XREF;
import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_W_AND_M_PRIMARY_KEY;
import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_W_AND_M_UNIT_B_XREF;
import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_W_AND_M_FACTOR;

/**
 * Created by miked on 05/11/14.
 */
class W_and_MListAdapter extends BaseAdapter {

    private ArrayList<HashMap<String, String>> mData;
    private LayoutInflater mInflater;
    private Context ctx;

    public W_and_MListAdapter(Context context,
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

        View row;

        if (convertView == null) {
            Log.i("W_and_MListAdapter 69", "*** " + position + "  " + convertView);

            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.w_and_m_item, parent,false);

        } else {
            row = convertView;
        }
//        mView = convertView;
        Log.i("W_and_MListAdapter 78", "*** " + row + "  " + position);

        return this.bindData(row, position);

    }

    private View bindData(View row, int position) {

        if (mData.get(position) == null) {
            return row;
        }

        View mRow = row;
        HashMap<String,String > item = mData.get(position);

        View viewElement = mRow.findViewById(R.id.w_and_mItemId);
        TextView tv = (TextView) viewElement;
        tv.setText(item.get(FN_W_AND_M_PRIMARY_KEY));

        viewElement = mRow.findViewById(R.id.w_and_m_ItemUnitA);
        tv = (TextView) viewElement;
        tv.setText(item.get(FN_W_AND_M_UNIT_A_XREF));

        viewElement = mRow.findViewById(R.id.w_and_m_ItemUnitB);
        tv = (TextView) viewElement;
        tv.setText(item.get(FN_W_AND_M_UNIT_B_XREF));

        viewElement = mRow.findViewById(R.id.w_and_m_ItemFactor);
        tv = (TextView) viewElement;
        tv.setText(item.get(FN_W_AND_M_FACTOR));

        return mRow;
    }
    public void updateAdapter() {

        //and call notifyDataSetChanged
        notifyDataSetChanged();
    }
}
