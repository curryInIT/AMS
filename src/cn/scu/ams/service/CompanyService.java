package cn.scu.ams.service;

import cn.scu.ams.domain.Company;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-16
 * Time:6:38
 * To change this template use File | Settings | File Templates.
 */
public interface CompanyService {
    Company findById(Integer id);

    void changeData(Company company);

    Integer apply(Company company);

    Company exist();

}
