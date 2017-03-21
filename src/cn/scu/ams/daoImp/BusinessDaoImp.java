package cn.scu.ams.daoImp;

import cn.scu.ams.dao.BusinessDao;
import cn.scu.ams.domain.Business;
import cn.scu.ams.domain.Customer;
import cn.scu.ams.domain.Employee;
import com.opensymphony.xwork2.ActionContext;
import org.hibernate.Query;
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
 * Time: 上午11:25
 * To change this template use File | Settings | File Templates.
 */
public class BusinessDaoImp extends HibernateDaoSupport implements BusinessDao{

    @Override
    public List<Business> findAll() {
        String hql = "from Business";
        List<Business> list = this.getHibernateTemplate().find(hql);
        if(list != null){
            return list;
        }
        return null;
    }

    @Override
    public Integer findCount() {
        String hql = "select count(*) from Business where existStatus = 0";
        List<Long> list = this.getHibernateTemplate().find(hql);
        if(list.size() > 0){
            return list.get(0).intValue();
        }
        return 0;
    }

    @Override
    public List<Business> findByPage(Integer begin, Integer pageSize) {
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        org.hibernate.Transaction ts = session.beginTransaction();
        List<Business> list = new ArrayList<Business>();
        try {
            String hql = "from Business where existStatus = 0";
            session.flush();
            ts.begin();
            Query query = session.createQuery(hql);
            query.setFirstResult(begin);
            query.setMaxResults(pageSize);
            list = query.list();
            ts.commit();
        } catch (Exception e) {
            ts.rollback();
        } finally {
            session.close();
        }

        return list;
    }

    @Override
    public List<Business> findByEmployeeId(Integer employeeId) {
        String hql = "from Business where employeeId=?";
        List<Business> list = this.getHibernateTemplate().find(hql, employeeId);
        if(list.size() > 0){
            return list;
        }
        return null;
    }

    //找到申请机动车业务的业务id集合
    @Override
    public List<Integer> findVehicleIdList() {
        List<Integer> idList = new ArrayList<Integer>();
        String hql = "from Business where businessType = 5 and existStatus = 0";
        List<Business> list = this.getHibernateTemplate().find(hql);
        if(list != null){
            for(int i = 0;i < list.size();i++){
                idList.add(list.get(i).getBusinessId());
            }
            return idList;
        }
        return null;
    }

    //根据bid找到business
    @Override
    public Business findById(Integer id) {
        return this.getHibernateTemplate().get(Business.class, id);
    }

    //将通过的机动车业务的passStatus设为1,设置passTime
    public void modifyPassedVehicleBusiness(Business business){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        business.setPassTime(format.format(date));
        business.setPassStatus(1);
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        org.hibernate.Transaction ts = session.beginTransaction();
        try {
            session.flush();
            ts.begin();
            session.update(business);
            ts.commit();
        } catch (Exception e) {
            ts.rollback();
        } finally {
            session.close();
        }
    }

    //将所有处理过的机动车业务的status设为2
    public void modifyAllVehicleBusiness(Business business){
        business.setStatus(2);
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        org.hibernate.Transaction ts = session.beginTransaction();
        try {
            session.flush();
            ts.begin();
            session.update(business);
            ts.commit();
        } catch (Exception e) {
            ts.rollback();
        } finally {
            session.close();
        }
    }

    //通过bid得到这条业务的vehicleId
    @Override
    public Integer getVehicleIdByBusinessId(Integer id) {
        return this.getHibernateTemplate().get(Business.class, id).getVehicleId();
    }



    //列出所有申请摇号的名单
    public List<Business> findAllVehicleBusiness(){
        String hql = "from Business where businessType=? and existStatus=? and status = ?";
        List<Business> list = this.getHibernateTemplate().find(hql,5,0,0);

        if(list.size()>0){
            return list;
        }else{
            return null;
        }
    }


    //列出所有摇号成功的人员名单
    public List<Business> findChoosedVehicle(){
        String hql = "from Business where businessType=? and passStatus=? and existStatus=?";
        List<Business> list = this.getHibernateTemplate().find(hql,5,1,0);

        if(list.size()>0){
            return list;
        }else{
            return null;
        }
    }

    public List<Business> findCompanyBusiness(){
        String hql = "from Business where (businessType=? or businessType=?) and existStatus=?";
        List<Business> list = this.getHibernateTemplate().find(hql,2,4,0);

        if(list.size()>0){
            return list;
        }else{
            return null;
        }
    }

    public List<Business> findPersonBusiness(){
        String hql = "from Business where (businessType=? or businessType=?) and existStatus=?";
        List<Business> list = this.getHibernateTemplate().find(hql,0,1,0);

        if(list.size()>0){
            return list;
        }else{
            return null;
        }
    }

    public void changeData(Business business){
        //update(business);
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        org.hibernate.Transaction ts = session.beginTransaction();
        try {
            session.flush();
            ts.begin();
            session.update(business);
            ts.commit();
        } catch (Exception e) {
            ts.rollback();
        } finally {
            session.close();
        }
    }

    //伍佳
    //通过传入的cardId新增一条身份证业务
    public Boolean insertByCardId(Integer businessType,Integer cardId){
        Business business = new Business();
        Customer customer = (Customer)ActionContext.getContext().getSession().get("customer");

        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);

