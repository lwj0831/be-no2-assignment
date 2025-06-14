package org.kakaoTechCampus.scheduleApp.lv3.user.presentation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kakaoTechCampus.scheduleApp.lv3.common.Response;
import org.kakaoTechCampus.scheduleApp.lv3.user.application.service.UserService;
import org.kakaoTechCampus.scheduleApp.lv3.user.presentation.dto.CreateUserReqDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1.2.0/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public Response<Long> createUser(@Valid @RequestBody CreateUserReqDto dto) {
        Long id = userService.save(dto);
        return Response.ok(id, "create user success");
    }
}
