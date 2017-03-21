package cn.scu.ams.serviceImp;

import cn.scu.ams.dao.SuggestionDao;
import cn.scu.ams.domain.Suggestion;
import cn.scu.ams.service.SuggestionService;
import cn.scu.ams.utils.PageBean;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ypx
 * Date: 17-3-15
 * Time: ����6:40
 * To change this template use File | Settings | File Templates.
 */
public class SuggestionServiceImp implements SuggestionService{
    private SuggestionDao suggestionDao;

    public void setSuggestionDao(SuggestionDao suggestionDao){
        this.suggestionDao = suggestionDao;
    }

    public List<Suggestion> findAll(){
        return suggestionDao.findAll();
    }

    public Suggestion findById(Integer sId){
        Suggestion suggestion = suggestionDao.findById(sId);
        return suggestion;
    }

    public void reply(Suggestion suggestion){
        suggestionDao.reply(suggestion);
    }

    public Boolean commit(Suggestion suggestion){
        return suggestionDao.commit(suggestion);
    }

    //找到当前页的留言
    public PageBean<Suggestion> findByPage(Integer currentPage) {
        PageBean<Suggestion> pageBean = new PageBean<Suggestion>();

        //总记录数
        Integer totalCount = suggestionDao.findCount();
        double tc = totalCount;
        //每页的记录数
        Integer pageSize = 3;
        //总页数
        Double totalPage = Math.ceil(tc / pageSize);
        //偏移量
        Integer begin = (currentPage - 1) * pageSize;
        List<Suggestion> list = suggestionDao.findByPage(begin, pageSize);
        //给pageBean赋值
        pageBean.setCurrPage(currentPage);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalPage.intValue());
        pageBean.setList(list);

        return pageBean;
    }
}
