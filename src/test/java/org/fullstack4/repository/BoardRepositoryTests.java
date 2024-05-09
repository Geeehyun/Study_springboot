package org.fullstack4.repository;

import lombok.extern.log4j.Log4j2;
import org.fullstack4.domain.BaseEntity;
import org.fullstack4.domain.BoardEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.stream.IntStream;

@Log4j2
@SpringBootTest
public class BoardRepositoryTests {
    // SpringBoot에서 Juit 테스트 진행할 때는 위에 선언한 @SpringBootTest 어노테이션이 꼭 필요함 => 그래야 build.gradle에 넣어놓은 테스트 라이브러리 사용가능
    @Autowired
    private BoardRepository boardRepository;
    @Test
    public void testGetNow() {
        String now = boardRepository.getNow();
        log.info("===========================================");
        log.info("now : " + now);
        log.info("===========================================");
    }

    @Test
    public void testRegist() {
        log.info("===========================================");
        log.info("testRegist()");
        // range : 0 이상 10미만 , rangeClosed : 0 이상 10이하
        IntStream.rangeClosed(0,10)
                .forEach(i->{
                    BoardEntity bbs = BoardEntity.builder()
                            .user_id("test1")
                            .title(i+"번째 제목입니다.")
                            .content(i+"번째 게시글 내용입니다.")
                            .display_date(new SimpleDateFormat("yyyy-MM-dd").format(
                                    new Date(2024-1900, 4, (i%10==0?1:(1%10)))).toString()
                            )
                            .build();
                    BoardEntity bbsResult = boardRepository.save(bbs);
                    log.info("bbsResult : " + bbsResult);
                });
        log.info("===========================================");
    }

    @Test
    public void testView() {
        log.info("===========================================");
        log.info("testView()");
        int idx = 1;
        Optional<BoardEntity> result = boardRepository.findById(idx);
        BoardEntity bbs = result.orElse(null);
        result.get(); //값이 없으면 NoSuchElementException 발생
        // 위 방법 말고도 아래의 방법으로 예외처리 가능
//        if(result.isPresent()) {
//         // 성공 시 처리하는 로직
//        } else {
//            throw new IllegalArgumentException();
//        }
//        result.orElseThrow(IllegalArgumentException::new);
//        result.orElseThrow(()->new IllegalArgumentException("no find data"));
//        result.orElseGet(BoardEntity::new);
//        result.ifPresent(b->{log.info("result : ", b);});
        log.info("result : " + result);
    }

    @Test
    public void testModify() {
        log.info("===========================================");
        log.info("testModify()");
        // JPA의 save() 메서드 사용해서 update하는 경우
        // save의 경우 키가되는 값이 기존꺼에 존재하면 update 아니면 insert로 실행됨.
        int idx = 1;
        Optional<BoardEntity> result = boardRepository.findById(idx);
        BoardEntity bbs = BoardEntity.builder()
                .idx(idx)
                .user_id("test1")
                .title("1번째 제목입니다.(수정)")
                .content("1번째 게시글 내용입니다.(수정)")
                .display_date(new SimpleDateFormat("yyyy-MM-dd").format(
                        new Date()).toString()).build();
        BoardEntity bbsResult = boardRepository.save(bbs);
        log.info("bbsResult : " + bbsResult);
    }

    @Test
    public void testDelete() {
        log.info("===========================================");
        log.info("testDelete()");
        int idx = 1;
        boardRepository.deleteById(idx);
        // JPA가 SELECT먼저 해보고 해당 데이터가 있으면 DELETE이어서 진행하고 SELECT해서 해보고 없으면 아무것도 안함.
    }

    @Test
    public void testSearch() {
        log.info("===========================================");
        log.info("testList()  >>>>>>>>>>>>  START");
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("idx").descending());
        boardRepository.search(pageRequest);
        log.info("testList()  >>>>>>>>>>>>  END");
    }
}
