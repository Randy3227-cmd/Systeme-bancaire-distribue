import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class OperationCompteCourant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double montant;

    @Column(nullable = false)
    private LocalDateTime dateInsertion;

    @ManyToOne
    @JoinColumn(name = "type_operation_id", nullable = false)
    private TypeOperationCompteCourant typeOperation;

    @ManyToOne
    @JoinColumn(name = "compte_courant_id", nullable = false)
    private CompteCourant compteCourant;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
}
