package com.wk.springbootapp.app.mapper.basemapper;

import com.wk.springbootapp.app.domain.entity.BaseEntity;

import java.util.List;
import java.util.Map;

public interface IBaseMapper<T extends BaseEntity> {
    /**
     * 增加一个对象
     * @param paramMap
     * @return Integer
     */
    public Integer insert(Map<String, Object> paramMap);

    /**
     * 批量增加对象
     * @param paramMap
     * @return Integer
     */
    public Integer insertBatch(Map<String, Object> paramMap);

    /**
     * 删除一个对象
     * @param paramMap
     * @return Integer
     */
    public Integer delete(Map<String, Object> paramMap);

    /**
     * 批量删除对象
     * @param paramMap
     * @return Integer
     */
    public Integer deleteBatch(Map<String, Object> paramMap);

    /**
     * 修改一个对象
     * @param paramMap
     * @return Integer
     */
    public Integer update(Map<String, Object> paramMap);

    /**
     * 批量修改对象
     * @param paramMap
     * @return Integer
     */
    public Integer updateBatch(Map<String, Object> paramMap);

    /**
     * 查询一个对象
     * @param paramMap
     * @return T
     */
    public T selectEntity(Map<String, Object> paramMap);

    /**
     * 批量查询对象
     * @param paramMap
     * @return List<T>
     */
    public List<T> selectEntities(Map<String, Object> paramMap);

    /**
     * 获取记录Count数量
     * @param paramMap
     * @return Integer
     */
    public Integer selectPageCount(Map<String, Object> paramMap);

    /**
     * 获取List<T>分页对象
     * @param paramMap
     * @return List<T>
     */
    public List<T> selectPageEntities(Map<String, Object> paramMap);

}
