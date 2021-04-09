package jee.memory.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ThinkTank {
    private long thinkId;
    private String title;
    private String description;
}
