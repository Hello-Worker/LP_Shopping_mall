package jpabook.jpashop.controller;

import jpabook.jpashop.domain.item.Kpop;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/kpops/new")
    public String createForm(Model model) {
        model.addAttribute("form", new KpopForm());
        return "kpops/createItemForm";
    }

    @PostMapping("/kpopList/new")
    public String create(KpopForm form) {

        Kpop book = new Kpop();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setArtist(form.getArtist());
        book.setManufacturer(form.getManufacturer());

        itemService.saveItem(book);
        return "redirect:/";
    }

    @GetMapping("/kpops")
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "item/kpopList";
    }

    @GetMapping("kpops/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        Kpop item = (Kpop) itemService.findOne(itemId);

        KpopForm form = new KpopForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setArtist(item.getArtist());
        form.setManufacturer(item.getManufacturer());

        model.addAttribute("form", form);
        return "item/updateItemForm";
    }

    @PostMapping("kpops/{itemId}/edit")
    public String updateItem(@PathVariable Long itemId, @ModelAttribute("form") KpopForm form) {

        itemService.updateItem(itemId, form.getName(), form.getPrice(), form.getStockQuantity());

        return "redirect:/item";
    }
}





