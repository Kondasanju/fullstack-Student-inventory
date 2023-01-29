package com.codewitharjun.fullstackbackend.repository;

import com.codewitharjun.fullstackbackend.model.Register;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepo extends JpaRepository<Register,Long> {

}
