package exercise.gameongradle.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_info")
@NamedQuery(
        name = "User.findByGender",
        query = "SELECT u from User u where u.gender = :string"
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name", length = 500)
    private String firstName;

    @Column(name = "last_name", length = 500)
    private String lastName;

    @Column(name = "username", length = 500)
    private String username;

    @Column(name = "user_password", length = 1000)
    @JsonIgnore
    private String password;

    @Column(name = "email", length = 1000)
    private String email;

    @Column(name = "telephone_num")
    private String tel;

    @Column(name = "address", length = 2000)
    private String address;

    @Column(name = "dob")
    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "profile_img", length = 5000)
    private String profileImg;

    @Column(name = "balance", columnDefinition = "Decimal(20,2)")
    private Double balance;

    @Column(name = "registered_date")
    private LocalDate registeredDate;

    @Column(name = "active")
    private boolean active;

    @OneToMany(orphanRemoval = true, mappedBy = "users", cascade = CascadeType.PERSIST)
    private List<UserRoleAssignment> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<Receipt> receiptList = new ArrayList<>();

    @OneToMany(orphanRemoval = true, mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(orphanRemoval = true, mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<Rating> ratingList = new ArrayList<>();
}
