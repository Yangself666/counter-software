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

    @RequestMapping("/addData")
    @ResponseBody
    public String addData(String cName,Integer num){
        String updateTime = DateUtil.getDate();
        countService.addCount(cName, num, updateTime);
        System.out.println("update success!" + cName);
        return "success";
    }

    @RequestMapping("/devices")
    @ResponseBody
    public List<String> devices(){
        return countService.queryDevices();
    }
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

    @RequestMapping("/history")
    public String history(){
        return "history";
    }

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


    //{
    //    columns: ['时间', '生产数量'],
    //    rows: [{
    //    '时间': '1234213',
    //            '生产数量': 234
    //}],
    //}
}
