package com.wk.springbootapp.app.mapper.settleordermapper;

import com.wk.springbootapp.app.domain.entity.BaseEntity;
import com.wk.springbootapp.app.domain.entity.settleorder.SettleOrderEntity;
import com.wk.springbootapp.app.mapper.basemapper.IBaseMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SettleOrderMapper<T extends BaseEntity> extends IBaseMapper<BaseEntity> {

    /*@Select("select * from settleorder")
    @Results(value = {
            @Result(property = "id", column = "id", javaType = Long.class),
            @Result(property = "settleOrderId", column = "settle_order_id", javaType = Integer.class),
            @Result(property = "orderId", column = "order_id", javaType = Integer.class)

    })*/
    public List<SettleOrderEntity> querySettleOrderByPage(Integer page, Integer pageSize);
}
