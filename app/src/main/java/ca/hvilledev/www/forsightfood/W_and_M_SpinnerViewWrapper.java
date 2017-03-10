package ca.hvilledev.www.forsightfood;


class W_and_M_SpinnerViewWrapper {

    private String unitId,unitDesc;


    public W_and_M_SpinnerViewWrapper()
    {
        this("unitId", "unitDesc");
    }

    private W_and_M_SpinnerViewWrapper(String unitId, String unitDesc)
    {
        super();

        this.unitId = unitId;
        this.unitDesc = unitDesc;

    }


    public String toString(){
        return this.unitDesc ;
    }




}

