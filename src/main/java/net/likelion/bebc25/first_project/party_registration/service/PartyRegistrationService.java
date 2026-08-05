package net.likelion.bebc25.first_project.party_registration.service;

import net.likelion.bebc25.first_project.party_registration.dto.PartyRegistrationDto;

import java.util.List;

public interface PartyRegistrationService {
    // 파티참가 신청정보 등록
    void register(PartyRegistrationDto registration);

    void delete(int registrationId, int postId);

    List<PartyRegistrationDto> getRegistrations(int postId);
}
