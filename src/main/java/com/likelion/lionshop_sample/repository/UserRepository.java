package com.likelion.lionshop_sample.repository;

import com.likelion.lionshop_sample.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
