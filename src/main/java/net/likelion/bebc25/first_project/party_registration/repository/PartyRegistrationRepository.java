package net.likelion.bebc25.first_project.party_registration.repository;

import net.likelion.bebc25.first_project.party_registration.dto.PartyRegistrationDto;

import java.util.List;

public interface PartyRegistrationRepository {

    void save(PartyRegistrationDto registration);

    void remove(int id);

    List<PartyRegistrationDto> findRegistrations(int postId);
}
