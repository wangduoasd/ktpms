package com.kaituo.pms.bean;

import java.io.Serializable;

public class ChangeIntegral implements Serializable {
    private Integer id;
    private String name;
    private Integer  integer;

    public ChangeIntegral() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }
}
