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
 * Time: ����6:37
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

    //����ܷ���й�˾����
    public String beforeApply(){
        List<Business> list =businessService.findCompanyBusinessExist();
        if(list.size()>0){
            //���ڴ�����Ĺ�˾ҵ�񣬲��ܽ��й�˾����
            String businessInfo="���ڴ�����Ĺ�˾ҵ�񣬲��ܽ���ע�ᣡ";
            this.request.setAttribute("businessInfo",businessInfo);
            return "wj_cannotapply";
        }else{
            //û�д�����Ĺ�˾ҵ�񣬼���Ƿ��Ѿ�ע���˹�˾
            Company company = companyService.exist();
            this.request.setAttribute("company2",company);
            return "wj_gotoapply";
        }
    }

    //ע�ṫ˾
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
                String applyInfo="�����ύ�ɹ���";
                this.request.setAttribute("applyCompanyInfo",applyInfo);
                return "wj_applycommit";
            }else{
                String applyInfo="�����ύʧ�ܣ�";
                this.request.setAttribute("applyCompanyInfo",applyInfo);
                return "wj_applycommit";
            }
        }else{
            String applyInfo="�����ύʧ�ܣ�";
            this.request.setAttribute("applyCompanyInfo",applyInfo);
            return "wj_applycommit";
        }


    }

    //����ܷ���й�˾ע��
    public String beforeDelete(){
        List<Business> list =businessService.findCompanyBusinessExist();
        if(list.size()>0){
            //���ڴ�����Ĺ�˾ҵ�񣬲��ܽ��й�˾ע��
            String businessInfo="���ڴ�����Ĺ�˾ҵ�񣬲��ܽ���ע����";
            this.request.setAttribute("businessInfo",businessInfo);
            return "wj_cannotdelete";
        }else{
            //û�д�����Ĺ�˾ҵ�񣬼��Ҫע���Ĺ�˾����
            Company company = companyService.exist();
            ActionContext.getContext().getSession().put("company",company);

            return "wj_delete";
        }
    }

    //ע����˾
    public String delete(){
        Company company = (Company) ActionContext.getContext().getSession().get("company");

        Boolean insertBusinessResult = businessService.insertByCompanyId(4,company.getCompanyId());
        if(insertBusinessResult){
            String deleteInfo="ע�������ύ�ɹ���";
            this.request.setAttribute("deleteCompanyInfo",deleteInfo);
            return "wj_deletecommit";
        }else{
            String deleteInfo="ע�������ύʧ�ܣ�";
            this.request.setAttribute("deleteCompanyInfo",deleteInfo);
            return "wj_deletecommit";
        }
    }

    //ͨ��companyId�ҵ�һ����˾
    public String findById(){
        Company company1 = companyService.findById(company.getCompanyId());
        this.request.setAttribute("companyDetail",company1);
        return "wj_company";
    }


}
