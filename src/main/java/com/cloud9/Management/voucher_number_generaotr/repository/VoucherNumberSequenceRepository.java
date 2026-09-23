package com.cloud9.Management.voucher_number_generaotr.repository;

import com.cloud9.Management.voucher_number_generaotr.entity.VoucherNumberSequence;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VoucherNumberSequenceRepository extends JpaRepository<VoucherNumberSequence, Long> {

    // For Preview only read
    Optional<VoucherNumberSequence> findByPrefixAndFinancialYear(String prefix, String financialYear);

    // Final Save(Pessimistic Lock - Prevents Concurrent Duplicates Updates)
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM VoucherNumberSequence s WHERE s.prefix = :prefix AND s.financialYear = :financialYear")
    Optional<VoucherNumberSequence> findByPrefixAndFinancialYearWithLock(String prefix,  String financialYear);
}
