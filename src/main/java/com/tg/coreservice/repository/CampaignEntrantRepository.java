package com.tg.coreservice.repository;

import com.tg.coreservice.domain.CampaignEntrant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignEntrantRepository extends JpaRepository<CampaignEntrant, Long> {
}