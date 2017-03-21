package cn.scu.ams.dao;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: jingjing
 * Date: 17-3-16
 * Time: 上午11:40
 * To change this template use File | Settings | File Templates.
 */
public interface SendMailDao {
    public int sendToServer(String order);
    public int getResult();
    public void regist() throws IOException;
    //  public void loginMail() throws IOException;
    public void setMailFrom() throws IOException;
    public void setMailTo() throws IOException;
    public void setData() throws IOException;
    public void quit() throws IOException;
    public boolean sendMail() throws IOException;
}
