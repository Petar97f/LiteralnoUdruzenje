package upp.backend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import upp.backend.dto.PaymentDTO;


@FeignClient(name = "kp")
public interface PaymentClient {

    @GetMapping(value = "/getTypes/{merchantId}")
    public PaymentDTO getPaymentTypes(@PathVariable("merchantId") String merchantId);


}
