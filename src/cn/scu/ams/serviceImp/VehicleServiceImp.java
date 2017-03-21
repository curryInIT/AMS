package cn.scu.ams.serviceImp;

import cn.scu.ams.dao.VehicleDao;
import cn.scu.ams.domain.Vehicle;
import cn.scu.ams.service.VehicleService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-16
 * Time: ÏÂÎç2:51
 * To change this template use File | Settings | File Templates.
 */
public class VehicleServiceImp implements VehicleService{
    private VehicleDao vehicleDao;

    public void setVehicleDao(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }


    @Override
    public List<Integer> getAllApplyVehicleId() {
        return vehicleDao.getAllApplyVehicleId();
    }

    public Integer apply(Vehicle vehicle){
        return vehicleDao.apply(vehicle);
    }
    public Vehicle findById(Integer vehicleId){
        return vehicleDao.findById(vehicleId);
    }
    public Vehicle exist(){
        return vehicleDao.exist();
    }
}
