package jee.memory.controller;

import jee.memory.common.ThinkTankInitialization;
import jee.memory.model.ThinkTank;
import jee.memory.service.ThinkTankService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.springframework.ui.Model;

import java.util.List;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ThinkTankControllerTests {

    private String designTitle = "Design Thinking";
    private String designDesc = "Design Thinking to add value to new ideas";

    @LocalServerPort
    private int port;

    @Autowired
    private ThinkTankService thinkTankService;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ThinkThankController thinkThankController;

    @Before
    public void cleanup () {
        thinkTankService.deleteAll();
    }

    @Test
    public void TestThinkTanksPath () throws Exception {
        log.info ("<><><><><><><> homeControllerTest");
        assertThat (this.testRestTemplate.getForObject("http://localhost:" + port + "/thinktank", String.class))
                .contains ("Description");
        log.info ("<><><><><><><> we have tested basics for our Think tank Controller");
    }

    @Test
    public void TestThinkTanksHomeContents () throws Exception {
        // Prepare
        ThinkTank thinkTank = new ThinkTank();
        thinkTank.setDescription(designDesc);
        thinkTank.setTitle(designTitle);
        thinkTankService.save(thinkTank);

        //Test
        assertThat (this.testRestTemplate.getForObject("http://localhost:" + port + "/thinktank", String.class))
                .contains (designTitle);
        log.info ("<><><><><><><> we have tested thinktanks page content");
    }

    @Test
    public void TestThinkTanksContent () throws Exception {
        ThinkTank thinkTank = new ThinkTank();
        thinkTank.setDescription(designDesc);
        thinkTank.setTitle(designTitle);
        thinkTankService.create(thinkTank);
        assertThat (this.testRestTemplate.getForObject("http://localhost:" + port + "/thinktank", String.class))
                .contains (designTitle);
        log.info ("<><><><><><><> we have tested thinktanks page content");
    }

    // by now we know that it is pretty much repeating process. thus in real life we write a factory class to generate those tests.
    @Mock
    Model model;
    @Test
    public void TestUpdateThinkTank () throws Exception {
        // prepare
        designDesc = "post update test before " + designDesc;
        designTitle = "post update test before " + designTitle;
        ThinkTank thinkTank = new ThinkTank();
        thinkTank.setDescription(designDesc);
        thinkTank.setTitle(designTitle);
        thinkTankService.save(thinkTank);

        designDesc = designDesc + " after";
        designTitle = designTitle + " after";
        ThinkTank thinkTankU = thinkTankService.findByTitle (thinkTank.getTitle());
        thinkTankU.setDescription(designDesc);
        thinkTankU.setTitle(designTitle);
        thinkThankController.updateThinkTank(thinkTankU.getThinkId(), thinkTankU, model);

        assertThat (this.testRestTemplate.getForObject("http://localhost:" + port + "/thinktank", String.class))
                .contains (designTitle);
        log.info ("<><><><><><><> we have tested thinktanks post for new thinktank");
    }

    @Test
    public void TestPostThinkTanks () throws Exception {
        designDesc = "post add test " + designDesc;
        designTitle = "post add test " + designTitle;
        ThinkTank thinkTank = new ThinkTank();
        thinkTank.setDescription(designDesc);
        thinkTank.setTitle(designTitle);
        thinkThankController.addNewThank(thinkTank);
        assertThat (this.testRestTemplate.getForObject("http://localhost:" + port + "/thinktank", String.class))
                .contains (designTitle);
        log.info ("<><><><><><><> we have tested thinktanks post for new thinktank");
    }
}
