package com.teko.codingtest.controllers;

import com.teko.codingtest.models.Cinema;
import javafx.util.Pair;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class SeatsManageController {
    Cinema cinema;
    SeatsManageController() {
        cinema = new Cinema();
    }
    // cấu hình rows, colums, min_distance
    @PostMapping(path = "/config/{rows}/{columns}/{min_distince}")
    public void setCinema(@PathVariable int rows, @PathVariable int columns, @PathVariable int min_distince) {
        this.cinema.setColumns(columns);
        this.cinema.setRows(rows);
        this.cinema.setMin_distance(min_distince);
        this.cinema.setSeats(new int[rows][columns]);
    }
    // lấy danh sách các vị trí khả dụng
    @GetMapping(path = "/getAvailableSlot")
    public ArrayList<Pair<Integer, Integer>> getAvailableSlot() {
        return this.cinema.getAvailableSlot();
    }
    // lua chon vi tri (x, y)
    @PostMapping(path = "/tickSlot/{x}/{y}")
    public String tickAlone(@PathVariable int x, @PathVariable int y) {
        int res = this.cinema.check(x, y);
        if (res == 0) {
            this.cinema.tick(x, y);
            this.cinema.setCountParties(this.cinema.getMin_distance()+1);
            return "Đặt chỗ thành công cho vị trí (" + x + ", " + y + ")";
        }
        else if (res == 1) {
            return "Vị trí đã có người chọn trước";
        }
        return "Vị trí vi phạm khoảng cách, không được chọn";
    }




}
