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
 * Time: 上午11:22
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
            System.out.println("没有业务存在数库据中");
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
            String error = "不存在该员工";
            ServletActionContext.getRequest().setAttribute("error", error);
            return "th_queryFail";
        }
    }

    //摇号业务
    public String rand(){

        //获取所有机动车申请业务的bid集合
        List<Integer> idList = businessService.findVehicleIdList();
        //更新business中的employeeId
        Employee temp = (Employee)session.getAttribute("employee");
        businessService.updateBusinessEmployeeId(idList, temp.getEmployeeId());
        //获取摇号的结果--businessId集合
        List<Integer> list = GenerateRandom.generateRandom(idList);
        //获取所有的摇到号的business集合
        List<Business> allVehicleBusiness = businessService.getVehicleBusiness(idList);
        //更新所有机动车业务
        businessService.updateAllVehicleBusiness(allVehicleBusiness);
        List<Business> passVehicleBusiness = businessService.getVehicleBusiness(list);
        //更新摇到号的机动车业务
        businessService.updatePassedVehicleBusiness(passVehicleBusiness);
        //获取所有摇到号的机动车的id，即vehicleId集合
        passVehicleIdList = businessService.findAllPassedVehicleId(list);
        //获取vehicle表中所有申请摇号的记录
        List<Integer> applyVehicleIdList = vehicleService.getAllApplyVehicleId();
        //得到vehicle表中未摇到号的记录id
        List<Integer> failedVehicleIdList = VehicleUtil.getAllFailedVehicleId(applyVehicleIdList, passVehicleIdList);
        //更新vehicle表中未摇到号的记录，即existStatus = 1.
        businessService.updateVehicleExistStatus(failedVehicleIdList);

        List<Business> list2 = businessService.findChoosedVehicle();
        if(list2 == null){
            String info = "摇号成功的对象为空";
            this.request.setAttribute("choosedVehicleInfo",info);
            return "th_index";
        }else{
            ActionContext.getContext().getValueStack().set("choosedVehicleList",list2);
            return "th_success3";
        }
    }

    //列出摇号业务
    public String findVehicleBusiness(){
        List<Business> list = businessService.findVehicleBusiness();
        if(list == null){
            String info = "待处理的摇号对象为空";
            this.request.setAttribute("vehicleBusinessInfo",info);
            return "th_index";
        }else{
            ActionContext.getContext().getValueStack().set("vehicleList",list);
            return "th_success2";
        }
    }

    //给摇到号的申请者发送邮件
    public String sendMail(){
          List<Integer> idList = businessService.findVehicleIdList();
          businessService.updateBusinessVehicleExistStatus(idList);
          List<String> email_list = businessService.getEmailList(passVehicleIdList);
          for(int i = 0;i < email_list.size();i++){
              MailUtil.sendMail(email_list.get(i));
          }
          return "th_sendMailSuccess";
    }


    //列出身份证业务
    public String findPersonBusiness(){
        List<Business> list = businessService.findPersonBusiness();
        if(list == null){
            String info = "待处理的身份证业务为空";
            this.request.setAttribute("personBusinessInfo",info);
            return "ypx_index";
        }else{
            ActionContext.getContext().getValueStack().set("personList",list);
            return "ypx_success1";
        }
    }

    //身份证业务详情
    public String cardDetail(){
        Business currentCardBusiness = businessService.findById(business.getBusinessId());
        session.setAttribute("curCardBusiness",currentCardBusiness);

        IdCard currentCard = idCardService.findById(currentCardBusiness.getIdCardId());
        session.setAttribute("curCard",currentCard);

        return "ypx_cardDetail";
    }

    //身份证申请通过
    public String passCard(){
        Business passCardBusiness = businessService.findById(business.getBusinessId());
        IdCard passCard = idCardService.findById(passCardBusiness.getIdCardId());

        String time = format.format(date);
        passCardBusiness.setPassTime(time);

        passCardBusiness.setExistStatus(1);//已处理
        passCardBusiness.setPassStatus(1);//申请已通过
        passCardBusiness.setStatus(2);//状态为审核后

        businessService.changeData(passCardBusiness);

        String info = "身份证审请通过";
        this.request.getSession().setAttribute("passCardInfo",info);

        return "ypx_passCard";
    }

    //身份证申请不通过
    public String unpassCard(){
        Business unpassCardBusiness = businessService.findById(business.getBusinessId());
        IdCard unpassCard = idCardService.findById(unpassCardBusiness.getIdCardId());

        String time = format.format(date);
        unpassCardBusiness.setPassTime(time);

        unpassCardBusiness.setExistStatus(1);//已处理
        unpassCardBusiness.setPassStatus(0);//申请未通过
        unpassCardBusiness.setStatus(2);//审核后

        unpassCard.setExistStatus(1);//未通过申请 删除信息

        businessService.changeData(unpassCardBusiness);
        idCardService.changeData(unpassCard);

        String info = "身份证申请未通过";
        this.request.getSession().setAttribute("unpassCardInfo",info);

        return "ypx_unpassCard";
    }

    //身份证挂失通过
    public String passLoss(){
        Business passLossBusiness = businessService.findById(business.getBusinessId());
        IdCard passLoss = idCardService.findById(passLossBusiness.getIdCardId());

        String time = format.format(date);
        passLossBusiness.setPassTime(time);

        passLossBusiness.setExistStatus(1);//已处理
        passLossBusiness.setPassStatus(1);//挂失申请通过
        passLossBusiness.setStatus(2);//审核后

        passLoss.setExistStatus(1);//挂失通过 删除信息

        businessService.changeData(passLossBusiness);
        idCardService.changeData(passLoss);

        String info = "身份证挂失通过";
        this.request.getSession().setAttribute("passLossInfo",info);

        return "ypx_passLoss";
    }

    //身份证挂失不通过
    public String unpassLoss(){
        Business unpassLossBusiness = businessService.findById(business.getBusinessId());
        IdCard unpassLoss = idCardService.findById(unpassLossBusiness.getIdCardId());

        String time = format.format(date);
        unpassLossBusiness.setPassTime(time);

        unpassLossBusiness.setExistStatus(1);//已处理
        unpassLossBusiness.setPassStatus(0);//挂失申请未通过
        unpassLossBusiness.setStatus(2);//审核后

        businessService.changeData(unpassLossBusiness);

        String info = "身份证挂失未通过";
        this.request.getSession().setAttribute("unpassLossInfo",info);

        return "ypx_unpassLoss";
    }

    //列出公司业务
    public String findCompanyBusiness(){
        List<Business> list = businessService.findCompanyBusiness();
        if(list == null){
            String info = "待处理的公司业务为空";
            this.request.setAttribute("companyBusinessInfo",info);
            return "ypx_index";
        }else{
            ActionContext.getContext().getValueStack().set("list",list);
            return "ypx_success";
        }
    }

    //公司业务详情
    public String detail(){
        Business currentBusiness = businessService.findById(business.getBusinessId());
        session.setAttribute("curBusiness", currentBusiness);

        Company currentCompany = companyService.findById(currentBusiness.getCompanyId());
        session.setAttribute("curCompany", currentCompany);

        return "ypx_detail";
    }

    //公司申请通过
    public String passCom(){
        Business passCompanyBusiness = businessService.findById(business.getBusinessId());

        String time = format.format(date);
        passCompanyBusiness.setPassTime(time);

        passCompanyBusiness.setExistStatus(1);//0是这条业务未处理 1是已经处理
        passCompanyBusiness.setPassStatus(1); //0是未通过 1是已通过
        passCompanyBusiness.setStatus(2);//0是审核前 2是审核后
        businessService.changeData(passCompanyBusiness);

        String info = "公司申请通过";
        this.request.getSession().setAttribute("passComInfo",info);

        return "ypx_passCom";
    }

    //公司申请不通过
    public String unpassCom(){
        Business unpassCompanyBusiness = businessService.findById(business.getBusinessId());
        Company unpassCompany = companyService.findById(unpassCompanyBusiness.getCompanyId());

        String time = format.format(date);
        unpassCompanyBusiness.setPassTime(time);

        unpassCompanyBusiness.setExistStatus(1);//0是这条业务未处理 1是已经处理
        unpassCompanyBusiness.setPassStatus(0); //0是未通过 1是已通过
        unpassCompanyBusiness.setStatus(2);//0是审核前 2是审核后

        unpassCompany.setExistStatus(1);//公司信息删除

        businessService.changeData(unpassCompanyBusiness);
        companyService.changeData(unpassCompany);

        String info="公司申请未通过";
        this.request.getSession().setAttribute("unpassComInfo",info);

        return "ypx_unpassCom";
    }

    //公司注销通过
    public String passOff(){
        Business passOffCompanyBusiness = businessService.findById(business.getBusinessId());
        Company passOffCompany = companyService.findById(passOffCompanyBusiness.getCompanyId());

        String time = format.format(date);
        passOffCompanyBusiness.setPassTime(time);

        passOffCompanyBusiness.setExistStatus(1);//已处理
        passOffCompanyBusiness.setPassStatus(1);//已通过注销
        passOffCompanyBusiness.setStatus(2);//已审核

        passOffCompany.setExistStatus(1);//注销后删除公司信息

        businessService.changeData(passOffCompanyBusiness);
        companyService.changeData(passOffCompany);


        String info = "公司注销通过";
        this.request.getSession().setAttribute("passOffInfo",info);

        return "ypx_passOff";
    }

    //公司注销不通过
    public String unpassOff(){
        Business unpassOffCompanyBusiness = businessService.findById(business.getBusinessId());
        Company unpassOffCompany = companyService.findById(unpassOffCompanyBusiness.getCompanyId());

        String time = format.format(date);
        unpassOffCompanyBusiness.setPassTime(time);

        unpassOffCompanyBusiness.setExistStatus(1);//已处理
        unpassOffCompanyBusiness.setPassStatus(0);//未通过注销 公司信息仍存在
        unpassOffCompanyBusiness.setStatus(2);//已审核

        businessService.changeData(unpassOffCompanyBusiness);

        String info = "公司注销未通过";
        this.request.getSession().setAttribute("unpassOffInfo",info);

        return "ypx_unpassOff";
    }

    //根据customerId查找出用户对应的所有业务（包括已经审核完成的）
    public String allBusiness(){

        //找出用户所有的个人业务
        List<Business> personBusinessList = businessService.allPersonBusiness();

        //找出用户所有的公司业务
        List<Business> companyBusinessList = businessService.allCompanyBusiness();

        ActionContext.getContext().getValueStack().set("personBusinessList",personBusinessList);
        ActionContext.getContext().getValueStack().set("companyBusinessList",companyBusinessList);

        return "wj_allBusiness";
    }
}
