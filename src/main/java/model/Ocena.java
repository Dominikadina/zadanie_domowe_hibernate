package model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Ocena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private LocalDateTime czasOtrzymaniaOceny;
private LocalDateTime czasPoprawyOceny;
private Double ocena;
@Enumerated(EnumType.STRING)
private Przedmiot przedmiot;

    public Ocena(LocalDateTime czasOtrzymaniaOceny, Double ocena, Przedmiot przedmiot) {
        this.czasOtrzymaniaOceny = czasOtrzymaniaOceny;
        this.ocena = ocena;
        this.przedmiot = przedmiot;
    }
    @ManyToOne()
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Student student;
}
