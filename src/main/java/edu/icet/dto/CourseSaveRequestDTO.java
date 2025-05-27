package edu.icet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseSaveRequestDTO {
    private String name;
    private String code;
    private double fee;
    private String duration;
}
