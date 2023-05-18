package com.example.paymentservice.service;

import com.example.paymentservice.entity.Payment;
import com.example.paymentservice.model.PaymentMode;
import com.example.paymentservice.model.PaymentRequest;
import com.example.paymentservice.model.PaymentResponse;
import com.example.paymentservice.repository.PaymentDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentDetailsRepository paymentDetailsRepository;

    @Override
    public long doPayment(PaymentRequest paymentRequest) {
        Payment payment = Payment.builder()
                .orderId(paymentRequest.getOrderId())
                .amount(paymentRequest.getAmount())
                .paymentMode(paymentRequest.getPaymentMode().toString())
                .referenceNumber(paymentRequest.getReferenceNumber())
                .paymentDate(Instant.now())
                .paymentStatus("SUCCESS")
                .build();
        Payment savedPayment = paymentDetailsRepository.save(payment);
        return savedPayment.getId();
    }

    @Override
    public PaymentResponse getPaymentDetailsByOrderId(String orderId) {
        log.info("Getting payment details for the Order Id: {}", orderId);
        Payment paymentDetails = paymentDetailsRepository.findByOrderId(Long.valueOf(orderId));
        log.info("Getting Payment paymentDetails: {}", paymentDetails);

        PaymentResponse paymentResponse
                = PaymentResponse.builder()
                .paymentId(paymentDetails.getId())
                .paymentMode(PaymentMode.valueOf(paymentDetails.getPaymentMode()))
                .paymentDate(paymentDetails.getPaymentDate())
                .orderId(paymentDetails.getOrderId())
                .status(paymentDetails.getPaymentStatus())
                .amount(paymentDetails.getAmount())
                .build();
        log.info("Getting Payment Response: {}", paymentResponse);

        return paymentResponse;

    }
}
