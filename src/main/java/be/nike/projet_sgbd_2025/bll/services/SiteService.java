package be.nike.projet_sgbd_2025.bll.services;

import be.nike.projet_sgbd_2025.dl.entities.Site;
import java.util.List;
import java.util.UUID;

public interface SiteService {

  List<Site> findAll();

  Site findById(UUID id);

}
