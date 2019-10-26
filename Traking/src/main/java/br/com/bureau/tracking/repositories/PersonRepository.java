package br.com.bureau.tracking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bureau.tracking.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

	Person findByCpf(String cpf);
	
}
