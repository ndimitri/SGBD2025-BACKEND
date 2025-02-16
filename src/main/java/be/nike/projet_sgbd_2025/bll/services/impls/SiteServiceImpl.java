package be.nike.projet_sgbd_2025.bll.services.impls;


import be.nike.projet_sgbd_2025.bll.services.SiteService;
import be.nike.projet_sgbd_2025.dal.repositories.SiteRepository;
import be.nike.projet_sgbd_2025.dl.entities.Site;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SiteServiceImpl implements SiteService {
  private final SiteRepository siteRepository;


  @Override
  public List<Site> findAll() {
    return siteRepository.findAll();
  }

  @Override
  public Site findById(UUID id) {
    return siteRepository.findById(id).orElseThrow();
  }
}
