package vn.tungnt.service.dao.impl;

import org.springframework.stereotype.Repository;

import vn.tungnt.jpa.domain.Actor;
import vn.tungnt.service.dao.IActorDao;
import vn.tungnt.service.dao.common.AbstractHibernateDao;

@Repository
public class ActorDao extends AbstractHibernateDao<Actor> implements IActorDao {

    public ActorDao() {
        super();

        setClazz(Actor.class);
    }

    // API

}
