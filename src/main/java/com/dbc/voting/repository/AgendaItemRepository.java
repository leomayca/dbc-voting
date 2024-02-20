package com.dbc.voting.repository;

import com.dbc.voting.entity.AgendaItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaItemRepository extends JpaRepository<AgendaItem, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM AgendaItem ai WHERE ai.id = :id")
    void deleteById(@Param("id") int id);

}
