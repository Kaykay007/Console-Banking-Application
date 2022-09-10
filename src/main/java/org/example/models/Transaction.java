package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.enumerations.TransactionForm;
import org.example.enumerations.TransactionType;

@Data
@AllArgsConstructor
public class Transaction {
    private TransactionType type;
    private TransactionForm transactionForm;
    private boolean isApproved;
    private String message;
}
