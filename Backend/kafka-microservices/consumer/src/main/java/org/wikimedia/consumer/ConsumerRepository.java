package org.wikimedia.consumer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumerRepository extends JpaRepository<WikimediaEntity, Long> {
}
