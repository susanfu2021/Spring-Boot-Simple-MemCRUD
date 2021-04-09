package jee.memory.common;

import jee.memory.model.ThinkTank;
import jee.memory.service.ThinkTankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ThinkTankUtils {
    private final String [] thinkTitles = {"Design Thinking", "Computational Thinking", "Critical Thinking", "Visual Thking"};
    private final String [] thinkDescriptions = {"Design thinking is to offer some values to an innovative ideas",
            "Computational Thinking consists of decomposition, pattern recognition, algrithem thining and abstract thinking",
            "Critical thinking is to reason basesd on facts",
            "Visual thinking is to use your mind map to provide a solution to a problem"};

    @Autowired
    private ThinkTankService thinkTankService;

    public void init () {
        for (int i = 0; i < thinkTitles.length; i++) {
            ThinkTank thinkTank = new ThinkTank();
            thinkTank.setTitle(thinkTitles[i]);
            thinkTank.setDescription(thinkDescriptions[i]);
            thinkTankService.create(thinkTank);
        }
    }
}
