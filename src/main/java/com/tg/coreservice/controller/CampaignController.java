package com.tg.coreservice.controller;

import com.tg.coreservice.auth.Auth;
import com.tg.coreservice.auth.UserContext;
import com.tg.coreservice.dto.CampaignResponseDto;
import com.tg.coreservice.service.CampaignService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "CAMPAIGN", description = "캠페인 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/campaign")
public class CampaignController {

    private final CampaignService campaignService;

    @Operation(summary = "캠페인 조회", description = "진행중인 캠페인을 목록을 조회합니다.")
    @GetMapping
    public ResponseEntity<List<CampaignResponseDto>> getCampaign() {
        return ResponseEntity.ok().body(campaignService.getCampaign());
    }

    @Operation(summary = "캠페인 응모", description = "캠페인에 응모합니다.")
    @Auth
    @PostMapping("/{campaignId}")
    public ResponseEntity<Void> drawCampaign(@PathVariable Long campaignId) {
        Long userId = UserContext.getContext();
        return campaignService.drawCampaign(userId, campaignId);
    }
}
