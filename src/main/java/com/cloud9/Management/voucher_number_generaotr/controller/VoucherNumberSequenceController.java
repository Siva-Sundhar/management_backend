package com.cloud9.Management.voucher_number_generaotr.controller;

import com.cloud9.Management.voucher_number_generaotr.service.VoucherNumberSequenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sequence")
@RequiredArgsConstructor
public class VoucherNumberSequenceController {

    private final VoucherNumberSequenceService sequenceService;

    @GetMapping("/preview/{prefix}")
    public ResponseEntity<String> getVoucherNumberSequence(@PathVariable String prefix) {
        String preview = sequenceService.getPreviewNumber(prefix.toUpperCase());
        return ResponseEntity.ok(preview);
    }
}
