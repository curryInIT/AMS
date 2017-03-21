package cn.scu.ams.dao;

import cn.scu.ams.domain.Company;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-16
 * Time: обнГ6:40
 * To change this template use File | Settings | File Templates.
 */
public interface CompanyDao {
    Company findById(Integer id);

    void changeData(Company company);

    Integer apply(Company company);

    Company exist();
}
