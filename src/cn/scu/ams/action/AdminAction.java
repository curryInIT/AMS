package cn.scu.ams.action;

import cn.scu.ams.domain.Admin;
import cn.scu.ams.domain.Employee;
import cn.scu.ams.service.AdminService;
import cn.scu.ams.utils.Token;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-14
 * Time: 上午9:38
 * To change this template use File | Settings | File Templates.
 */
public class AdminAction extends ActionSupport implements ModelDriven<Admin>{
    private AdminService adminService;
    private Admin admin = new Admin();
    private File upload;
    private String uploadFileName;
    private String uploadContentType;
    private HttpServletRequest request = ServletActionContext.getRequest();
    private int BUFFER_SIZE = 1024;

    @Override
    public Admin getModel() {
        return admin;
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

    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    //管理员登录检验
    public String login(){
        Admin existAdmin = adminService.login(admin);
        if(existAdmin == null){
            this.addActionError("用户名或密码错误");
            return INPUT;
        }else{
            Token token = new Token(existAdmin.getAccount(), 1);
            ActionContext.getContext().getSession().put("token", token);
            ActionContext.getContext().getSession().put("admin", existAdmin);
            return SUCCESS;
        }
    }

    //注销
    public String logout(){
        ActionContext.getContext().getSession().remove("admin");
        ActionContext.getContext().getSession().remove("token");
        return "logout";
    }

    //文件上传
    public String upload(){
        String realPath = "F:\\java_workspace\\AMS\\web\\upload";
        System.out.println("destination:" + realPath);
        System.out.println("uploadFileName" + uploadFileName);
        System.out.println("uploadContentType" + uploadContentType);
        //自动命名
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        int last = uploadFileName.lastIndexOf(".");
        String type = uploadFileName.substring(last);
        uploadFileName = simpleDateFormat.format(date)  + type;
        System.out.println("新的文件名称是："+uploadFileName);
        String imgPath = "${pageContext.request.contextPath}/upload/" + uploadFileName;
        System.out.println("文件的路径是：" + imgPath);

        //创建父文件夹
        if(upload != null){
            File saveFile = new File(new File(realPath), uploadFileName);
            if(!saveFile.getParentFile().exists()){
                saveFile.getParentFile().mkdirs();
            }
            try {
                FileUtils.copyFile(upload, saveFile);     //保存文件
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "uploadSuccess";
    }


    public String download(){
          String path = request.getParameter("path");

        return "downloadSuccess";
    }

}