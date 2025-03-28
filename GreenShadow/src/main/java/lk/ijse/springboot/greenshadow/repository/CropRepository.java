package lk.ijse.springboot.greenshadow.repository;

import lk.ijse.springboot.greenshadow.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropRepository extends JpaRepository<Crop, String> {
}
