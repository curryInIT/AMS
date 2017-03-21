package cn.scu.ams.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-17
 * Time: 下午10:17
 * To change this template use File | Settings | File Templates.
 */
public class GenerateRandom {
    //摇号产生的个数
    private static int num = 2;

    //返回摇号产生的id集合
    public static List<Integer> generateRandom(List<Integer> list){
        List<Integer> vehicleList = new ArrayList<Integer>();
        List<Integer> temp = new ArrayList<Integer>();
        int size = list.size();
        while(temp.size() != num){
            int randNum = (int)(Math.random() * size);
            if(!temp.contains(randNum)){
                temp.add(randNum);
            }
        }
        for(int i = 0;i < num;i++){
             vehicleList.add(list.get(temp.get(i)));
        }
        if(vehicleList != null){
            return vehicleList;
        }
        return null;
    }


}
