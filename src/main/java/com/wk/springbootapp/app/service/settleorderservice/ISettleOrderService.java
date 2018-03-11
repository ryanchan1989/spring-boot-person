package com.wk.springbootapp.app.service.settleorderservice;

import com.wk.springbootapp.app.domain.entity.settleorder.SettleOrderEntity;

import java.util.List;
import java.util.Map;

public interface ISettleOrderService {

    List<SettleOrderEntity> querySettleOrderByPage(int page, int pageSize);

    void insert(Map map);
}
