package com.technology.service;

import java.util.Optional;

import com.technology.model.User;
import com.technology.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserService sut;

  @Test
  void shouldCreateUserWhenUserNotExist() {
    final String name = "any_name";
    final String password = "any_password";

    given(userRepository.getUser(name)).willReturn(Optional.empty());

    sut.addUser(name, password);

    then(userRepository)
        .should()
        .addUser(name, password);
  }

  @Test
  void shouldThrowExceptionWHenUserExists() {
    final String name = "any_name";
    final String password = "any_password";
    final User user = mock(User.class);
    given(userRepository.getUser(name)).willReturn(Optional.of(user));
    final RuntimeException actual = assertThrows(
        RuntimeException.class, () -> sut.addUser(name, password));

    assertThat(actual)
        .hasMessage("User already exists");
  }
}
