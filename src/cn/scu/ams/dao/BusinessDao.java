package cn.scu.ams.dao;

import cn.scu.ams.domain.Business;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-16
 * Time: ÉÏÎç11:25
 * To change this template use File | Settings | File Templates.
 */
public interface BusinessDao {
    List<Business> findAll();

    Integer findCount();

    List<Business> findByPage(Integer begin, Integer pageSize);

    List<Business> findByEmployeeId(Integer employeeId);

    List<Integer> findVehicleIdList();

    Business findById(Integer id);

    void modifyPassedVehicleBusiness(Business business);

    void modifyAllVehicleBusiness(Business business);

    Integer getVehicleIdByBusinessId(Integer id);

    List<Business> findAllVehicleBusiness();

    List<Business> findChoosedVehicle();

    List<Business> findCompanyBusiness();

    List<Business> findPersonBusiness();

    void changeData(Business business);

    //Îé¼Ñ
    Boolean insertByCardId(Integer businessType,Integer cardId);

    Boolean insertByCompanyId(Integer businessType,Integer companyId);

    Boolean insertByVehicleId(Integer businessType,Integer vehicleId);

    List<Business> allPersonBusiness();

    List<Business> allCompanyBusiness();

    List<Business> findCardBusinessExist();

    List<Business> findCompanyBusinessExist();

    List<Business> findVehicleBusinessExist();


    void updateBusinessEmployeeId(Business business);

    void update(Business business);
}
