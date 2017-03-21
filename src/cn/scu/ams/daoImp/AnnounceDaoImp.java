package cn.scu.ams.daoImp;

import cn.scu.ams.dao.AnnounceDao;
import cn.scu.ams.domain.Announce;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ypx
 * Date: 17-3-15
 * Time: 上午11:18
 * To change this template use File | Settings | File Templates.
 */
public class AnnounceDaoImp extends HibernateDaoSupport implements AnnounceDao {

    public Boolean save(Announce announce) {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(date);
        announce.setPubTime(time);

        announce.setExistStatus(0);
        Integer announceId = (Integer)this.getHibernateTemplate().save(announce);
        if(announceId != null){
            return true;
        }else{
            return false;
        }
    }

    //公告总数
    public int findCount() {
        String hql = "select count(*) from Announce where existStatus = 0";
        List<Long> list = this.getHibernateTemplate().find(hql);
        if(list.size() > 0){
            return list.get(0).intValue();
        }
        return 0;
    }

    //当前页留言列表
    public List<Announce> findByPage(Integer begin, Integer pageSize) {
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        org.hibernate.Transaction ts = session.beginTransaction();
        List<Announce> list = new ArrayList<Announce>();
        try {
            String hql = "from Announce where existStatus = 0";
            session.flush();
            ts.begin();
            Query query = session.createQuery(hql);
            query.setFirstResult(begin);
            query.setMaxResults(pageSize);
            list = query.list();
            Collections.reverse(list);
            ts.commit();
        } catch (Exception e) {
            ts.rollback();
        } finally {
            session.close();
        }

        return list;
    }
    public Announce findById(Integer announceId){
        String hql = "from Announce where announceId=? and existStatus=?";
        List<Announce> list = this.getHibernateTemplate().find(hql, announceId,0);
        if(list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}
