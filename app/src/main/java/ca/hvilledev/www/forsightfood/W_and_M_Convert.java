package ca.hvilledev.www.forsightfood;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import static ca.hvilledev.www.forsightfood.R.layout.w_and_m_convert;
import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_UNITS_DESCRIPTION;

/**
 * Created by miked on 06/11/14.
 */
// TODO     think about showiing existing conversions for unit1 below unit2 and unit2's below unit1.  Then should be selectable to replace the unit above them.
public class W_and_M_Convert extends Activity{
    private Integer sw;
//    private final String  swu1="lvUnit1", swu2="lvUnit2", swa1="lvAmt1", swa2="lvAmt2";
    private Double factor,amount1=0.,amount2=0.;
    private ListAdapter listAdapterUnit1Alt,listAdapterUnit2Alt;
 //   private DecimalFormat fourDForm = new DecimalFormat("#.####");


    private ArrayList<String> stringArrayUnit1Alt = new ArrayList<String>();
    private ArrayList<String> stringArrayUnit2Alt = new ArrayList<String>();


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
            // action with ID action_refresh was selected
            case R.id.wandm_Edit:
                Toast.makeText(this, "Edit selected!!!", Toast.LENGTH_SHORT)
                        .show();
                break;
            // action with ID action_settings was selected
            case R.id.action_settings:
                Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT)
                        .show();

                Intent theIntent = new Intent(getApplicationContext(), W_and_M_Convert.class);

                startActivity(theIntent);

                break;
            default:
                break;
        }

        return true;
    }

    private W_and_MListAdapter lvWandMSpinAdapter;
    private ArrayList<W_and_MViewWrapper> adapterWandM;
    EditText inputUnit;
    private ArrayList<HashMap<String, String>> w_and_mArrayHashList, unitsArrayHashList;
    private ArrayList<String> spinnerUnitList;
//    ArrayList<String> stringArrayUnit1Alt,stringArrayUnit2Alt;
    private ArrayAdapter<String> arrayAdapterUnit1Alt;
    private ArrayAdapter<String> arrayAdapterUnit2Alt;
    String[] w_and_mList;
    private String unit1,unit2;
    Integer spin1Pos, spin2Pos;
    TextView lineItemId;
    private Cursor cursor;
    private Spinner lvSpin2;
    private  EditText lvAmt1, lvAmt2;
    private  View row;
    private SimpleAdapter simpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(w_and_m_convert);

        final ListView listViewUnit1Alt = (ListView) findViewById(R.id.unit1AltListView);
        final ListView listViewUnit2Alt = (ListView) findViewById(R.id.unit2AltListView);


        sw=0;

        arrayAdapterUnit1Alt = new ArrayAdapter<String>(this,
                R.layout.unit_alt_item,
                R.id.unitAltItemText,
                stringArrayUnit1Alt);

        listViewUnit1Alt.setAdapter(arrayAdapterUnit1Alt);

        arrayAdapterUnit2Alt = new ArrayAdapter<String>(this,
                R.layout.unit_alt_item,
                R.id.unitAltItemText,
                stringArrayUnit2Alt);

        listViewUnit2Alt.setAdapter(arrayAdapterUnit2Alt);
        adapterWandM = new ArrayList<W_and_MViewWrapper>();

//        stringArrayUnit1Alt = new ArrayList<String>();
//        stringArrayUnit2Alt = new ArrayList<String>();
//
//
//
//        stringArrayUnit1Alt.add("unit A1");
//        stringArrayUnit1Alt.add("unit A2");
//        stringArrayUnit1Alt.add("unit A3");
//        stringArrayUnit1Alt.add("unit A4");
//
//        stringArrayUnit2Alt.add("unit B1");
//        stringArrayUnit2Alt.add("unit B2");
//        stringArrayUnit2Alt.add("unit B3");
//        stringArrayUnit2Alt.add("unit B4");



