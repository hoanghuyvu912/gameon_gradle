package exercise.gameongradle.rest;

import com.lowagie.text.DocumentException;
import com.nonIt.GameOn.entity.Gender;
import com.nonIt.GameOn.service.UserService;
import com.nonIt.GameOn.service.createdto.UserDto;
import com.nonIt.GameOn.service.restdto.UserRestDto;
import com.nonIt.GameOn.utils.PdfGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
//@PreAuthorize("hasRole('ADMIN')")
public class UserResources {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserRestDto>> getAllUser() {
        return ResponseEntity.ok(userService.getAll());
    }

    @PostMapping
    public ResponseEntity<UserRestDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserRestDto> getUserById(@PathVariable("userId") Integer userId) {
        return ResponseEntity.ok(userService.findById(userId));
    }

    @GetMapping(path = "/account-info")
    public ResponseEntity<UserRestDto> getAccountInfo(@RequestHeader("Authorization") String authorization)
    {
        //here I will add more code which should replace the String in the ResponseEntity.
        return ResponseEntity.ok(userService.getAccountInfo(authorization.substring(7)));
    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping(path = "/deposit")
    public ResponseEntity<Void> depositAmountIntoAccount(@RequestHeader("Authorization") String authorization, @RequestParam("amount") Double amount) {
        userService.depositAmountIntoAccount(authorization.substring(7), amount );
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{userId}")
    public ResponseEntity<UserRestDto> updateUserById(@PathVariable("userId") Integer userId, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(userId, userDto));
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("userId") Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/gender")
    public ResponseEntity<List<UserRestDto>> getByGender(@RequestParam("gender") Gender gender) {
        return ResponseEntity.ok(userService.findByGender(gender));
    }

    @GetMapping("/first-name")
    public ResponseEntity<List<UserRestDto>> getByFirstNameContaining(@RequestParam("firstName") String firstName) {
        return ResponseEntity.ok(userService.findByFirstNameContaining(firstName));
    }

    @GetMapping("/last-name")
    public ResponseEntity<List<UserRestDto>> getByLastNameContaining(@RequestParam("lastName") String lastName) {
        return ResponseEntity.ok(userService.findByLastNameContaining(lastName));
    }

    @GetMapping("/email")
    public ResponseEntity<List<UserRestDto>> getByEmailContaining(@RequestParam("email") String email) {
        return ResponseEntity.ok(userService.findByEmailContaining(email));
    }

    @GetMapping("/tel")
    public ResponseEntity<List<UserRestDto>> getByTelContaining(@RequestParam("tel") String tel) {
        return ResponseEntity.ok(userService.findByTelContaining(tel));
    }

    @GetMapping("/address")
    public ResponseEntity<List<UserRestDto>> getByAddressContaining(@RequestParam("address") String address) {
        return ResponseEntity.ok(userService.findByAddressContaining(address));
    }

    @GetMapping("/dob-after")
    public ResponseEntity<List<UserRestDto>> getByDobAfter(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(userService.findByDobAfter(date));
    }

    @GetMapping("/dob-before")
    public ResponseEntity<List<UserRestDto>> getByDobBefore(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(userService.findByDobBefore(date));
    }

    @GetMapping("/balance-greater-than")
    public ResponseEntity<List<UserRestDto>> getByBalanceGreaterThan(@RequestParam("balance") Double balance) {
        return ResponseEntity.ok(userService.findByBalanceGreaterThan(balance));
    }

    @GetMapping("/balance-less-than")
    public ResponseEntity<List<UserRestDto>> getByBalanceLessThan(@RequestParam("balance") Double balance) {
        return ResponseEntity.ok(userService.findByBalanceLessThan(balance));
    }

    @GetMapping("/active-true")
    public ResponseEntity<List<UserRestDto>> getByActiveTrue() {
        return ResponseEntity.ok(userService.findByActiveTrue());
    }

    @GetMapping("/active-false")
    public ResponseEntity<List<UserRestDto>> getByActiveFalse() {
        return ResponseEntity.ok(userService.findByActiveFalse());
    }


    @GetMapping("/export-to-pdf")
    public void generatePdfFile(HttpServletResponse response) throws DocumentException, IOException
    {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=UsersList" + currentDateTime + ".pdf";
        response.setHeader(headerkey, headervalue);
        List <UserRestDto> listofUsers = userService.getAll();
        PdfGenerator generator = new PdfGenerator();
        generator.generate(listofUsers, response);
    }
}
