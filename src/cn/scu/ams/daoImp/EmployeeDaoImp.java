package cn.scu.ams.daoImp;

import cn.scu.ams.dao.EmployeeDao;
import cn.scu.ams.domain.Employee;
import cn.scu.ams.domain.Suggestion;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-14
 * Time: ����9:41
 * To change this template use File | Settings | File Templates.
 */
public class EmployeeDaoImp extends HibernateDaoSupport implements EmployeeDao{

    @Override
    public void save(Employee employee) {
        employee.setExistStatus(0);
        this.getHibernateTemplate().save(employee);
    }

    @Override
    public Employee findById(Integer eid) {
        String hql = "from Employee where employeeId=?";
        List<Employee> list = this.getHibernateTemplate().find(hql,eid);
        if(list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public void update(Employee employee) {
        //this.getHibernateTemplate().update(employee);
         Session session = getHibernateTemplate().getSessionFactory().openSession();
         org.hibernate.Transaction ts = session.beginTransaction();
              try {
              session.flush();
              ts.begin();
              session.update(employee);
              ts.commit();
             } catch (Exception e) {
              ts.rollback();
              } finally {
              session.close();
              }
    }

    @Override
    public void delete(Employee employee) {
        //this.getHibernateTemplate().update(employee);
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        org.hibernate.Transaction ts = session.beginTransaction();
        try {
            session.flush();
            ts.begin();
            session.update(employee);
            ts.commit();
        } catch (Exception e) {
            ts.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public int findCount() {
        String hql = "select count(*) from Employee where existStatus = 0";
        List<Long> list = this.getHibernateTemplate().find(hql);
        if(list.size() > 0){
            return list.get(0).intValue();
        }
        return 0;
    }

    @Override
    public List<Employee> findAll() {
        String hql = "from Employee where existStatus=?";
        List<Employee> list = this.getHibernateTemplate().find(hql, 0);
        if(list.size() > 0){
            return list;
        }
        return null;
    }

    @Override
    public List<Employee> findByName(String name) {
        String hql = "from Employee user where user.name = ? and existStatus = 0";
        List<Employee> list = this.getHibernateTemplate().find(hql, name);
        if(list != null){
            return list;
        }
        return null;
    }

    @Override
    public Employee findByAccountAndPassword(Employee employee) {
        String hql = "from Employee where account=? and password=? and existStatus = 0";
        List<Employee> list = this.getHibernateTemplate().find(hql, employee.getAccount(), employee.getPassword());
        if(list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Employee> findByPage(Integer begin, Integer pageSize) {
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        org.hibernate.Transaction ts = session.beginTransaction();
        List<Employee> list = new ArrayList<Employee>();
        try {
            String hql = "from Employee where existStatus = 0";
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
}
