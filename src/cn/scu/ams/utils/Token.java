package cn.scu.ams.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 16-7-25
 * Time: 下午4:45
 * To change this template use File | Settings | File Templates.
 */
public class Token {
    /**
     * 登录时间
     */
    private String loginTime;
    /**
     * 登录账号
     */
    private String user;
    /**
     * 用户权限
     */
    private int level;

    public Token(String user, int level){
        this.user = user;
        this.level = level;

        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        loginTime = format.format(date);
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
