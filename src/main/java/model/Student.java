package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String last_name;
    private String indeks_no;
    private String birth_date;
    private int listaOcen;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    Set<Ocena> ocena;

    public Student(String name, String last_name, String indeks_no, String birth_date, int listaOcen) {
        this.name = name;
        this.last_name = last_name;
        this.indeks_no = indeks_no;
        this.birth_date = birth_date;
        this.listaOcen = listaOcen;
    }
}
