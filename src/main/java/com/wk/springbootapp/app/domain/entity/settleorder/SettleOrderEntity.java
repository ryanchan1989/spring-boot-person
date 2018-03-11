package com.wk.springbootapp.app.domain.entity.settleorder;


import com.wk.springbootapp.app.domain.entity.BaseEntity;

public class SettleOrderEntity extends BaseEntity{
    private Long id;

    private Integer settleOrderId;

    private Integer orderId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSettleOrderId() {
        return settleOrderId;
    }

    public void setSettleOrderId(Integer settleOrderId) {
        this.settleOrderId = settleOrderId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
