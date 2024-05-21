package es.alex.taller.controller.table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import es.alex.taller.model.repository.TableRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class TableController {

    @Autowired
    private TableRepository tableRepository;

    @GetMapping("/table")
    public String getTable() {
        return new String();
    }
    
}
