package cn.scu.ams.service;

import cn.scu.ams.domain.Suggestion;
import cn.scu.ams.utils.PageBean;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ypx
 * Date: 17-3-15
 * Time: обнГ6:37
 * To change this template use File | Settings | File Templates.
 */
public interface SuggestionService {
    List<Suggestion> findAll();

    Suggestion findById(Integer sId);

    void reply(Suggestion suggestion);

    Boolean commit(Suggestion suggestion);

    PageBean<Suggestion> findByPage(Integer currentPage);
}
