package ca.hvilledev.www.forsightfood;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.SoftReference;

import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_UNITS_PRIMARY_KEY;
import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_UNITS_DESCRIPTION;
import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_UNITS_SYSTEM;

/**
 * Created by miked on 31/10/14.
 */
public class UnitsViewWrapper {

    View base;
    TextView descriptionTV = null;
    TextView systemTV = null;

    public String description;
    public String system;

    UnitsViewWrapper(View base) {
        this.base = base;
    }

    TextView getDesc() {
        if (descriptionTV == null) {
            descriptionTV  = (TextView) base.findViewById(R.id.unitDescriptionEditText);
        }
        return (descriptionTV);
    }

    TextView getSystem() {
        if (systemTV == null) {
            systemTV = (TextView) base.findViewById(R.id.unitSystemEditText);
        }
        return (systemTV);
    }


    public UnitsViewWrapper()
    {
        this(FN_UNITS_DESCRIPTION, FN_UNITS_SYSTEM);
    }

    public UnitsViewWrapper(String description, String system)
    {
        this.description = description;
        this.system = system;

    }

    public void setText(){
        Log.i("settext in UVH  ", "???");
        return;
    }

//    public String toString(){
//        return this.description + " " + this.system;
//    }

}
