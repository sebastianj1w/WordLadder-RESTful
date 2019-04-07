package wordladder;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;


@RunWith(SpringRunner.class)
//SpringBootTest 是springboot 用于测试的注解，可指定启动类或者测试环境等，这里直接默认。
@SpringBootTest
public class WordLadderTest {

    @Autowired
    WordLadder wordLadderTest;

    public void test() {
        String msg = "this is a test";
        String result = testService.process(msg);
        //断言 是否和预期一致
        Assert.assertEquals(msg, result);
    }
}