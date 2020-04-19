package com.mercadolivre.simian.repository;

import java.util.Optional;

import com.mercadolivre.simian.domain.Human;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;

public interface HumanRepository extends DatastoreRepository<Human, Long> {

    Optional<Human> findByMd5(String md5);
    long countByIsSimian(boolean isSimian);
}
