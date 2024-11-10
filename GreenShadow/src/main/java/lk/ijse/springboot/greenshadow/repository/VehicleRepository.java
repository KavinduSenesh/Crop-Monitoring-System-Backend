package lk.ijse.springboot.greenshadow.repository;

import lk.ijse.springboot.greenshadow.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
}
