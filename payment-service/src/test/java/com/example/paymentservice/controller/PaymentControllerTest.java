package com.example.paymentservice.controller;

import com.example.paymentservice.entity.Payment;
import com.example.paymentservice.model.PaymentMode;
import com.example.paymentservice.model.PaymentRequest;
import com.example.paymentservice.model.PaymentResponse;
import com.example.paymentservice.repository.PaymentDetailsRepository;
import com.example.paymentservice.service.PaymentService;
import com.example.paymentservice.service.PaymentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PaymentControllerTest {

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private PaymentService paymentService;

    @Test
    void testDoPayment() {
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .orderId(1234L)
                .amount(100L)
                .paymentMode(PaymentMode.CASH)
                .referenceNumber("ABCD1234")
                .build();

        when(paymentService.doPayment(Mockito.any(PaymentRequest.class))).thenReturn(1L);

        ResponseEntity<Long> responseEntity = paymentController.doPayment(paymentRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1L, responseEntity.getBody());
    }


    @Test
    void testGetPaymentDetailsByOrderId() {
        Instant paymentDate = Instant.parse("2023-05-06T10:45:59.632336Z");

        PaymentResponse expectedResponse = PaymentResponse.builder()
                .paymentId(1L)
                .status("SUCCESS")
                .paymentMode(PaymentMode.CASH)
                .amount(100L)
                .paymentDate(paymentDate)
                .orderId(1234L)
                .build();

        when(paymentService.getPaymentDetailsByOrderId(Mockito.anyString())).thenReturn(expectedResponse);

        ResponseEntity<PaymentResponse> actualResponseEntity = paymentController.getPaymentDetailsByOrderId("1234");

        PaymentResponse actualResponse = actualResponseEntity.getBody();

        assertEquals(HttpStatus.OK, actualResponseEntity.getStatusCode());
        assertEquals(expectedResponse, actualResponse);
    }
}
