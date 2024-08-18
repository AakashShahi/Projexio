package com.aakash.Projexio.service;

import com.aakash.Projexio.model.PlanType;
import com.aakash.Projexio.model.Subscription;
import com.aakash.Projexio.model.User;

public interface SubscriptionService {

    Subscription createSubscription(User user);
    Subscription getUserSubscription(Long userId)throws Exception;
    Subscription upgradeSubscription(Long userId, PlanType planType);
    boolean isValid(Subscription subscription);
}
