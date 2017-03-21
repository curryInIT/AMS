package cn.scu.ams.service;

import cn.scu.ams.domain.Announce;
import cn.scu.ams.utils.PageBean;

/**
 * Created with IntelliJ IDEA.
 * User: ypx
 * Date: 17-3-15
 * Time: ионГ11:10
 * To change this template use File | Settings | File Templates.
 */
public interface AnnounceService {
    Boolean save(Announce announce);

    PageBean<Announce> findByPage(Integer currentPage);

    Announce findById(Integer announceId);

}
