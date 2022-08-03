package br.com.gustavo.junitlearn.repositories;

import br.com.gustavo.junitlearn.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
