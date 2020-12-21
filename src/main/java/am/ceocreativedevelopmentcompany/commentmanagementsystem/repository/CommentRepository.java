package am.ceocreativedevelopmentcompany.commentmanagementsystem.repository;

import am.ceocreativedevelopmentcompany.commentmanagementsystem.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
