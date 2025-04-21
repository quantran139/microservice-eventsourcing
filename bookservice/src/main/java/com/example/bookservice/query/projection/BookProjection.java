package com.example.bookservice.query.projection;


import com.example.bookservice.command.data.Book;
import com.example.bookservice.command.data.BookRepository;
import com.example.bookservice.query.model.BookResponseModel;
import com.example.bookservice.query.queries.GetAllBookQuery;
import com.example.commonservice.model.BookResponseCommonModel;
import com.example.commonservice.query.GetBookDetailQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookProjection {

    @Autowired
    private BookRepository bookRepository;

    @QueryHandler
    public List<BookResponseModel> handle(GetAllBookQuery query){
        List<Book> list = bookRepository.findAll();
        List<BookResponseModel> listBookResponse = new ArrayList<>();
        list.forEach(book -> {
            BookResponseModel model = new BookResponseModel();
            BeanUtils.copyProperties(book, model);
            listBookResponse.add(model);
        });
        //cách khác dùng java 8
//        List<BookResponseModel> result = list.stream().map(book -> {
//            BookResponseModel model = new BookResponseModel();
//            BeanUtils.copyProperties(book, model);
//            return  model;
//        }).toList();

        return listBookResponse;
    }

    @QueryHandler
    public BookResponseCommonModel handle(GetBookDetailQuery query) throws Exception {
        Book book = bookRepository.findById(query.getId())
                .orElseThrow(() -> new Exception("Book not found with id:" + query.getId()));
        BookResponseCommonModel bookResponseModel = new BookResponseCommonModel();
        BeanUtils.copyProperties(book, bookResponseModel);
        return bookResponseModel;
    }
}
