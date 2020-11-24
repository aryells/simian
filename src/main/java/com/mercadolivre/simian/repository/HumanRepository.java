package com.mercadolivre.simian.repository;

import com.mercadolivre.simian.domain.Human;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;

import java.util.Optional;

public interface HumanRepository extends DatastoreRepository<Human, Long> {

    Optional<Human> findByMd5(String md5);

    long countByIsSimian(boolean isSimian);
}
