package vn.tungnt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.tungnt.jpa.domain.Film;
import vn.tungnt.service.IFilmService;
import vn.tungnt.service.common.AbstractService;
import vn.tungnt.service.dao.IFilmDao;
import vn.tungnt.service.dao.common.IOperations;

@Service
public class FilmService extends AbstractService<Film> implements IFilmService {

    @Autowired
    private IFilmDao dao;

    public FilmService() {
        super();
    }

    // API

    @Override
    protected IOperations<Film> getDao() {
        return dao;
    }

}