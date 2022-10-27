package com.example.loginlivesession2.post.entity;

import com.example.loginlivesession2.account.entity.Account;
import com.example.loginlivesession2.comment.entity.Comment;
import com.example.loginlivesession2.post.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(nullable = false)
    private String category;

    @Lob
    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String content;


    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Comment> comment;

    public Post(PostRequestDto postRequestDto, Account account){
        this.account = account;
        this.title = postRequestDto.getTitle();
        this.category = postRequestDto.getCategory();
        this.image = postRequestDto.getImage();
        this.content = postRequestDto.getContent();
    }

    public void update(PostRequestDto postRequestDto) {
        this.content = postRequestDto.getContent();
    }
}
