package cn.whm.rest.result;

/**
 * Created by wanghm on 2014/9/30.
 */
public abstract class AbstractRESTResult {
    private boolean handled = false;

    public boolean isHandled() {
        return handled;
    }

    public void setHandled(boolean handled) {
        this.handled = handled;
    }

    public abstract String toHttpResponse();
}
