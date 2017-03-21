package cn.scu.ams.dao;

import cn.scu.ams.domain.Employee;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-14
 * Time: ионГ9:40
 * To change this template use File | Settings | File Templates.
 */
public interface EmployeeDao {
    void save(Employee employee);

    Employee findById(Integer eid);


    void update(Employee employee);

    void delete(Employee employee);

    int findCount();

    List<Employee> findAll();

    List<Employee> findByName(String name);

    Employee findByAccountAndPassword(Employee employee);

    List<Employee> findByPage(Integer begin, Integer pageSize);
}
