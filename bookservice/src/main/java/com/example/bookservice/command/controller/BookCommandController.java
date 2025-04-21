package com.example.bookservice.command.controller;

import com.example.bookservice.command.commands.CreateBookCommand;
import com.example.bookservice.command.commands.DeleteBookCommand;
import com.example.bookservice.command.commands.UpdateBookCommand;
import com.example.bookservice.command.model.BookRequestModel;
import com.example.commonservice.service.KafkaService;
import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/books")
public class BookCommandController {
    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private KafkaService kafkaService;

    @PostMapping
    public String addBook(@Valid @RequestBody BookRequestModel model) {
        CreateBookCommand command = new CreateBookCommand(UUID.randomUUID().toString(), model.getName(), model.getAuthor(), true);

        return commandGateway.sendAndWait(command);

    }

    @PutMapping("/{bookId}")
    public String updateBook(@RequestBody BookRequestModel model, @PathVariable String bookId) {
        UpdateBookCommand command = new UpdateBookCommand(bookId, model.getName(), model.getAuthor(), model.getIsReady());
        return commandGateway.sendAndWait(command);
    }

    @DeleteMapping("/{bookId}")
    public String deleteBook(@PathVariable String bookId) {
        DeleteBookCommand command = new DeleteBookCommand(bookId);
        return commandGateway.sendAndWait(command);
    }

    @PostMapping("/sendMessage")
    public void sendMessage(@RequestBody String message){
        kafkaService.sendMessage("test",message);
    }
}
