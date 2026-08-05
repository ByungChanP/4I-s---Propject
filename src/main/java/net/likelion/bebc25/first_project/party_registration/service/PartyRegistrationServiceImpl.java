package net.likelion.bebc25.first_project.party_registration.service;

import net.likelion.bebc25.first_project.party_registration.dto.PartyRegistrationDto;
import net.likelion.bebc25.first_project.party_registration.repository.PartyRegistrationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartyRegistrationServiceImpl implements PartyRegistrationService {

    private final PartyRegistrationRepository partyRegistrationRepository;

    public PartyRegistrationServiceImpl(PartyRegistrationRepository partyRegistrationRepository) {
        this.partyRegistrationRepository = partyRegistrationRepository;
    }

    @Override
    public void register(PartyRegistrationDto registration) {
        partyRegistrationRepository.save(registration);
    }

    @Override
    public void delete(int registrationId) {
        partyRegistrationRepository.remove(registrationId);
    }

    @Override
    public List<PartyRegistrationDto> getRegistrations(int postId) {
        return partyRegistrationRepository.findRegistrations(postId);
    }
}
