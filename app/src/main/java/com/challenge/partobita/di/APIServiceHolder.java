package com.challenge.partobita.di;

import androidx.annotation.Nullable;


import com.challenge.data.api.MApi;

public class APIServiceHolder {

    private MApi apiService;

    @Nullable
    MApi apiService() {
        return apiService;
    }

    void setAPIService(MApi apiService) {
        this.apiService = apiService;
    }
}