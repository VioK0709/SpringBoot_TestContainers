package ru.netology.springboot.profile.dev;

import ru.netology.springboot.profile.SystemProfile;

public class DevProfile implements SystemProfile {
    @Override
    public String getProfile() {
        return "Current profile is dev";
    }
}