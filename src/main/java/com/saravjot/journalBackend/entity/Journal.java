package com.saravjot.journalBackend.entity;

import com.saravjot.journalBackend.dto.UserInfo;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "journals")
@Data
public class Journal implements Serializable {
    @Id
    private String id;
    private String title;
    private String description;
    private String user;


    @Transient
    private UserInfo userInfo;
}
