package cn.scu.ams.action;

import cn.scu.ams.domain.Admin;
import cn.scu.ams.domain.Employee;
import cn.scu.ams.service.EmployeeService;
import cn.scu.ams.utils.PageBean;
import cn.scu.ams.utils.Token;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-14
 * Time: 上午9:37
 * To change this template use File | Settings | File Templates.
 */
public class EmployeeAction extends ActionSupport implements ModelDriven<Employee>{
    private Integer currentPage = 1;
    private EmployeeService employeeService;
    private Employee employee = new Employee();

    @Override
    public Employee getModel() {
        return employee;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    //管理员登录检验
    public String login(){
        Employee existEmployee = employeeService.findByAccountAndPassword(employee);
        if(existEmployee == null){
            this.addActionError("用户名或密码错误");
            return INPUT;
        }else{
            Token token = new Token(existEmployee.getAccount(), 1);
            ActionContext.getContext().getSession().put("employee_token", token);
            ActionContext.getContext().getSession().put("employee", existEmployee);
            return SUCCESS;
        }
    }
    //分页查询所有员工
    public String findAll(){
        PageBean<Employee> pageBean = employeeService.findByPage(currentPage);
        if(pageBean == null){

        }else{
            ActionContext.getContext().getValueStack().push(pageBean);
            return "findAll";
        }
        return null;
    }

    //保存员工
    public String save(){
        employeeService.save(employee);
        return "saveSuccess";
    }

    //删除员工，将existStatus置为1
    public String delete(){
        Employee currEmployee = employeeService.findById(employee.getEmployeeId());
        currEmployee.setExistStatus(1);
        employeeService.delete(currEmployee);
        return "deleteSuccess";
    }

    //更新员工
    public String update(){
        employee.setExistStatus(0);
        employeeService.update(employee);
        return "updateSuccess";
    }


    //跳转到显示员工详情页面
    public String details(){
        employee= employeeService.findById(employee.getEmployeeId());
        if(employee != null){
            return "details";
        }
        return null;
    }

    //跳转到修改界面
    public String editPage(){
        employee = employeeService.findById(employee.getEmployeeId());
        if(employee != null){
            return "toEditPage";
        }
        return "findAll";
    }

    public String query(){
        List<Employee> list = employeeService.findByName(employee.getName());
        if(list == null){

        }else{
            ActionContext.getContext().getValueStack().set("list", list);
        }
        return "querySuccess";
    }

    public String logout(){
        ActionContext.getContext().getSession().remove("employee");
        ActionContext.getContext().getSession().remove("employee_token");

        return "th_logout";
    }
}
