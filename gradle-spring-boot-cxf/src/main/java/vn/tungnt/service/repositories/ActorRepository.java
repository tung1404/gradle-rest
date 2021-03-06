package vn.tungnt.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.tungnt.jpa.domain.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

}
