package cn.scu.ams.serviceImp;

import cn.scu.ams.common.BusinessInfo;
import cn.scu.ams.dao.*;
import cn.scu.ams.domain.*;
import cn.scu.ams.service.*;
import cn.scu.ams.utils.BusinessUtil;
import cn.scu.ams.utils.PageBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-16
 * Time: 上午11:24
 * To change this template use File | Settings | File Templates.
 */
public class BusinessServiceImp implements BusinessService{
    private BusinessDao businessDao;
    private EmployeeDao employeeDao;
    public VehicleDao vehicleDao;
    public CompanyDao companyDao;
    public IdCardDao idCardDao;

    public void setBusinessDao(BusinessDao businessDao) {
        this.businessDao = businessDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void setVehicleDao(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    public void setCompanyDao(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    public void setIdCardDao(IdCardDao idCardDao) {
        this.idCardDao = idCardDao;
    }

    @Override
    public List<Business> findAll() {
        return businessDao.findAll();
    }

    @Override
    public PageBean<BusinessInfo> findByPage(Integer currentPage) {
        PageBean<BusinessInfo> pageBean = new PageBean<BusinessInfo>();
        //记录总数
        Integer totalCount = businessDao.findCount();
        double tc = totalCount;
        //页大小
        Integer pageSize = 2;
        //总页数
        Double totalPage = Math.ceil(tc / pageSize);
        //偏移量
        Integer begin = (currentPage - 1) * pageSize;

        List<Business> list = businessDao.findByPage(begin, pageSize);
        List<BusinessInfo> info_list  = new ArrayList<BusinessInfo>();
        String customerName;
        String employName = null;
        String businessType;
        String status;
        String passStatus;
        Integer vehicleId;
        Integer companyId;
        Integer idCardId;

        if(list != null){
            for(int i = 0;i < list.size();i++){
                BusinessInfo businessInfo = new BusinessInfo();
                //判断并设置对应业务id
                if((list.get(i).getBusinessType() == 0) || (list.get(i).getBusinessType() == 1)){
                    idCardId = list.get(i).getIdCardId();
                    businessInfo.setIdCardId(idCardId);
                    customerName = idCardDao.findById(idCardId).getApplyName();
                    businessInfo.setCustomerName(customerName);
                }
                if((list.get(i).getBusinessType() == 2) || (list.get(i).getBusinessType() == 4)){
                    companyId = list.get(i).getCompanyId();
                    businessInfo.setCompanyId(companyId);
                    customerName = companyDao.findById(companyId).getApplyCustomer();
                    businessInfo.setCustomerName(customerName);
                }
                if(list.get(i).getBusinessType() == 5){
                    vehicleId = list.get(i).getVehicleId();
                    businessInfo.setVehicleId(vehicleId);
                    customerName = vehicleDao.findById(vehicleId).getCustomerName();
                    businessInfo.setCustomerName(customerName);
                }
                //获取业务类型
                businessType = BusinessUtil.findBusinessType(list.get(i).getBusinessType());
                Integer employeeId = list.get(i).getEmployeeId();
                if((employeeId != null) && (employeeId != 0)){
                       employName =  employeeDao.findById(employeeId).getName();
                }
                businessInfo.setEmployeeName(employName);
                status = BusinessUtil.getStatus(list.get(i).getStatus());
                passStatus = BusinessUtil.getPassStatus(list.get(i).getPassStatus());
                businessInfo.setBusinessId(list.get(i).getBusinessId());
                businessInfo.setExistStatus(list.get(i).getExistStatus());
                businessInfo.setApplyTime(list.get(i).getApplyTime());
                businessInfo.setExistStatus(list.get(i).getExistStatus());
                businessInfo.setPassTime(list.get(i).getPassTime());
                businessInfo.setPassStatus(passStatus);
                businessInfo.setStatus(status);
                businessInfo.setBusinessType(businessType);

                info_list.add(businessInfo);
            }
        }
        pageBean.setTotalPage(totalPage.intValue());
        pageBean.setTotalCount(totalCount);
        pageBean.setPageSize(pageSize);
        pageBean.setCurrPage(currentPage);
        pageBean.setList(info_list);

        return pageBean;
    }

    //根据办公人员的名字查询相关业务
    @Override
    public List<BusinessInfo> findByName(String name) {
        List<Employee> employeeList = employeeDao.findByName(name);
        if(employeeList.size() == 0){
            return null;
        }
        Integer employeeId = 0;
        if(employeeList.size() > 0){
            employeeId = employeeList.get(0).getEmployeeId();
        }
        List<Business> list = businessDao.findByEmployeeId(employeeId);
        List<BusinessInfo> info_list  = new ArrayList<BusinessInfo>();
        String employeeName;
        String businessType;
        String status;
        String passStatus;
        Integer vehicleId;
        Integer companyId;
        Integer idCardId;

        if(list != null){
            for(int i = 0;i < list.size();i++){
                BusinessInfo businessInfo = new BusinessInfo();
                String customerName;
                if((list.get(i).getBusinessType() == 0) || (list.get(i).getBusinessType() == 1)){
                    idCardId = list.get(i).getIdCardId();
                    businessInfo.setIdCardId(idCardId);
                    customerName = idCardDao.findById(idCardId).getApplyName();
                    businessInfo.setCustomerName(customerName);
                }
                if((list.get(i).getBusinessType() == 2) || (list.get(i).getBusinessType() == 4)){
                    companyId = list.get(i).getCompanyId();
                    businessInfo.setCompanyId(companyId);
                    customerName = companyDao.findById(companyId).getApplyCustomer();
                    businessInfo.setCustomerName(customerName);
                }
                if(list.get(i).getBusinessType() == 5){
                    vehicleId = list.get(i).getVehicleId();
                    businessInfo.setVehicleId(vehicleId);
                    customerName = vehicleDao.findById(vehicleId).getCustomerName();
                    businessInfo.setCustomerName(customerName);
                }

                employeeName = employeeDao.findById(list.get(i).getEmployeeId()).getName();
                businessType = BusinessUtil.findBusinessType(list.get(i).getBusinessType());
                status = BusinessUtil.getStatus(list.get(i).getStatus());
                passStatus = BusinessUtil.getPassStatus(list.get(i).getPassStatus());
                businessInfo.setBusinessId(list.get(i).getBusinessId());
                businessInfo.setExistStatus(list.get(i).getExistStatus());
                businessInfo.setApplyTime(list.get(i).getApplyTime());
                businessInfo.setExistStatus(list.get(i).getExistStatus());
                businessInfo.setPassTime(list.get(i).getPassTime());
                businessInfo.setPassStatus(passStatus);
                businessInfo.setStatus(status);
                businessInfo.setBusinessType(businessType);
                businessInfo.setEmployeeName(employeeName);

                info_list.add(businessInfo);
            }
            return info_list;
        }
        return null;
    }

    //获取申请机动车摇号的记录的id即bid集合
   public  List<Integer> findVehicleIdList(){
        return businessDao.findVehicleIdList();
    }

    //返回集合中的bid所对应的business
   public  List<Business> getVehicleBusiness(List<Integer> list){
        List<Business> businessList = new ArrayList<Business>();
        for(int i = 0;i < list.size();i++){
            businessList.add(businessDao.findById(list.get(i)));
        }
        if(businessList != null){
            return businessList;
        }
        return null;
    }

    //对集合中的business进行机动车申请通过的更新
    public void updatePassedVehicleBusiness(List<Business> list){
        for(int i = 0;i < list.size();i++){
            businessDao.modifyPassedVehicleBusiness(list.get(i));
        }
    }

    //对集合中的business进行所有处理过的机动车申请的更新
    public void updateAllVehicleBusiness(List<Business> list){
        for (int i = 0;i < list.size();i++){
            businessDao.modifyAllVehicleBusiness(list.get(i));
        }
    }

    //根据摇到号的bid得到相应的vehicleId集合
    @Override
    public List<Integer> findAllPassedVehicleId(List<Integer> list) {
        List<Integer> idList = new ArrayList<Integer>();
        for(int i = 0;i < list.size();i++){
            idList.add(businessDao.getVehicleIdByBusinessId(list.get(i)));
        }
        if(idList != null){
            return idList;
        }
        return null;
    }

    @Override
    public void updateVehicleExistStatus(List<Integer> list) {
          for(int i = 0;i < list.size();i++){
                Vehicle vehicle = vehicleDao.findById(list.get(i));
                vehicle.setExistStatus(1);
                vehicleDao.updateVehicle(vehicle);
          }

    }

    public List<Business> findVehicleBusiness(){
        return businessDao.findAllVehicleBusiness();
    }

    public List<Business> findChoosedVehicle(){
        return businessDao.findChoosedVehicle();
    }

    @Override
    public List<String> getEmailList(List<Integer> passVehicleIdList) {
        List<String> result = new ArrayList<String>();
        for(int i = 0;i < passVehicleIdList.size();i++){
            result.add(vehicleDao.findById(passVehicleIdList.get(i)).getEmail());
        }
        if(result != null){
            return result;
        }
        return null;
    }

    public List<Business> findCompanyBusiness(){
        return businessDao.findCompanyBusiness();
    }


    public List<Business> findPersonBusiness(){
        return businessDao.findPersonBusiness();
    }


    public Business findById(Integer bId){
        Business business = businessDao.findById(bId);
        return business;
    }


    public void changeData(Business business){
        businessDao.changeData(business);
    }

    //伍佳
    public Boolean insertByCardId(Integer businessType ,Integer cardId){
        Boolean insertResult=businessDao.insertByCardId(businessType,cardId);
        return insertResult;
    }
    public Boolean insertByCompanyId(Integer businessType,Integer companyId){
        Boolean insertResult=businessDao.insertByCompanyId(businessType,companyId);
        return insertResult;
    }
    public Boolean insertByVehicleId(Integer businessType,Integer VehicleId){
        Boolean insertResult=businessDao.insertByVehicleId(businessType, VehicleId);
        return insertResult;
    }
    public List<Business> allPersonBusiness(){
        return businessDao.allPersonBusiness();
    }
    public List<Business> allCompanyBusiness(){
        return businessDao.allCompanyBusiness();
    }
    public List<Business> findCardBusinessExist(){
        return businessDao.findCardBusinessExist();
    }
    public List<Business> findCompanyBusinessExist(){
        return businessDao.findCompanyBusinessExist();
    }
    public List<Business> findVehicleBusinessExist(){
        return businessDao.findVehicleBusinessExist();
    }

    @Override
    public void updateBusinessEmployeeId(List<Integer> idList, Integer employeeId) {
        for(int i = 0;i < idList.size();i++){
               Business business = businessDao.findById(idList.get(i));
               business.setEmployeeId(employeeId);
               businessDao.updateBusinessEmployeeId(business);
        }
    }

    @Override
    public void updateBusinessVehicleExistStatus(List<Integer> idList) {
        for(int i = 0;i < idList.size();i++){
            Business business = businessDao.findById(idList.get(i));
            business.setExistStatus(1);
            businessDao.update(business);
        }
    }


}
