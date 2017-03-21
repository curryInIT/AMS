package cn.scu.ams.dao;

import cn.scu.ams.domain.Customer;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-14
 * Time: ионГ9:40
 * To change this template use File | Settings | File Templates.
 */
public interface CustomerDao {

    Customer findByUsernameAndPassword(Customer customer);

    Boolean registerANewCustomer(Customer customer);

    Boolean accountIsExist(Customer customer);

    Boolean modifyInfo(Customer customer);

    Customer findByAccountAndEmail(Customer customer);

    void update(Customer customer);

    Customer findCode(Customer customer);

}
