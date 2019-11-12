package br.senac.tads.dsw.usuariocrud.repositories;

import br.senac.tads.dsw.usuariocrud.Papel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PapelRepository extends JpaRepository<Papel, Long> {
}
