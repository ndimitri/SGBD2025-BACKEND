package be.nike.projet_sgbd_2025.dal.repositories;

import be.nike.projet_sgbd_2025.dl.entities.Site;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteRepository extends JpaRepository<Site, UUID> {

}
