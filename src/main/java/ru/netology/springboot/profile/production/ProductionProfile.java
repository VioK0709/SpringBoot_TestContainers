package ru.netology.springboot.profile.production;

import ru.netology.springboot.profile.SystemProfile;

public class ProductionProfile implements SystemProfile {
    @Override
    public String getProfile() {
        return "Current profile is production";
    }
}