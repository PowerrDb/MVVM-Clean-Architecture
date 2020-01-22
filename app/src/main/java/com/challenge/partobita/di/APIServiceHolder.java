package com.challenge.partobita.di;

import androidx.annotation.Nullable;


import com.challenge.data.api.ListCharacterApi;

public class APIServiceHolder {

    private ListCharacterApi apiService;

    @Nullable
    ListCharacterApi apiService() {
        return apiService;
    }

    void setAPIService(ListCharacterApi apiService) {
        this.apiService = apiService;
    }
}