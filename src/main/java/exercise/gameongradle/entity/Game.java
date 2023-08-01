package exercise.gameongradle.entity;

//import jakarta.persistence.*;

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
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "game_name", length = 2000)
    private String name;

    @Column(name = "thumbnail", length = 2000)
    private String thumbnail;

    @Column(name = "description", length = 3000)
    private String description;

    @Column(name = "trailer", length = 3000)
    private String trailer;

    @Column(name = "released_date")
    private LocalDate releasedDate;

    @Column(name = "system_req", length = 5000)
    private String systemReq;

    @Column(name = "price", columnDefinition = "Decimal(4,2)")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "developer_id")
    private Developer developer;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @OneToMany(orphanRemoval = true, mappedBy = "game", cascade = CascadeType.PERSIST)
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(orphanRemoval = true, mappedBy = "game", cascade = CascadeType.PERSIST)
    private List<Rating> ratingList = new ArrayList<>();

    @OneToMany(orphanRemoval = true, mappedBy = "game", cascade = CascadeType.PERSIST)
    private List<GameImage> gameImageList = new ArrayList<>();

    @OneToMany(orphanRemoval = true, mappedBy = "game", cascade = CascadeType.PERSIST)
    private List<GameSubGenre> gameSubGenreList = new ArrayList<>();

    @OneToMany(orphanRemoval = true, mappedBy = "game", cascade = CascadeType.PERSIST)
    private List<GameGenre> gameGenreList = new ArrayList<>();

    @OneToMany(mappedBy = "game", cascade = CascadeType.PERSIST)
    private List<GameCode> gameCodeList = new ArrayList<>();
}

