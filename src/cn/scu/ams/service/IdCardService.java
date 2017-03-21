package cn.scu.ams.service;

import cn.scu.ams.domain.IdCard;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-16
 * Time: обнГ7:44
 * To change this template use File | Settings | File Templates.
 */
public interface IdCardService {
    IdCard findById(Integer id);

    void changeData(IdCard idCard);

    Integer apply(IdCard idCard);

    IdCard exist();

}
