package cn.scu.ams.daoImp;

import cn.scu.ams.dao.IdCardDao;
import cn.scu.ams.domain.Customer;
import cn.scu.ams.domain.IdCard;
import com.opensymphony.xwork2.ActionContext;
import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-16
 * Time: 下午7:47
 * To change this template use File | Settings | File Templates.
 */
public class IdCardDaoImp extends HibernateDaoSupport implements IdCardDao{
    @Override
    public IdCard findById(Integer id) {
        String hql="from IdCard where cardId=?";
        List<IdCard> list = this.getHibernateTemplate().find(hql,id);
        if(list.size()>0){
            return list.get(0);
        }else{
            return null;
        }

    }

    @Override
    public void changeData(IdCard idCard) {
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        org.hibernate.Transaction ts = session.beginTransaction();
        try {
            session.flush();
            ts.begin();
            session.update(idCard);
            ts.commit();
        } catch (Exception e) {
            ts.rollback();
        } finally {
            session.close();
        }
    }

    //新增一条申请身份证的业务
    public Integer apply(IdCard idCard) {
        Customer customer = (Customer) ActionContext.getContext().getSession().get("customer");
        idCard.setExistStatus(0);
        idCard.setCustomerId(customer.getCustomerId());
        Integer cardId = (Integer)this.getHibernateTemplate().save(idCard);

        return cardId;
    }

    //检查要挂失的身份证存在
    public IdCard exist(){
        Customer customer = (Customer) ActionContext.getContext().getSession().get("customer");
        String hql ="from IdCard where customerId=? and existStatus=?";
        List<IdCard> list = this.getHibernateTemplate().find(hql,customer.getCustomerId(),0);
        if(list.size() > 0){
            return list.get(0);
        }
        return null;
    }

}
