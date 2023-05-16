package com.hiltonhotel.controller;

import com.hiltonhotel.controller.main.Attributes;
import com.hiltonhotel.model.Room;
import com.hiltonhotel.model.Stat;
import com.hiltonhotel.model.enums.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/room")
public class RoomCont extends Attributes {
    @GetMapping("/all")
    String rooms(Model model) {
        AddAttributesRooms(model);
        return "rooms";
    }

    @GetMapping("/category/{category}")
    String roomsCategory(Model model, @PathVariable Category category) {
        AddAttributesRoomsCategory(model, category);
        return "rooms";
    }

    @PostMapping("/search")
    String roomsSearch(Model model, @RequestParam Category category, @RequestParam String search) {
        AddAttributesRoomsSearch(model, category, search);
        return "rooms";
    }

    @GetMapping("/add")
    String roomAdd(Model model) {
        AddAttributesRoomAdd(model);
        return "roomAdd";
    }

    @PostMapping("/add")
    String roomAddNew(Model model, @RequestParam MultipartFile img, @RequestParam String name, @RequestParam String floor, @RequestParam Category category, @RequestParam int quantity, @RequestParam int price, @RequestParam String description) {
        Room room = roomRepo.saveAndFlush(new Room(name, category, price, quantity, description, floor));

        if (img != null && !Objects.requireNonNull(img.getOriginalFilename()).isEmpty()) {
            String uuidFile = UUID.randomUUID().toString();
            boolean createDir = true;
            String res = "";
            try {
                File uploadDir = new File(uploadImg);
                if (!uploadDir.exists()) createDir = uploadDir.mkdir();
                if (createDir) {
                    res = uuidFile + "_" + img.getOriginalFilename();
                    img.transferTo(new File(uploadImg + "/" + res));
                }
            } catch (IOException e) {
                model.addAttribute("message", "Не удалось загрузить изображение");
                AddAttributesRoomAdd(model);
                return "roomAdd";
            }

            room.setImg(res);
            roomRepo.save(room);
        }

        Stat stat = statRepo.saveAndFlush(new Stat(room));
        room.setStat(stat);
        roomRepo.save(room);
        return "redirect:/room/all";
    }

    @GetMapping("/edit/{id}")
    String roomEdit(Model model, @PathVariable Long id) {
        AddAttributesRoomEdit(model, id);
        return "roomEdit";
    }

    @PostMapping("/edit/{id}")
    String roomEditOld(@PathVariable Long id, Model model, @RequestParam MultipartFile img, @RequestParam String name, @RequestParam String floor, @RequestParam Category category, @RequestParam int quantity, @RequestParam int price, @RequestParam String description) {
        Room room = roomRepo.getReferenceById(id);

        room.setName(name);
        room.setFloor(floor);
        room.setCategory(category);
        room.setPrice(price);
        room.setQuantity(quantity);
        room.setDescription(description);

        if (img != null && !Objects.requireNonNull(img.getOriginalFilename()).isEmpty()) {
            String uuidFile = UUID.randomUUID().toString();
            boolean createDir = true;
            String res = "";
            try {
                File uploadDir = new File(uploadImg);
                if (!uploadDir.exists()) createDir = uploadDir.mkdir();
                if (createDir) {
                    res = uuidFile + "_" + img.getOriginalFilename();
                    img.transferTo(new File(uploadImg + "/" + res));
                }
            } catch (IOException e) {
                model.addAttribute("message", "Не удалось загрузить изображение");
                AddAttributesRoomAdd(model);
                return "roomEdit";
            }

            room.setImg(res);
        }

        roomRepo.save(room);

        return "redirect:/room/all";
    }

    @GetMapping("/delete/{id}")
    String roomDelete(@PathVariable Long id) {
        roomRepo.deleteById(id);
        return "redirect:/room/all";
    }

}
