package jee.memory.common;

import jee.memory.model.ThinkTank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Slf4j
@Component
public class ThinkTankInitialization {
    @Autowired
    private ThinkTankUtils thinkTankUtils;

    @PostConstruct
    public void initialization () {
        thinkTankUtils.init();
    }
}
