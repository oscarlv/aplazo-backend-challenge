package com.aplazo.challenge.service;

import com.aplazo.challenge.dto.InstallmentResponse;
import com.aplazo.challenge.dto.LoanRequest;
import com.aplazo.challenge.dto.LoanResponse;
import com.aplazo.challenge.dto.PaymentPlan;
import com.aplazo.challenge.entity.Customer;
import com.aplazo.challenge.entity.Installment;
import com.aplazo.challenge.entity.Loan;
import com.aplazo.challenge.exception.CustomerNotFoundException;
import com.aplazo.challenge.exception.InvalidLoanRequestException;
import com.aplazo.challenge.exception.LoanNotFoundException;
import com.aplazo.challenge.model.InstallmentStatus;
import com.aplazo.challenge.model.LoanStatus;
import com.aplazo.challenge.repository.CustomerRepository;
import com.aplazo.challenge.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.aplazo.challenge.exception.ErrorCode.INVALID_LOAN_REQUEST_MESSAGE;
import static com.aplazo.challenge.util.ServiceUtils.validateAndParseCustomerID;

@RequiredArgsConstructor
@Slf4j
@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;

    @Override
    public LoanResponse createLoan(LoanRequest request) {
        log.info("Creating a loan for customer id {}", request.getCustomerId());

        UUID customerId = validateAndParseCustomerID(request.getCustomerId());
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(request.getCustomerId()));

        if (request.getAmount() > customer.getAvailableCreditLineAmount()) {

            throw new InvalidLoanRequestException(INVALID_LOAN_REQUEST_MESSAGE);
        }

        double interestRate = determineInterestRate(customer);
        double commission = request.getAmount() * interestRate;
        double total = request.getAmount() + commission;
        double installmentAmount = total / 5;

        Loan loan = Loan.builder()
                .id(UUID.randomUUID())
                .customer(customer)
                .amount(request.getAmount())
                .status(LoanStatus.ACTIVE)
                .commissionAmount(request.getAmount() * 0.10)
                .build();

        customer.setAvailableCreditLineAmount(
                customer.getAvailableCreditLineAmount() - request.getAmount()
        );
        customerRepository.save(customer);

        List<Installment> installments = generateInstallments(loan, installmentAmount);
        loan.setInstallments(installments);

        Loan saved = loanRepository.save(loan);
        return toLoanResponse(saved);
    }

    @Override
    public LoanResponse getLoanById(String loanId) {
        UUID uuid = validateAndParseLoanId(loanId);

        Loan loan = loanRepository.findById(uuid)
                .orElseThrow(() -> new LoanNotFoundException(loanId));

        return toLoanResponse(loan);
    }

    private UUID validateAndParseLoanId(String loanId) {
        try {
            log.info("Validating loan id {}", loanId);
            return UUID.fromString(loanId);
        } catch (IllegalArgumentException ex) {
            log.warn("Loan ID {} is not a valid UUID", loanId, ex);
            throw new LoanNotFoundException(loanId);
        }
    }

    private double determineInterestRate(Customer customer) {

        //TODO: confirmar que pasa con la regla basada en el ID del customer si el type es UUID

        String name = customer.getFirstName().toUpperCase();
        if (name.startsWith("C") || name.startsWith("L") || name.startsWith("H")) {
            return 0.13;
        } else {
            return 0.16;
        }
    }

    private List<Installment> generateInstallments(Loan loan, double amountPerInstallment) {
        List<Installment> installments = new ArrayList<>();
        LocalDate startDate = LocalDate.now().plusWeeks(2);
        for (int i = 0; i < 5; i++) {
            Installment inst = Installment.builder()
                    .loan(loan)
                    .amount(amountPerInstallment)
                    .scheduledPaymentDate(startDate.plusWeeks(i * 2))
                    .status(i == 0 ? InstallmentStatus.NEXT : InstallmentStatus.PENDING)
                    .build();
            installments.add(inst);
        }
        return installments;
    }

    private LoanResponse toLoanResponse(Loan loan) {
        LoanResponse response = LoanResponse.builder()
                .id(loan.getId().toString())
                .customerId(loan.getCustomer().getId().toString())
                .amount(loan.getAmount())
                .status(loan.getStatus())
                .createdAt(loan.getCreatedAt())
                .build();

        PaymentPlan plan = PaymentPlan
                .builder()
                .commissionAmount(loan.getCommissionAmount())
                .build();

        List<InstallmentResponse> installmentResponses = loan.getInstallments().stream().map(inst -> InstallmentResponse.builder()
                .amount(inst.getAmount())
                .scheduledPaymentDate(inst.getScheduledPaymentDate())
                .status(inst.getStatus()).build()).toList();

        plan.setInstallments(installmentResponses);
        response.setPaymentPlan(plan);

        return response;
    }
}
