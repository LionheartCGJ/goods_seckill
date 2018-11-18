package org.seckill.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;

public interface SeckillDao {

    /**
     * 减库存
     * 
     * @param seckillId
     * @param killTime
     * @return 删除的结果及行数
     */
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    /**
     * 跟据seckillId查询商品
     * 
     * @param seckillId
     * @return
     */
    Seckill queryById(@Param("seckillId") long seckillId);

    /**
     * 根据偏移量查询商品列表
     * 
     * @param offet
     * @param limit
     * @return
     */
    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);
    
    /**
     * 通过存储过程，实现秒杀逻辑
     * @param paramMap
     */
    void killByprocedure(Map<String, Object> paramMap);
}
