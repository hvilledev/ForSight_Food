package ca.hvilledev.www.forsightfood;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_UNITS_PRIMARY_KEY;
import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_UNITS_DESCRIPTION;
import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_UNITS_SYSTEM;

public class EditUnit extends Activity{

    EditText unitUpdtDesc, unitUpdtSys;
    public static EditUnit instance;

    SQLite_Control dbTools = new SQLite_Control(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_unit);

        unitUpdtDesc = (EditText) findViewById(R.id.unitDescriptionEditText);
        unitUpdtSys = (EditText) findViewById(R.id.unitSystemEditText);

        Intent theIntent = getIntent();

        String unitId = theIntent.getStringExtra(FN_UNITS_PRIMARY_KEY);

        HashMap<String, String> unitList = dbTools.getUnitInfo(unitId);

        if(unitList.size() != 0){

            Log.i("EditUnit if :", "udesc " + FN_UNITS_DESCRIPTION + "  usys  "+ FN_UNITS_SYSTEM);

            unitUpdtDesc.setText(unitList.get(FN_UNITS_DESCRIPTION));
            unitUpdtSys.setText(unitList.get(FN_UNITS_SYSTEM));

        }

        Button editBtn = (Button) findViewById(R.id.editButton);
        Button deleteBtn = (Button) findViewById(R.id.deleteButton);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText unitUpdtDesc = (EditText) findViewById(R.id.unitDescriptionEditText);
                EditText unitUpdtSys = (EditText) findViewById(R.id.unitSystemEditText);

                Intent theIntent = getIntent();
                String unitId = theIntent.getStringExtra(FN_UNITS_PRIMARY_KEY);

                HashMap<String, String> unitList = dbTools.getUnitInfo(unitId);

//                unitUpdtDesc.setText(unitList.get(FN_UNITS_DESCRIPTION));
//                unitUpdtSys.setText(unitList.get(FN_UNITS_SYSTEM));

                HashMap<String, String> unitUpdtHashMap = new HashMap<String, String>();
                unitUpdtHashMap.put(FN_UNITS_PRIMARY_KEY, unitId);
                unitUpdtHashMap.put(FN_UNITS_DESCRIPTION, unitUpdtDesc.getText().toString());
                unitUpdtHashMap.put(FN_UNITS_SYSTEM, unitUpdtSys.getText().toString());

                Long updateResults = dbTools.updateUnit(unitUpdtHashMap);

                Log.i("editunit update", updateResults.toString());



            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i("unit delete", "***********************");

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

    public static EditUnit getConfig(){
        return instance;
    }
}