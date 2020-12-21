package am.ceocreativedevelopmentcompany.commentmanagementsystem.repository;

import am.ceocreativedevelopmentcompany.commentmanagementsystem.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
}
