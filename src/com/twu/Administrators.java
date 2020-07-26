package com.twu;

public class Administrators {
    String name="admin"; // 管理员名称
    String codeNum="admin123"; // 管理员密码;
    public boolean testifyCodeNum(String name1,String code1) {
        if(this.name.equals(name1)&&this.codeNum.equals(code1))
            return true;
        else
            return false;
    }
}
