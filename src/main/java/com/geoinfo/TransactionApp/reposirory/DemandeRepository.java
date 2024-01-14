package com.geoinfo.TransactionApp.reposirory;

import com.geoinfo.TransactionApp.entities.Annonce;
import com.geoinfo.TransactionApp.entities.Demande;
import com.geoinfo.TransactionApp.entities.User_role.Citoyen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DemandeRepository extends JpaRepository<Demande,Long> {

    @Query("SELECT d FROM Demande d LEFT JOIN FETCH d.annonce WHERE d.demandeur = :citoyen")
    List<Demande> findDemandesByDemandeurWithAnnonce(@Param("citoyen") Citoyen citoyen);

    @Query("SELECT COUNT(d) FROM Demande d")
    int countTotalDemandes();


    @Query("SELECT d FROM Demande d")
    List<Demande> findAlldemande();

    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM Demande d WHERE d.annonce.id = :annonceId AND d.demandeur.id = :demandeurId")
    boolean existsByAnnonceIdAndDemandeurId(Long annonceId, Long demandeurId);

    List<Demande> findDemandesByAnnonce(Annonce annonce);

}
