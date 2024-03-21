package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PayedUserProductDto {
    private Long id;
    private Long userProductId;
    private Date payedDate;
}
