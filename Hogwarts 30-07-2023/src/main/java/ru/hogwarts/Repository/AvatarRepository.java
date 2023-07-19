package ru.hogwarts.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.model.Avatar;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar,Long> {
    Optional<Avatar> findByStudentId(long id);

}
