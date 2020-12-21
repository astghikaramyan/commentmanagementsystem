package am.ceocreativedevelopmentcompany.commentmanagementsystem.controller;

import am.ceocreativedevelopmentcompany.commentmanagementsystem.dao.NotificationDao;
import am.ceocreativedevelopmentcompany.commentmanagementsystem.entity.NotificationEntity;
import am.ceocreativedevelopmentcompany.commentmanagementsystem.mapper.NotificationMapper;
import am.ceocreativedevelopmentcompany.commentmanagementsystem.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private NotificationMapper notificationMapper;

    @GetMapping
    public ResponseEntity<List<NotificationDao>> getNotificationList(@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {
        List<NotificationEntity> notificationEntities = notificationService.get(page, size);
        if (notificationEntities != null) {
            return ResponseEntity.ok(notificationMapper.toDtoList(notificationEntities));
        }
        return ResponseEntity.notFound().build();
    }
}
