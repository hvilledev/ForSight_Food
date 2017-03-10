package ca.hvilledev.www.forsightfood;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

//import  ca.hvilledev.www.forsightfood.Manage_Units.updateUnitRowLv;
import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_UNITS_DESCRIPTION;
import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_UNITS_PRIMARY_KEY;
import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_UNITS_SYSTEM;

public class EditUnit extends Activity{

    private EditUnit instance;

    private SQLite_Control dbTools = new SQLite_Control(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_unit);

        EditText unitUpdtId = (EditText) findViewById(R.id.unitIdEditText);
        EditText unitUpdtDesc = (EditText) findViewById(R.id.unitDescriptionEditText);
        EditText unitUpdtSys = (EditText) findViewById(R.id.unitSystemEditText);

        Intent theIntent = getIntent();

        String unitId = theIntent.getStringExtra(FN_UNITS_PRIMARY_KEY);

        HashMap<String, String> unitList = dbTools.getUnitInfo(unitId);

        if(unitList.size() != 0){

            Log.i("EditUnit 44 :","ukey "+ FN_UNITS_PRIMARY_KEY + " udesc " + FN_UNITS_DESCRIPTION + "  usys  "+ FN_UNITS_SYSTEM);
            Log.i("EditUnit 45 :","unitUpdtId "+ unitUpdtId + " unitUpdtDesc " + unitUpdtDesc + "  unitUpdtSys  "+ unitUpdtSys);

            unitUpdtId.setText(unitList.get(FN_UNITS_PRIMARY_KEY));
            unitUpdtDesc.setText(unitList.get(FN_UNITS_DESCRIPTION));
            unitUpdtSys.setText(unitList.get(FN_UNITS_SYSTEM));

        }

        Button editBtn = (Button) findViewById(R.id.editButton);
        Button deleteBtn = (Button) findViewById(R.id.deleteButton);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText unitUpdtId = (EditText) findViewById(R.id.unitIdEditText);
                EditText unitUpdtDesc = (EditText) findViewById(R.id.unitDescriptionEditText);
                EditText unitUpdtSys = (EditText) findViewById(R.id.unitSystemEditText);

                Intent theIntent = getIntent();
                String unitId = theIntent.getStringExtra(FN_UNITS_PRIMARY_KEY);

                HashMap<String, String> unitList = dbTools.getUnitInfo(unitId);


//    ************ Update DB *************
                HashMap<String, String> unitUpdtHashMap = new HashMap<String, String>();

                unitUpdtHashMap.put(FN_UNITS_PRIMARY_KEY, unitUpdtId.getText().toString());
                unitUpdtHashMap.put(FN_UNITS_DESCRIPTION, unitUpdtDesc.getText().toString());
                unitUpdtHashMap.put(FN_UNITS_SYSTEM, unitUpdtSys.getText().toString());

                Long updateResults = dbTools.updateUnit(unitUpdtHashMap);
//    ************ Update DB *************

//    ************ Update ListView *************
                ca.hvilledev.www.forsightfood.Manage_Units.updateUnitRowLv(unitUpdtHashMap);
//    ************ Update ListView *************

                Log.i("editunit update", updateResults.toString());
                Log.i("EditUnit 85 ", unitId);

                Intent returnIntent = new Intent();
                returnIntent.putExtra("key",unitId);

//                setResult(RESULT_OK);

                Log.i("EditUnit 92 ", unitId);

                finish();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i("EditUnit unit delete", "***********************");

            }
        });
    }

    public void editUnitValues(){

        EditText unitUpdtDesc = (EditText) this.findViewById(R.id.unitDescriptionEditText);
        EditText unitUpdtSys = (EditText) this.findViewById(R.id.unitSystemEditText);

        Intent theIntent = getIntent();
        String unitId = theIntent.getStringExtra(FN_UNITS_PRIMARY_KEY);

        HashMap<String, String> unitList = dbTools.getUnitInfo(unitId);

        unitUpdtDesc.setText(unitList.get(FN_UNITS_DESCRIPTION));
        unitUpdtSys.setText(unitList.get(FN_UNITS_SYSTEM));

        dbTools.updateUnit(unitList);
    }


    public  EditUnit getConfig(){
        return instance;
    }
}
