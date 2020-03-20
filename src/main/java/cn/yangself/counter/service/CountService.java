package cn.yangself.counter.service;


import cn.yangself.counter.dao.CountMapper;
import cn.yangself.counter.domain.Count;
import cn.yangself.counter.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CountService {
    private CountMapper countMapper;
    @Autowired
    public void setCountMapper(CountMapper countMapper) {
        this.countMapper = countMapper;
    }

    /**
     * 查询所有记录的实现类
     * @return 返回所有数据记录
     */
    public List<Count> queryAllCounts(){
        return countMapper.queryAllCounts();
    }

    /**
     * 添加设备的一条数据记录
     * @param cName
     * @param num
     * @param updateTime
     */
    public void addCount(String cName,Integer num ,String updateTime){
        countMapper.addCount(new Count(cName,num,updateTime));
    }

    /**
     * 查询数据库中有的设备种类
     * @return
     */
    public List<String> queryDevices(){
        return countMapper.queryDevices();
    }

    /**
     * 根据设备名称，查询当日的生产数据
     * @param cName
     * @return
     */
    public List<Count> getCounts(String cName){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("cName", cName);
        map.put("currentDay", DateUtil.getCurrentDate());
        return countMapper.getCounts(map);
    }

    public List<Count> getHistoryCounts(String cName,String pre,String suf){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("cName", cName);
        map.put("pre", pre);
        map.put("suf", suf);
        return countMapper.getHistoryCounts(map);
    }
}
