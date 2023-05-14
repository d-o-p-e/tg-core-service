package com.tg.coreservice.dto;

import com.tg.coreservice.domain.Campaign;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class CampaignResponseDto {
    private Long campaignId;
    private String title;
    private String imageUrl;

    public static List<CampaignResponseDto> of(List<Campaign> campaignList) {
        return campaignList.stream()
            .map(campaign -> new CampaignResponseDto(
                campaign.getId(),
                campaign.getTitle(),
                campaign.getImageUrl()))
            .collect(Collectors.toList());
    }
}
