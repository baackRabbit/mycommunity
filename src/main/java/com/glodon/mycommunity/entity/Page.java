package com.glodon.mycommunity.entity;

public class Page {

    //当前页码
    private int current = 1;
    //总条目数
    private int rows;
    //每页展示内容条目上限
    private int limit = 10;
    //查询路径（复用）
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if(current >= 1){
            this.current = current;
        }

    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if(rows >= 0){
            this.rows = rows;
        }

    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if(limit >=0 && limit <= 100){
            this.limit = limit;
        }
    }

    /**
     * 计算总页码
     * @return
     */
    public int getTotal() {
        if(rows / limit == 0){
            return  rows / limit;
        }
        else{
            return rows / limit + 1;
        }
    }

    /**
     * 计算起始页
     * @return
     */
    public int getFrom(){
        int from = current - 2;
        return (from < 1)? 1:from;
    }

    /**
     * 计算终止页
     * @return
     */
    public int getTo(){
        int to = current + 2;
        return (to > getTotal())?getTotal():to;
    }

    /**
     * 计算当前页的内容起始位置
     * @return
     */
    public int getOffset() {
        return  (current-1)*limit;
    }

}
