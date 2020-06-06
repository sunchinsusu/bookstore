package com.ntt.controller;

import com.ntt.entity.Book;
import com.ntt.entity.Category;
import com.ntt.entity.Customer;
import com.ntt.entity.Item;
import com.ntt.repository.CustomerRepository;
import com.ntt.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Random;

@Controller
public class HomeController {
    @Autowired
    BookService bookSer;

    @Autowired
    CustomerRepository customerRepo;

    @GetMapping("/detail/{id}")
    public String viewBookDetail(@PathVariable("id")int id, Model model){
        Book b = bookSer.getById(id);
        model.addAttribute("book", b);
        ArrayList<Book> random4 = new ArrayList<>(bookSer.getAll());
        Random random = new Random();
        while(random4.size()>4){
            int index = random.nextInt(random4.size()-1);
            random4.remove(index);
        }
        model.addAttribute("random", random4);
        return "detail";
    }

    @GetMapping("/search/category/{ctg}")
    public String searchByCategory(@PathVariable(name = "ctg") Category ctg, Model model){
        ArrayList<Book> books = new ArrayList<>(bookSer.getByCategory(ctg));
        model.addAttribute("books", books);
        return "search";
    }

    @GetMapping("/search/category/{ctg}/sort/{type}")
    public String searchByCategoryAndSort(@PathVariable(name = "ctg") Category ctg,
                                          @PathVariable(name = "type") int type, Model model){
        if(type==0){
            model.addAttribute("books", BookService.sortPriceLowToHeigh(bookSer.getByCategory(ctg)));
        }
        if(type==1){
            model.addAttribute("books", BookService.sortPriceHeighToLow(bookSer.getByCategory(ctg)));
        }
        return "search";
    }

    @GetMapping("/search/name/{name}")
    public String searchByName(@PathVariable(name = "name") String name, Model model){
        ArrayList<Book> books = new ArrayList<>(bookSer.getByName(name));
        model.addAttribute("books", books);
        return "search";
    }

    @GetMapping("/search/name/{name}/sort/{type}")
    public String searchByNameAndSort(@PathVariable(name = "name") String name,
                                      @PathVariable(name = "type") int type,Model model){
        if(type==0){
            model.addAttribute("books", BookService.sortPriceLowToHeigh(bookSer.getByName(name)));
        }
        if(type==1){
            model.addAttribute("books", BookService.sortPriceHeighToLow(bookSer.getByName(name)));
        }
        return "search";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(Principal principal,HttpServletRequest req, @RequestParam(name = "id") int id, @RequestParam(name = "quantity") int quantity, Model model){
        HttpSession session = req.getSession();

        Customer customer = customerRepo.findByAccount_UserName(principal.getName());
        model.addAttribute("customer", customer);

        ArrayList<Item> items = (ArrayList<Item>) session.getAttribute("items");
        Book book = bookSer.getById(id);

        if(items == null){
            items = new ArrayList<>();
            Item item = new Item();
            item.setBook(book);
            item.setPrice(book.getSalePrice());
            item.setPriceAfterDiscount(book.getSalePrice()-book.getSaleOff()*book.getSalePrice());
            item.setQuantity(quantity);
            item.setTotal(quantity*(book.getSalePrice()-book.getSaleOff()*book.getSalePrice()));
            items.add(item);
            session.setAttribute("items", items);
        }
        else{
            boolean isExist = false;
            for(Item item : items){
                if(item.getBook().getId() == book.getId()){
                    int newQuantity = item.getQuantity()+quantity;
                    float newTotal = newQuantity*item.getPriceAfterDiscount();
                    item.setQuantity(newQuantity);
                    item.setTotal(newTotal);
                    isExist = true;
                }
            }
            if(isExist==false){
                Item item = new Item();
                item.setBook(book);
                item.setPrice(book.getSalePrice());
                item.setPriceAfterDiscount(book.getSalePrice()-book.getSaleOff()*book.getSalePrice());
                item.setQuantity(quantity);
                item.setTotal(quantity*(book.getSalePrice()-book.getSaleOff()*book.getSalePrice()));
                items.add(item);
            }
        }
        return "redirect:/cart";
    }
}
