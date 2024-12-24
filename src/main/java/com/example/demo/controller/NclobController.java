package com.example.demo.controller;

import com.example.demo.entity.NClobImpl;
import java.sql.NClob;
import java.sql.SQLException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nclob")
public class NclobController {

    @GetMapping("/test")
    public String getNclob() throws SQLException {
        try {
            NClob nClob = createNClob("Đây là một NClob example.");

            // Sử dụng NClob (in ra nội dung)
            System.out.println("NClob content: " + nClob.getSubString(1, (int) nClob.length()));

            return nClob.toString();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    private static NClob createNClob(String data) throws SQLException {
        // Tạo một NClob trong bộ nhớ
        NClob nClob = new NClobImpl(data);
        return nClob;
    }
}
