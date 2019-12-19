package com.template.TempEnum;

import lombok.Getter;

import java.util.Objects;

/**
 * @Author: ZhouYuJi
 * @Description:通用型枚举模板
 * @Date: Created in 22:04 2019/12/18
 * @Modified by:
 */
@Getter
public enum MsgEnum {

    instance("a", "b");

    private String code;

    private String msg;

    MsgEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * @Description:通过code获取msg
     * @Param: * @param code
     * @Date:10:11 2019/12/19
     */
    public static MsgEnum getMsgEnum(String code) {
        if (null != code) {
            for (MsgEnum msgEnum : MsgEnum.values()){
                if (Objects.equals(msgEnum.code,code)){
                    return msgEnum;
                }
            }
        }
        return null;
    }

}
