package database.dao;

import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class User {

    @Setter
    private Long id;
    private String name;
    private LocalDate createdDate;
}
