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
 * Time: 下午2:00
 * To change this template use File | Settings | File Templates.
 */
public class BusinessUtil {

    //根据业务类型标识符找到业务类型
    public static String findBusinessType(Integer type){
        if(type == 0){
            return "身份证申请";
        }else if(type == 1){
            return "身份证挂失";
        }else if(type == 2){
            return "公司注册";
        }else if(type == 3){
            return "公司修改";
        }else if(type == 4){
            return "公司注销";
        }else if(type == 5){
            return "机动车摇号";
        }else{
            return null;
        }
    }

    //根据审核状态码获得审核状态
    public static String getStatus(Integer status){
        if(status == 0){
            return "待审核";
        }else if(status == 1){
            return "审核中";
        }else{
            return "审核结束";
        }
    }

    //根据通过状态码获取通过状态
    public static String getPassStatus(Integer status){
        if(status == 0){
            return "未通过";
        }else{
            return "已通过";
        }
    }
}