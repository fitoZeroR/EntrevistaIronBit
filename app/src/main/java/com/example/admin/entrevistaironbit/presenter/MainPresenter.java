package com.example.admin.entrevistaironbit.presenter;

import com.example.admin.entrevistaironbit.interactor.MainInteractor;
import com.example.admin.entrevistaironbit.modelo.modeloWS.Venue;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class MainPresenter extends Presenter<MainPresenter.View> implements MainInteractor.MainInteractorListener {
    private final MainInteractor mainInteractor;

    @Inject
    public MainPresenter(MainInteractor mainInteractor) {
        this.mainInteractor = mainInteractor;
        this.mainInteractor.setMainInteractorListener(this);
    }

    @Override
    public void terminate() {
        super.terminate();
        setView(null);
    }

    public void disparaServicioLugares(String latLong, String fecha) {
        getView().showLoading();
        Disposable disposable = mainInteractor.consultaListaLugares(latLong, fecha).subscribe(lugar -> {
            if (lugar == null) {
                getView().showError(null);
            } else {
                Logger.json(new Gson().toJson(lugar));

                mainInteractor.ordenaRegistros(lugar.getResponse().getVenues(), latLong);
            }
        }, throwable -> {
            getView().hideLoading();
            getView().showError(throwable.getMessage());
        });

        addDisposableObserver(disposable);
    }

    @Override
    public void onOrderRegistros(List<Venue> venueList) {
        getView().hideLoading();
        getView().despliegaLugares(venueList);
    }

    public interface View extends Presenter.View {
        void showLoading();
        void hideLoading();
        void showError(String mensaje);
        void despliegaLugares(List<Venue> venueList);
    }
}