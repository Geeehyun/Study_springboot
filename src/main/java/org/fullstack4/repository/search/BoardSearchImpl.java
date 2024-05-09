package org.fullstack4.repository.search;

import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.domain.BoardEntity;
import org.fullstack4.domain.QBoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {
    // BaordSearch 인터페이스를 구현한 구현체라는걸 명시하기 위해 Impl을 꼭 붙여야 알아서 인식함.
    public BoardSearchImpl() {
        super(BoardEntity.class);
        // 원래 내용물 이렇게 안생겼었는데 수정함.
    }
    @Override
    public Page<BoardEntity> search(Pageable pageable) {
        log.info("=====================================");
        log.info("search >>>>>>>>>>> Start");
        QBoardEntity qBoardEntity = QBoardEntity.boardEntity; // QBoardEntity 객체 생성
        JPQLQuery<BoardEntity> query = from(qBoardEntity); // SELECT ~~~~ FROM QBoardEntity ---> tbl_board

        //Paging
        this.getQuerydsl().applyPagination(pageable, query);
        log.info("query : {}", query);

        List<BoardEntity> boards = query.fetch();
        long total = query.fetchCount();
        log.info("boards : {}", boards);
        log.info("total : {}", total);

        log.info("search >>>>>>>>>>> End");
        log.info("=====================================");
        return null;
    }
}
