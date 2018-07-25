package com.example.admin.entrevistaironbit.presenter;

import com.example.admin.entrevistaironbit.interactor.MainInteractor;
import com.example.admin.entrevistaironbit.modelo.Lugar;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class MainPresenter extends Presenter<MainPresenter.View> {
    private MainInteractor mainInteractor;

    @Inject
    public MainPresenter(MainInteractor mainInteractor) {
        this.mainInteractor = mainInteractor;
    }

    @Override
    public void terminate() {
        super.terminate();
        setView(null);
    }

    public void disparaServicioLugares() {
        getView().showLoading();
        Disposable disposable = mainInteractor.consultaListaLugares().subscribe(lugar -> {
            if (lugar == null) {
                getView().showError(null);
            } else {
                Logger.json(new Gson().toJson(lugar));
                getView().hideLoading();
                getView().despliegaLugares(lugar);
            }
        }, throwable -> {
            getView().hideLoading();
            getView().showError(throwable.getMessage());
        });

        addDisposableObserver(disposable);
    }

    public interface View extends Presenter.View {
        void showLoading();
        void hideLoading();
        void showError(String mensaje);
        void despliegaLugares(Lugar registroList);
    }
}