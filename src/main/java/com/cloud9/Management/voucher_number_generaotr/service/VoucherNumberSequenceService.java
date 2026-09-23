package com.cloud9.Management.voucher_number_generaotr.service;

import com.cloud9.Management.voucher_number_generaotr.entity.VoucherNumberSequence;
import com.cloud9.Management.voucher_number_generaotr.repository.VoucherNumberSequenceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class VoucherNumberSequenceService {

    private final VoucherNumberSequenceRepository sequenceRepository;

    // Preview VoucherNumber
    @Transactional(readOnly = true)
    public String getPreviewNumber(String prefix) {
        String financialYear = getFinancialYear();

        VoucherNumberSequence sequence = sequenceRepository.findByPrefixAndFinancialYear(prefix, financialYear).orElse(null);

        long nextNumber = (sequence != null) ? sequence.getLastNumber() + 1 : 1L;
        log.info("getPreviewNumber: nextNumber={}", nextNumber);

        return String.format("%s/%04d/%s", prefix, nextNumber, financialYear);
    }

    @Transactional
    public String saveVoucherNumber(String prefix) {
        String financialYear = getFinancialYear();

        VoucherNumberSequence seq =
                sequenceRepository.findByPrefixAndFinancialYearWithLock(prefix, financialYear).orElseGet(() ->
                        {
                            VoucherNumberSequence sequence = new VoucherNumberSequence();
                            sequence.setPrefix(prefix);
                            sequence.setFinancialYear(financialYear);
                            sequence.setLastNumber(0L);
                            return sequenceRepository.save(sequence);
                        }
                );
        long nextNumber = seq.getLastNumber() + 1;
        seq.setLastNumber(nextNumber);
        sequenceRepository.save(seq);
        return String.format("%s/%04d/%s", prefix, nextNumber, financialYear);
    }

    private String getFinancialYear() {
        LocalDate currentDay = LocalDate.now();
        int year = currentDay.getYear();

        if (currentDay.getMonthValue() < 4) {
            return String.format("%02d-%2d", (year - 1) % 100, year % 100);
        }
        return String.format("%02d-%2d", year % 100, (year + 1) % 100);
    }
}
