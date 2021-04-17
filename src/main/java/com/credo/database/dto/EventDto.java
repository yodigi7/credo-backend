package com.credo.database.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class EventDto {
    String eventName;
    Date eventDate;
    String amount;
}
