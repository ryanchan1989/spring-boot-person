package com.wk.springbootapp.app.conytroller;

import com.wk.springbootapp.app.domain.entity.settleorder.SettleOrderEntity;
import com.wk.springbootapp.app.mapper.settleordermapper.SettleOrderMapper;
import com.wk.springbootapp.app.service.settleorderservice.ISettleOrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
public class AppController {

    @Resource
    private ISettleOrderService settleOrdermapper;

    @RequestMapping("/health")
    public String health(){
        List<SettleOrderEntity> list = settleOrdermapper.querySettleOrderByPage(0 ,10);
        settleOrdermapper.insert(new HashMap<>());
        SettleOrderEntity settleOrderEntity = list.get(0);
        return "success";
    }
}
