import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Pret {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double montant;
    private Double interet;

    private LocalDate dateOuverture;
    private LocalDate dateFermeture;

    @ManyToOne
    private EtatPret etat;

    @ManyToOne
    private Client client;
}

