package br.com.project.mscooperativa.repository;

import br.com.project.mscooperativa.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {

    @Query("from Pauta p where p.status = 'ABERTA'")
    List<Pauta> findByStatusEqualsAberta();

}
