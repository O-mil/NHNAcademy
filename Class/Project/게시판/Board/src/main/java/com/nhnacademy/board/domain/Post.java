package com.nhnacademy.board.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Posts")
public class Post implements IPost{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;
    @Column(name="writer_id")
    private String writerUserId;

    @Column(name="view_count")
    private int viewCount;

    @Transient
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date writeTime;

    public Post(Long id, String title, String content,String writerUserId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writerUserId = writerUserId;
        writeTime = new Date();
        this.viewCount = 0;
    }

    @Override
    public void increaseViewCount() {
        viewCount++;
    }
}
