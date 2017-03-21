package cn.scu.ams.utils;

import cn.scu.ams.dao.SendMailDao;
import cn.scu.ams.daoImp.SendMailDaoImp;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-18
 * Time: ����1:23
 * To change this template use File | Settings | File Templates.
 */
public class testMail {

    public static void main(String[] args) {
        MailMessage message = new MailMessage();
        message.setFrom("18708107395@163.com");
        message.setTo("1834698331@qq.com");
        message.setSubject("this is a test email");
        message.setUser("18708107395");
        message.setContent("Hello,this is a mail send test\r\n你们好吗");
        message.setDatafrom("18708107395@163.com");
        message.setDatato("1834698331@qq.com");
        message.setPassword("wangyi123456");

        SendMailDao send = SendMailDaoImp.getInstance(SendMailDaoImp.WANGYI163).setMessage(message);
        try {
            send.sendMail();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
