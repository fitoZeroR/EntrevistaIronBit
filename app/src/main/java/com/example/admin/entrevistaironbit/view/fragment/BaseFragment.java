package com.example.admin.entrevistaironbit.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.admin.entrevistaironbit.Aplicacion;
import com.example.admin.entrevistaironbit.di.HasComponent;
import com.example.admin.entrevistaironbit.di.components.DaggerFragmentComponent;
import com.example.admin.entrevistaironbit.di.components.FragmentComponent;
import com.example.admin.entrevistaironbit.di.modules.FragmentModule;

import java.util.Objects;

/**
 * Base {@link android.app.Fragment} class for every fragment in this application.
 */
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