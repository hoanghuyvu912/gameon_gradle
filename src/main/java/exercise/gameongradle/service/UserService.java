package exercise.gameongradle.service;

import exercise.gameongradle.entity.Gender;
import exercise.gameongradle.service.createdto.UserDto;
import exercise.gameongradle.service.restdto.UserRestDto;

import java.time.LocalDate;
import java.util.List;

public interface UserService {
    List<UserRestDto> getAll();

    UserRestDto findById(Integer userId);

    UserRestDto getAccountInfo(String authorization);

    void depositAmountIntoAccount(String authorzation, Double amount);

    UserRestDto createUser(UserDto userDto);

    UserRestDto updateUser(Integer userId, UserDto userDto);

    List<UserRestDto> findByGender(Gender gender);

    List<UserRestDto> findByFirstNameContaining(String name);

    List<UserRestDto> findByLastNameContaining(String name);

    List<UserRestDto> findByEmailContaining(String name);

    List<UserRestDto> findByTelContaining(String name);

    List<UserRestDto> findByAddressContaining(String name);

    List<UserRestDto> findByDobAfter(LocalDate date);

    List<UserRestDto> findByDobBefore(LocalDate date);

    List<UserRestDto> findByBalanceGreaterThan(Double balance);

    List<UserRestDto> findByBalanceLessThan(Double balance);

    List<UserRestDto> findByActiveTrue();

    List<UserRestDto> findByActiveFalse();

    List<UserRestDto> findByMonthOfRegisteredDate(Integer month);

    void deleteUser(Integer userId);
}
