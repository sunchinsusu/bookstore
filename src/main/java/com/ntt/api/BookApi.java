package com.ntt.api;

import com.ntt.entity.Book;
import com.ntt.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/book")
public class BookApi {
    @Autowired
    BookService bookSer;

    @PostMapping("/create/list")
    public ResponseEntity<List<Book>> createList(@RequestBody List<Book> books){
        return new ResponseEntity<>(bookSer.create(books), HttpStatus.OK) ;
    }

    @GetMapping("/get")
    public ResponseEntity<List<Book>> get(){
        return new ResponseEntity<>(bookSer.getAll(), HttpStatus.OK) ;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Book> get(@PathVariable(name = "id")int id){
        return new ResponseEntity<>(bookSer.getById(id), HttpStatus.OK) ;
    }
}

