package com.company.reqres.screenplay.questions;

import com.company.reqres.mapper.UserMapper;
import com.company.reqres.model.users.UserResponseDto;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class UserCreated implements Question<UserResponseDto> {

    public static UserCreated asDto() { return new UserCreated(); }

    @Override
    public UserResponseDto answeredBy(Actor actor) {
        String json = LastResponseBody.text().answeredBy(actor);
        return UserMapper.fromJson(json);
    }
}
