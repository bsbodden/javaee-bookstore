package com.integrallis.modernjee.bookstore.dao;

import java.util.List;

public interface DataAccessObject<PrimaryKeyClass, Entity> {
    void persist(Entity entity);
    void remove(Entity entity);
    Entity findById(PrimaryKeyClass id);
    List<Entity> findAll();
}
