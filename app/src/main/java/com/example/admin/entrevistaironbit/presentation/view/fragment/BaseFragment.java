package com.example.admin.entrevistaironbit.presentation.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.admin.entrevistaironbit.Aplicacion;
import com.example.admin.entrevistaironbit.presentation.di.HasComponent;
import com.example.admin.entrevistaironbit.presentation.di.components.DaggerFragmentComponent;
import com.example.admin.entrevistaironbit.presentation.di.components.FragmentComponent;
import com.example.admin.entrevistaironbit.presentation.di.modules.FragmentModule;

import java.util.Objects;

public abstract class BaseFragment extends Fragment implements HasComponent<FragmentComponent> {
  private FragmentComponent fragmentComponent;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    initializeDagger();
  }

  @Override
  public FragmentComponent getComponent() {
    return fragmentComponent;
  }

  private void initializeDagger() {
    fragmentComponent = DaggerFragmentComponent.builder()
            .fragmentModule(new FragmentModule(this))
            .mainComponent(((Aplicacion) Objects.requireNonNull(getActivity()).getApplication()).getMainComponent())
            .build();
  }
}