package ca.hvilledev.www.forsightfood;

import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_UNITS_DESCRIPTION;
import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_UNITS_PRIMARY_KEY;
import static ca.hvilledev.www.forsightfood.SQLite_Control.FN_UNITS_SYSTEM;

class UnitsViewWrapper {

    private String key;
    private String description;
    private String system;


    public UnitsViewWrapper()
    {
        this(FN_UNITS_PRIMARY_KEY,FN_UNITS_DESCRIPTION, FN_UNITS_SYSTEM);
    }

    private UnitsViewWrapper(String key, String description, String system)
    {
        super();

        this.key = key;
        this.description = description;
        this.system = system;

    }


    public String toString(){
        return this.description + " " + this.system;
    }




//    View base;
//    TextView descriptionTV = null;
//    TextView systemTV = null;
//
//    public String description;
//    public String system;
//
//    UnitsViewWrapper(View base) {
//        this.base = base;
//    }
//
//    TextView getDesc() {
//        if (descriptionTV == null) {
//            descriptionTV  = (TextView) base.findViewById(R.id.unitDescriptionEditText);
//        }
//        return (descriptionTV);
//    }
//
//    TextView getSystem() {
//        if (systemTV == null) {
//            systemTV = (TextView) base.findViewById(R.id.unitSystemEditText);
//        }
//        return (systemTV);
//    }
//
//
//    public UnitsViewWrapper()
//    {
//        this(FN_UNITS_DESCRIPTION, FN_UNITS_SYSTEM);
//    }
//
//    public UnitsViewWrapper(String description, String system)
//    {
//        this.description = description;
//        this.system = system;
//
//    }
//
//    public void setText(){
//        Log.i("settext in UVH  ", "???");
//        return;
//    }
//
////    public String toString(){
////        return this.description + " " + this.system;
////    }

}
