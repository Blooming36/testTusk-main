package com.example.testTask.models.taskModels;

import com.example.testTask.models.userModels.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creator_id")
    private User userCreator;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "executor_id")
    private User userExecutor;
    @Enumerated(EnumType.STRING)
    @NotBlank
    private Prioritie prioritie;
    @Enumerated(EnumType.STRING)
    @NotBlank
    private Status status;
    @NotBlank
    private String heading;
    @NotBlank
    private String description;
}
