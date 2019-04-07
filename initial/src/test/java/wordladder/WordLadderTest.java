package wordladder;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;
import java.util.Stack;

import static wordladder.WordLadderController.loadDict;

@RunWith(SpringRunner.class)
//SpringBootTest 是springboot 用于测试的注解，可指定启动类或者测试环境等，这里直接默认。
@SpringBootTest
public class WordLadderTest {
    private Set<String> dict = loadDict("static/dictionary.txt");

    //    @Autowired
    private String start = "this";
    private String end = "lade";

    @Test
    public void testAns() {
        WordLadder wordLadderTest = new WordLadder(start, end, dict);
        String[] ansArray = {"this","tais","tads","lads","lade"};
        Stack<String> ans = new Stack<>();
        for (int i = 0; i < ansArray.length; i++) {
            ans.push(ansArray[i]);
        }
        //断言 是否和预期一致
        Assert.assertEquals(ans, wordLadderTest.getAns());
        Assert.assertEquals(true, wordLadderTest.getFound());
    }

    @Test
    public void testErr() {
        WordLadder wordLadderTest0 = new WordLadder("this", "lade", dict);
        WordLadder wordLadderTest1 = new WordLadder("shit", "lade", dict);
        WordLadder wordLadderTest2 = new WordLadder("these", "lade", dict);
        WordLadder wordLadderTest3 = new WordLadder("this", "lades", dict);
        //断言 是否和预期一致
        Assert.assertEquals(false, wordLadderTest0.getErr());
        Assert.assertEquals(true, wordLadderTest1.getErr());
        Assert.assertEquals(true, wordLadderTest2.getErr());
        Assert.assertEquals(true, wordLadderTest3.getErr());
    }

    @Test
    public void testErrMsg() {
        WordLadder wordLadderTest0 = new WordLadder("this", "lade", dict);
        WordLadder wordLadderTest1 = new WordLadder("shit", "lade", dict);
        WordLadder wordLadderTest2 = new WordLadder("these", "lade", dict);
        WordLadder wordLadderTest3 = new WordLadder("this", "lades", dict);
        WordLadder wordLadderTest4 = new WordLadder("this", "this", dict);
        WordLadder wordLadderTest5 = new WordLadder("this", "shit", dict);
        //断言 是否和预期一致
        Assert.assertEquals("", wordLadderTest0.getErr_message());
        Assert.assertEquals("starting word not exits", wordLadderTest1.getErr_message());
        Assert.assertEquals("length of two words are not equal", wordLadderTest2.getErr_message());
        Assert.assertEquals("length of two words are not equal", wordLadderTest3.getErr_message());
        Assert.assertEquals("ending word is the same as starting word", wordLadderTest4.getErr_message());
        Assert.assertEquals("ending word not exits", wordLadderTest5.getErr_message());

    }
}