package com.poss.req;

import jakarta.validation.constraints.NotBlank;

/***
 * @title MemberRegisterReq
 * @description
 * @author poss
 * @version 1.0.0
 * @create 2023/11/12 19:43
 **/
public class MemberRegisterReq {
    
    @NotBlank(message = "【手机号】不能为空")
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "MemberRegisterReq{" +
                "mobile='" + mobile + '\'' +
                '}';
    }
}
