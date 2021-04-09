package jee.memory.service;

import jee.memory.model.ThinkTank;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ThinkTankServiceTests {
    @Autowired
    private ThinkTankService thinkTankService;

    private final String designTitle = "Design Thinking";
    private final String designDesc = "Design Thinking to add value to new ideas";

    private final String lateralTitle = "Lateral Thinking";
    private final String lateralDesc = "Lateral Thinking to multiview to new ideas";

    @Test
    public void testThinkingCreat () {
        // prepare
        ThinkTank thinkTank = new ThinkTank();
        thinkTank.setTitle(designTitle);
        thinkTank.setDescription(designDesc);

        // call the method we want verify
        thinkTankService.create (thinkTank);
        // verify the addition
        ThinkTank addedThinkTank = thinkTankService.findById(thinkTank.getThinkId());
        Assertions.assertNotNull (addedThinkTank);
        Assertions.assertEquals (addedThinkTank.getTitle(), designTitle);
        Assertions.assertEquals (addedThinkTank.getDescription(), designDesc);
        log.info ("<><><><> We have tested on create method for our service ");

        // clean up
        thinkTank = thinkTankService.findByTitle(designTitle);
        thinkTankService.deleteById (thinkTank.getThinkId());
    }

    @Test
    public void testThinkingUpdate () {
        // test prepare
        ThinkTank thinkTank = new ThinkTank();
        thinkTank.setTitle(designTitle);
        thinkTank.setDescription(designDesc);
        thinkTankService.create (thinkTank);
        ThinkTank existingThinkTank = thinkTankService.findById(thinkTank.getThinkId());
        existingThinkTank.setTitle(lateralTitle);
        existingThinkTank.setDescription(lateralDesc);

        // call the method we will verify
        ThinkTank updatedThinkTank = thinkTankService.updateById(existingThinkTank);
        Assertions.assertNotNull (updatedThinkTank);
        Assertions.assertEquals (updatedThinkTank.getTitle(), lateralTitle);
        Assertions.assertEquals (updatedThinkTank.getDescription(), lateralDesc);
        log.info ("<><><><> We have tested on update method for our service ");

        // clean up
        thinkTank = thinkTankService.findByTitle(lateralTitle);
        thinkTankService.deleteById(thinkTank.getThinkId());
    }

    @Test
    public void testThinkingDeleteById() {
        // prepare
        ThinkTank thinkTank = new ThinkTank();
        thinkTank.setTitle(designTitle);
        thinkTank.setDescription(designDesc);
        thinkTankService.create (thinkTank);
        thinkTank = thinkTankService.findByTitle("Design Thinking");

        // test
        if (null != thinkTank) {
            thinkTankService.deleteById(thinkTank.getThinkId());
        }
        assertThat(thinkTankService.findByTitle("Design Thinking") == null);

        // clean up - nothing to clean up for delete :)
    }
}
