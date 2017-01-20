package vn.tungnt.service.dao.impl;

import org.springframework.stereotype.Repository;

import vn.tungnt.jpa.domain.Film;
import vn.tungnt.service.dao.IFilmDao;
import vn.tungnt.service.dao.common.AbstractHibernateDao;

@Repository
public class FilmDao extends AbstractHibernateDao<Film> implements IFilmDao {

    public FilmDao() {
        super();

        setClazz(Film.class);
    }

    // API

}
