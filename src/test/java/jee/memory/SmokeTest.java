package jee.memory;

import jee.memory.controller.HomeController;
import jee.memory.controller.ThinkThankController;
import jee.memory.model.ThinkTank;
import jee.memory.service.ThinkTankService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
@SpringBootTest
public class SmokeTest {
    @Autowired
    private HomeController homeController;
    @Test
    public void TestHomeControllerLoad () throws Exception {
        assertThat (homeController).isNotNull();
        log.info ("<><><><><><> We have smoke tested controller Load");
    }

    @Autowired
    private ThinkThankController thinkThankController;
    @Test
    public void TestThinkTankControllerLoad () throws Exception {
        assertThat(thinkThankController).isNotNull();
        log.info ("<><><><><><> We have smoke tested our think thank controller");
    }

    @Autowired
    private ThinkTankService thinkTankService;
    @Test
    public void TestServiceLoad () throws Exception {
        assertThat(thinkTankService).isNotNull();
        log.info ("<><><><><><> We have smoke tested service Load");
    }

    @Autowired
    ThinkTank thinkTank;
    @Test
    public void TestDataLoad () throws Exception {
        assertThat(thinkTank).isNotNull();
        log.info ("<><><><><><> We have smoke tested data object Load");
    }

}
