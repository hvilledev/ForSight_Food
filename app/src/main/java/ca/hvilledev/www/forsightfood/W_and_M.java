package ca.hvilledev.www.forsightfood;

/**
 * Created by miked on 05/11/14.
 */
class W_and_M {
    private String description, system;
    private Integer _id;

    public W_and_M(){

    }

    public W_and_M(int id, String desc, String sys){
        _id=id;
        description = desc;
        system = sys;
    }

    public W_and_M(String desc, String sys){
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