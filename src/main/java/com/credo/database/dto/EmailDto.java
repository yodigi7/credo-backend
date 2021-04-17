package com.credo.database.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EmailDto {
    String email;
    String emailType;
}
