package exercise.gameongradle.rest;

import com.nonIt.GameOn.rest.resourcesdto.SimplifiedRatingDto;
import com.nonIt.GameOn.service.RatingService;
import com.nonIt.GameOn.service.createdto.RatingDto;
import com.nonIt.GameOn.service.restdto.RatingRestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ratings")
public class RatingResources {
    private final RatingService ratingService;

    @GetMapping
    public ResponseEntity<List<RatingRestDto>> getAllRating() {
        return ResponseEntity.ok(ratingService.getAll());
    }

    @GetMapping("/{ratingId}")
    public ResponseEntity<RatingRestDto> getRatingById(@PathVariable("ratingId") Integer ratingId) {
        return ResponseEntity.ok(ratingService.findById(ratingId));
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<SimplifiedRatingDto> createRating(@RequestBody RatingDto ratingDto) {
        return ResponseEntity.ok(ratingService.createRating(ratingDto));
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{ratingId}")
    public ResponseEntity<RatingRestDto> updateRatingById(@PathVariable("ratingId") Integer ratingId, @RequestBody RatingDto ratingDto) {
        return ResponseEntity.ok(ratingService.updateRating(ratingId, ratingDto));
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{ratingId}")
    public ResponseEntity<Void> deleteRatingById(@PathVariable("ratingId") Integer ratingId) {
        ratingService.deleteRating(ratingId);
        return ResponseEntity.noContent().build();
    }

//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<RatingRestDto>> getRatingByUserId(@PathVariable("userId") Integer userId) {
        return ResponseEntity.ok(ratingService.getByUserId(userId));
    }
//    @GetMapping("/demo")
//    public ResponseEntity<List<Rating>> getDemo(){
//        return ResponseEntity.ok(ratingService.demo());
//    }
}
