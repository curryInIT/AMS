package cn.scu.ams.utils;

import cn.scu.ams.dao.SendMailDao;
import cn.scu.ams.daoImp.SendMailDaoImp;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: jingjing
 * Date: 17-3-18
 * Time: 涓嬪崍7:53
 * To change this template use File | Settings | File Templates.
 */
public class MailUtil {
    public static void sendMail(String email){
        MailMessage message = new MailMessage();
        message.setFrom("18708107395@163.com");
        message.setTo(email);
        message.setSubject("机动车指标申请");
        message.setUser("18708107395");
        message.setContent("恭喜你获得机动车指标，请到当地车管局办理相关手续");
        message.setDatafrom("18708107395@163.com");
        message.setDatato(email);
        message.setPassword("wangyi123456");

        SendMailDao send = SendMailDaoImp.getInstance(SendMailDaoImp.WANGYI163).setMessage(message);
        try{
            send.sendMail();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
