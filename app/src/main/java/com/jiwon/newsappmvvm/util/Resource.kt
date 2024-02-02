package com.jiwon.newsappmvvm.util

// generic type, error 처리를 할거냐, 로딩 처리를 할거냐
// handle error response, hadle the loading

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    //3개의 다른 클래스 정의 resource class 상속을 허용하는

    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}