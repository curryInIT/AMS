package cn.scu.ams.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-18
 * Time: ÉÏÎç9:14
 * To change this template use File | Settings | File Templates.
 */
public class VehicleUtil {

    public static List<Integer> getAllFailedVehicleId(List<Integer> big, List<Integer> small){
        List<Integer> result = new ArrayList<Integer>();
        Integer temp = 0;
        for(int i = 0;i < big.size();i++){
            temp = big.get(i);
            if(!small.contains(temp)){
                result.add(temp);
            }
        }
        if(result != null){
            return result;
        }
        return null;
    }

    //²âÊÔ
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<Integer>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        List<Integer> list2 = new ArrayList<Integer>();
        list2.add(1);
        List<Integer> list3 = VehicleUtil.getAllFailedVehicleId(list1, list2);
        for(int i = 0;i < list3.size();i++){
            System.out.println(list3.get(i));
        }
    }
}
