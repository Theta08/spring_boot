package com.bit;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bit.domain.WebBoard;
import com.bit.domain.WebReply;
import com.bit.persistence.WebReplyRepository;

import lombok.extern.java.Log;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Log
@Commit
public class WebReplyRepositoryTests {

	@Autowired
	WebReplyRepository repo;
	
	@Test
	public void testInsertReplies() {
		Long[] arr = {300L,299L,295L};
		Arrays.stream(arr).forEach(num->{
			WebBoard board =new WebBoard();
			board.setBno(num);
			IntStream.range(0, 10).forEach(i->{
				WebReply reply=new WebReply();
				reply.setReplyText("Reply...");
				reply.setReplyer("replyer"+i);
				reply.setBoard(board);
				repo.save(reply);
			});
		});
	}
}
