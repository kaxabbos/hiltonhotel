package com.hiltonhotel.controller.main;

import com.hiltonhotel.model.Cart;
import com.hiltonhotel.model.Room;
import com.hiltonhotel.model.Stat;
import com.hiltonhotel.model.enums.CartStatus;
import com.hiltonhotel.model.enums.Category;
import com.hiltonhotel.model.enums.Role;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Attributes extends Main {

    protected void AddAttributes(Model model) {
        model.addAttribute("user", getUser());
        model.addAttribute("inStock", roomRepo.findAll().size());
    }

    protected void AddAttributesStats(Model model) {
        AddAttributes(model);
        List<Room> rooms = roomRepo.findAll();
        Room maxQuantity = rooms.get(0);
        Room maxPrice = rooms.get(0);
        Room minPrice = rooms.get(0);
        List<Category> categories = List.of(Category.values());
        int[] intsQuantity = new int[categories.size()];
        int[] intsPrice = new int[categories.size()];
        int profit = 0;
        for (Room i : rooms) {
            profit += i.getStat().getPrice();
            if (i.getStat().getQuantity() > maxQuantity.getStat().getQuantity()) {
                maxQuantity = i;
            }
            if (i.getStat().getPrice() > maxPrice.getStat().getPrice()) {
                maxPrice = i;
            }
            if (i.getStat().getPrice() < maxPrice.getStat().getPrice()) {
                minPrice = i;
            }
            for (int j = 0; j < categories.size(); j++) {
                if (categories.get(j).name().equals(i.getCategory().name())) {
                    intsQuantity[j] += i.getStat().getQuantity();
                    intsPrice[j] += i.getStat().getPrice();
                }
            }
        }
        model.addAttribute("profit", profit);
        model.addAttribute("rooms", rooms);
        model.addAttribute("maxQuantity", maxQuantity);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("intsQuantity", intsQuantity);
        model.addAttribute("intsPrice", intsPrice);

        String[] topQuantityName = new String[5];
        int[] topQuantityNumber = new int[5];
        List<Stat> stats = new ArrayList<>();
        for (Room i : rooms) {
            stats.add(i.getStat());
        }
        stats.sort(Comparator.comparing(Stat::getQuantity));
        Collections.reverse(stats);
        for (int i = 0; i < stats.size(); i++) {
            if (i == 5) break;
            topQuantityName[i] = stats.get(i).getRoom().getName();
            topQuantityNumber[i] = stats.get(i).getQuantity();
        }
        model.addAttribute("topQuantityName", topQuantityName);
        model.addAttribute("topQuantityNumber", topQuantityNumber);

        String[] topPriceName = new String[5];
        int[] topPriceNumber = new int[5];
        stats.sort(Comparator.comparing(Stat::getPrice));
        Collections.reverse(stats);
        for (int i = 0; i < stats.size(); i++) {
            if (i == 5) break;
            topPriceName[i] = stats.get(i).getRoom().getName();
            topPriceNumber[i] = stats.get(i).getPrice();
        }
        model.addAttribute("topPriceName", topPriceName);
        model.addAttribute("topPriceNumber", topPriceNumber);
    }

    protected void AddAttributesApps(Model model) {
        AddAttributes(model);
        model.addAttribute("carts", cartRepo.findAllByStatus(CartStatus.WAITING));
    }

    protected void AddAttributesArchive(Model model) {
        AddAttributes(model);
        List<Cart> cartList = cartRepo.findAllByUserAndStatus(getUser(), CartStatus.APPROVED);
        cartList.addAll(cartRepo.findAllByUserAndStatus(getUser(), CartStatus.REFUSED));
        cartList.addAll(cartRepo.findAllByUserAndStatus(getUser(), CartStatus.WAITING));
        model.addAttribute("carts", cartList);
    }

    protected void AddAttributesRoomAdd(Model model) {
        AddAttributes(model);
        model.addAttribute("categories", Category.values());
    }

    protected void AddAttributesRoomEdit(Model model, Long id) {
        AddAttributes(model);
        model.addAttribute("categories", Category.values());
        model.addAttribute("room", roomRepo.getReferenceById(id));
    }

    protected void AddAttributesUsers(Model model) {
        AddAttributes(model);
        model.addAttribute("users", userRepo.findAll());
        model.addAttribute("roles", Role.values());
    }

    protected void AddAttributesRooms(Model model) {
        AddAttributes(model);
        model.addAttribute("rooms", roomRepo.findAll());
        model.addAttribute("categories", Category.values());
    }

    protected void AddAttributesRoomsCategory(Model model, Category category) {
        AddAttributesRooms(model);
        model.addAttribute("rooms", roomRepo.findAllByCategory(category));
        model.addAttribute("selectedCategory", category);
    }

    protected void AddAttributesRoomsSearch(Model model, Category category, String search) {
        AddAttributesRooms(model);
        List<Room> rooms = roomRepo.findAllByCategoryAndNameContaining(category, search);

        model.addAttribute("rooms", rooms);
        model.addAttribute("selectedCategory", category);
        model.addAttribute("input", search);
    }
}
