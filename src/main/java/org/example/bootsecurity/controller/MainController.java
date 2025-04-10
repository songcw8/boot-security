package org.example.bootsecurity.controller;

import org.example.bootsecurity.model.domain.Memo;
import org.example.bootsecurity.model.domain.MemoForm;
import org.example.bootsecurity.model.mapper.MemoMapper;
import org.example.bootsecurity.service.MemoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {
    private final MemoService memoService;

    public MainController(MemoMapper memoMappe, MemoService memoService) {
        this.memoService = memoService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("memoList", memoService.findAll());
        model.addAttribute("memoForm", new MemoForm());
        return "index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("memoForm", new MemoForm());
        return "add";
    }

    @PostMapping("/add")
    public String save(MemoForm form) throws Exception {
        //Memo memo = new Memo(0L, form.getText(), "");
        //factory 패턴?
        Memo memo = Memo.formText(form.getText());
        memoService.create(memo);
        return "redirect:/";
    }

    @PostMapping("/delete-all")
    public String deleteAll(RedirectAttributes redirectAttributes) throws Exception {
        memoService.deleteAll();
        redirectAttributes.addFlashAttribute("msg", "전체 삭제");
        return "redirect:/";
    }
}
