package cn.scu.ams.dao;

import cn.scu.ams.domain.Announce;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ypx
 * Date: 17-3-15
 * Time: ионГ11:16
 * To change this template use File | Settings | File Templates.
 */
public interface AnnounceDao {

    Boolean save(Announce announce);

    int findCount();

    List<Announce> findByPage(Integer begin, Integer pageSize);

    Announce findById(Integer announceId);

}
