package com.spring.memo.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.memo.entity.Memo;

@SpringBootTest
public class MemoRepositoryTest {

	@Autowired
	private MemoRepository memoRepository;

	@Test
	public void createMemoTest() {

//		Memo memo = new Memo();
//		memo.setMemoText("메모 첫번째");
//		
//		//save() : insert or update
//		memoRepository.save(memo);

//		for (int i = 0; i < 100; i++) {
//			Memo memo = new Memo();
//			memo.setMemoText("Memo..."+i);
//			memoRepository.save(memo);
//			
//			Memo memo = Memo.builder()
//							.memoText("Memo..."+i)
//							.build();
//			memoRepository.save(memo);
//		}
	}

//	@Test
//	public void findByMemo() {
//		// 조회 : findById() : return 값이 Optional<Memo>
//		Optional<Memo> result = memoRepository.findById(90L);
//
//		if (result.isPresent()) {
//			System.out.println(result.get());
//		}
//	}
//
//	@Test
//	public void findByMemoList() {
//		List<Memo> result = memoRepository.findAll();
//
//		result.forEach(memo -> System.out.println(memo));
//
//	}
	
//	@Test
//	public void updateMemo() {
//		// save() : insert, update 
//		Memo memo = Memo.builder()
//						.mno(80L)
//						.memoText("이거 수정함")
//						.build();
//		memoRepository.save(memo);
//	}
	
	@Test
	public void deleteMemo() {
		memoRepository.deleteById(90L);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
