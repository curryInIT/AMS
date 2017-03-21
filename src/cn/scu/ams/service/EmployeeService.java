package cn.scu.ams.service;

import cn.scu.ams.domain.Admin;
import cn.scu.ams.domain.Employee;
import cn.scu.ams.utils.PageBean;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-14
 * Time: ионГ9:45
 * To change this template use File | Settings | File Templates.
 */
public interface EmployeeService {
    void save(Employee employee);

    Employee findById(Integer eid);

    void update(Employee employee);

    void delete(Employee employee);

    List<Employee> findAll();

    List<Employee> findByName(String name);

    Employee findByAccountAndPassword(Employee employee);

    PageBean<Employee> findByPage(Integer currentPage);
}
