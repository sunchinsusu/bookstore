package com.ntt.service;

import com.ntt.entity.Invoice;
import com.ntt.entity.Item;
import com.ntt.repository.InvoiceRepository;
import com.ntt.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {
    @Autowired
    InvoiceRepository invoiceRepo;

    @Autowired
    ItemRepository itemRepo;

    public void create(Invoice invoice){
        Invoice i = invoiceRepo.save(invoice);
        for(Item item : i.getItems()){
            item.setInvoice(i);
            itemRepo.save(item);
        }
    }

    public Invoice update(Invoice invoice){
        return invoiceRepo.save(invoice);
    }

    public List<Invoice> getInvoiceByIdCustomer(int id){
        return invoiceRepo.findByCustomer_Id(id);
    }

    public Invoice getInvoiceById(int id){
        return invoiceRepo.findById(id);
    }
}
