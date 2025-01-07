package com.bmcotuk.library_manager.repository;

import com.bmcotuk.library_manager.repository.model.LendingTransaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LendingTransactionRepository extends JpaRepository<LendingTransaction, String> {

    // eagerly fetch book and member to avoid ByteBuddyInterceptor with Jackson
    @Query("SELECT lt FROM LendingTransaction lt " +
            "JOIN FETCH lt.book " +
            "JOIN FETCH lt.member " +
            "WHERE lt.dueDate < :currentDate AND lt.returnedDate IS NULL")
    List<LendingTransaction> findOverdueTransactions(LocalDate currentDate, Pageable pageable);

    @Modifying
    @Query("UPDATE LendingTransaction lt SET lt.returnedDate = :returnedDate " +
            "WHERE lt.book.id = :bookId" +
            " AND lt.member.id = :memberId" +
            " AND lt.returnedDate IS NULL")
    void updateReturnedDate(String bookId, String memberId, LocalDate returnedDate);
}
