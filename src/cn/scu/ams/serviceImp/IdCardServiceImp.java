package cn.scu.ams.serviceImp;

import cn.scu.ams.dao.IdCardDao;
import cn.scu.ams.domain.IdCard;
import cn.scu.ams.service.IdCardService;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-16
 * Time: ÏÂÎç7:45
 * To change this template use File | Settings | File Templates.
 */
public class IdCardServiceImp implements IdCardService{
    private IdCardDao idCardDao;

    @Override
    public IdCard findById(Integer id) {
        return idCardDao.findById(id);
    }

    @Override
    public void changeData(IdCard idCard) {
        idCardDao.changeData(idCard);
    }

    public void setIdCardDao(IdCardDao idCardDao) {
        this.idCardDao = idCardDao;
    }

    public Integer apply(IdCard idCard) {
        Integer cardId = idCardDao.apply(idCard);
        return cardId;
    }
    public IdCard exist(){
        return idCardDao.exist();
    }

}
