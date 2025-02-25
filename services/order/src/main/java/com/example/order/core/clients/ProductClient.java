package com.example.order.core.clients;

import com.example.order.core.exceptions.BusinessException;
import com.example.order.entities.requests.PurchaseRequest;
import com.example.order.entities.responses.PurchaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductClient {

    @Value("${application.config.product-url}")
    private String url;
    private final RestTemplate restTemplate;

    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> purchaseRequests) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpEntity<List<PurchaseRequest>> requestEntity = new HttpEntity<>(purchaseRequests, headers);
        ParameterizedTypeReference<List<PurchaseResponse>> responseType = new ParameterizedTypeReference<>() {};
        ResponseEntity<List<PurchaseResponse>> responseEntity = restTemplate.exchange(
                url + "/purchase",
                HttpMethod.POST,
                requestEntity,
                responseType
        );

        if(responseEntity.getStatusCode().isError()) {
            throw new BusinessException("Error occurred while purchasing products: "+ responseEntity.getStatusCode());
        }

        return responseEntity.getBody();
    }
}
