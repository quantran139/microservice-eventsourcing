package com.example.borrowingservice.command.event;


import com.example.borrowingservice.command.data.Borrowing;
import com.example.borrowingservice.command.data.BorrowingRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BorrowingEventsHandler {
    @Autowired
    private BorrowingRepository borrowingRepository;

    @EventHandler
    public void on(BorrowingCreatedEvent event){
        Borrowing model = new Borrowing();
        model.setId(event.getId());
        model.setBorrowingDate(event.getBorrowingDate());
        model.setBookId(event.getBookId());
        model.setEmployeeId(event.getEmployeeId());
        borrowingRepository.save(model);
    }

    @EventHandler
    public void on(BorrowingDeletedEvent event){
        Optional<Borrowing> oldBorrowing = borrowingRepository.findById(event.getId());
        oldBorrowing.ifPresent(borrowing -> borrowingRepository.delete((borrowing)));
    }
}
