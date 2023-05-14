package com.tg.coreservice.service;

import com.tg.coreservice.domain.Campaign;
import com.tg.coreservice.domain.CampaignEntrant;
import com.tg.coreservice.domain.Mileage;
import com.tg.coreservice.domain.User;
import com.tg.coreservice.dto.CampaignResponseDto;
import com.tg.coreservice.repository.CampaignEntrantRepository;
import com.tg.coreservice.repository.CampaignRepository;
import com.tg.coreservice.repository.MileageRepository;
import com.tg.coreservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignService {

    private final UserRepository userRepository;
    private final CampaignRepository campaignRepository;
    private final CampaignEntrantRepository campaignEntrantRepository;
    private final MileageRepository mileageRepository;

    public List<CampaignResponseDto> getCampaign() {
        List<Campaign> campaignList = campaignRepository.findAll();
        return CampaignResponseDto.of(campaignList);
    }

    @Transactional
    public ResponseEntity<Void> drawCampaign(Long userId, Long campaignId) {
        User user = userRepository.getReferenceById(userId);
        Campaign campaign = campaignRepository.findById(campaignId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 캠페인입니다."));

        Mileage mileage = mileageRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("옳바르지 않은 유저입니다."));
        if(mileage.getMileage() < 1){
            throw new IllegalArgumentException("마일리지가 부족합니다.");
        } else {
            mileage.decrement();
        }

        campaignEntrantRepository.save(new CampaignEntrant(campaign, user));
        return ResponseEntity.ok().build();
    }
}
