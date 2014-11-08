package ca.hvilledev.www.forsightfood;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import java.util.List;

import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_UNITS_DESCRIPTION;
import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_UNITS_PRIMARY_KEY;
import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_W_AND_M_PRIMARY_KEY;

/**
 * Created by miked on 06/11/14.
 */
public class W_and_M_Convert extends ListActivity {
    private Integer sw;
//    private final String  swu1="lvUnit1", swu2="lvUnit2", swa1="lvAmt1", swa2="lvAmt2";
    private Double factor,amount1=0.,amount2=0.;
    DecimalFormat fourDForm = new DecimalFormat("#.####");

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
            case R.id.wandm_Convert:
                Toast.makeText(this, "Convert selected!!!", Toast.LENGTH_SHORT)
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
    ArrayList<W_and_MViewWrapper> adapterWandM;
    EditText inputUnit;
    ArrayList<HashMap<String, String>> w_and_mArrayHashList, unitsArrayHashList;
    ArrayList<String> spinnerUnitList;
    String[] w_and_mList;
    String unit1,unit2;
    Integer spin1Pos, spin2Pos;
    TextView lineItemId;
    private Cursor cursor;
    private static Spinner lvSpin1, lvSpin2;
    private static EditText lvAmt1, lvAmt2;
    private static View row;
    private SimpleAdapter simpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapterWandM = new ArrayList<W_and_MViewWrapper>();

        setContentView(R.layout.w_and_m_convert);

//        Get data to fill spinners
        SQLite_Control w_and_mDB = new SQLite_Control(this);
        unitsArrayHashList = w_and_mDB.getAllUnits();
        w_and_mArrayHashList = w_and_mDB.getAllWandM();
        spinnerUnitList = new ArrayList<String>();
        int i;
        for (i = 0; i < unitsArrayHashList.size(); i++) {

            spinnerUnitList.add(unitsArrayHashList.get(i).get(FN_UNITS_DESCRIPTION).toString());
        }

        final Spinner spinnerUnit1 = (Spinner) findViewById(R.id.w_and_m_convert_unit_1_spinner);
        lvSpin1 = (Spinner) findViewById(R.id.w_and_m_convert_unit_1);
        final Spinner spinnerUnit2 = (Spinner) findViewById(R.id.w_and_m_convert_unit_2_spinner);
        lvSpin2 = (Spinner) findViewById(R.id.w_and_m_convert_unit_2);
        lvAmt1 = (EditText) findViewById(R.id.w_and_m_convert_amount_1);
        lvAmt2 = (EditText) findViewById(R.id.w_and_m_convert_amount_2);

//        FillSpinner(lvSpin1, unitsArrayHashList);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                spinnerUnitList);

//  todo   sort the spinners.
//  todo   use view adapter to put both Id and Desc in spinners.

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnit1.setAdapter(spinnerAdapter);
        spinnerUnit2.setAdapter(spinnerAdapter);


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
//            String amount1 = thisIntent.getStringExtra("amount1");
//            String amount2 = thisIntent.getStringExtra("amount2");


//            TextView tvAmount1 = (TextView) findViewById(R.id.w_and_m_convert_amount_1);
//            TextView tvAmount2 = (TextView) findViewById(R.id.w_and_m_convert_amount_2);

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
                    if (unit1!=unit2) {

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
                            Log.i("lvAmt1 afterTextChanged CATCH   ", "*" + tmp);
                        }
                    }
                    if (sw==20 || sw ==2){
                        sw=0;
                    }else {

                        tmp = lvAmt1.getText().toString();
                        Log.i("BEFORE            lvAmt1 afterTextChanged if   ","*"+tmp+ "* :" +tmp.length() + ":");
                        if (tmp.length() > 0) {
                            Log.i("lvAmt1 afterTextChanged if   ","*"+tmp+ "* :" +tmp.length() + ":");
                            try {
                                amount1 = Double.parseDouble(tmp);
                                if (amount1 > 0) {
                                    sw = 10;
                                    checkDoConvert();
                                }
                            } catch (NumberFormatException e) {
                                Log.i("lvAmt1 afterTextChanged CATCH   ", "*" + tmp);
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
                            Log.i("lvAmt2 afterTextChanged CATCH   ","*"+tmp);
                        }
                    }
                    if (sw==10 || sw==1){
                        sw=0;
                    }else {
                        tmp = lvAmt2.getText().toString();
                        Log.i("BEFORE            lvAmt2 afterTextChanged if   ","*"+tmp+ "* :" +tmp.length() + ":");
                        if (tmp.length() > 0) {
                            Log.i("lvAmt2 afterTextChanged if   ","*"+tmp+ "* :" +tmp.length() + ":");
                            try {
                                amount2 = Double.parseDouble(tmp);
                                if (amount2 > 0) {
                                    sw = 20;
                                    checkDoConvert();
                                }
                            } catch (NumberFormatException e) {
                                Log.i("lvAmt2 afterTextChanged CATCH   ","*"+tmp);
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
        }
    }

    private void checkDoConvert() {

        if(unit1!=null && unit2!=null && amount1+amount2!=0) {
            switch (sw){

                case 1:
                    factor = getFactor(unit1, unit2);
                    if (amount1 > 0 && factor > 0) {
                        amount2 = amount1 * factor;
                        EditText tmp = (EditText) findViewById(R.id.w_and_m_convert_amount_2);
                        tmp.setText(amount2.toString());

                    }
                    break;

                case 2:
                    factor = getFactor(unit1, unit2);
                    if (amount1 > 0 && factor > 0) {
                        amount2 = amount1 * factor;
                        EditText tmp = (EditText) findViewById(R.id.w_and_m_convert_amount_2);
                        tmp.setText(amount2.toString());

                    }
                    break;

                case 10:
                    if(amount1>0){

                        if (factor!=0) {
                            amount2=amount1*factor;
                            amount2 =  Double.valueOf(fourDForm.format(amount2));
                            EditText tmp = (EditText) findViewById(R.id.w_and_m_convert_amount_2);
                            tmp.setText(amount2.toString());
                        } else {
                            // todo if no factor
                        }

                    }
                    break;

                case 20:
                    if(amount2>0){
                        if (factor!=0) {
                            amount1=amount2/factor;
                            amount1 =  Double.valueOf(fourDForm.format(amount1));
                            EditText tmp = (EditText) findViewById(R.id.w_and_m_convert_amount_1);
                            tmp.setText(amount1.toString());
                        } else {
                            // todo like above.
                        }

                    }
                    break;

           }
        }
        sw=0;
    }

    private double getFactor(String unit1, String unit2) {

        SQLite_Control gfDb = new SQLite_Control(this);
        HashMap<String, String> hash = new HashMap<String, String>();
        hash = gfDb.getWandMFactor(unit1, unit2);
        Double tmpp = Double.parseDouble(hash.get("factor").toString());

        return tmpp;
    }
}