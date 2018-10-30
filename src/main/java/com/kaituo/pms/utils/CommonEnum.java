package com.kaituo.pms.utils;


/**
 * @BelongsProject: gs_server
 * @BelongsPackage: com.gs.util
 * @Author: kaituo
 * @CreateTime: 2018-06-25 10:40
 * @Description:
 */
public enum CommonEnum {

    NOLOGGING("用户未登录,或登录超时。", "-1"),
    SUCCESS("成功", "1"),
    ERROR("失败", "0"),
    PASSWORD("密码格式错误","55"),

    TRUE("true","1"),
    FALSE("false","0");



    private String name;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    private CommonEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public static String getName(String code) {
        for (CommonEnum c : CommonEnum.values()) {
            if (c.getCode() .equals(code) ) {
                return c.name;
            }
        }
        return null;
    }


}
