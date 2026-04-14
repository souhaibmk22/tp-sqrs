package com.example.msqueryjoin.api;

import com.example.msqueryjoin.Entities.EditeurJoinBook;
import com.example.msqueryjoin.dao.EditeurJoinBookRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/query")
public class QueryController {

    private final EditeurJoinBookRepository editeurJoinBookRepository;

    public QueryController(EditeurJoinBookRepository editeurJoinBookRepository) {
        this.editeurJoinBookRepository = editeurJoinBookRepository;
    }

    @GetMapping("/join")
    public List<EditeurJoinBook> allJoinRows() {
        return editeurJoinBookRepository.findAll();
    }

    @GetMapping("/join/editeur/{editeurId}")
    public List<EditeurJoinBook> byEditeur(@PathVariable String editeurId) {
        return editeurJoinBookRepository.findByEditeurId(editeurId);
    }
}
