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
 * Time: ����9:39
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

    //��ͨ�û���¼����
    public String login(){
        Customer existCustomer = customerService.login(customer);
        if(existCustomer == null){
            String error="�˺Ż��������";
            this.request.setAttribute("loginOrRegisterInfo",error);
            return "wj_gotologin";
        }else{
            ActionContext.getContext().getSession().put("customer", existCustomer);
            return "wj_gotoindex";
        }
    }
    //��ͨ�û�ע��
    public String register(){
        String email=customer.getEmail();
        String account=customer.getAccount();
        String password=customer.getPassword();
        String password2=request.getParameter("password2");
        String emailFormat = "\\w{2,15}[@][a-z0-9]{2,}[.]\\p{Lower}{2,}";
        //w{2,15}: 2~15��[a-zA-Z_0-9]�ַ���w{}�����Ǳ�ѡ�ġ� �磺dyh@152.com�ǺϷ��ġ�
        //[a-z0-9]{2,}����������[a-z0-9]�ַ�,[]�ڵ��Ǳ�ѡ�ģ�
        //[.]:'.'��ʱ��ѡ�ģ� �磺dyh200896@163com�ǲ��Ϸ��ġ�
        //p{Lower}{2,}Сд��ĸ���������ϡ��磺dyh200896@163.c�ǲ��Ϸ��ġ�

        if(!email.matches(emailFormat)){
            String registerInfo="�����ʽ����ȷ��";
            this.request.setAttribute("loginOrRegisterInfo",registerInfo);
            return "wj_gotologin";
        }else if(account.length()>6){
            String registerInfo="�˺ų��Ȳ��ܳ���6λ��";
            this.request.setAttribute("loginOrRegisterInfo",registerInfo);
            return "wj_gotologin";
        }else if(password.length()>12 || password.length()<6){
            String registerInfo="���볤��6-12λ��";
            this.request.setAttribute("loginOrRegisterInfo",registerInfo);
            return "wj_gotologin";
        }else if(!password2.equals(password)){
            String registerInfo="ȷ�����벻��ȷ��";
            this.request.setAttribute("loginOrRegisterInfo",registerInfo);
            return "wj_gotologin";
        }else{
            Boolean findResult = customerService.accountIsExist(customer);
            if(findResult){
                String registerInfo="�˺��Ѵ��ڣ����������룡";
                this.request.setAttribute("loginOrRegisterInfo",registerInfo);
                return "wj_gotologin";
            }else{
                Boolean registerResult = customerService.register(customer);
                if(registerResult){
                    String registerInfo="ע��ɹ���";
                    this.request.setAttribute("loginOrRegisterInfo",registerInfo);
                    return "wj_gotologin";
                }else{
                    String registerInfo="ע��ʧ�ܣ�";
                    this.request.setAttribute("loginOrRegisterInfo",registerInfo);
                    return "wj_gotologin";
                }
            }
        }
    }

    //��ͨ�û�ע����¼
    public String exit(){
        ActionContext.getContext().getSession().remove("customer");
        return "wj_gotoindex";
    }
    //��ͨ�û��޸ĸ�����Ϣ
    public String modify(){
        String email=customer.getEmail();
        String newPassword=customer.getPassword();
        String oldPassword=request.getParameter("oldPassword");
        String emailFormat = "\\w{2,15}[@][a-z0-9]{2,}[.]\\p{Lower}{2,}";
        Customer loginCustomer = (Customer)ActionContext.getContext().getSession().get("customer");

        if(!oldPassword.equals(loginCustomer.getPassword())) {
            String modifyInfo="ԭ���벻��ȷ�����������룡";
            this.request.setAttribute("modifyInfo",modifyInfo);
            return "wj_modify";
        }else if(oldPassword.equals(newPassword)){
            String modifyInfo="�����벻����ԭ������ͬ�����������룡";
            this.request.setAttribute("modifyInfo",modifyInfo);
            return "wj_modify";
        }else if(newPassword.length()>12 || newPassword.length()<6){
            String modifyInfo="���볤��6-12λ��";
            this.request.setAttribute("modifyInfo",modifyInfo);
            return "wj_modify";
        }else if(!email.matches(emailFormat)){
            String modifyInfo="�����ʽ���ԣ����������룡";
            this.request.setAttribute("modifyInfo",modifyInfo);
            return "wj_modify";
        }else{
            Boolean modifyResult = customerService.modifyInfo(customer);
            if(modifyResult){
                String modifyInfo="�޸ĳɹ���";
                this.request.setAttribute("modifyInfo",modifyInfo);
                return "wj_modify";
            }else{
                String modifyInfo="�޸�ʧ�ܣ�";
                this.request.setAttribute("modifyInfo",modifyInfo);
                return "wj_modify";
            }
        }
    }

    private static String getFixLenthString(int strLength) {

        Random rm = new Random();

        // ��������
        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);

        // ����õĻ�������ת��Ϊ�ַ���
        String fixLenthString = String.valueOf(pross);

        // ���ع̶��ĳ��ȵ������
        return fixLenthString.substring(1, strLength + 1);
    }

    public String forgetPasswd(){
        Customer existCustomer = customerService.forgetPasswd(customer);
        if(existCustomer == null){
            String error= "�û��������䲻����";
            this.request.setAttribute("accountOrEmail",error);
            return "wj_noexist";
        }else {
            MailMessage message = new MailMessage();
            message.setFrom("smailcici@163.com");
            message.setTo(customer.getEmail());
            message.setSubject("�������������������");
            message.setUser("smailcici");
            code = getFixLenthString(6);
            message.setContent("���������һ���������롣\n�����֤����" + code);
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
            String error= "��֤�����";
            this.request.setAttribute("codeerror",error);
            return "wj_gotocode";
        }else {
            cust.setPassword(customer.getPassword());
            customerService.update(cust);
            return "wj_gotologin";
        }
    }

}
