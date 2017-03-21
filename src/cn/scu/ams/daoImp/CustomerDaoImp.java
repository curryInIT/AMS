package cn.scu.ams.daoImp;

import cn.scu.ams.dao.CustomerDao;
import cn.scu.ams.domain.Customer;
import com.opensymphony.xwork2.ActionContext;
import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-14
 * Time: 锟斤拷锟斤拷9:40
 * To change this template use File | Settings | File Templates.
 */
public class CustomerDaoImp extends HibernateDaoSupport implements CustomerDao{

    //通过账号密码找对象
    public Customer findByUsernameAndPassword(Customer customer){
        String hql = "from Customer where account=? and password=? and existStatus=?";
        List<Customer> list = this.getHibernateTemplate().find(hql, customer.getAccount(), customer.getPassword(),0);
        if(list.size() > 0){
            return list.get(0);
        }
        return null;
    }
    //新加一个对象
    public Boolean registerANewCustomer(Customer customer){
        customer.setExistStatus(0);
        Integer customerId = (Integer) this.getHibernateTemplate().save(customer);
        if(customerId!=null){
            return true;
        }else{
            return false;
        }
    }

    //查找注册的账号是否已经存在
    public  Boolean accountIsExist(Customer customer){
        String hql = "from Customer where account=? and existStatus=?";
        List<Customer> list = this.getHibernateTemplate().find(hql,customer.getAccount(),0);
        if(list.size() >0){
            return true;
        }else{
            return false;
        }
    }
    //修改个人信息
    public Boolean modifyInfo(Customer customer){
        Customer loginCustomer = (Customer) ActionContext.getContext().getSession().get("customer");
        customer.setCustomerId(loginCustomer.getCustomerId());
        customer.setAccount(loginCustomer.getAccount());
        customer.setExistStatus(loginCustomer.getExistStatus());

        Session session = getHibernateTemplate().getSessionFactory().openSession();
        org.hibernate.Transaction ts = session.beginTransaction();
        try{
            session.flush();
            ts.begin();
            session.update(customer);
            ts.commit();
        }catch (Exception e){
            ts.rollback();
        }finally {
            session.close();
        }
        //检查信息是否修改成功
        String hql="from Customer where customerId=? and account=? and password=? and email=? and existStatus=?";
        List<Customer> list = this.getHibernateTemplate().find(hql,customer.getCustomerId(),customer.getAccount(),customer.getPassword(),customer.getEmail(),customer.getExistStatus());
        if(list.size()>0){
            return true;
        }else{
            return false;
        }
    }

    public Customer findByAccountAndEmail(Customer customer){
        String hql = "from Customer where account=? and email=?";
        List<Customer> list = this.getHibernateTemplate().find(hql, customer.getAccount(), customer.getEmail());
        if(list.size() > 0){
            return list.get(0);
        }
        return null;
    }
    public Customer findCode(Customer customer){
        String hql = "from Customer where passcode=?";
        List<Customer> list = this.getHibernateTemplate().find(hql, customer.getPasscode());
        if(list.size() > 0){
            return list.get(0);
        }
        return null;
    }
    public void update(Customer customer) {
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        org.hibernate.Transaction ts = session.beginTransaction();
        try {
            session.flush();
            ts.begin();
            session.update(customer);
            ts.commit();
        } catch (Exception e) {
            ts.rollback();
        } finally {
            session.close();
        }

    }
}