//        Get data to fill spinners
        SQLite_Control w_and_mDB = new SQLite_Control(this);
        unitsArrayHashList = w_and_mDB.getAllUnits();
        w_and_mArrayHashList = w_and_mDB.getAllWandM();
        spinnerUnitList = new ArrayList<String>();
        int i;
        for (i = 0; i < unitsArrayHashList.size(); i++) {

            spinnerUnitList.add(unitsArrayHashList.get(i).get(FN_UNITS_DESCRIPTION));
        }

        final Spinner spinnerUnit1 = (Spinner) findViewById(R.id.wandmConvertUnit1Spinner);
     //   lvSpin1 = (Spinner) findViewById(R.id.w_and_m_convert_unit_1_spinner);
        Spinner lvSpin1 = (Spinner) spinnerUnit1;
        final Spinner spinnerUnit2 = (Spinner) findViewById(R.id.wandmConvertUnit2Spinner);
      //  lvSpin2 = (Spinner) findViewById(R.id.w_and_m_convert_unit_2_spinner);
        lvSpin2 = spinnerUnit2;
        lvAmt1 = (EditText) findViewById(R.id.wandmConvertAmount1);
        lvAmt2 = (EditText) findViewById(R.id.wandmConvertAmount2);
//
//        final ListView listViewUnit1Alt = (ListView) findViewById(R.id.unit1AltListView);
//        final ListView listViewUnit2Alt = (ListView) findViewById(R.id.unit2AltListView);
//

//        FillSpinner(lvSpin1, unitsArrayHashList);
//  todo   sort the spinners.
//  todo   use view adapter to put both Id and Desc in spinners.

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                spinnerUnitList);


        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnit1.setAdapter(spinnerAdapter);
        spinnerUnit2.setAdapter(spinnerAdapter);

//
//
//        Setup alternate list for unit1 and unit2
//
//        storesList = getResources().getStringArray(R.array.dummy_stores_list);
//
////        For multiple selection.  (It also allow single pic with check box.
//        storesAA =  new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, storesList);
//
//        setListAdapter(storesAA);
//
//        arrayAdapterUnit1Alt = new ArrayAdapter<String>(this,
//                R.layout.unit_alt_item,
//                R.id.unitAltItemText,
//                stringArrayUnit1Alt);
//
//        listViewUnit1Alt.setAdapter(arrayAdapterUnit1Alt);
//
//        arrayAdapterUnit2Alt = new ArrayAdapter<String>(this,
//                R.layout.unit_alt_item,
//                R.id.unitAltItemText,
//                stringArrayUnit2Alt);
//
//        listViewUnit2Alt.setAdapter(arrayAdapterUnit2Alt);


