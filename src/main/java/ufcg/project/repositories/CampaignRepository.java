package ufcg.project.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

import ufcg.project.entities.Campaign;

@Document("Campaign")
public interface CampaignRepository extends MongoRepository<Campaign, Long>{
	public Optional<Campaign> findByShortName(String shortName);
}
