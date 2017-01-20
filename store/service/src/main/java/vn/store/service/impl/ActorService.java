package vn.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.store.jpa.domain.Actor;
import vn.store.service.IActorService;
import vn.store.service.common.AbstractService;
import vn.store.service.dao.IActorDao;
import vn.store.service.dao.common.IOperations;

@Service
public class ActorService extends AbstractService<Actor> implements IActorService {

    @Autowired
    private IActorDao dao;

    public ActorService() {
        super();
    }

    // API

    @Override
    protected IOperations<Actor> getDao() {
        return dao;
    }

}
