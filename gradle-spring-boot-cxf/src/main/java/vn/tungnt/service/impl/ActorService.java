package vn.tungnt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.tungnt.jpa.domain.Actor;
import vn.tungnt.service.IActorService;
import vn.tungnt.service.common.AbstractService;
import vn.tungnt.service.dao.IActorDao;
import vn.tungnt.service.dao.common.IOperations;

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
