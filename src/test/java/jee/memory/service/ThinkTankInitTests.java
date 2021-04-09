package jee.memory.service;

import jee.memory.common.ThinkTankInitialization;
import jee.memory.model.ThinkTank;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ThinkTankInitTests {
    @Autowired
    private ThinkTankInitialization thinkTankInitialization;
    @Autowired
    private ThinkTankService thinkTankService;
    private final String [] thinkTitles = {"Design Thinking", "Computational Thinking", "Critical Thinking", "Visual Thking"};
    private final String [] thinkDescriptions = {"Design thinking is to offer some values to an innovative ideas",
            "Computational Thinking consists of decomposition, pattern recognition, algrithem thining and abstract thinking",
            "Critical thinking is to reason basesd on facts",
            "Visual thinking is to use your mind map to provide a solution to a problem"};

    @Test
    public void TestThinkingInitialization () {
        // prepare data
        thinkTankInitialization.initialization();
        Iterable<ThinkTank> thinkTanks = thinkTankService.findAll();

        // Test
        String titleMem = "";
        String descMem="";
        int n =0;
        for (ThinkTank thinkTank : thinkTanks) {
            assertThat(titleMem.equals(thinkTitles[n]));
            titleMem = thinkTank.getTitle();
            descMem = thinkTank.getDescription();
            assertThat(descMem.equals(thinkDescriptions[n]));
            n++;
        }
        log.info ("<><><><> We have tested data initialization ");
    }
}
