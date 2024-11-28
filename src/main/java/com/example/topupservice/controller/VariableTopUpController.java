package com.example.topupservice.controller;

import com.example.topupservice.dto.BalanceResponse;
import com.example.topupservice.dto.TopUpRequest;
import com.example.topupservice.dto.TopUpResponse;
import com.example.topupservice.service.VariableTopUpService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/variable-topup")
public class VariableTopUpController {

    private final VariableTopUpService topUpService;

    public VariableTopUpController(VariableTopUpService topUpService) {
        this.topUpService = topUpService;
    }

    @GetMapping("/balance")
    public BalanceResponse getAccountBalance() {
        return topUpService.getAccountBalance();
    }

    @PostMapping("/topup")
    public TopUpResponse performTopUp(@RequestBody TopUpRequest request) {
        return topUpService.performTopUp(request);
    }
}
