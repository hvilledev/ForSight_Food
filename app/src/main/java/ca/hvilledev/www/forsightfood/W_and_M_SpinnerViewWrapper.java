package ca.hvilledev.www.forsightfood;


public class W_and_M_SpinnerViewWrapper {

    public String unitId,unitDesc;


    public W_and_M_SpinnerViewWrapper()
    {
        this("unitId", "unitDesc");
    }

    public W_and_M_SpinnerViewWrapper(String unitId, String unitDesc)
    {
        super();

        this.unitId = unitId;
        this.unitDesc = unitDesc;

    }


    public String toString(){
        return this.unitDesc ;
    }




}

