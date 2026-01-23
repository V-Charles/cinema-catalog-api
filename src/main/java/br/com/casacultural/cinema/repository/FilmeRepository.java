package br.com.casacultural.cinema.repository;

import br.com.casacultural.cinema.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmeRepository extends JpaRepository<Filme, Integer> {
}
