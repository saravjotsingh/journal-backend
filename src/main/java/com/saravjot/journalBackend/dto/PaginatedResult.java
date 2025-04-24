package com.saravjot.journalBackend.dto;

import com.saravjot.journalBackend.entity.Journal;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Data
public class PaginatedResult implements Serializable {
    private List<Journal> docs;
    private long total;
    private long pageSize;
    private int page;
}
