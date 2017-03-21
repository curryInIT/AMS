package cn.scu.ams.daoImp;

import cn.scu.ams.dao.AdminDao;
import cn.scu.ams.domain.Admin;
import cn.scu.ams.domain.Employee;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-14
 * Time: ÉÏÎç9:40
 * To change this template use File | Settings | File Templates.
 */
public class AdminDaoImp extends HibernateDaoSupport implements AdminDao{

    @Override
    public Admin findByUsernameAndPassword(Admin admin) {
        String hql = "from Admin where account=? and password=?";
        List<Admin> list = this.getHibernateTemplate().find(hql, admin.getAccount(), admin.getPassword());
        if(list.size() > 0){
            return list.get(0);
        }
        return null;
    }


}
