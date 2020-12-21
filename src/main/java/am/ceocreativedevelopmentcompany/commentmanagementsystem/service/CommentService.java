package am.ceocreativedevelopmentcompany.commentmanagementsystem.service;


import am.ceocreativedevelopmentcompany.commentmanagementsystem.entity.CommentEntity;

import java.util.List;

public interface CommentService {
    CommentEntity getById(long id);

    CommentEntity save(CommentEntity commentEntity);

    void delete(long id);

    List<CommentEntity> get(int page, int perPage);
}
