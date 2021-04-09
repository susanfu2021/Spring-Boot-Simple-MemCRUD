package jee.memory.controller;

import jee.memory.model.ThinkTank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ThinkTankControllerTest {
    private String designTitle = "Design Thinking";
    private String designDesc = "Design Thinking to add value to new ideas";
    @Autowired
    private TestRestTemplate testRestTemplate;
    @MockBean
    MockMvc mockMvc;
    @Test
    public void TestCreateThinkTank () throws Exception {
        ThinkTank thinkTank = new ThinkTank();
        thinkTank.setTitle(designDesc);
        thinkTank.setTitle(designTitle);
        mockMvc.perform(post("http://localhost:8080/thinktank/add")
                .param("action","save")
                .flashAttr("thinktank", thinkTank));
//        assertThat (this.testRestTemplate.getForObject("http://localhost:" + port + "/thinktank", String.class))
//                .contains (designTitle);
    }
}
