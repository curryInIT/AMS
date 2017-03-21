package cn.scu.ams.serviceImp;

import cn.scu.ams.dao.CustomerDao;
import cn.scu.ams.domain.Customer;
import cn.scu.ams.service.CustomerService;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-14
 * Time: ÉÏÎç9:46
 * To change this template use File | Settings | File Templates.
 */
public class CustomerServiceImp implements CustomerService{
    private CustomerDao customerDao;

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Customer login(Customer customer){
        Customer existCustomer = customerDao.findByUsernameAndPassword(customer);
        return existCustomer;
    }
    public Boolean register(Customer customer){
        Boolean result = customerDao.registerANewCustomer(customer);
        return result;
    }
    public Boolean accountIsExist(Customer customer){
        Boolean result = customerDao.accountIsExist(customer);
        return result;
    }
    public Boolean modifyInfo(Customer customer){
        return customerDao.modifyInfo(customer);
    }
    public Customer forgetPasswd(Customer customer){
        Customer existCustomer = customerDao.findByAccountAndEmail(customer);
        return existCustomer;
    }
    public void update(Customer customer) {
        customerDao.update(customer);
    }

    public Customer testCode(Customer customer){
        Customer rightCode = customerDao.findCode(customer);
        return rightCode;
    }
}
