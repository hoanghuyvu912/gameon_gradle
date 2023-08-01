package exercise.gameongradle.entity;

//import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "receipt_details")
public class ReceiptDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "receipt_id")
    private Receipt receipt;

//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "game_id")
//    private Game game;

    @OneToOne
    @JoinColumn(name = "game_code_id")
    private GameCode gameCode;

    @Column(name="game_price", columnDefinition="Decimal(4,2)")
    private Double gamePrice;

}
