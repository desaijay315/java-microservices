package com.example.paymentservice.service;

import com.example.paymentservice.entity.Payment;
import com.example.paymentservice.model.PaymentMode;
import com.example.paymentservice.model.PaymentRequest;
import com.example.paymentservice.model.PaymentResponse;
import com.example.paymentservice.repository.PaymentDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService{
    private final PaymentDetailsRepository paymentDetailsRepository;

    public PaymentServiceImpl(PaymentDetailsRepository paymentDetailsRepository) {
        this.paymentDetailsRepository = paymentDetailsRepository;
    }

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
        Payment payment = paymentDetailsRepository.findByOrderId(Long.parseLong(orderId));
        return PaymentResponse.builder()
                .paymentId(payment.getId())
                .status(payment.getPaymentStatus())
                .paymentMode(PaymentMode.valueOf(payment.getPaymentMode()))
                .amount(payment.getAmount())
                .paymentDate(payment.getPaymentDate())
                .orderId(payment.getOrderId())
                .build();
    }
}
