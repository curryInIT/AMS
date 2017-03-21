package cn.scu.ams.dao;

import cn.scu.ams.domain.Admin;
import cn.scu.ams.domain.Employee;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-14
 * Time: ионГ9:40
 * To change this template use File | Settings | File Templates.
 */
public interface AdminDao {
    Admin findByUsernameAndPassword(Admin admin);
}
