package ufcg.project.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import ufcg.project.entities.Campaign;
import util.PossibleState;

@Document("Campaign")
public interface CampaignRepository extends MongoRepository<Campaign, Long>{
	public Optional<Campaign> findByShortName(String shortName);
	
	@Query("{'shortName' : { $regex : ?0 }, 'status': ONGOING}")
	public List<Campaign> findActiveBySubstring(String shortName);
	
	@Query("{'shortName' : { $regex : ?0 }}")
	public List<Campaign> findByAnyBySubstring(String shortName);
}
