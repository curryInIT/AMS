package cn.scu.ams.action;

import cn.scu.ams.dao.SendMailDao;
import cn.scu.ams.daoImp.SendMailDaoImp;
import cn.scu.ams.domain.Customer;
import cn.scu.ams.service.CustomerService;
import cn.scu.ams.utils.MailMessage;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-14
 * Time: 锟斤拷锟斤拷9:39
 * To change this template use File | Settings | File Templates.
 */
public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{
    private CustomerService customerService;
    private Customer customer = new Customer();
    private HttpServletRequest request = ServletActionContext.getRequest();
    private static String code;


    @Override
    public Customer getModel() {
        return customer;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    //普通用户登录检验
    public String login(){
        Customer existCustomer = customerService.login(customer);
        if(existCustomer == null){
            String error="账号或密码错误！";
            this.request.setAttribute("loginOrRegisterInfo",error);
            return "wj_gotologin";
        }else{
            ActionContext.getContext().getSession().put("customer", existCustomer);
            return "wj_gotoindex";
        }
    }
    //普通用户注册
    public String register(){
        String email=customer.getEmail();
        String account=customer.getAccount();
        String password=customer.getPassword();
        String password2=request.getParameter("password2");
        String emailFormat = "\\w{2,15}[@][a-z0-9]{2,}[.]\\p{Lower}{2,}";
        //w{2,15}: 2~15个[a-zA-Z_0-9]字符；w{}内容是必选的。 如：dyh@152.com是合法的。
        //[a-z0-9]{2,}：至少两个[a-z0-9]字符,[]内的是必选的；
        //[.]:'.'号时必选的； 如：dyh200896@163com是不合法的。
        //p{Lower}{2,}小写字母，两个以上。如：dyh200896@163.c是不合法的。

        if(!email.matches(emailFormat)){
            String registerInfo="邮箱格式不正确！";
            this.request.setAttribute("loginOrRegisterInfo",registerInfo);
            return "wj_gotologin";
        }else if(account.length()>6){
            String registerInfo="账号长度不能超过6位！";
            this.request.setAttribute("loginOrRegisterInfo",registerInfo);
            return "wj_gotologin";
        }else if(password.length()>12 || password.length()<6){
            String registerInfo="密码长度6-12位！";
            this.request.setAttribute("loginOrRegisterInfo",registerInfo);
            return "wj_gotologin";
        }else if(!password2.equals(password)){
            String registerInfo="确认密码不正确！";
            this.request.setAttribute("loginOrRegisterInfo",registerInfo);
            return "wj_gotologin";
        }else{
            Boolean findResult = customerService.accountIsExist(customer);
            if(findResult){
                String registerInfo="账号已存在，请重新输入！";
                this.request.setAttribute("loginOrRegisterInfo",registerInfo);
                return "wj_gotologin";
            }else{
                Boolean registerResult = customerService.register(customer);
                if(registerResult){
                    String registerInfo="注册成功！";
                    this.request.setAttribute("loginOrRegisterInfo",registerInfo);
                    return "wj_gotologin";
                }else{
                    String registerInfo="注册失败！";
                    this.request.setAttribute("loginOrRegisterInfo",registerInfo);
                    return "wj_gotologin";
                }
            }
        }
    }

    //普通用户注销登录
    public String exit(){
        ActionContext.getContext().getSession().remove("customer");
        return "wj_gotoindex";
    }
    //普通用户修改个人信息
    public String modify(){
        String email=customer.getEmail();
        String newPassword=customer.getPassword();
        String oldPassword=request.getParameter("oldPassword");
        String emailFormat = "\\w{2,15}[@][a-z0-9]{2,}[.]\\p{Lower}{2,}";
        Customer loginCustomer = (Customer)ActionContext.getContext().getSession().get("customer");

        if(!oldPassword.equals(loginCustomer.getPassword())) {
            String modifyInfo="原密码不正确，请重新输入！";
            this.request.setAttribute("modifyInfo",modifyInfo);
            return "wj_modify";
        }else if(oldPassword.equals(newPassword)){
            String modifyInfo="新密码不能与原密码相同，请重新输入！";
            this.request.setAttribute("modifyInfo",modifyInfo);
            return "wj_modify";
        }else if(newPassword.length()>12 || newPassword.length()<6){
            String modifyInfo="密码长度6-12位！";
            this.request.setAttribute("modifyInfo",modifyInfo);
            return "wj_modify";
        }else if(!email.matches(emailFormat)){
            String modifyInfo="邮箱格式不对，请重新输入！";
            this.request.setAttribute("modifyInfo",modifyInfo);
            return "wj_modify";
        }else{
            Boolean modifyResult = customerService.modifyInfo(customer);
            if(modifyResult){
                String modifyInfo="修改成功！";
                this.request.setAttribute("modifyInfo",modifyInfo);
                return "wj_modify";
            }else{
                String modifyInfo="修改失败！";
                this.request.setAttribute("modifyInfo",modifyInfo);
                return "wj_modify";
            }
        }
    }

    private static String getFixLenthString(int strLength) {

        Random rm = new Random();

        // 获得随机数
        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);

        // 将获得的获得随机数转化为字符串
        String fixLenthString = String.valueOf(pross);

        // 返回固定的长度的随机数
        return fixLenthString.substring(1, strLength + 1);
    }

    public String forgetPasswd(){
        Customer existCustomer = customerService.forgetPasswd(customer);
        if(existCustomer == null){
            String error= "用户名或邮箱不存在";
            this.request.setAttribute("accountOrEmail",error);
            return "wj_noexist";
        }else {
            MailMessage message = new MailMessage();
            message.setFrom("smailcici@163.com");
            message.setTo(customer.getEmail());
            message.setSubject("网上政务大厅密码重设");
            message.setUser("smailcici");
            code = getFixLenthString(6);
            message.setContent("你提起了找回密码的申请。\n你的验证码是" + code);
            message.setDatafrom("smailcici@163.com");
            message.setDatato(customer.getEmail());
            message.setPassword("vivian816830");

            SendMailDao send = SendMailDaoImp.getInstance(SendMailDaoImp.WANGYI163).setMessage(message);
            try {
                send.sendMail();

            }
            catch (IOException e) {
                e.printStackTrace();
            }
            existCustomer.setPasscode(code);
            customerService.update(existCustomer);
            return "wj_gotocode";
        }
    }

    public String examinecode(){
        Customer cust = customerService.testCode(customer);
        if(cust == null){
            String error= "验证码错误";
            this.request.setAttribute("codeerror",error);
            return "wj_gotocode";
        }else {
            cust.setPassword(customer.getPassword());
            customerService.update(cust);
            return "wj_gotologin";
        }
    }

}
