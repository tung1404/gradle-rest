package vn.store.service.dao.impl;

import org.springframework.stereotype.Repository;

import vn.store.jpa.domain.Actor;
import vn.store.service.dao.IActorDao;
import vn.store.service.dao.common.AbstractHibernateDao;

@Repository
public class ActorDao extends AbstractHibernateDao<Actor> implements IActorDao {

    public ActorDao() {
        super();

        setClazz(Actor.class);
    }

    // API

}
