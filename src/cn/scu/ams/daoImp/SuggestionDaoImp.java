package cn.scu.ams.daoImp;

import cn.scu.ams.dao.SuggestionDao;
import cn.scu.ams.domain.Suggestion;
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
 * Time: 下午7:07
 * To change this template use File | Settings | File Templates.
 */
public class SuggestionDaoImp extends HibernateDaoSupport implements SuggestionDao {
    public List<Suggestion> findAll(){
        String hql = "from Suggestion where existStatus=?";
        List<Suggestion> list = this.getHibernateTemplate().find(hql,0);

        if(list.size()>0){
            return list;
        }else{
            return null;
        }
    }

    public Suggestion findById(Integer sId){
        String hql = "from Suggestion where suggestionId=?";
        List<Suggestion> list = this.getHibernateTemplate().find(hql,sId);
        if(list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    public void reply(Suggestion suggestion){
        //update(suggestion);
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        org.hibernate.Transaction ts = session.beginTransaction();
        try {
            session.flush();
            ts.begin();
            session.update(suggestion);
            ts.commit();
        } catch (Exception e) {
            ts.rollback();
        } finally {
            session.close();
        }

    }

    //提交留言
    public Boolean commit(Suggestion suggestion){
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);

        suggestion.setPubTime(time);
        suggestion.setDealStatus(0);
        suggestion.setFeedback(null);
        suggestion.setExistStatus(0);
        suggestion.setEmployeeId(null);

        Integer suggestionId = (Integer)this.getHibernateTemplate().save(suggestion);
        if(suggestionId!=null){
            return true;
        }else{
            return false;
        }
    }

    //留言总数
    public int findCount() {
        String hql = "select count(*) from Suggestion where dealStatus = 1";
        List<Long> list = this.getHibernateTemplate().find(hql);
        if(list.size() > 0){
            return list.get(0).intValue();
        }
        return 0;
    }

    //当前页留言列表
    public List<Suggestion> findByPage(Integer begin, Integer pageSize) {
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        org.hibernate.Transaction ts = session.beginTransaction();
        List<Suggestion> list = new ArrayList<Suggestion>();
        try {
            String hql = "from Suggestion where dealStatus = 1";
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
}
