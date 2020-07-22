package com.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CandidateDTO implements Serializable {

    private Long userId;
    private Long accelerationId;
    private Long companyId;
    private Integer status;
    private String createdAt;

}
