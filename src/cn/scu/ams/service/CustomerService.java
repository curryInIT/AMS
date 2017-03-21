package cn.scu.ams.service;

import cn.scu.ams.domain.Customer;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-14
 * Time: ионГ9:45
 * To change this template use File | Settings | File Templates.
 */
public interface CustomerService {
    Customer login(Customer customer);

    Boolean register(Customer customer);

    Boolean accountIsExist(Customer customer);

    Boolean modifyInfo(Customer customer);

    Customer forgetPasswd(Customer customer);

    void update(Customer customer);

    Customer testCode(Customer customer);

}
