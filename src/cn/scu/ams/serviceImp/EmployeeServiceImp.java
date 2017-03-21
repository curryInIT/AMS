package cn.scu.ams.serviceImp;

import cn.scu.ams.dao.EmployeeDao;
import cn.scu.ams.domain.Admin;
import cn.scu.ams.domain.Employee;
import cn.scu.ams.service.EmployeeService;
import cn.scu.ams.utils.PageBean;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-14
 * Time: 上午9:46
 * To change this template use File | Settings | File Templates.
 */
public class EmployeeServiceImp implements EmployeeService{
    private EmployeeDao employeeDao;


    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public void save(Employee employee) {
        employeeDao.save(employee);
    }

    @Override
    public Employee findById(Integer eid) {
        Employee employee = employeeDao.findById(eid);
        return employee;
    }

    @Override
    public void update(Employee employee) {
        employeeDao.update(employee);
    }

    @Override
    public void delete(Employee employee) {
        employeeDao.delete(employee);
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public List<Employee> findByName(String name) {
        return employeeDao.findByName(name);
    }

    @Override
    public Employee findByAccountAndPassword(Employee employee) {
        return employeeDao.findByAccountAndPassword(employee);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PageBean<Employee> findByPage(Integer currentPage) {
        PageBean<Employee> pageBean = new PageBean<Employee>();
        //总记录数
        Integer totalCount = employeeDao.findCount();
        double tc = totalCount;
        //每页的记录数
        Integer pageSize = 3;
        //总页数
        Double totalPage = Math.ceil(tc / pageSize);
        //偏移量
        Integer begin = (currentPage - 1) * pageSize;
        List<Employee> list = employeeDao.findByPage(begin, pageSize);
        //给pageBean赋值
        pageBean.setCurrPage(currentPage);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalPage.intValue());
        pageBean.setList(list);

        return pageBean;
    }
}
