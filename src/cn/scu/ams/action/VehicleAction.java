package cn.scu.ams.action;

import cn.scu.ams.domain.Business;
import cn.scu.ams.domain.Vehicle;
import cn.scu.ams.service.BusinessService;
import cn.scu.ams.service.VehicleService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-16
 * Time: ����2:49
 * To change this template use File | Settings | File Templates.
 */
public class VehicleAction extends ActionSupport implements ModelDriven<Vehicle>{
    private VehicleService vehicleService;
    private BusinessService businessService;
    private Vehicle vehicle = new Vehicle();
    private HttpServletRequest request = ServletActionContext.getRequest();


    @Override
    public Vehicle getModel() {
        return vehicle;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public void setBusinessService(BusinessService businessService) {
        this.businessService = businessService;
    }

    //���
    //����ܷ����������ҵ��
    public String beforeApply(){
        List<Business> list =businessService.findVehicleBusinessExist();
        if(list.size()>0){
            //���ڴ�����Ļ�����ҵ�񣬲��ܽ��л���������
            String businessInfo="���ڴ�����Ļ�����ҵ�񣬲��ܽ������룡";
            this.request.setAttribute("businessInfo",businessInfo);
            return "wj_cannotapply";
        }else{
            //û�д�����Ļ�����ҵ�񣬼���Ƿ�������ҡ���ŵĻ�����
            Vehicle vehicle = vehicleService.exist();
            this.request.setAttribute("vehicle2",vehicle);
            return "wj_gotoapply";
        }
    }

    //����������
    public String apply(){
        Integer vehicleId = vehicleService.apply(vehicle);
        if(vehicleId!=null){
            Boolean insertBusinessResult = businessService.insertByVehicleId(5,vehicleId);
            if(insertBusinessResult){
                String applyInfo="�����ύ�ɹ���";
                request.setAttribute("applyVehicleInfo",applyInfo);
                return "wj_applycommit";
            }else{
                String applyInfo="�����ύʧ�ܣ�";
                request.setAttribute("applyVehicleInfo",applyInfo);
                return "wj_applycommit";
            }

        }else{
            String applyInfo="�����ύʧ�ܣ�";
            request.setAttribute("applyVehicleInfo",applyInfo);
            return "wj_applycommit";
        }
    }

    //ͨ��vehicleId�ҵ�һ��vehicle
    public String findById(){
        Vehicle vehicle1 = vehicleService.findById(vehicle.getVehicleId());
        this.request.setAttribute("vehicleDetail",vehicle1);
        return "wj_vehicle";
    }


}
