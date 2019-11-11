package ru.java.mentor.demoproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.java.mentor.demoproject.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

   // @Query("select role from Role role where role.role=:role")
    Role findByRole(@Param("role") String role);

    //@Query("select b from Role b where b.id=:id")
    Role findById(@Param("id") long id);
}
