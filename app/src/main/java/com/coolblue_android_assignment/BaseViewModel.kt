package com.coolblue_android_assignment

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Consumer

abstract class BaseViewModel : ViewModel() {

    private val disposables = CompositeDisposable()

    protected fun <T> executeSingle(
        loadingConsumer: Consumer<Disposable>,
        successConsumer: Consumer<T>,
        throwableConsumer: Consumer<Throwable>,
        source: Single<T>
    ) {
        val observable = source
            .doOnSubscribe(loadingConsumer)
        disposables.add(observable.subscribe(successConsumer, throwableConsumer))
    }

    private fun dispose() {
        if (!disposables.isDisposed)
            disposables.dispose()
    }

    override fun onCleared() {
        super.onCleared()
        dispose()
    }
}
