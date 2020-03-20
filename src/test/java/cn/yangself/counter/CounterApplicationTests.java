package cn.yangself.counter;

import cn.yangself.counter.domain.Count;
import cn.yangself.counter.service.CountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CounterApplicationTests {
    @Autowired
    private CountService countService;
    @Test
    void contextLoads() {
        List<Count> device01 = countService.getCounts("Device001");
        System.out.println(device01);
    }

}
