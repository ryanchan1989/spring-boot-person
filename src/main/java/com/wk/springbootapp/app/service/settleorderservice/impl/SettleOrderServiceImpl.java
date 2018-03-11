package com.wk.springbootapp.app.service.settleorderservice.impl;

import com.wk.springbootapp.app.domain.entity.settleorder.SettleOrderEntity;
import com.wk.springbootapp.app.mapper.settleordermapper.SettleOrderMapper;
import com.wk.springbootapp.app.service.settleorderservice.ISettleOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class SettleOrderServiceImpl implements ISettleOrderService{

    @Resource
    private SettleOrderMapper settleOrderMapper;

    @Override
    public List<SettleOrderEntity> querySettleOrderByPage(int page, int pageSize) {
        return settleOrderMapper.querySettleOrderByPage(page, pageSize);
    }

    @Override
    public void insert(Map map) {
        settleOrderMapper.insert(map);
    }
}