//        ListAdapter spin1Adapter = new SimpleAdapter(
//                this,
//                unitsArrayHashList,
//                R.layout.w_and_m_item,
//                new String[] {"unit1Id", "unit1Desc"}
//        new int[] {R.id.w_and_mSpinItemId, R.id.w_and_mSpinItemDesc});
//        setListAdapter(spin1Adapter);
//
//        ListAdapter spin2Adapter = new SimpleAdapter(
//                this,
//                unitsArrayHashList,
//                R.layout.w_and_m_item,
//                new String[] {"unit1Id", "unit1Desc"}
//        new int[] {R.id.w_and_mSpinItemId, R.id.w_and_mSpinItemDesc});
//        setListAdapter(spin1Adapter);
//
//        FillSpinner(lvSpin2, unitsArrayHashList);

        Intent thisIntent = getIntent();

        String unitId = thisIntent.getStringExtra("unit1");

        if (thisIntent.hasExtra("unit1")) {
//            called from ItemClick on w_and_m list.
            String unit1 = thisIntent.getStringExtra("unit1");
            String unit2 = thisIntent.getStringExtra("unit2");
            factor = Double.parseDouble(thisIntent.getStringExtra("factor"));

            int spinnerPosition1 = spinnerAdapter.getPosition(unit1);
            int spinnerPosition2 = spinnerAdapter.getPosition(unit2);

//            set the default according to value
            spinnerUnit1.setSelection(spinnerPosition1);
            spinnerUnit2.setSelection(spinnerPosition2);

//            Don't check for conversion now because this came from
//            a click on list of all conversions.  No amounts yet.

        }

        if (unitsArrayHashList.size() != 0) {

            spinnerUnit1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    unit1 = spinnerUnit1.getItemAtPosition(i).toString();
                    sw = 1;
                    arrayAdapterUnit1Alt.clear();
                    stringArrayUnit1Alt=fillUnitAltList(unit1);
                    arrayAdapterUnit1Alt.addAll(stringArrayUnit1Alt);  // API level [1..10] use for(..){ adapter.add(question.get(index)) }  or simply implement addAll method in you custom adapter for your own convenience (thanks to @Mena)
                    arrayAdapterUnit1Alt.notifyDataSetChanged();
                    if (unit1 != unit2) {

                        if (unit2 != null) factor = getFactor(unit1, unit2);

                        checkDoConvert();
                    }

                }
             @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            spinnerUnit2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    unit2 = spinnerUnit2.getItemAtPosition(i).toString();
                    sw = 2;
                    arrayAdapterUnit2Alt.clear();
                    stringArrayUnit2Alt=fillUnitAltList(unit2);
                    arrayAdapterUnit2Alt.addAll(stringArrayUnit2Alt);  // API level [1..10] use for(..){ adapter.add(question.get(index)) }  or simply implement addAll method in you custom adapter for your own convenience (thanks to @Mena)
                    arrayAdapterUnit2Alt.notifyDataSetChanged();

                    if (unit1!=unit2) {

                        if (unit1 != null) factor = getFactor(unit1, unit2);
                        checkDoConvert();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            lvAmt1.addTextChangedListener(new TextWatcher() {

                @Override
                public void afterTextChanged(Editable editable) {
                    String tmp = lvAmt1.getText().toString();
                    if (tmp.length() > 0) {
                        try {
                            amount1 = Double.parseDouble(tmp);
                        } catch (NumberFormatException e) {
                            // lvAmt1 afterTextChanged CATCH
                            Log.i("W_and_M_Conv 307 ", "*" + tmp);
                        }
                    }
                    if (sw == 20 || sw == 2) {
                        sw = 0;
                    } else {

                        tmp = lvAmt1.getText().toString();
                        // BEFORE lvAmt1 afterTextChanged if
                        Log.i("W_and_M_Conv 315", "*" + tmp + "* :" + tmp.length() + ":");
                        if (tmp.length() > 0) {
                            // lvAmt1 afterTextChanged if
                            Log.i("W_and_M_Conv 318 ", "*" + tmp + "* :" + tmp.length() + ":");
                            try {
                                amount1 = Double.parseDouble(tmp);
                                if (amount1 > 0) {
                                    sw = 10;
                                    checkDoConvert();
                                }
                            } catch (NumberFormatException e) {
                                // lvAmt1 afterTextChanged CATCH
                                Log.i("W_and_M_Conv 327", "*" + tmp);
                            }
                        }
                    }

                }

                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

                }

            });

            lvAmt2.addTextChangedListener(new TextWatcher() {

                @Override
                public void afterTextChanged(Editable editable) {
                    String tmp = lvAmt2.getText().toString();
                    if (tmp.length() > 0) {
                        try {
                            amount2 = Double.parseDouble(tmp);
                        } catch (NumberFormatException e) {
                            //lvAmt2 afterTextChanged CATCH
                            Log.i("W_and_M_Conv 356 ","*"+tmp);
                        }
                    }
                    if (sw==10 || sw==1){
                        sw=0;
                    }else {
                        tmp = lvAmt2.getText().toString();
                        // BEFORE            lvAmt2 afterTextChanged if
                        Log.i("W_and_M_Conv 364 ","*"+tmp+ "* :" +tmp.length() + ":");
                        if (tmp.length() > 0) {
                            //lvAmt2 afterTextChanged if
                            Log.i("W_and_M_Conv 368 ","*"+tmp+ "* :" +tmp.length() + ":");
                            try {
                                amount2 = Double.parseDouble(tmp);
                                if (amount2 > 0) {
                                    sw = 20;
                                    checkDoConvert();
                                }
                            } catch (NumberFormatException e) {
                                // lvAmt2 afterTextChanged CATCH
                                Log.i("W_and_M_Conv 376 ","*"+tmp);
                            }
                        }
                    }

                }

                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

                }

            });

            listViewUnit1Alt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    String unit2alt = listViewUnit1Alt.getItemAtPosition(i).toString();
                    Integer pos = spinnerUnitList.indexOf(unit2alt);
                    spinnerUnit2.setSelection(pos);

                }
            });

            listViewUnit2Alt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String unit1alt = listViewUnit2Alt.getItemAtPosition(i).toString();
                    Integer pos = spinnerUnitList.indexOf(unit1alt);
                    spinnerUnit1.setSelection(pos);

                }
            });
        }


