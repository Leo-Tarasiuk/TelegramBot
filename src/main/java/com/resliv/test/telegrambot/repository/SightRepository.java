package com.resliv.test.telegrambot.repository;

import com.resliv.test.telegrambot.entity.Sight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SightRepository extends JpaRepository<Sight, Long> {

    List<Sight> findSightByCity_Name(String name);

    Optional<Sight> findSightByName(String name);
}
