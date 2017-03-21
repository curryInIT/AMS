package cn.scu.ams.action;

import cn.scu.ams.common.BusinessInfo;
import cn.scu.ams.domain.*;
import cn.scu.ams.service.*;
import cn.scu.ams.utils.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-16
 * Time: ����11:22
 * To change this template use File | Settings | File Templates.
 */
public class BusinessAction extends ActionSupport implements ModelDriven<Business>{
     private Integer currentPage = 1;
     private String employeeName = "";
     private Integer employeeId2;
     private BusinessService businessService;
     private VehicleService vehicleService;
     private Business business = new Business();
     private static List<Integer> passVehicleIdList;
     private HttpServletRequest request = ServletActionContext.getRequest();
     private HttpSession session = request.getSession();

    private CompanyService companyService;
    private IdCardService idCardService;

    Date date = new Date();
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Business getModel() {
        return business;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    public void setIdCardService(IdCardService idCardService) {
        this.idCardService = idCardService;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setBusinessService(BusinessService businessService) {
        this.businessService = businessService;
    }

    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }


    public Integer getEmployeeId2() {
        return employeeId2;
    }

    public void setEmployeeId2(Integer employeeId2) {
        this.employeeId2 = employeeId2;
    }

    public String findAll(){
        PageBean<BusinessInfo> businessPageBean = businessService.findByPage(currentPage);
        if(businessPageBean == null){
            System.out.println("û��ҵ������������");
        }else{
            ActionContext.getContext().getValueStack().push(businessPageBean);
            return "th_findAll";
        }
        return null;
    }

    public String query(){
        List<BusinessInfo> businessInfos = businessService.findByName(employeeName);
        if(businessInfos != null){
            ActionContext.getContext().getValueStack().set("businessInfos", businessInfos);
            return "th_querySuccess";
        }else{
            String error = "�����ڸ�Ա��";
            ServletActionContext.getRequest().setAttribute("error", error);
            return "th_queryFail";
        }
    }

    //ҡ��ҵ��
    public String rand(){

        //��ȡ���л���������ҵ���bid����
        List<Integer> idList = businessService.findVehicleIdList();
        //����business�е�employeeId
        Employee temp = (Employee)session.getAttribute("employee");
        businessService.updateBusinessEmployeeId(idList, temp.getEmployeeId());
        //��ȡҡ�ŵĽ��--businessId����
        List<Integer> list = GenerateRandom.generateRandom(idList);
        //��ȡ���е�ҡ���ŵ�business����
        List<Business> allVehicleBusiness = businessService.getVehicleBusiness(idList);
        //�������л�����ҵ��
        businessService.updateAllVehicleBusiness(allVehicleBusiness);
        List<Business> passVehicleBusiness = businessService.getVehicleBusiness(list);
        //����ҡ���ŵĻ�����ҵ��
        businessService.updatePassedVehicleBusiness(passVehicleBusiness);
        //��ȡ����ҡ���ŵĻ�������id����vehicleId����
        passVehicleIdList = businessService.findAllPassedVehicleId(list);
        //��ȡvehicle������������ҡ�ŵļ�¼
        List<Integer> applyVehicleIdList = vehicleService.getAllApplyVehicleId();
        //�õ�vehicle����δҡ���ŵļ�¼id
        List<Integer> failedVehicleIdList = VehicleUtil.getAllFailedVehicleId(applyVehicleIdList, passVehicleIdList);
        //����vehicle����δҡ���ŵļ�¼����existStatus = 1.
        businessService.updateVehicleExistStatus(failedVehicleIdList);

        List<Business> list2 = businessService.findChoosedVehicle();
        if(list2 == null){
            String info = "ҡ�ųɹ��Ķ���Ϊ��";
            this.request.setAttribute("choosedVehicleInfo",info);
            return "th_index";
        }else{
            ActionContext.getContext().getValueStack().set("choosedVehicleList",list2);
            return "th_success3";
        }
    }

    //�г�ҡ��ҵ��
    public String findVehicleBusiness(){
        List<Business> list = businessService.findVehicleBusiness();
        if(list == null){
            String info = "�������ҡ�Ŷ���Ϊ��";
            this.request.setAttribute("vehicleBusinessInfo",info);
            return "th_index";
        }else{
            ActionContext.getContext().getValueStack().set("vehicleList",list);
            return "th_success2";
        }
    }

    //��ҡ���ŵ������߷����ʼ�
    public String sendMail(){
          List<Integer> idList = businessService.findVehicleIdList();
          businessService.updateBusinessVehicleExistStatus(idList);
          List<String> email_list = businessService.getEmailList(passVehicleIdList);
          for(int i = 0;i < email_list.size();i++){
              MailUtil.sendMail(email_list.get(i));
          }
          return "th_sendMailSuccess";
    }


    //�г����֤ҵ��
    public String findPersonBusiness(){
        List<Business> list = businessService.findPersonBusiness();
        if(list == null){
            String info = "����������֤ҵ��Ϊ��";
            this.request.setAttribute("personBusinessInfo",info);
            return "ypx_index";
        }else{
            ActionContext.getContext().getValueStack().set("personList",list);
            return "ypx_success1";
        }
    }

    //���֤ҵ������
    public String cardDetail(){
        Business currentCardBusiness = businessService.findById(business.getBusinessId());
        session.setAttribute("curCardBusiness",currentCardBusiness);

        IdCard currentCard = idCardService.findById(currentCardBusiness.getIdCardId());
        session.setAttribute("curCard",currentCard);

        return "ypx_cardDetail";
    }

    //���֤����ͨ��
    public String passCard(){
        Business passCardBusiness = businessService.findById(business.getBusinessId());
        IdCard passCard = idCardService.findById(passCardBusiness.getIdCardId());

        String time = format.format(date);
        passCardBusiness.setPassTime(time);

        passCardBusiness.setExistStatus(1);//�Ѵ���
        passCardBusiness.setPassStatus(1);//������ͨ��
        passCardBusiness.setStatus(2);//״̬Ϊ��˺�

        businessService.changeData(passCardBusiness);

        String info = "���֤����ͨ��";
        this.request.getSession().setAttribute("passCardInfo",info);

        return "ypx_passCard";
    }

    //���֤���벻ͨ��
    public String unpassCard(){
        Business unpassCardBusiness = businessService.findById(business.getBusinessId());
        IdCard unpassCard = idCardService.findById(unpassCardBusiness.getIdCardId());

        String time = format.format(date);
        unpassCardBusiness.setPassTime(time);

        unpassCardBusiness.setExistStatus(1);//�Ѵ���
        unpassCardBusiness.setPassStatus(0);//����δͨ��
        unpassCardBusiness.setStatus(2);//��˺�

        unpassCard.setExistStatus(1);//δͨ������ ɾ����Ϣ

        businessService.changeData(unpassCardBusiness);
        idCardService.changeData(unpassCard);

        String info = "���֤����δͨ��";
        this.request.getSession().setAttribute("unpassCardInfo",info);

        return "ypx_unpassCard";
    }

    //���֤��ʧͨ��
    public String passLoss(){
        Business passLossBusiness = businessService.findById(business.getBusinessId());
        IdCard passLoss = idCardService.findById(passLossBusiness.getIdCardId());

        String time = format.format(date);
        passLossBusiness.setPassTime(time);

        passLossBusiness.setExistStatus(1);//�Ѵ���
        passLossBusiness.setPassStatus(1);//��ʧ����ͨ��
        passLossBusiness.setStatus(2);//��˺�

        passLoss.setExistStatus(1);//��ʧͨ�� ɾ����Ϣ

        businessService.changeData(passLossBusiness);
        idCardService.changeData(passLoss);

        String info = "���֤��ʧͨ��";
        this.request.getSession().setAttribute("passLossInfo",info);

        return "ypx_passLoss";
    }

    //���֤��ʧ��ͨ��
    public String unpassLoss(){
        Business unpassLossBusiness = businessService.findById(business.getBusinessId());
        IdCard unpassLoss = idCardService.findById(unpassLossBusiness.getIdCardId());

        String time = format.format(date);
        unpassLossBusiness.setPassTime(time);

        unpassLossBusiness.setExistStatus(1);//�Ѵ���
        unpassLossBusiness.setPassStatus(0);//��ʧ����δͨ��
        unpassLossBusiness.setStatus(2);//��˺�

        businessService.changeData(unpassLossBusiness);

        String info = "���֤��ʧδͨ��";
        this.request.getSession().setAttribute("unpassLossInfo",info);

        return "ypx_unpassLoss";
    }

    //�г���˾ҵ��
    public String findCompanyBusiness(){
        List<Business> list = businessService.findCompanyBusiness();
        if(list == null){
            String info = "������Ĺ�˾ҵ��Ϊ��";
            this.request.setAttribute("companyBusinessInfo",info);
            return "ypx_index";
        }else{
            ActionContext.getContext().getValueStack().set("list",list);
            return "ypx_success";
        }
    }

    //��˾ҵ������
    public String detail(){
        Business currentBusiness = businessService.findById(business.getBusinessId());
        session.setAttribute("curBusiness", currentBusiness);

        Company currentCompany = companyService.findById(currentBusiness.getCompanyId());
        session.setAttribute("curCompany", currentCompany);

        return "ypx_detail";
    }

    //��˾����ͨ��
    public String passCom(){
        Business passCompanyBusiness = businessService.findById(business.getBusinessId());

        String time = format.format(date);
        passCompanyBusiness.setPassTime(time);

        passCompanyBusiness.setExistStatus(1);//0������ҵ��δ���� 1���Ѿ�����
        passCompanyBusiness.setPassStatus(1); //0��δͨ�� 1����ͨ��
        passCompanyBusiness.setStatus(2);//0�����ǰ 2����˺�
        businessService.changeData(passCompanyBusiness);

        String info = "��˾����ͨ��";
        this.request.getSession().setAttribute("passComInfo",info);

        return "ypx_passCom";
    }

    //��˾���벻ͨ��
    public String unpassCom(){
        Business unpassCompanyBusiness = businessService.findById(business.getBusinessId());
        Company unpassCompany = companyService.findById(unpassCompanyBusiness.getCompanyId());

        String time = format.format(date);
        unpassCompanyBusiness.setPassTime(time);

        unpassCompanyBusiness.setExistStatus(1);//0������ҵ��δ���� 1���Ѿ�����
        unpassCompanyBusiness.setPassStatus(0); //0��δͨ�� 1����ͨ��
        unpassCompanyBusiness.setStatus(2);//0�����ǰ 2����˺�

        unpassCompany.setExistStatus(1);//��˾��Ϣɾ��

        businessService.changeData(unpassCompanyBusiness);
        companyService.changeData(unpassCompany);

        String info="��˾����δͨ��";
        this.request.getSession().setAttribute("unpassComInfo",info);

        return "ypx_unpassCom";
    }

    //��˾ע��ͨ��
    public String passOff(){
        Business passOffCompanyBusiness = businessService.findById(business.getBusinessId());
        Company passOffCompany = companyService.findById(passOffCompanyBusiness.getCompanyId());

        String time = format.format(date);
        passOffCompanyBusiness.setPassTime(time);

        passOffCompanyBusiness.setExistStatus(1);//�Ѵ���
        passOffCompanyBusiness.setPassStatus(1);//��ͨ��ע��
        passOffCompanyBusiness.setStatus(2);//�����

        passOffCompany.setExistStatus(1);//ע����ɾ����˾��Ϣ

        businessService.changeData(passOffCompanyBusiness);
        companyService.changeData(passOffCompany);


        String info = "��˾ע��ͨ��";
        this.request.getSession().setAttribute("passOffInfo",info);

        return "ypx_passOff";
    }

    //��˾ע����ͨ��
    public String unpassOff(){
        Business unpassOffCompanyBusiness = businessService.findById(business.getBusinessId());
        Company unpassOffCompany = companyService.findById(unpassOffCompanyBusiness.getCompanyId());

        String time = format.format(date);
        unpassOffCompanyBusiness.setPassTime(time);

        unpassOffCompanyBusiness.setExistStatus(1);//�Ѵ���
        unpassOffCompanyBusiness.setPassStatus(0);//δͨ��ע�� ��˾��Ϣ�Դ���
        unpassOffCompanyBusiness.setStatus(2);//�����

        businessService.changeData(unpassOffCompanyBusiness);

        String info = "��˾ע��δͨ��";
        this.request.getSession().setAttribute("unpassOffInfo",info);

        return "ypx_unpassOff";
    }

    //����customerId���ҳ��û���Ӧ������ҵ�񣨰����Ѿ������ɵģ�
    public String allBusiness(){

        //�ҳ��û����еĸ���ҵ��
        List<Business> personBusinessList = businessService.allPersonBusiness();

        //�ҳ��û����еĹ�˾ҵ��
        List<Business> companyBusinessList = businessService.allCompanyBusiness();

        ActionContext.getContext().getValueStack().set("personBusinessList",personBusinessList);
        ActionContext.getContext().getValueStack().set("companyBusinessList",companyBusinessList);

        return "wj_allBusiness";
    }
}
