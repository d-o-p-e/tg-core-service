package com.tg.coreservice.service;

import com.tg.coreservice.domain.Campaign;
import com.tg.coreservice.dto.CampaignResponseDto;
import com.tg.coreservice.repository.CampaignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignRepository;

    public List<CampaignResponseDto> getCampaign() {
        List<Campaign> campaignList = campaignRepository.findAll();
        return CampaignResponseDto.of(campaignList);
    }
}
