package cn.scu.ams.daoImp;

import cn.scu.ams.dao.CompanyDao;
import cn.scu.ams.domain.Company;
import cn.scu.ams.domain.Customer;
import com.opensymphony.xwork2.ActionContext;
import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-16
 * Time: 6:40
 * To change this template use File | Settings | File Templates.
 */
public class CompanyDaoImp extends HibernateDaoSupport implements CompanyDao{

    @Override
    public Company findById(Integer id) {
        return this.getHibernateTemplate().get(Company.class, id);
    }

    public void changeData(Company company){
        //update(company);
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        org.hibernate.Transaction ts = session.beginTransaction();
        try {
            session.flush();
            ts.begin();
            session.update(company);
            ts.commit();
        } catch (Exception e) {
            ts.rollback();
        } finally {
            session.close();
        }
    }

    public Integer apply(Company company){
        Customer customer = (Customer) ActionContext.getContext().getSession().get("customer");
        company.setExistStatus(0);
        company.setCustomerId(customer.getCustomerId());

        Integer companyId = (Integer)this.getHibernateTemplate().save(company);
        return companyId;
    }
    public Company exist(){
        Customer customer = (Customer) ActionContext.getContext().getSession().get("customer");
        String hql="from Company where customerId=? and existStatus=?";
        List<Company> list = this.getHibernateTemplate().find(hql,customer.getCustomerId(),0);
        if(list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }


}
