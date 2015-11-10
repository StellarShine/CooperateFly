package com.cooperate.fly.bo;

import com.cooperate.fly.web.util.EasyUITreeNode;

/**
 * Created by yj-mbp on 15/11/4.
 * 数据包数据项信息
 */
public class PackageData extends EasyUITreeNode {

    private String name;

    private int type;

    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
