package cn.yangself.counter.dao;


import cn.yangself.counter.domain.Count;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CountMapper {
    /**
     * 查询所有的数量数据
     * @return
     */
    List<Count> queryAllCounts();

    /**
     * 添加一条数据
     * @param count
     */
    void addCount(Count count);

    /**
     * 查询总共有几种设备
     * @return 返回包含所有设备名称的List
     */
    List<String> queryDevices();

    /**
     * 使用名字和今天的日期查询今天的数量
     * @param map
     * @return
     */
    List<Count> getCounts(Map<String,Object> map);

    /**
     * 使用两个日期查询期间的数量
     * @param map
     * @return
     */
    List<Count> getHistoryCounts(Map<String,Object> map);

}
