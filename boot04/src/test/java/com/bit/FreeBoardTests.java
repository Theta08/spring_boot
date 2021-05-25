package com.bit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bit.domain.FreeBoard;
import com.bit.domain.FreeBoardReply;
import com.bit.domain.PDSBoard;
import com.bit.domain.PDSFile;
import com.bit.persistence.FreeBordReplyReposity;
import com.bit.persistence.FreeBordReposity;
import com.bit.persistence.PDSBoardRepository;

import lombok.extern.java.Log;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Log
@Commit
public class FreeBoardTests {

	@Autowired
	FreeBordReplyReposity replyRepo;
	
	@Autowired
	FreeBordReposity boardRepo;
	

	@Test
	public void insertDummy() {
		//System.out.println("*********** 테이블 생성");
		IntStream.range(1, 200).forEach(i->{
			FreeBoard board=new FreeBoard();
			board.setTitle("Free Board.."+i);
			board.setContent("Free Content.."+i);
			board.setWriter("user"+i%10);
			boardRepo.save(board);
			
		});
	}
	
	@Transactional
	@Test
	//트랜잭션과 cascade 기능이 추가(FreeBoard.java 추가)
	public void insertReply2Way() {
		Optional<FreeBoard> result=boardRepo.findById(198L);
		result.ifPresent(board->{
			List<FreeBoardReply> replies =board.getReplies();
			FreeBoardReply reply=new FreeBoardReply();
			reply.setReply("REPLY........");
			reply.setReplyer("reply00");
			reply.setBoard(board);
			replies.add(reply);
			board.setReplies(replies);
			boardRepo.save(board);
		});
	}
	
	@Test
	public void insertReply1Way() {
		FreeBoard board =new FreeBoard();
		board.setBno(199L);
		
		FreeBoardReply reply=new FreeBoardReply();
		reply.setReply("REPLY........");
		reply.setReplyer("reply00");
		reply.setBoard(board);
		
		replyRepo.save(reply);
	}
	
	//게시판용
	@Test
	public void testList1() {
		Pageable pageable=PageRequest.of(0, 10,Sort.Direction.DESC,"bno");
		boardRepo.findByBnoGreaterThan(0L, pageable).forEach(board->{
			System.out.println("******"+board.getBno()+":"+board.getTitle());
		});
	}
	
	@Transactional // 1:n n:m OntTo..같이 관계가 있을경우 대부분 Traansactional을 사용한다.
	@Test
	// 트렌지션 필수 프레임워크가 여러게라 그룹묶기? 같은게 필요
	public void testList2(){						//					order by 컬럼
		Pageable pageable=PageRequest.of(0, 10,Sort.Direction.DESC,"bno");
		boardRepo.findByBnoGreaterThan(0L, pageable).forEach(board->{	//board return 값이 board이기 떄문에
			System.out.println("*****"+board.getBno()+":"+board.getTitle()+":"+board.getReplies().size());
		});
	}
	
	@Test
	public void testList3() {
		Pageable pageable=PageRequest.of(0, 10,Sort.Direction.DESC,"bno");
		boardRepo.getPage(pageable).forEach(arr->{
			System.out.println("***"+Arrays.toString(arr));
		});
	}
}

















