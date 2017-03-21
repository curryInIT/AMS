package cn.scu.ams.dao;

import cn.scu.ams.domain.Vehicle;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-16
 * Time: обнГ2:50
 * To change this template use File | Settings | File Templates.
 */
public interface VehicleDao {

    void updateVehicle(Vehicle vehicle);

    List<Integer> getAllApplyVehicleId();

    Integer apply(Vehicle vehicle);

    Vehicle findById(Integer vehicleId);

    Vehicle exist();
}
