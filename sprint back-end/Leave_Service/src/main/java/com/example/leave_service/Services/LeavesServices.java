package com.example.leave_service.Services;

import com.example.leave_service.Entities.LeavesEntity;
import com.example.leave_service.Repositories.LeavesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeavesServices {

    @Autowired
    LeavesRepo leavesRepo;
    //type nomvariable
    //String x

    public LeavesEntity create (LeavesEntity leaves) {
        return leavesRepo.save(leaves);
    }
    public LeavesEntity update(LeavesEntity L){

        return leavesRepo.save(L);
    }
    public LeavesEntity details(Long id){
        return leavesRepo.findById(id).orElse(null);
    }
    public List<LeavesEntity> All(){
        return leavesRepo.findAll();
    }
    public void Delete (Long id){
        leavesRepo.deleteById(id);
    }
    public List<LeavesEntity> Allbyiduser(Long id){
        return leavesRepo.findByUserid(id);
    }

}
