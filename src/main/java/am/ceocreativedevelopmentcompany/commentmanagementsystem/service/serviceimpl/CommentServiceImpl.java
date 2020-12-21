package am.ceocreativedevelopmentcompany.commentmanagementsystem.service.serviceimpl;

import am.ceocreativedevelopmentcompany.commentmanagementsystem.entity.CommentEntity;
import am.ceocreativedevelopmentcompany.commentmanagementsystem.entity.NotificationEntity;
import am.ceocreativedevelopmentcompany.commentmanagementsystem.exceptions.BadRequestException;
import am.ceocreativedevelopmentcompany.commentmanagementsystem.repository.CommentRepository;
import am.ceocreativedevelopmentcompany.commentmanagementsystem.service.CommentService;
import am.ceocreativedevelopmentcompany.commentmanagementsystem.service.NotificationService;
import am.ceocreativedevelopmentcompany.commentmanagementsystem.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private NotificationService notificationService;

    @Override
    public CommentEntity getById(long id) {
        CommentEntity commentEntity = commentRepository.findById(id).orElseThrow(
                () -> new BadRequestException("Comment with id " + id + " does not exist")
        );
        return commentEntity;
    }

    @Override
    public CommentEntity save(CommentEntity commentEntity) {
        commentEntity.setTime(LocalDateTime.now());
        commentEntity = commentRepository.save(commentEntity);
        if (commentEntity != null) {
            NotificationEntity notificationEntity = new NotificationEntity();
            notificationEntity.setComment(commentEntity);
            notificationEntity.setDelivered(false);
            notificationEntity = notificationService.save(notificationEntity);
            commentEntity.setNotification(notificationEntity);
            return commentEntity;
        }
        return null;
    }

    @Override
    public void delete(long id) {
        CommentEntity commentEntity = commentRepository.findById(id).orElseThrow(
                () -> new BadRequestException("Comment with id " + id + " was deleted. ")
        );
        commentRepository.delete(commentEntity);
    }

    @Override
    public List<CommentEntity> get(int page, int perPage) {
        validatePageNumberAndSize(page, perPage);
        Page<CommentEntity> commentEntities = this.commentRepository
                .findAll(PageRequest.of(page, perPage, Sort.Direction.ASC, "commentId"));
        return commentEntities.stream().collect(Collectors.toCollection(LinkedList::new));
    }

    private void validatePageNumberAndSize(int page, int size) {
        if (page < 0 || size < 0) {
            throw new BadRequestException("Page number can not be less then 0 ");
        }
        if (size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }
    }
}
