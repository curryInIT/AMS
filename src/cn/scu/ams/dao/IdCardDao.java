package cn.scu.ams.dao;

import cn.scu.ams.domain.IdCard;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-16
 * Time: обнГ7:46
 * To change this template use File | Settings | File Templates.
 */
public interface IdCardDao {
    IdCard findById(Integer id);

    void changeData(IdCard idCard);

    Integer apply(IdCard idCard);

    IdCard exist();
}
