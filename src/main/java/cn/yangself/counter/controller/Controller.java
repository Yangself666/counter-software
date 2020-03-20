package cn.yangself.counter.controller;


import cn.yangself.counter.domain.Count;
import cn.yangself.counter.domain.CountsJson;
import cn.yangself.counter.service.CountService;
import cn.yangself.counter.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Controller
public class Controller {
    private CountService countService;
    @Autowired
    public void setCountService(CountService countService) {
        this.countService = countService;
    }

    /**
     * 向数据库添加一条生产线产量数据
     * @param cName 设备名称
     * @param num 设备所记数量
     * @return 上传成功返回success字符串
     */
    @RequestMapping("/addData")
    @ResponseBody
    public String addData(String cName,Integer num){
        String updateTime = DateUtil.getDate();
        countService.addCount(cName, num, updateTime);
        System.out.println("update success!" + cName);
        return "success";
    }

    /**
     * 查询数据库中总共有那些设备
     * @return 设备名称的集合
     */
    @RequestMapping("/devices")
    @ResponseBody
    public List<String> devices(){
        return countService.queryDevices();
    }

    /**
     * 获取今日所有设备的产量数据
     * @return 产量数据
     */
    @RequestMapping("/counts")
    @ResponseBody
    public List<Object> counts(){
        List<Object> result = new ArrayList<Object>();//创建空的结果集
        List<CountsJson> countsJsons = new ArrayList<CountsJson>();//创建一个总的表数据
        List<String> cNames = countService.queryDevices();//查询所有产品名称
        List<Boolean> flag = new ArrayList<Boolean>();//如果数据为空，设置标记
        String [] title = new String[]{"时间", "生产数量"};
        for (String cName : cNames) {//循环产品名称
            CountsJson countsJson = new CountsJson();//新建一个单位产品表格
            countsJson.setColumns(title);//设置标题
            List<Count> counts = countService.getCounts(cName);//根据产品名称查询数据
            if (counts.size() > 0) {
                flag.add(false);
            }else{
                flag.add(true);
            }
            List<Map<String,Object>> rows = new ArrayList<Map<String,Object>>();
            for (Count count : counts) {
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("时间", count.getUpdateTime());
                map.put("生产数量", count.getNum());
                rows.add(map);
            }
            countsJson.setRows(rows);
            countsJsons.add(countsJson);
        }
        result.add(countsJsons);//加入图表数据
        result.add(flag);
        return result;
    }


    /**
     * 导航至history页面
     * @return
     */
    @RequestMapping("/history")
    public String history(){
        return "history";
    }


    /**
     * 根据日期时间范围查找所有设备的产量数据
     * @param pre 开始时间
     * @param suf 结束时间
     * @return 产量数据
     */
    @RequestMapping("/historyCounts")
    @ResponseBody
    public List<Object> historyCounts(String pre,String suf){
        List<Object> result = new ArrayList<Object>();//创建空的结果集
        List<CountsJson> countsJsons = new ArrayList<CountsJson>();//创建一个总的表数据
        List<String> cNames = countService.queryDevices();//查询所有产品名称
        List<Boolean> flag = new ArrayList<Boolean>();//如果数据为空，设置标记
        String [] title = new String[]{"时间", "生产数量"};
        for (String cName : cNames) {//循环产品名称
            CountsJson countsJson = new CountsJson();//新建一个单位产品表格
            countsJson.setColumns(title);//设置标题
            List<Count> counts = countService.getHistoryCounts(cName,pre, suf);//根据产品名称查询数据
            if (counts.size() > 0) {
                flag.add(false);
            }else{
                flag.add(true);
            }
            List<Map<String,Object>> rows = new ArrayList<Map<String,Object>>();
            for (Count count : counts) {
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("时间", count.getUpdateTime());
                map.put("生产数量", count.getNum());
                rows.add(map);
            }
            countsJson.setRows(rows);
            countsJsons.add(countsJson);
        }
        result.add(countsJsons);//加入图表数据
        result.add(flag);
        return result;
    }
}
