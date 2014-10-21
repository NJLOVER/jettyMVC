package cn.whm.rest.result;

/**
 * Created by wanghm on 2014/10/21.
 */
public enum ResultConst {
    SUCESS(800),
    FAIL(500),
    ERROR(400);

    private int value;
    ResultConst(int value){
        this.value=value;
    }
    public int getValue(){
        return value;
    }
}
