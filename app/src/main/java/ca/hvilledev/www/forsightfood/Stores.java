package ca.hvilledev.www.forsightfood;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by miked on 25/10/14.
 */
public class Stores extends ListActivity {

//    Fill in some dummy data.
/*    String[] storesList = {"one", "two", "three",
        "one", "two", "three",
        "one", "two", "three",
        "one", "two", "three",
        "one", "two", "three","one", "two", "three",
        "one", "two", "three",
        "one", "two", "three",
        "one", "two", "three",
        "four"};
*/
    String[] storesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stores);

//        Connect to the ListView to see clicks in the list.
        ListView storesListView;
        storesListView = getListView();

//        Set to only select one item at a time use 1. 2 is for multiple
        storesListView.setChoiceMode(2);

//        Enable the ability to type first letter(s) to narrow list.
//        storesListView.setTextFilterEnabled(true);


        ArrayAdapter storesAA;
//        For none or single selection
//        storesAA =  new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, storesList);

//        Getting array data from strings.xml
        storesList = getResources().getStringArray(R.array.dummy_stores_list);

//        For multiple selection.  (It also allow single pic with check box.
        storesAA =  new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, storesList);

        setListAdapter(storesAA);

    }

    public void onListItemClick (ListView l_view, View view, int position, long id){

//        Tis puts a checkbox on the right of each item to allow for multiple select
        l_view.setItemChecked(position, l_view.isItemChecked(position));

        Toast.makeText(getBaseContext(),"You clicked on " + storesList[position],Toast.LENGTH_LONG).show();

    }
}
