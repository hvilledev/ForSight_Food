package ca.hvilledev.www.forsightfood;

public class Unit {

    String description, system;
    Integer _id;

    public Unit(){

    }

    public Unit(int id, String desc, String sys){
        _id=id;
        description = desc;
        system = sys;
    }

    public Unit(String desc, String sys){
        description = desc;
        system = sys;
    }

    public int getId(){
        return this._id;
    }

    public String getDescription(String desc){
        return this.description;
    }

    public String getSystem(String sys){
        return this.system;
    }

    public void setDescription(String desc){
        this.description=desc;
    }

    public void setSystem(String sys){
        this.system=sys;
    }


}
