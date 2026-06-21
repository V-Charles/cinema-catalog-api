package br.com.casacultural.cinema.repository;

import br.com.casacultural.cinema.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface FilmeRepository extends JpaRepository<Filme, Integer> {

    @Query("SELECT f FROM Filme f LEFT JOIN FETCH f.analises WHERE f.id = :id")
    Optional<Filme> findByIdWithAnalises(Integer id);
}
