package com.example.paymentservice.service;

import com.example.paymentservice.entity.Payment;
import com.example.paymentservice.model.PaymentMode;
import com.example.paymentservice.model.PaymentRequest;
import com.example.paymentservice.model.PaymentResponse;
import com.example.paymentservice.repository.PaymentDetailsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class PaymentServiceImplTest {
    @Mock
    private PaymentDetailsRepository paymentDetailsRepository;

    @InjectMocks
    PaymentService paymentService = new PaymentServiceImpl();

    @DisplayName("Do Payment - Success Scenario")
    @Test
    void test_When_Do_Payment_Success() {
        PaymentRequest paymentRequest = getMockPaymentRequest();
        Payment payment = getMockPayment();

        when(paymentDetailsRepository.save(any(Payment.class))).thenReturn(payment);

        long paymentId = paymentService.doPayment(paymentRequest);

        verify(paymentDetailsRepository, times(1)).save(any(Payment.class));

        assertEquals(payment.getId(), paymentId);
    }

    @DisplayName("Get Payment Details - Success Scenario")
    @Test
    void test_When_Get_Payment_Details_Success() {
        Payment payment = getMockPayment();

        when(paymentDetailsRepository.findByOrderId(anyLong())).thenReturn(payment);

        PaymentResponse paymentResponse = paymentService.getPaymentDetailsByOrderId(String.valueOf(payment.getOrderId()));

        verify(paymentDetailsRepository, times(1)).findByOrderId(anyLong());

        assertEquals(payment.getId(), paymentResponse.getPaymentId());
    }


    private Payment getMockPayment() {
        return Payment.builder()
                .id(1L)
                .amount(100)
                .orderId(1L)
                .paymentMode(PaymentMode.CASH.toString())
                .paymentStatus("SUCCESS")
                .paymentDate(Instant.now())
                .referenceNumber("ref-123")
                .build();
    }

    private PaymentRequest getMockPaymentRequest() {
        return PaymentRequest.builder()
                .amount(100)
                .orderId(1L)
                .paymentMode(PaymentMode.CASH)
                .referenceNumber("ref-123")
                .build();
    }
}
