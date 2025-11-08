package com.axelor.apps.selllicenseplates2.repository;

import com.axelor.apps.selllicenseplates2.model.CarNumberLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CarNumberLotRepository extends JpaRepository<CarNumberLot, Long>, JpaSpecificationExecutor<CarNumberLot> {
    Collection<CarNumberLot> findByFullCarNumberAndRegionId(String fullCarNumber, Long regionId);
}