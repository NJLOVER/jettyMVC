package cn.whm.dao.utility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanghm on 2014/10/9.
 */
public class Page<T> {
    //公共变量
    public static final String ASC = "asc";
    public static final String DESC = "desc";

    //分页查询参数
    protected int pageNo = 1;
    protected int pageSize = 5;
    protected String orderBy = null;

    private int startPageIndex;//显示的页码列表的开始索引
    private int endPageIndex;//显示的页码列表的结束索引
    private int pageCount;//总页数

    // -- 返回结果 --//
    protected List<T> result = new ArrayList<T>();
    protected long totalCount = -1;

    public Page<T> end(){
        //1.总页码
        pageCount = ((int)this.totalCount+pageSize-1)/pageSize;
        if (pageCount <= 10) {
            startPageIndex = 1;
            endPageIndex = pageCount;
        }
        // b, 总码大于10页
        else {
            // 在中间，显示前面4个，后面5个
            startPageIndex = pageNo - 4;
            endPageIndex = pageNo + 5;

            // 前面不足4个时，显示前10个页码
            if (startPageIndex < 1) {
                startPageIndex = 1;
                endPageIndex = 10;
            }
            // 后面不足5个时，显示后10个页码
            else if (endPageIndex > pageCount) {
                endPageIndex = pageCount;
                startPageIndex = pageCount - 10 + 1;
            }
        }
        return this;
    }

    public Page(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public int getStartPageIndex() {
        return startPageIndex;
    }

    public void setStartPageIndex(int startPageIndex) {
        this.startPageIndex = startPageIndex;
    }

    public int getEndPageIndex() {
        return endPageIndex;
    }

    public void setEndPageIndex(int endPageIndex) {
        this.endPageIndex = endPageIndex;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public int getOffset(){
        return (pageNo-1)*pageSize;
    }

    /**
     * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从1开始.
     * 用于Oracle.
     */
    public int getStartRow() {
        return getOffset() + 1;
    }

    /**
     * 根据pageNo和pageSize计算当前页最后一条记录在总结果集中的位置, 序号从1开始.
     * 用于Oracle.
     */
    public int getEndRow() {
        return pageSize * pageNo;
    }


}
