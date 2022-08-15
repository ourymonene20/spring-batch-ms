package sn.isi.batchms.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="UTILISATEUR")
@ToString
public class AppUser {

    @Id
    @Column(name = "UTILISATEUR_ID")
    private long id;
    @Column(name = "NOM")
    private String nom;
    @Column(name = "prenom")
    private String prenom;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PASSWORD")
    private String password;

}
