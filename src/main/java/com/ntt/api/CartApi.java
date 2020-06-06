package com.ntt.api;

import com.ntt.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/cart")
public class CartApi {
    @Autowired
    BookService bookSer;


}
