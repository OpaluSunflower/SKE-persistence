package com.example.fotowoltaika.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DailyMeasurementsJPARepository extends JpaRepository<DailyMeasurement,Long> {
    List<DailyMeasurement> findByInstalation(Instalation instalation);
}
