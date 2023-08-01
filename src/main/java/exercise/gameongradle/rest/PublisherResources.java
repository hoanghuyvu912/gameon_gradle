package exercise.gameongradle.rest;

import com.nonIt.GameOn.service.PublisherService;
import com.nonIt.GameOn.service.createdto.PublisherDto;
import com.nonIt.GameOn.service.restdto.PublisherRestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/publishers")
public class PublisherResources {
    private final PublisherService publisherService;

    @GetMapping
    public ResponseEntity<List<PublisherRestDto>> getAllPublisher() {
        return ResponseEntity.ok(publisherService.getAll());
    }

    @GetMapping(value = "/name-contain")
    public ResponseEntity<List<PublisherRestDto>> findByNameContaining(@RequestParam("name") String name) {
        return ResponseEntity.ok(publisherService.findByNameContaining("%" + name + "%"));
    }

    @GetMapping(value = "/established-after")
    public ResponseEntity<List<PublisherRestDto>> findByEstablishedDateAfter(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(publisherService.findByEstablishedDateAfter(date));
    }

    @GetMapping(value = "/established-before")
    public ResponseEntity<List<PublisherRestDto>> findByEstablishedDateBefore(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(publisherService.findByEstablishedDateBefore(date));
    }

    @GetMapping(value = "/{publisherId}")
    public ResponseEntity<PublisherRestDto> getPublisherById(@PathVariable("publisherId") Integer publisherId) {
        return ResponseEntity.ok(publisherService.findById(publisherId));
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PublisherRestDto> createPublisher(@RequestBody PublisherDto PublisherDto) {
        return ResponseEntity.ok(publisherService.createPublisher(PublisherDto));
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{publisherId}")
    public ResponseEntity<PublisherRestDto> updatePublisherById(@PathVariable("publisherId") Integer publisherId, @RequestBody PublisherDto PublisherDto) {
        return ResponseEntity.ok(publisherService.updatePublisher(publisherId, PublisherDto));
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{publisherId}")
    public ResponseEntity<Void> deletePublisherById(@PathVariable("publisherId") Integer publisherId) {
        publisherService.deletePublisher(publisherId);
        return ResponseEntity.noContent().build();
    }
}
