package cn.scu.ams.utils;

import cn.scu.ams.domain.Company;
import cn.scu.ams.domain.IdCard;
import cn.scu.ams.domain.Vehicle;
import cn.scu.ams.service.CompanyService;
import cn.scu.ams.service.EmployeeService;
import cn.scu.ams.service.IdCardService;
import cn.scu.ams.service.VehicleService;
import cn.scu.ams.serviceImp.CompanyServiceImp;
import cn.scu.ams.serviceImp.EmployeeServiceImp;
import cn.scu.ams.serviceImp.IdCardServiceImp;
import cn.scu.ams.serviceImp.VehicleServiceImp;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-16
 * Time: ����2:00
 * To change this template use File | Settings | File Templates.
 */
public class BusinessUtil {

    //����ҵ�����ͱ�ʶ���ҵ�ҵ������
    public static String findBusinessType(Integer type){
        if(type == 0){
            return "���֤����";
        }else if(type == 1){
            return "���֤��ʧ";
        }else if(type == 2){
            return "��˾ע��";
        }else if(type == 3){
            return "��˾�޸�";
        }else if(type == 4){
            return "��˾ע��";
        }else if(type == 5){
            return "������ҡ��";
        }else{
            return null;
        }
    }

    //�������״̬�������״̬
    public static String getStatus(Integer status){
        if(status == 0){
            return "�����";
        }else if(status == 1){
            return "�����";
        }else{
            return "��˽���";
        }
    }

    //����ͨ��״̬���ȡͨ��״̬
    public static String getPassStatus(Integer status){
        if(status == 0){
            return "δͨ��";
        }else{
            return "��ͨ��";
        }
    }
}