        business.setCustomerId(customer.getCustomerId());
        //0是身份证申请 1是身份证挂失 2是公司注册 3是公司修改 4是公司注销 5是摇号
        business.setBusinessType(businessType);
        business.setApplyTime(time);
        business.setPassTime(null);
        //0是待审核 1是审核中 2是审核结束
        business.setStatus(0);
        //0是为通过 1是已通过
        business.setPassStatus(0);
        business.setEmployeeId(null);
        business.setCompanyId(null);
        business.setVehicleId(null);
        business.setIdCardId(cardId);
        //0为存在可用 1为已被删除不可用
        business.setExistStatus(0);

        Integer businessId = (Integer)this.getHibernateTemplate().save(business);
        if(businessId!=null){
            return true;
        }else{
            return false;
        }
    }

    //通过传入的companyId新增一条公司业务
    public Boolean insertByCompanyId(Integer businessType,Integer companyId){
        Business business = new Business();
        Customer customer = (Customer)ActionContext.getContext().getSession().get("customer");

        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);

        business.setCustomerId(customer.getCustomerId());
        //0是身份证申请 1是身份证挂失 2是公司注册 3是公司修改 4是公司注销 5是摇号
        business.setBusinessType(businessType);
        business.setApplyTime(time);
        business.setPassTime(null);
        //0是待审核 1是审核中 2是审核结束
        business.setStatus(0);
        //0是为通过 1是已通过
        business.setPassStatus(0);
        business.setEmployeeId(null);
        business.setCompanyId(companyId);
        business.setVehicleId(null);
        business.setIdCardId(null);
        //0为存在可用 1为已被删除不可用
        business.setExistStatus(0);

        Integer businessId = (Integer)this.getHibernateTemplate().save(business);
        if(businessId!=null){
            return true;
        }else{
            return false;
        }
    }

    //通过传入的vehicleId新增一条机动车业务
    public Boolean insertByVehicleId(Integer businessType,Integer vehicleId){
        Business business = new Business();
        Customer customer = (Customer)ActionContext.getContext().getSession().get("customer");

        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);

        business.setCustomerId(customer.getCustomerId());
        //0是身份证申请 1是身份证挂失 2是公司注册 3是公司修改 4是公司注销 5是摇号
        business.setBusinessType(businessType);
        business.setApplyTime(time);
        business.setPassTime(null);
        //0是待审核 1是审核中 2是审核结束
        business.setStatus(0);
        //0是为通过 1是已通过
        business.setPassStatus(0);
        business.setEmployeeId(null);
        business.setCompanyId(null);
        business.setVehicleId(vehicleId);
        business.setIdCardId(null);
        //0为存在可用 1为已被删除不可用
        business.setExistStatus(0);

        Integer businessId = (Integer)this.getHibernateTemplate().save(business);
        if(businessId!=null){
            return true;
        }else{
            return false;
        }
    }

    //根据customerId查找出用户对应的所有个人业务
    public List<Business> allPersonBusiness(){
        Customer customer = (Customer)ActionContext.getContext().getSession().get("customer");
        String hql="from Business where customerId=? and (businessType=? or businessType=? or businessType=?)";
        List<Business> personBusinessList = this.getHibernateTemplate().find(hql,customer.getCustomerId(),0,1,5);
        return  personBusinessList;
    }

    //根据customerId查找出用户对应的所有公司业务
    public List<Business> allCompanyBusiness(){
        Customer customer = (Customer)ActionContext.getContext().getSession().get("customer");
        String hql="from Business where customerId=? and (businessType=? or businessType=?)";
        List<Business> companyBusinessList = this.getHibernateTemplate().find(hql,customer.getCustomerId(),2,4);
        return  companyBusinessList;
    }

    //查找用户存在的待处理的身份证相关业务
    public List<Business> findCardBusinessExist(){
        Customer customer = (Customer)ActionContext.getContext().getSession().get("customer");
        String hql="from Business where customerId=? and (businessType=? or businessType=?) and existStatus=?";
        List<Business> list = this.getHibernateTemplate().find(hql,customer.getCustomerId(),0,1,0);
        return list;
    }

    //查找用户存在的待处理的公司相关业务
    public List<Business> findCompanyBusinessExist(){
        Customer customer = (Customer)ActionContext.getContext().getSession().get("customer");
        String hql="from Business where customerId=? and (businessType=? or businessType=?) and existStatus=?";
        List<Business> list = this.getHibernateTemplate().find(hql,customer.getCustomerId(),2,4,0);
        return list;
    }

    //查找用户存在的待处理的机动车业务
    public List<Business> findVehicleBusinessExist(){
        Customer customer = (Customer) ActionContext.getContext().getSession().get("customer");
        String hql="from Business where customerId=? and businessType=? and existStatus=?";
        List<Business> list = this.getHibernateTemplate().find(hql,customer.getCustomerId(),5,0);
        return list;
    }

    @Override
    public void updateBusinessEmployeeId(Business business) {
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        org.hibernate.Transaction ts = session.beginTransaction();
        try {
            session.flush();
            ts.begin();
            session.update(business);
            ts.commit();
        } catch (Exception e) {
            ts.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Business business) {
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        org.hibernate.Transaction ts = session.beginTransaction();
        try {
            session.flush();
            ts.begin();
            session.update(business);
            ts.commit();
        } catch (Exception e) {
            ts.rollback();
        } finally {
            session.close();
        }
    }
}
