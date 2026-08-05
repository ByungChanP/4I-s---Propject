package net.likelion.bebc25.first_project.party_registration.service;

import net.likelion.bebc25.first_project.party_registration.dto.PartyRegistrationDto;
import net.likelion.bebc25.first_project.party_registration.repository.PartyRegistrationRepository;
import net.likelion.bebc25.first_project.post.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartyRegistrationServiceImpl implements PartyRegistrationService {

    private final PartyRegistrationRepository partyRegistrationRepository;
    private final PostRepository postRepository;

    public PartyRegistrationServiceImpl(PartyRegistrationRepository partyRegistrationRepository, PostRepository postRepository) {
        this.partyRegistrationRepository = partyRegistrationRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void register(PartyRegistrationDto registration) {
        partyRegistrationRepository.save(registration);
        postRepository.updateParticipantCount(registration.getPostId(), "add");
    }

    @Override
    public void delete(int registrationId, int postId) {
        partyRegistrationRepository.remove(registrationId);
        postRepository.updateParticipantCount(postId, "remove");

    }

    @Override
    public List<PartyRegistrationDto> getRegistrations(int postId) {
        return partyRegistrationRepository.findRegistrations(postId);
    }
}
