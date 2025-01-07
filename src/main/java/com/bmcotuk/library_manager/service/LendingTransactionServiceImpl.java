package com.bmcotuk.library_manager.service;

import com.bmcotuk.library_manager.repository.LendingTransactionRepository;
import com.bmcotuk.library_manager.repository.model.Book;
import com.bmcotuk.library_manager.repository.model.LendingTransaction;
import com.bmcotuk.library_manager.repository.model.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LendingTransactionServiceImpl implements LendingTransactionService {

    private final BookServiceImpl bookService;
    private final MemberServiceImpl memberService;
    private final LendingTransactionRepository lendingTransactionRepository;

    @Transactional
    public LendingTransaction borrowBook(String bookId, String memberId) {
        Book book = bookService.getBookById(bookId);
        if (book.getCopiesAvailable() < 1) {
            throw new RuntimeException("Book is not available");
        }

        Member member = memberService.getMemberById(memberId);

        bookService.decrementCopiesAvailable(bookId);

        LendingTransaction lendingTransaction = new LendingTransaction();
        book.setCopiesAvailable(book.getCopiesAvailable() - 1);
        lendingTransaction.setBook(book);
        lendingTransaction.setMember(member);
        LocalDate now = LocalDate.now();
        lendingTransaction.setBorrowedDate(now);
        lendingTransaction.setDueDate(now.minusDays(14));
        return lendingTransactionRepository.save(lendingTransaction);
    }

    @Transactional
    public void returnBook(String bookId, String memberId) {
        bookService.getBookById(bookId);
        memberService.getMemberById(memberId);

        bookService.incrementCopiesAvailable(bookId);

        lendingTransactionRepository.updateReturnedDate(bookId, memberId, LocalDate.now());
    }

    public List<LendingTransaction> getOverdueTransactions(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return lendingTransactionRepository.findOverdueTransactions(LocalDate.now(), pageRequest);
    }
}
