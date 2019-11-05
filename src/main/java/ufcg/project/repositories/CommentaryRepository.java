package ufcg.project.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import ufcg.project.entities.Commentary;

public interface CommentaryRepository extends MongoRepository<Commentary, String> {
    public Optional<Commentary> findByCampaingID(long campaignID);
}
