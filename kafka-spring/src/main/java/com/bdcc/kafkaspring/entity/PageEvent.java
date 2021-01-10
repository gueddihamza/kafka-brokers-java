package com.bdcc.kafkaspring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor @AllArgsConstructor
public class PageEvent {
    private String page;
    private Date date;
    private int duration;
}
