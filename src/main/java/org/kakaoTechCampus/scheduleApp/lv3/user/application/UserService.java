package org.kakaoTechCampus.scheduleApp.lv3.user.application;

import lombok.RequiredArgsConstructor;
import org.kakaoTechCampus.scheduleApp.lv3.user.application.dto.CreateUserReqDto;
import org.kakaoTechCampus.scheduleApp.lv3.user.application.interfaces.UserRepository;
import org.kakaoTechCampus.scheduleApp.lv3.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long save(CreateUserReqDto dto) {
        return userRepository.save(new User(null, dto.name(), dto.email()));
    }
}
