package lk.ijse.springboot.greenshadow.repository;

import lk.ijse.springboot.greenshadow.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, String> {
}
