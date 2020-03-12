package cn.yangself.counter.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Count implements Serializable {
    private String cName;
    private Integer num;
    private String updateTime;
}
