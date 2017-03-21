package cn.scu.ams.serviceImp;

import cn.scu.ams.dao.AdminDao;
import cn.scu.ams.domain.Admin;
import cn.scu.ams.domain.Employee;
import cn.scu.ams.service.AdminService;
import cn.scu.ams.utils.PageBean;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-14
 * Time: ÉÏÎç9:46
 * To change this template use File | Settings | File Templates.
 */
public class AdminServiceImp implements AdminService{
    private AdminDao adminDao;


    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @Override
    public Admin login(Admin admin) {
        Admin existAdmin = adminDao.findByUsernameAndPassword(admin);
        return existAdmin;
    }

}
