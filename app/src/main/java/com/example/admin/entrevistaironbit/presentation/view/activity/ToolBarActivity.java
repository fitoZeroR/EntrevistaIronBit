package com.example.admin.entrevistaironbit.presentation.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.admin.entrevistaironbit.Aplicacion;
import com.example.admin.entrevistaironbit.R;
import com.example.admin.entrevistaironbit.presentation.di.HasComponent;
import com.example.admin.entrevistaironbit.presentation.di.components.ClienteComponent;
import com.example.admin.entrevistaironbit.presentation.di.components.DaggerClienteComponent;
import com.example.admin.entrevistaironbit.presentation.di.modules.ActivityModule;
import com.example.admin.entrevistaironbit.domain.modelo.modeloWS.Venue;
import com.example.admin.entrevistaironbit.presentation.presenter.MainPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.admin.entrevistaironbit.presentation.utilidades.Tools.mensajeInformativo;

public abstract class ToolBarActivity extends AppCompatActivity implements MainPresenter.View, HasComponent<ClienteComponent> {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    public MainPresenter mainPresenter;

    private ClienteComponent clienteComponent;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        ButterKnife.bind(this);

        initializeDagger();

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.blanco));
        mainPresenter.setView(this);
    }

    @Override
    public void onDestroy() {
        mainPresenter.terminate();
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return true;
    }

    @Override
    public ClienteComponent getComponent() {
        return clienteComponent;
    }

    @Override
    public void showLoading() {
        progressDialog = ProgressDialog.show(this, "", getString(R.string.prb_cargando), true, false);
    }

    @Override
    public void hideLoading() {
        progressDialog.dismiss();
    }

    @Override
    public void showError(String mensaje) {
        String mensajeAlerta = mensaje == null ? getString(R.string.error) : mensaje;
        mensajeInformativo(this, mensajeAlerta, true);
    }

    @Override
    public Context context() {
        return this;
    }

    protected abstract int getLayoutResource();

    private void initializeDagger() {
        clienteComponent = DaggerClienteComponent.builder()
                .mainComponent(((Aplicacion) getApplication()).getMainComponent())
                .activityModule(new ActivityModule(this))
                //.clienteModule(new ClienteModule())
                .build();

        getComponent().inject(this);
    }

    void addFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame_container, fragment, fragment.getClass().getSimpleName());

        transaction.commit();
    }

    @Override
    public void despliegaLugares(List<Venue> venueList) {}
}