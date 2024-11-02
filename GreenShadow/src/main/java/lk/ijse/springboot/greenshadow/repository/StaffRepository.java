package lk.ijse.springboot.greenshadow.repository;

import lk.ijse.springboot.greenshadow.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, String> {
}
