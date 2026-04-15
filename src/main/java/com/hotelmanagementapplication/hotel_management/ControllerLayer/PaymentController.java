package com.hotelmanagementapplication.hotel_management.ControllerLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelmanagementapplication.hotel_management.DTOLayer.PaymentDTO;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.PaymentService;




@RestController
@RequestMapping("/api/payments")
public class PaymentController {
	@Autowired
    private PaymentService paymentService;

    // CREATE PAYMENT
    @PostMapping
    public PaymentDTO create(@RequestBody PaymentDTO dto) {
        return paymentService.createPayment(dto);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public PaymentDTO get(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }

    // GET ALL
    @GetMapping
    public List<PaymentDTO> getAll() {
        return paymentService.getAllPayments();
    }

    // UPDATE
    @PutMapping("/{id}")
    public PaymentDTO update(@PathVariable Long id,
                             @RequestBody PaymentDTO dto) {
        return paymentService.updatePayment(id, dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return "Payment deleted successfully";
    }
}
