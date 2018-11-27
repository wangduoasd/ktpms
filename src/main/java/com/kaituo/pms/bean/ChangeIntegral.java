package com.kaituo.pms.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChangeIntegral implements Serializable {
    private Integer id;
    private String username;
    private Integer  integer;
    private Integer beforeChange;
    private Integer changeInt;
    private Integer afterChange;
    private String changeStr;
}
