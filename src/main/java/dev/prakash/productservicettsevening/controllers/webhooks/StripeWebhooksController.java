package dev.prakash.productservicettsevening.controllers.webhooks;

import com.stripe.model.Event;
import com.stripe.net.Webhook;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhooks/stripe")
public class StripeWebhooksController {
    @PostMapping("")
    public void handleWebhookRequest(@RequestBody Event webhookEvent){
        // Do all stuff related to webhook here
        System.out.println("=======Webhook Endpoint hit=========");
    }
}
