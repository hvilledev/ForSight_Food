package ca.hvilledev.www.forsightfood;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MyActivity extends Activity implements View.OnClickListener{

    private static final String DATABASE_NAME = "forsight.db";



    SQLite_Control sqldb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        sqldb = new SQLite_Control(getApplicationContext());

        Button storesButton;
        storesButton = (Button) findViewById(R.id.whats_in_stores_button);
        storesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent storesIntent = new Intent(getApplicationContext(), Stores.class);
                startActivity(storesIntent);

            }
        });
        Button unitsButton;
        unitsButton = (Button) findViewById(R.id.manage_units_button);
        unitsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent unitsIntent = new Intent(getApplicationContext(), Manage_Units.class);
                startActivity(unitsIntent);

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.whats_in_stores_button:
                Intent storesIntent = new Intent(getApplicationContext(), Stores.class);
                startActivity(storesIntent);

                break;

            case R.id.manage_stores_button:
                Intent manageStoresIntent = new Intent(getApplicationContext(), Manage_Items.class);
                startActivity(manageStoresIntent);

                break;

            case R.id.weighs_and_measures_button:
                Intent w_and_m_Intent = new Intent(getApplicationContext(), Weights_and_Measures.class);
                startActivity(w_and_m_Intent);

                break;
            case R.id.manage_units_button:
                Intent manageUnits_Intent = new Intent(getApplicationContext(), Manage_Units.class);
                startActivity(manageUnits_Intent);

        }

    }
}
