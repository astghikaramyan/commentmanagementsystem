package am.ceocreativedevelopmentcompany.commentmanagementsystem.mapper;

import am.ceocreativedevelopmentcompany.commentmanagementsystem.dao.NotificationDao;
import am.ceocreativedevelopmentcompany.commentmanagementsystem.entity.NotificationEntity;
import org.springframework.stereotype.Component;

@Component
@org.mapstruct.Mapper(componentModel = "spring")
public interface NotificationMapper extends Mapper<NotificationEntity, NotificationDao> {
}
