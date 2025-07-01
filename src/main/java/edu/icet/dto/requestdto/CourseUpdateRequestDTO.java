package edu.icet.dto.requestdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseUpdateRequestDTO {
    private Long courseId;
    private String name;
    private String code;
    private double fee;
    private String duration;
}
