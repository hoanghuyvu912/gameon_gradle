package exercise.gameongradle.repository.GameCustomRespository.impl;

import exercise.gameongradle.entity.Developer;
import exercise.gameongradle.entity.Game;
import exercise.gameongradle.entity.Publisher;
import exercise.gameongradle.repository.GameCustomRespository.CustomGameRepository;
import exercise.gameongradle.service.customDto.GameSearchDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomGameRepositoryImpl implements CustomGameRepository {
    @Autowired
    EntityManager em;

    @Override
    public List<Game> findGamesBySystemReqAndPriceLessThan(String systemReq, Double price) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Game> cq = cb.createQuery(Game.class);

        Root<Game> game = cq.from(Game.class);
        List<Predicate> predicates = new ArrayList<>();


        if (systemReq != null) {
            predicates.add(cb.like(game.get("systemReq"), "%" + systemReq + "%"));
        }
        if (price != null) {
            predicates.add(cb.lessThanOrEqualTo(game.get("price"), price));
        }
        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<Game> findGamesByDto(GameSearchDto gameSearchDto) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Game> cq = cb.createQuery(Game.class);


        Root<Game> game = cq.from(Game.class);
//        Root<GameGenre> gameGenre = cq.from(GameGenre.class);

        Join<Game, Developer> developerJoin = game.join("developer");
        Join<Game, Publisher> publisherJoin = game.join("publisher");


        List<Predicate> predicates = new ArrayList<>();

        if (gameSearchDto.getName() != null) {
            predicates.add(cb.like(cb.upper(game.get("name")), "%" + gameSearchDto.getName().toUpperCase() + "%"));
        }

        if (gameSearchDto.getPriceLessThan() != null) {
            predicates.add(cb.lessThanOrEqualTo(game.get("price"), gameSearchDto.getPriceLessThan()));
        }

        if (gameSearchDto.getPriceGreaterThan() != null) {
            predicates.add(cb.lessThanOrEqualTo(game.get("price"), gameSearchDto.getPriceGreaterThan()));
        }

        if (gameSearchDto.getSystemReq() != null) {
            predicates.add(cb.like(cb.upper(game.get("systemReq")), "%" + gameSearchDto.getSystemReq() + "%"));
        }

        if (gameSearchDto.getReleasedDateAfter() != null) {
            predicates.add(cb.greaterThanOrEqualTo(game.get("releasedDate"), gameSearchDto.getReleasedDateAfter()));
        }

        if (gameSearchDto.getReleasedDateBefore() != null) {
            predicates.add(cb.lessThanOrEqualTo(game.get("releasedDate"), gameSearchDto.getReleasedDateBefore()));
        }

        if (gameSearchDto.getDeveloperId() != null) {
            predicates.add(cb.equal(developerJoin.get("id"), gameSearchDto.getDeveloperId()));
        }

        if (gameSearchDto.getPublisherId() != null) {
            predicates.add(cb.equal(publisherJoin.get("id"), gameSearchDto.getPublisherId()));
        }

        if (gameSearchDto.getDeveloperName() != null) {
            predicates.add(cb.like(cb.upper(developerJoin.get("name")), "%" + gameSearchDto.getDeveloperName().toUpperCase() + "%"));
        }

        if (gameSearchDto.getPublisherName() != null) {
            predicates.add(cb.like(cb.upper(publisherJoin.get("name")), "%" + gameSearchDto.getPublisherName().toUpperCase() + "%"));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();
    }
}
