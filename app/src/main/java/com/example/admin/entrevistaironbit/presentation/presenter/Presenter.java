package com.example.admin.entrevistaironbit.presentation.presenter;

import android.content.Context;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

abstract class Presenter<T extends Presenter.View> {

  private final CompositeDisposable compositeDisposable = new CompositeDisposable();

  private T view;

  T getView() {
    return view;
  }

  public void setView(T view) {
    this.view = view;
  }

  void terminate() {
    dispose();
  }

  void addDisposableObserver(Disposable disposable) {
    compositeDisposable.add(disposable);
  }

  private void dispose() {
    if (!compositeDisposable.isDisposed()) {
      compositeDisposable.dispose();
    }
  }

  protected interface View {

    Context context();
  }
}