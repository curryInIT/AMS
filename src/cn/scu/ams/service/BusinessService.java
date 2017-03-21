package cn.scu.ams.service;

import cn.scu.ams.common.BusinessInfo;
import cn.scu.ams.domain.Business;
import cn.scu.ams.utils.PageBean;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-16
 * Time: ����11:24
 * To change this template use File | Settings | File Templates.
 */
public interface BusinessService {

    List<Business> findAll();

    PageBean<BusinessInfo> findByPage(Integer currentPage);

    List<BusinessInfo> findByName(String employeeName);

    List<Integer> findVehicleIdList();

    List<Business> getVehicleBusiness(List<Integer> list);

    void updatePassedVehicleBusiness(List<Business> list);

    void updateAllVehicleBusiness(List<Business> list);

    //�ҵ�����ҡ���ŵ�vehicleId����
    List<Integer> findAllPassedVehicleId(List<Integer> list);

    //�޸�vehicle����δҡ���ŵļ�¼�ġ�
    void updateVehicleExistStatus(List<Integer> list);


    List<Business> findVehicleBusiness();

    List<Business> findChoosedVehicle();

    List<String> getEmailList(List<Integer> passVehicleIdList);

    List<Business> findCompanyBusiness();

    List<Business> findPersonBusiness();

    Business findById(Integer bId);

    void changeData(Business business);

    //���
    Boolean insertByCardId(Integer businessType,Integer cardId);

    Boolean insertByCompanyId(Integer businessType,Integer companyId);

    Boolean insertByVehicleId(Integer businessType,Integer vehicleId);

    List<Business> allPersonBusiness();

    List<Business> allCompanyBusiness();

    List<Business> findCardBusinessExist();

    List<Business> findCompanyBusinessExist();

    List<Business> findVehicleBusinessExist();


    void updateBusinessEmployeeId(List<Integer> idList, Integer employeeId);

    void updateBusinessVehicleExistStatus(List<Integer> idList);
}
