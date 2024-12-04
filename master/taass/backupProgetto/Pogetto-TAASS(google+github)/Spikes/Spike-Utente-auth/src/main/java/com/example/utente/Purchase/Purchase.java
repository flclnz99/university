package com.example.utente.Purchase;


import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "Purchase")
public class Purchase {
    @Id
    @SequenceGenerator(
            name = "Purchase_id_sequence",
            sequenceName = "Purchase_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Purchase_id_sequence"
    )
    @Column(nullable = false)
    private Long id;
    private Long nPurchase;
    private String type;
    private String status;


    public Purchase() {
    }

    public void setId(Long id) {this.id = id;}

    public void setnPurchase(Long nPurchase) {this.nPurchase = nPurchase;}

    public void setType(String type) {this.type = type;}

    public void setStatus(String status) {this.status = status;}

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", nPurchase=" + nPurchase +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

