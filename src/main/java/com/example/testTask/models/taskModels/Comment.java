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
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String textComments;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tasks_id")
    private UserTask tasksId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creator_id")
    private User userCreator;

    public Comment(String textComments, UserTask tasksId, User userCreator) {
        this.textComments = textComments;
        this.tasksId = tasksId;
        this.userCreator = userCreator;
    }
}
