package lk.ijse.springboot.greenshadow.repository;

import lk.ijse.springboot.greenshadow.entity.MonitoringLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitoringLogRepository extends JpaRepository<MonitoringLog, String> {
}
