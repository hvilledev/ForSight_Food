package ca.hvilledev.www.forsightfood;

import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_W_AND_M_UNIT_A_XREF;
import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_W_AND_M_UNIT_B_XREF;
import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_W_AND_M_PRIMARY_KEY;
import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_W_AND_M_FACTOR;

/**
 * Created by miked on 05/11/14.
 */
class W_and_MViewWrapper {
    private String key;
    private String description;
    private String system;


    public W_and_MViewWrapper()
    {
        this(FN_W_AND_M_PRIMARY_KEY,
                FN_W_AND_M_UNIT_A_XREF,
                FN_W_AND_M_UNIT_B_XREF,
                FN_W_AND_M_FACTOR);
    }

    private W_and_MViewWrapper(String key, String unitAXref, String unitBXref, String factor)
    {
        super();

        this.key = key;
        this.description = description;
        this.system = system;

    }


    public String toString(){
        return this.description + " " + this.system;
    }




}
