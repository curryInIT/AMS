package cn.scu.ams.serviceImp;

import cn.scu.ams.dao.AnnounceDao;
import cn.scu.ams.domain.Announce;
import cn.scu.ams.service.AnnounceService;
import cn.scu.ams.utils.PageBean;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ypx
 * Date: 17-3-15
 * Time: ����11:12
 * To change this template use File | Settings | File Templates.
 */
public class AnnounceServiceImp implements AnnounceService {
    private AnnounceDao announceDao;

    public void setAnnounceDao(AnnounceDao announceDao){
        this.announceDao = announceDao;
    }

    public Boolean save(Announce announce){
        boolean result = announceDao.save(announce);
        return result;
    }

    //�ҵ���ǰҳ������
    public PageBean<Announce> findByPage(Integer currentPage) {
        PageBean<Announce> pageBean = new PageBean<Announce>();

        //�ܼ�¼��
        Integer totalCount = announceDao.findCount();
        double tc = totalCount;
        //ÿҳ�ļ�¼��
        Integer pageSize = 3;
        //��ҳ��
        Double totalPage = Math.ceil(tc / pageSize);
        //ƫ����
        Integer begin = (currentPage - 1) * pageSize;
        List<Announce> list = announceDao.findByPage(begin, pageSize);
        //��pageBean��ֵ
        pageBean.setCurrPage(currentPage);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalPage.intValue());
        pageBean.setList(list);

        return pageBean;
    }
    public Announce findById(Integer announceId){
        return announceDao.findById(announceId);
    }
}
