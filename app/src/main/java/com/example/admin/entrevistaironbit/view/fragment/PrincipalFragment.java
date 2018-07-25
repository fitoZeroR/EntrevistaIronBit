package com.example.admin.entrevistaironbit.view.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.entrevistaironbit.R;
import com.example.admin.entrevistaironbit.modelo.Lugar;
import com.example.admin.entrevistaironbit.view.adapter.CustomAdapterPrincipal;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class PrincipalFragment extends Fragment {
    @BindView(R.id.rcv_principal_id)
    RecyclerView rcvPrincipal;

    private List<Lugar> listaLugares;

    private static PrincipalFragment instancia;

    public PrincipalFragment(List<Lugar> listaLugares) {
        this.listaLugares = listaLugares;
    }

    public static PrincipalFragment initInstance(List<Lugar> listaLugares) {
        PrincipalFragment pagosFragment = new PrincipalFragment(listaLugares);
        instancia = pagosFragment;
        return pagosFragment;
    }

    public static PrincipalFragment newInstance(List<Lugar> listaLugares) {
        if (instancia == null) {
            instancia = new PrincipalFragment(listaLugares);
        }
        return instancia;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_principal, container, false);
        ButterKnife.bind(this, view);

        CustomAdapterPrincipal customAdapterPrincipal = new CustomAdapterPrincipal(listaLugares);
        customAdapterPrincipal.getViewClickedObservable()
                .subscribe(itemView -> {
                    /*Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(phoneNumberFormat(tituloTelefonos[rcvPrincipal.getChildAdapterPosition(itemView)]));
                    startActivity(intent);*/
                });
        rcvPrincipal.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvPrincipal.setHasFixedSize(true);
        rcvPrincipal.swapAdapter(customAdapterPrincipal, true);

        return view;
    }

}