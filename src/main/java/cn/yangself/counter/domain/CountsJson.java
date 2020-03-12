package cn.yangself.counter.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CountsJson {
    //{
    //    columns: ['时间', '生产数量'],
    //    rows: [{
    //    '时间': '1234213',
    //            '生产数量': 234
    //}],
    //}
    private String[] columns;
    private List<Map<String,Object>> rows;
}
