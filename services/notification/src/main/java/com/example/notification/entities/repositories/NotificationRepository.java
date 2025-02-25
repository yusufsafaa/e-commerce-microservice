package com.example.notification.entities.repositories;

import com.example.notification.entities.concrete.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {

}
