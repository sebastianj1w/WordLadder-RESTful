package wordladder;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Set;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static wordladder.WordLadderController.loadDict;


@RunWith(SpringRunner.class)

//SpringBootTest 是springboot 用于测试的注解，可指定启动类或者测试环境等，这里直接默认。

//因为是mock测试，在实际开发过程中，可指定其测试启动时为随机端口，避免了不必要的端口冲突。

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

//测试单一接口时 ，也可利用注解@WebMvcTest 进行单一测试

//@WebMvcTest(DemoController.class)

public class WordLadderControllerTest {
    //使用 WebMvcTest 时使用
    //@autowired mockMvc 是可自动注入的。
    //当直接使用SpringBootTest 会提示 注入失败  这里直接示例利用 MockMvcBuilders工具创建
    //@Autowired
    MockMvc mockMvc;
    Set<String> dict = loadDict("static/dictionary.txt");

    @Autowired
    WebApplicationContext wc;

    @Before
    public void beforeSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();
    }

    @Test
    public void testReturnedAns() throws Exception {
        String start = "this";
        String end = "lade";

        MvcResult result = this.mockMvc.perform(get("/word_ladder").param("start", start).param("end", end)).andDo(print()).andExpect(status().isOk())

                .andReturn();

        WordLadder wl = new WordLadder(start, end, dict);

        String str = result.getResponse().getContentAsString();
        JSONObject jsonObject = JSONObject.fromObject(str);
        WordLadder wl_res = (WordLadder) JSONObject.toBean(jsonObject, WordLadder.class);

        //断言 是否和预期相等
        Assert.assertEquals(wl.getAns(), wl_res.getAns());
    }

}