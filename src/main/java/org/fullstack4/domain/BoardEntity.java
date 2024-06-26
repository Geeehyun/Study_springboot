package org.fullstack4.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "tbl_board")
public class BoardEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int idx;
    // GenerationType.IDENTITY => MariaDB에서는 AUTO_INCREMENT와 동일
    @Column(length = 20, nullable = false)
    private String user_id;
    @Column(length = 200, nullable = false)
    private String title;
    @Column(length = 2000, nullable = false)
    private String content;
    @Column(length = 10, nullable = false)
    private String display_date;

    public void modify(String user_id, String title, String content, String display_date) {
        this.user_id = user_id;
        this.title = title;
        this.content = content;
        this.display_date = display_date;
        super.setModify_date(LocalDateTime.now());
    }
}
