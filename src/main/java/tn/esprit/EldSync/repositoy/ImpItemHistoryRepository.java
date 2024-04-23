package tn.esprit.EldSync.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.EldSync.model.ItemHistory;


public interface ImpItemHistoryRepository extends JpaRepository<ItemHistory, Integer> {

}
