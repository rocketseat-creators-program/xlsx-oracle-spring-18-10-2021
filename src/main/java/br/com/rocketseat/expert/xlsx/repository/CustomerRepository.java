package br.com.rocketseat.expert.xlsx.repository;

import br.com.rocketseat.expert.xlsx.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
