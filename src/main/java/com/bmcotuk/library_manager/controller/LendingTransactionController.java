package com.bmcotuk.library_manager.controller;

import com.bmcotuk.library_manager.controller.dto.LendingTransactionRestRequest;
import com.bmcotuk.library_manager.repository.model.LendingTransaction;
import com.bmcotuk.library_manager.service.LendingTransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lending-transaction")
public class LendingTransactionController {

    private final LendingTransactionService lendingTransactionService;

    @PostMapping("/borrow")
    public ResponseEntity<LendingTransaction> borrowBook(@Valid @RequestBody LendingTransactionRestRequest restRequest) {
        LendingTransaction lendingTransaction = lendingTransactionService.borrowBook(restRequest.getBookId(), restRequest.getMemberId());
        return ResponseEntity.ok(lendingTransaction);
    }

    @PutMapping("/return")
    public ResponseEntity<Void> returnBook(@Valid @RequestBody LendingTransactionRestRequest restRequest) {
        lendingTransactionService.returnBook(restRequest.getBookId(), restRequest.getMemberId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<LendingTransaction>> getOverdueTransactions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(lendingTransactionService.getOverdueTransactions(page, size));
    }
}
