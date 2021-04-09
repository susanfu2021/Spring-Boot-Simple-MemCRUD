package jee.memory.service;

import jee.memory.model.ThinkTank;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class ThinkTankMemory implements ThinkTankService {
    private static Map <Long, ThinkTank> thinkTanks = new ConcurrentHashMap<>();

    @Override
    public Iterable <ThinkTank> findAll() {
        return thinkTanks.values();
    }

    @Override
    public void deleteAll() {
        for (ThinkTank thinkTank: thinkTanks.values())
            thinkTanks.remove(thinkTank.getThinkId());
    }

    @Override
    public ThinkTank findById(long thinkId) {
        for (ThinkTank thinkTank: thinkTanks.values()) {
            if (thinkTank.getThinkId() == thinkId) {
                return thinkTank;
            }
        }
        return null;
    }

    @Override
    public void deleteById(long memberId) {
        for (ThinkTank m : thinkTanks.values()) {
            if (m.getThinkId() == memberId) {
                thinkTanks.remove(memberId);
                break;
            }
        }
    }

    @Override
    public ThinkTank updateById(ThinkTank newThinkTank) {
        ThinkTank update = findById(newThinkTank.getThinkId());
        update.setDescription(newThinkTank.getDescription());
        update.setTitle(newThinkTank.getTitle());
        return update;
    }

    @Override
    public String update(ThinkTank newThinkTank) {
        ThinkTank exist = findById(newThinkTank.getThinkId());
        if (null != exist) {
			exist.setDescription(newThinkTank.getDescription());
			exist.setTitle(newThinkTank.getTitle());
            log.info("Updated");
            return ("Updated");
        } else {
            create(newThinkTank);
            log.info("Update could not found so create a new one");
            return ("Update could not found so create a new one");
        }
    }
	
    @Override
    public void create(ThinkTank thinkTank) {
        thinkTank.setThinkId(nextKey());
        thinkTanks.put (thinkTank.getThinkId(), thinkTank);
    }

    @Override
    public void save(ThinkTank thinktank) {
        for (ThinkTank existingtank: thinkTanks.values()) {
            if (thinktank.getThinkId() == existingtank.getThinkId()) {
                thinkTanks.remove(existingtank.getThinkId());
                thinkTanks.put(thinktank.getThinkId(), thinktank);
            }
        }
        create(thinktank);
    }

    @Override
    public ThinkTank findByTitle(String title) {
        val thinkTanks = ThinkTankMemory.thinkTanks;
        for (ThinkTank thinkTank: thinkTanks.values()) {
            if (thinkTank.getTitle().equalsIgnoreCase(title)) {
                return thinkTank;
            }
        }
        return null;
    }

    private long nextKey() {
        long nextKey = 1000;
        nextKey = thinkTanks.values().stream().mapToLong(t->t.getThinkId()).max().orElse(nextKey);
        nextKey++;
        return nextKey;
    }
}
