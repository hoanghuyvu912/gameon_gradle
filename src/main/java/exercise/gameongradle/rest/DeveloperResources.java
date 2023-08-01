package exercise.gameongradle.rest;

import com.nonIt.GameOn.exception.GameOnException;
import com.nonIt.GameOn.service.DeveloperService;
import com.nonIt.GameOn.service.createdto.DeveloperDto;
import com.nonIt.GameOn.service.restdto.DeveloperRestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/developers")
public class DeveloperResources {
    private final DeveloperService developerService;

//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping()
    public ResponseEntity<List<DeveloperRestDto>> getAllDeveloper(@RequestHeader("Authorization") String authorization) {
        return ResponseEntity.ok(developerService.getAll());
    }



//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping(value = "/name-contain")
    public ResponseEntity<List<DeveloperRestDto>> findByNameContaining(@RequestParam("name") String name) {
        if (name == null || name.trim().isBlank() || name.isEmpty()) {
            throw GameOnException.badRequest("UsernameNotFound", "Username search query is missing.");
        }
        return ResponseEntity.ok(developerService.findByNameContaining("%" + name + "%"));
    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping(value = "/established-after")
    public ResponseEntity<List<DeveloperRestDto>> findByEstablishedDateAfter(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(developerService.findByEstablishedDateAfter(date));
    }


//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping(value = "/established-before")
    public ResponseEntity<List<DeveloperRestDto>> findByEstablishedDateBefore(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(developerService.findByEstablishedDateBefore(date));
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/{devId}")
    public ResponseEntity<DeveloperRestDto> getDeveloperById(@PathVariable("devId") String devId) {
        boolean valid = true;
        int devIdInt = 0;
        if (devId == null || devId.trim().isBlank() || devId.isEmpty()) {
            valid = false;
        } else {
            try {
                devIdInt = Integer.parseInt(devId);
            } catch (Exception e) {
                valid = false;
                throw GameOnException.badRequest("MissingDeveloperId", "Developer Id search query not found.");
            }
        }
        if (valid) {
            return ResponseEntity.ok(developerService.findById(devIdInt));
        } else {
            throw GameOnException.badRequest("InvalidDeveloperId", "Invalid developer ID!");
        }
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<DeveloperRestDto> createDeveloper(@RequestBody DeveloperDto developerDto) {
        return ResponseEntity.ok(developerService.createDeveloper(developerDto));
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{devId}")
    public ResponseEntity<DeveloperRestDto> updateDeveloperById(@PathVariable("devId") String devId, @RequestBody DeveloperDto developerDto) {
        boolean valid = true;
        int devIdInt = 0;
        if (devId == null || devId.trim().isBlank() || devId.isEmpty()) {
            valid = false;
        } else {
            try {
                devIdInt = Integer.parseInt(devId);
            } catch (Exception e) {
                valid = false;
                throw GameOnException.badRequest("MissingDeveloperId", "Developer Id search query not found.");
            }
        }
        if (valid) {
            return ResponseEntity.ok(developerService.updateDeveloper(devIdInt, developerDto));
        } else {
            throw GameOnException.badRequest("InvalidDeveloperId", "Invalid developer ID!");
        }
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{devId}")
    public ResponseEntity<Void> deleteDeveloperById(@PathVariable("devId") String devId) {
        boolean valid = true;
        int devIdInt = 0;
        if (devId == null || devId.trim().isBlank() || devId.isEmpty()) {
            valid = false;
        } else {
            try {
                devIdInt = Integer.parseInt(devId);
            } catch (Exception e) {
                valid = false;
                throw GameOnException.badRequest("MissingDeveloperId", "Developer Id search query not found.");
            }
        }
        if (valid) {
            developerService.deleteDeveloper(devIdInt);
            return ResponseEntity.noContent().build();
        } else {
            throw GameOnException.badRequest("InvalidDeveloperId", "Invalid developer ID!");
        }
    }
}
