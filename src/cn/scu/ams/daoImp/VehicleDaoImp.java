package cn.scu.ams.daoImp;

import cn.scu.ams.dao.VehicleDao;
import cn.scu.ams.domain.Customer;
import cn.scu.ams.domain.Vehicle;
import com.opensymphony.xwork2.ActionContext;
import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-16
 * Time: 下午2:50
 * To change this template use File | Settings | File Templates.
 */
public class VehicleDaoImp extends HibernateDaoSupport implements VehicleDao{


    @Override
    public void updateVehicle(Vehicle vehicle) {
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        org.hibernate.Transaction ts = session.beginTransaction();
        try {
            session.flush();
            ts.begin();
            session.update(vehicle);
            ts.commit();
        } catch (Exception e) {
            ts.rollback();
        } finally {
            session.close();
        }
    }

    //获取所有申请摇号的vehicleId,即status = 0。
    @Override
    public List<Integer> getAllApplyVehicleId() {
        String hql = "from Vehicle where existStatus = 0";
        List<Integer> result = new ArrayList<Integer>();
        List<Vehicle> list = this.getHibernateTemplate().find(hql);
        if(list != null){
           for(int i = 0;i < list.size();i++){
               result.add(list.get(i).getVehicleId());
           }
        }
        if(result != null){
            return result;
        }
        return null;
    }

    //新增一条机动车业务
    public Integer apply(Vehicle vehicle){
        Customer customer = (Customer) ActionContext.getContext().getSession().get("customer");

        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);

        vehicle.setCustomerId(customer.getCustomerId());
        vehicle.setApplyTime(time);
        vehicle.setExistStatus(0);

        Integer vehicleId = (Integer)this.getHibernateTemplate().save(vehicle);
        return vehicleId;
    }

    //通过vehicleId找到一个vehicle
    public Vehicle findById(Integer vehicleId){
        String hql="from Vehicle where vehicleId=?";
        List<Vehicle> list = this.getHibernateTemplate().find(hql,vehicleId);
        if(list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }

    //检查用户是否存在已摇到号的车
    public Vehicle exist(){
        Customer customer = (Customer) ActionContext.getContext().getSession().get("customer");
        String hql="from Vehicle where customerId=? and existStatus=?";
        List<Vehicle> list = this.getHibernateTemplate().find(hql,customer.getCustomerId(),0);
        if(list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }


}
