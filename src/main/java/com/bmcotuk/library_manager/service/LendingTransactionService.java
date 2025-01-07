package com.bmcotuk.library_manager.service;

import com.bmcotuk.library_manager.repository.model.LendingTransaction;

import java.util.List;

public interface LendingTransactionService {
    LendingTransaction borrowBook(String bookId, String memberId);

    void returnBook(String bookId, String memberId);

    List<LendingTransaction> getOverdueTransactions(int page, int size);
}
