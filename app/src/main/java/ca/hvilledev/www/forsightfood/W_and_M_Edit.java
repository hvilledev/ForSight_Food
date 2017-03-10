package ca.hvilledev.www.forsightfood;

/**
 * Created by miked on 05/11/14.
 */


        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;

        import java.util.HashMap;

      //  import static ca.hvilledev.www.forsightfood.W_and_M_Manage.updateUnitRowLv;
        import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_W_AND_M_PRIMARY_KEY;
        import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_W_AND_M_UNIT_A_XREF;
        import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_W_AND_M_UNIT_B_XREF;
        import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_W_AND_M_FACTOR;

public class W_and_M_Edit extends Activity{

    private EditText w_and_mUpdtId,w_and_mUpdtUAX, w_and_mUpdtUBX,w_and_mUpdtFactor;
    private EditUnit instance;

    private SQLite_Control dbTools = new SQLite_Control(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.w_and_m_edit);

        w_and_mUpdtId = (EditText) findViewById(R.id.w_and_mIdEditText);
        w_and_mUpdtUAX = (EditText) findViewById(R.id.w_and_mUnit_A_EditText);
        w_and_mUpdtUBX = (EditText) findViewById(R.id.w_and_mUnit_B_EditText);
        w_and_mUpdtFactor = (EditText) findViewById(R.id.w_and_mFactorEditText);

        Intent theIntent = getIntent();

        String w_and_mId = theIntent.getStringExtra(FN_W_AND_M_PRIMARY_KEY);

        HashMap<String, String> w_and_mList = dbTools.getWandMInfo(w_and_mId);

        if(w_and_mList.size() != 0){

            Log.i("W_and_M_Edit  49 ","wmkey "+ FN_W_AND_M_PRIMARY_KEY + " wmAx " + FN_W_AND_M_UNIT_A_XREF + "  uBx  " + FN_W_AND_M_UNIT_B_XREF+ "  uBx  " +  FN_W_AND_M_FACTOR);

            w_and_mUpdtId.setText(w_and_mList.get(FN_W_AND_M_PRIMARY_KEY));
            w_and_mUpdtUAX.setText(w_and_mList.get(FN_W_AND_M_UNIT_A_XREF));
            w_and_mUpdtUBX.setText(w_and_mList.get(FN_W_AND_M_UNIT_B_XREF));
            w_and_mUpdtFactor.setText(w_and_mList.get(FN_W_AND_M_FACTOR));

        }

        Button editBtn = (Button) findViewById(R.id.editButton);
        Button deleteBtn = (Button) findViewById(R.id.deleteButton);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText w_and_mUpdtId = (EditText) findViewById(R.id.w_and_mIdEditText);
                EditText w_and_mUpdtUAX = (EditText) findViewById(R.id.w_and_mUnit_A_EditText);
                EditText w_and_mUpdtUBX = (EditText) findViewById(R.id.w_and_mUnit_B_EditText);
                EditText w_and_mUpdtSys = (EditText) findViewById(R.id.w_and_mFactorEditText);

                Intent theIntent = getIntent();
                String w_and_mId = theIntent.getStringExtra(FN_W_AND_M_PRIMARY_KEY);

                HashMap<String, String> w_and_mList = dbTools.getWandMInfo(w_and_mId);


//    ************ Update DB *************
                HashMap<String, String> w_and_mUpdtHashMap = new HashMap<String, String>();

                w_and_mUpdtHashMap.put(FN_W_AND_M_PRIMARY_KEY, w_and_mUpdtId.getText().toString());
                w_and_mUpdtHashMap.put(FN_W_AND_M_UNIT_A_XREF, w_and_mUpdtUAX.getText().toString());
                w_and_mUpdtHashMap.put(FN_W_AND_M_UNIT_B_XREF, w_and_mUpdtUBX.getText().toString());
                w_and_mUpdtHashMap.put(FN_W_AND_M_FACTOR, w_and_mUpdtFactor.getText().toString());

                Long updateResults = dbTools.updateWandM(w_and_mUpdtHashMap);
//    ************ Update DB *************

//    ************ Update ListView *************
                ca.hvilledev.www.forsightfood.W_and_M_Manage.updateUnitRowLv(w_and_mUpdtHashMap);
//    ************ Update ListView *************

                Log.i("W_and_M_Edit 91", updateResults.toString());
                Log.i("W_and_M_Edit  92 ", w_and_mId);

                Intent returnIntent = new Intent();
                returnIntent.putExtra("key",w_and_mId);

//                setResult(RESULT_OK);

                Log.i("W_and_M_Edit  99 ", w_and_mId);

                finish();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i("W_and_M_Edit  109", "***********************");

            }
        });
    }

    public void editUnitValues(){

        EditText w_and_mUpdtUAX = (EditText) this.findViewById(R.id.w_and_mUnit_A_EditText);
        EditText w_and_mUpdtUBX = (EditText) this.findViewById(R.id.w_and_mUnit_B_EditText);
        EditText w_and_mUpdtFactor = (EditText) this.findViewById(R.id.w_and_mFactorEditText);

        Intent theIntent = getIntent();
        String w_and_mId = theIntent.getStringExtra(FN_W_AND_M_PRIMARY_KEY);

        HashMap<String, String> w_and_mList = dbTools.getUnitInfo(w_and_mId);

        w_and_mUpdtUAX.setText(w_and_mList.get(FN_W_AND_M_UNIT_A_XREF));
        w_and_mUpdtUBX.setText(w_and_mList.get(FN_W_AND_M_UNIT_B_XREF));
        w_and_mUpdtFactor.setText(w_and_mList.get(FN_W_AND_M_FACTOR));

        dbTools.updateUnit(w_and_mList);
    }


    public  EditUnit getConfig(){
        return instance;
    }
}
