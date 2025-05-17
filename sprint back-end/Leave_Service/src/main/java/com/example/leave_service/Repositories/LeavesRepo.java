package com.example.leave_service.Repositories;

import com.example.leave_service.Entities.LeavesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LeavesRepo extends JpaRepository<LeavesEntity,Long> {

    @Query("SELECT P FROM LeavesEntity P where P.iduser = :id")
    public List<LeavesEntity> findByUserid(Long id);
}
