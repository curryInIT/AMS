package cn.scu.ams.dao;

import cn.scu.ams.domain.Suggestion;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ypx
 * Date: 17-3-15
 * Time: обнГ7:03
 * To change this template use File | Settings | File Templates.
 */
public interface SuggestionDao {
    List<Suggestion> findAll();

    Suggestion findById(Integer sId);

    void reply(Suggestion suggestion);

    Boolean commit(Suggestion suggestion);

    int findCount();

    List<Suggestion> findByPage(Integer begin, Integer pageSize);

}
