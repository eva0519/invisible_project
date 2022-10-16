package com.sparta.invisible_project.repository;

import com.sparta.invisible_project.model.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberRepository  extends JpaRepository<Members, Long> {
    Optional<Members> findByMembers_name(String members_name);
    boolean existsByMembers_name(String members_name);
}