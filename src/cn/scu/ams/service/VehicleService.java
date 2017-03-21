package cn.scu.ams.service;

import cn.scu.ams.domain.Vehicle;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-16
 * Time: обнГ2:51
 * To change this template use File | Settings | File Templates.
 */
public interface VehicleService {

    List<Integer> getAllApplyVehicleId();

    Integer apply(Vehicle vehicle);

    Vehicle findById(Integer vehicleId);

    Vehicle exist();

}
