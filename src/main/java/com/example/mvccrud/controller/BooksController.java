package com.example.mvccrud.controller;

import com.example.mvccrud.entity.Author;
import com.example.mvccrud.entity.Book;
import com.example.mvccrud.service.BookService;
import jakarta.validation.Valid;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BooksController {
    private final BookService bookService;
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/book/update")
    public String saveBook(@Valid Book book,BindingResult result,Model model,RedirectAttributes attributes){
        if(result.hasErrors()){
            model.addAttribute("book",bookService.findBookById(book.getId()));
            model.addAttribute("authors",bookService.listAuthors());
            return "book-update";
        }
        bookService.update(book);
        return "redirect:/list-books";
    }

    @GetMapping("/book/update")
    public String updateForm(@RequestParam("id")int id,Model model){
        model.addAttribute("book",bookService.findBookById(id));
        model.addAttribute("authors",bookService.listAuthors());
        return "book-update";
    }

    @GetMapping("/book/remove")
    public String removeBook(@RequestParam("id")int id,RedirectAttributes attributes){
        bookService.removeBook(id);
        attributes.addFlashAttribute("delete",true);
        return "redirect:/list-books";
    }
    @GetMapping({"/","/home"})
    public ModelAndView index(ModelMap model){
       return new ModelAndView("books","books",bookService.listBooks());
    }
    @GetMapping("/author-form")
    public String authorForm(Model model){
        model.addAttribute("author",new Author());
        return "author-form";
    }
    @PostMapping("/author-form")
    public String saveAuthor(@Valid Author author, BindingResult result){
        if(result.hasErrors()){
            return "author-form";
        }
        bookService.saveAuthor(author);
        return "redirect:/authors";
    }
    @GetMapping("/authors")
    public String listAuthor(Model model){
        model.addAttribute("authors",bookService.listAuthors());
        return "authors";
    }
    @GetMapping("/book-form")
    public String bookForm(Model model){
        model.addAttribute("authors",bookService.listAuthors());
        model.addAttribute("book",new Book());
        return "bookform";
    }
    @PostMapping("/book-form")
    public String saveBook(@Valid Book book, BindingResult result,
                           RedirectAttributes redirectAttributes,Model model){
        if(result.hasErrors()){
            model.addAttribute("authors",bookService.listAuthors());
            return "bookform";
        }
        bookService.saveBook(book);
        redirectAttributes.addFlashAttribute("success",true);
        return "redirect:/list-books";
    }
    @RequestMapping("/list-books")
    public String listAllBooks(Model model){
        model.addAttribute("delete",model.containsAttribute("delete"));
        model.addAttribute("success",model.containsAttribute("success"));
        model.addAttribute("books",bookService.listBooks());

        return "books";
    }
}
