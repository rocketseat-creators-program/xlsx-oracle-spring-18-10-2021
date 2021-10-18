package br.com.rocketseat.expert.xlsx.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CO_CUSTOMERS")
public class Customer {

    @Id
    @Column(name="CUSTOMER_ID")
    private Long id;

    @Column(name="EMAIL_ADDRESS", unique = true)
    private String email;

    @Column(name="FULL_NAME")
    private String fullName;

}
