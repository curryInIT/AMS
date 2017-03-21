package cn.scu.ams.action;

import cn.scu.ams.domain.Business;
import cn.scu.ams.domain.Company;
import cn.scu.ams.service.BusinessService;
import cn.scu.ams.service.CompanyService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-16
 * Time: 下午6:37
 * To change this template use File | Settings | File Templates.
 */
public class CompanyAction extends ActionSupport implements ModelDriven<Company> {
    private Company company = new Company();
    private CompanyService companyService;
    private BusinessService businessService;
    private HttpServletRequest request = ServletActionContext.getRequest();
    private File upload;
    private String uploadFileName;
    private String uploadContentType;
    private int BUFFER_SIZE = 1024;

    public Company getModel(){
        return company;
    }
    public void setCompanyService(CompanyService companyService){
        this.companyService=companyService;
    }
    public void setBusinessSerivce(BusinessService businessSerivce){
        this.businessService=businessSerivce;
    }

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    //检查能否进行公司申请
    public String beforeApply(){
        List<Business> list =businessService.findCompanyBusinessExist();
        if(list.size()>0){
            //存在待处理的公司业务，不能进行公司申请
            String businessInfo="存在待处理的公司业务，不能进行注册！";
            this.request.setAttribute("businessInfo",businessInfo);
            return "wj_cannotapply";
        }else{
            //没有待处理的公司业务，检查是否已经注册了公司
            Company company = companyService.exist();
            this.request.setAttribute("company2",company);
            return "wj_gotoapply";
        }
    }

    //注册公司
    public String apply(){
        String realPath = "F:\\java_workspace\\AMS\\web\\upload";
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        int last = uploadFileName.lastIndexOf(".");
        String type = uploadFileName.substring(last);
        uploadFileName = simpleDateFormat.format(date) + type;

        if(upload != null){
            File saveFile = new File(new File(realPath), uploadFileName);
            if(!saveFile.getParentFile().exists()){
                saveFile.getParentFile().mkdirs();
            }
            try {
                FileUtils.copyFile(upload, saveFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String filePath = "/upload/" + uploadFileName;
        company.setUploadFilesPath(filePath);
        Integer companyId = companyService.apply(company);
        if(companyId!=null){
            Boolean insertBusinessResult = businessService.insertByCompanyId(2, companyId);
            if(insertBusinessResult){
                String applyInfo="申请提交成功！";
                this.request.setAttribute("applyCompanyInfo",applyInfo);
                return "wj_applycommit";
            }else{
                String applyInfo="申请提交失败！";
                this.request.setAttribute("applyCompanyInfo",applyInfo);
                return "wj_applycommit";
            }
        }else{
            String applyInfo="申请提交失败！";
            this.request.setAttribute("applyCompanyInfo",applyInfo);
            return "wj_applycommit";
        }


    }

    //检查能否进行公司注销
    public String beforeDelete(){
        List<Business> list =businessService.findCompanyBusinessExist();
        if(list.size()>0){
            //存在待处理的公司业务，不能进行公司注销
            String businessInfo="存在待处理的公司业务，不能进行注销！";
            this.request.setAttribute("businessInfo",businessInfo);
            return "wj_cannotdelete";
        }else{
            //没有待处理的公司业务，检查要注销的公司存在
            Company company = companyService.exist();
            ActionContext.getContext().getSession().put("company",company);

            return "wj_delete";
        }
    }

    //注销公司
    public String delete(){
        Company company = (Company) ActionContext.getContext().getSession().get("company");

        Boolean insertBusinessResult = businessService.insertByCompanyId(4,company.getCompanyId());
        if(insertBusinessResult){
            String deleteInfo="注销申请提交成功！";
            this.request.setAttribute("deleteCompanyInfo",deleteInfo);
            return "wj_deletecommit";
        }else{
            String deleteInfo="注销申请提交失败！";
            this.request.setAttribute("deleteCompanyInfo",deleteInfo);
            return "wj_deletecommit";
        }
    }

    //通过companyId找到一个公司
    public String findById(){
        Company company1 = companyService.findById(company.getCompanyId());
        this.request.setAttribute("companyDetail",company1);
        return "wj_company";
    }


}
