package com.example.eventhunt.networkClasses.retrofit

class RequestUrl {

    companion object {
        const val BASE_URL = "https://api.insider.in/"
        const val DATA_URL = "home?norm=1&filterBy=go-out&city=%s"
    }
}