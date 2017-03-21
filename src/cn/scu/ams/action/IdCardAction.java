package cn.scu.ams.action;

import cn.scu.ams.domain.Business;
import cn.scu.ams.domain.IdCard;
import cn.scu.ams.service.BusinessService;
import cn.scu.ams.service.IdCardService;
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
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-16
 * Time: 锟斤拷锟斤拷7:36
 * To change this template use File | Settings | File Templates.
 */
public class IdCardAction extends ActionSupport implements ModelDriven<IdCard>{
    private IdCardService idCardService;
    private IdCard idCard = new IdCard();
    private File upload;
    private String uploadFileName;
    private String uploadContentType;
    private int BUFFER_SIZE = 1024;
    private BusinessService businessSerivce;
    private HttpServletRequest request = ServletActionContext.getRequest();


    @Override
    public IdCard getModel() {
        return idCard;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setIdCardService(IdCardService idCardService) {
        this.idCardService = idCardService;
    }

    public void setBusinessSerivce(BusinessService businessSerivce) {
        this.businessSerivce = businessSerivce;
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
        String imgPath = "upload/" + uploadFileName;
        idCard.setPhotoPath(imgPath);

        Integer cardId = idCardService.apply(idCard);
        if(cardId!=null){
            Boolean insertBusinessResult = businessSerivce.insertByCardId(0,cardId);
            if(insertBusinessResult){
                String applyInfo="申请提交成功！";
                this.request.setAttribute("applyCardInfo",applyInfo);
                return "wj_applycommit";
            }else{
                String applyInfo="申请提交失败！";
                this.request.setAttribute("applyCardInfo",applyInfo);
                return "wj_applycommit";
            }
        }else{
            String applyInfo="申请提交失败！";
            this.request.setAttribute("applyCardInfo",applyInfo);
            return "wj_applycommit";
        }
    }

    //检查能否挂失身份证
    public String beforeAbolish(){
        List<Business> list = businessSerivce.findCardBusinessExist();
        if(list.size()>0){
            //存在待处理的身份证业务，不能进行身份证挂失
            String businessInfo="存在待处理的身份证业务，不能进行挂失！";
            this.request.setAttribute("businessInfo",businessInfo);
            return "wj_cannotabolish";
        }else{
            //没有待处理的身份证业务，检查要挂失的身份证存在
            IdCard idCard = idCardService.exist();
            ActionContext.getContext().getSession().put("idCard",idCard);
            return "wj_abolish";
        }
    }

    //挂失身份证
    public  String abolish(){
        IdCard idCard = (IdCard)ActionContext.getContext().getSession().get("idCard");
        Boolean insertBusinessResult = businessSerivce.insertByCardId(1,idCard.getCardId());
        if(insertBusinessResult){
            String abolishInfo="挂失申请提交成功！";
            this.request.setAttribute("abolishCardInfo",abolishInfo);;
            return "wj_applycommit";
        }else{
            String abolishInfo="挂失申请提交失败！";
            this.request.setAttribute("abolishInfo",abolishInfo);
            return "wj_applycommit";
        }
    }

    //通过cardId找到一个身份证
    public String findById(){
        IdCard idCard1 = idCardService.findById(idCard.getCardId());
        this.request.setAttribute("idCardDetail",idCard1);
        return "wj_idCard";
    }

    //检查能否申请身份证
    public String beforeApply(){
        List<Business> list = businessSerivce.findCardBusinessExist();
        if(list.size()>0){
            //存在待处理的身份证业务，不能进行身份证挂失
            String businessInfo="存在待处理的身份证业务，不能进行申请！";
            this.request.setAttribute("businessInfo",businessInfo);
            return "wj_cannotapply";
        }else{
            //没有待处理的身份证业务，检查是否申请了身份证
            IdCard idCard = idCardService.exist();
            this.request.setAttribute("idCard2",idCard);
            return "wj_gotoapply";
        }
    }

}
