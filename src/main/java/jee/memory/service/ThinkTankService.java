package jee.memory.service;

import jee.memory.model.ThinkTank;

import java.util.List;

public interface ThinkTankService {
    public void create (ThinkTank thinkTank); //C
    public Iterable<ThinkTank> findAll (); //CRUD - R
    public ThinkTank updateById (ThinkTank thinkTankNew); //U
	public String update(ThinkTank newThinkTank);
    public void deleteAll(); //D
    public ThinkTank findById (long id); //R
    public void save (ThinkTank thinkTank); //C
    public ThinkTank findByTitle (String title); //R
    public void deleteById(long id); //D
}
