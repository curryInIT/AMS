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
 * Time: ����11:25
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

    //�ҵ����������ҵ���ҵ��id����
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

    //����bid�ҵ�business
    @Override
    public Business findById(Integer id) {
        return this.getHibernateTemplate().get(Business.class, id);
    }

    //��ͨ���Ļ�����ҵ���passStatus��Ϊ1,����passTime
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

    //�����д�����Ļ�����ҵ���status��Ϊ2
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

    //ͨ��bid�õ�����ҵ���vehicleId
    @Override
    public Integer getVehicleIdByBusinessId(Integer id) {
        return this.getHibernateTemplate().get(Business.class, id).getVehicleId();
    }



    //�г���������ҡ�ŵ�����
    public List<Business> findAllVehicleBusiness(){
        String hql = "from Business where businessType=? and existStatus=? and status = ?";
        List<Business> list = this.getHibernateTemplate().find(hql,5,0,0);

        if(list.size()>0){
            return list;
        }else{
            return null;
        }
    }


    //�г�����ҡ�ųɹ�����Ա����
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

    //���
    //ͨ�������cardId����һ�����֤ҵ��
    public Boolean insertByCardId(Integer businessType,Integer cardId){
        Business business = new Business();
        Customer customer = (Customer)ActionContext.getContext().getSession().get("customer");

        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);

        business.setCustomerId(customer.getCustomerId());
        //0�����֤���� 1�����֤��ʧ 2�ǹ�˾ע�� 3�ǹ�˾�޸� 4�ǹ�˾ע�� 5��ҡ��
        business.setBusinessType(businessType);
        business.setApplyTime(time);
        business.setPassTime(null);
        //0�Ǵ���� 1������� 2����˽���
        business.setStatus(0);
        //0��Ϊͨ�� 1����ͨ��
        business.setPassStatus(0);
        business.setEmployeeId(null);
        business.setCompanyId(null);
        business.setVehicleId(null);
        business.setIdCardId(cardId);
        //0Ϊ���ڿ��� 1Ϊ�ѱ�ɾ��������
        business.setExistStatus(0);

        Integer businessId = (Integer)this.getHibernateTemplate().save(business);
        if(businessId!=null){
            return true;
        }else{
            return false;
        }
    }

    //ͨ�������companyId����һ����˾ҵ��
    public Boolean insertByCompanyId(Integer businessType,Integer companyId){
        Business business = new Business();
        Customer customer = (Customer)ActionContext.getContext().getSession().get("customer");

        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);

        business.setCustomerId(customer.getCustomerId());
        //0�����֤���� 1�����֤��ʧ 2�ǹ�˾ע�� 3�ǹ�˾�޸� 4�ǹ�˾ע�� 5��ҡ��
        business.setBusinessType(businessType);
        business.setApplyTime(time);
        business.setPassTime(null);
        //0�Ǵ���� 1������� 2����˽���
        business.setStatus(0);
        //0��Ϊͨ�� 1����ͨ��
        business.setPassStatus(0);
        business.setEmployeeId(null);
        business.setCompanyId(companyId);
        business.setVehicleId(null);
        business.setIdCardId(null);
        //0Ϊ���ڿ��� 1Ϊ�ѱ�ɾ��������
        business.setExistStatus(0);

        Integer businessId = (Integer)this.getHibernateTemplate().save(business);
        if(businessId!=null){
            return true;
        }else{
            return false;
        }
    }

    //ͨ�������vehicleId����һ��������ҵ��
    public Boolean insertByVehicleId(Integer businessType,Integer vehicleId){
        Business business = new Business();
        Customer customer = (Customer)ActionContext.getContext().getSession().get("customer");

        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);

        business.setCustomerId(customer.getCustomerId());
        //0�����֤���� 1�����֤��ʧ 2�ǹ�˾ע�� 3�ǹ�˾�޸� 4�ǹ�˾ע�� 5��ҡ��
        business.setBusinessType(businessType);
        business.setApplyTime(time);
        business.setPassTime(null);
        //0�Ǵ���� 1������� 2����˽���
        business.setStatus(0);
        //0��Ϊͨ�� 1����ͨ��
        business.setPassStatus(0);
        business.setEmployeeId(null);
        business.setCompanyId(null);
        business.setVehicleId(vehicleId);
        business.setIdCardId(null);
        //0Ϊ���ڿ��� 1Ϊ�ѱ�ɾ��������
        business.setExistStatus(0);

        Integer businessId = (Integer)this.getHibernateTemplate().save(business);
        if(businessId!=null){
            return true;
        }else{
            return false;
        }
    }

    //����customerId���ҳ��û���Ӧ�����и���ҵ��
    public List<Business> allPersonBusiness(){
        Customer customer = (Customer)ActionContext.getContext().getSession().get("customer");
        String hql="from Business where customerId=? and (businessType=? or businessType=? or businessType=?)";
        List<Business> personBusinessList = this.getHibernateTemplate().find(hql,customer.getCustomerId(),0,1,5);
        return  personBusinessList;
    }

    //����customerId���ҳ��û���Ӧ�����й�˾ҵ��
    public List<Business> allCompanyBusiness(){
        Customer customer = (Customer)ActionContext.getContext().getSession().get("customer");
        String hql="from Business where customerId=? and (businessType=? or businessType=?)";
        List<Business> companyBusinessList = this.getHibernateTemplate().find(hql,customer.getCustomerId(),2,4);
        return  companyBusinessList;
    }

    //�����û����ڵĴ���������֤���ҵ��
    public List<Business> findCardBusinessExist(){
        Customer customer = (Customer)ActionContext.getContext().getSession().get("customer");
        String hql="from Business where customerId=? and (businessType=? or businessType=?) and existStatus=?";
        List<Business> list = this.getHibernateTemplate().find(hql,customer.getCustomerId(),0,1,0);
        return list;
    }

    //�����û����ڵĴ�����Ĺ�˾���ҵ��
    public List<Business> findCompanyBusinessExist(){
        Customer customer = (Customer)ActionContext.getContext().getSession().get("customer");
        String hql="from Business where customerId=? and (businessType=? or businessType=?) and existStatus=?";
        List<Business> list = this.getHibernateTemplate().find(hql,customer.getCustomerId(),2,4,0);
        return list;
    }

    //�����û����ڵĴ�����Ļ�����ҵ��
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
