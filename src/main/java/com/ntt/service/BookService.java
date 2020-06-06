package com.ntt.service;

import com.ntt.entity.Book;
import com.ntt.entity.Category;
import com.ntt.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepo;

    public List<Book> getAll(){
        return bookRepo.findAll();
    }

    public Book getById(int id){
        return bookRepo.findById(id);
    }

    public List<Book> getByName(String name){
        return bookRepo.findBookByNameLike("%"+name+"%");
    }

    public List<Book> getByAuthor(String name){
        return bookRepo.findByAuthor(name);
    }

    public List<Book> getByCategory(Category c){
        return bookRepo.findByCategory(c);
    }

    public List<Book> create(List<Book> books){
        return bookRepo.saveAll(books);
    }

    public Book create(Book book){
        return bookRepo.save(book) ;
    }

    public static ArrayList<Book> sortPriceLowToHeigh(List<Book> books){
        ArrayList<Book> newBooks = new ArrayList<>();
        while (books.size()>0){
            Book min = books.get(0);
            for (Book b : books){
                if((b.getSalePrice()-b.getSalePrice()*b.getSaleOff())<(min.getSalePrice()-min.getSalePrice()*min.getSaleOff())){
                    min = b;
                }
            }
            newBooks.add(min);
            books.remove(min);
        }
        return newBooks;
    }

    public static ArrayList<Book> sortPriceHeighToLow(List<Book> books){
        ArrayList<Book> newBooks = new ArrayList<>();
        while (books.size()>0){
            Book max = books.get(0);
            for (Book b : books){
                if((b.getSalePrice()-b.getSalePrice()*b.getSaleOff())>(max.getSalePrice()-max.getSalePrice()*max.getSaleOff())){
                    max = b;
                }
            }
            newBooks.add(max);
            books.remove(max);
        }
        return newBooks;
    }
}
