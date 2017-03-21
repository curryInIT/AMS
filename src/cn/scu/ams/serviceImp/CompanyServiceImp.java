package cn.scu.ams.serviceImp;

import cn.scu.ams.dao.CompanyDao;
import cn.scu.ams.domain.Company;
import cn.scu.ams.service.CompanyService;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-16
 * Time: ����6:39
 * To change this template use File | Settings | File Templates.
 */
public class CompanyServiceImp implements CompanyService{
    private CompanyDao companyDao;

    public void setCompanyDao(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @Override
    public Company findById(Integer id) {
        return companyDao.findById(id);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void changeData(Company company){
        companyDao.changeData(company);
    }

    public Integer apply(Company company){
        return companyDao.apply(company);
    }

    public Company exist(){
        return companyDao.exist();
    }


}
