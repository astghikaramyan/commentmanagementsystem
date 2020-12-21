package am.ceocreativedevelopmentcompany.commentmanagementsystem.mapper;

import am.ceocreativedevelopmentcompany.commentmanagementsystem.dao.CommentDao;
import am.ceocreativedevelopmentcompany.commentmanagementsystem.entity.CommentEntity;
import org.springframework.stereotype.Component;

@Component
@org.mapstruct.Mapper(componentModel = "spring")
public interface CommentMapper extends Mapper<CommentEntity, CommentDao> {
}