//        *** Unit 1 alternate conversion units.






    }


    private void notifyUnit1Changed() {
        arrayAdapterUnit1Alt.notifyDataSetChanged();
    }


    private void notifyUnit2changed() {
        arrayAdapterUnit2Alt.notifyDataSetChanged();
    }


    private void checkDoConvert() {

        if(unit1!=null && unit2!=null && amount1+amount2!=0) {
            switch (sw){

                case 1:
                    factor = getFactor(unit1, unit2);
                    if (amount1 > 0 && factor > 0) {
                        //  To Type has chnaged
/*
                        amount2 = amount1 * factor;
                        EditText tmp = (EditText) findViewById(R.id.wandmConvertAmount2);
                        String result = String.format("%.2f", amount2);
*/
                        amount1 = amount2 / factor;
                        EditText tmp = (EditText) findViewById(R.id.wandmConvertAmount1);
                        String result = String.format(getString(R.string.twodecplace), amount1);
                        tmp.setText(result);

                    }
                    break;

                case 2:
                    factor = getFactor(unit1, unit2);
                    if (amount1 > 0 && factor > 0) {
                        //  From Type has chnaged
                        amount2 = amount1 * factor;
                        EditText tmp = (EditText) findViewById(R.id.wandmConvertAmount2);
                        String result = String.format(getString(R.string.twodecplace), amount2);
                        tmp.setText(result);

                    }
                    break;

                case 10:
                    if(amount1>0){

                        if (factor!=0) {
                            //  From amount has changed.
     //                       amount2 =  Double.valueOf(fourDForm.format(amount2));
                            amount2=amount1*factor;
                            EditText tmp = (EditText) findViewById(R.id.wandmConvertAmount2);
                            String result = String.format(getString(R.string.twodecplace), amount2);
/*                            amount1=amount2*factor;
                            EditText tmp = (EditText) findViewById(R.id.wandmConvertAmount1);
                            String result = String.format(getString(R.string.twodecplace), amount1);*/
                            tmp.setText(result);
                        } else {
                            // todo if no factor
                        }

                    }
                    break;

                case 20:
                    if(amount2>0){
                        if (factor!=0) {
                         //   To amount has changed
                            amount1=amount2/factor;
     //                       amount1 =  Double.valueOf(fourDForm.format(amount1));
                            EditText tmp = (EditText) findViewById(R.id.wandmConvertAmount1);
                            String result = String.format(getString(R.string.twodecplace), amount1);
                            tmp.setText(result);
                        } else {
                            // todo like above.
                        }

                    }
                    break;

           }
        }
        sw=0;
    }

    private ArrayList<String> fillUnitAltList(String unit) {

        SQLite_Control gfDb = new SQLite_Control(this);
     //   ArrayList<String> sAUnit1Alt  = new ArrayList<String>();
        @SuppressWarnings("unchecked")
        ArrayList<String> sAUnit1Alt = (ArrayList<String>) gfDb.getAllUnitConversions(unit);
        return sAUnit1Alt;
    }

    private double getFactor(String unit1, String unit2) {

        SQLite_Control gfDb = new SQLite_Control(this);
        HashMap<String, String> hash = new HashMap<String, String>();
        hash = gfDb.getWandMFactor(unit1, unit2);
        Double tmpp = Double.parseDouble(hash.get("factor").toString());

        return tmpp;
    }
//
//    private class StableArrayAdapter extends ArrayAdapter<String> {
//
//        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();
//
//        public StableArrayAdapter(Context context, int textViewResourceId,
//                                  List<String> objects) {
//            super(context, textViewResourceId, objects);
//            for (int i = 0; i < objects.size(); ++i) {
//                mIdMap.put(objects.get(i), i);
//            }
//        }
//
//        @Override
//        public long getItemId(int position) {
//            String item = getItem(position);
//            return mIdMap.get(item);
//        }
//
//        @Override
//        public boolean hasStableIds() {
//            return true;
//        }
//
//    }


}