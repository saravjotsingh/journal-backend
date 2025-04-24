package com.saravjot.journalBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class UserInfo implements Serializable {
    private String id;
    private String email;
    private String number;
}
