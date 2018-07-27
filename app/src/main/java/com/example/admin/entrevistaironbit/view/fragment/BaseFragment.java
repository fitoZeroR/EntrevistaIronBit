package com.example.admin.entrevistaironbit.view.fragment;

import android.support.v4.app.Fragment;

import com.example.admin.entrevistaironbit.di.HasComponent;

import java.util.Objects;

/**
 * Base {@link android.app.Fragment} class for every fragment in this application.
 */
public abstract class BaseFragment extends Fragment {
  /**
   * Gets a component for dependency injection by its type.
   */
  @SuppressWarnings("unchecked")
  <C> C getComponent(Class<C> componentType) {
    return componentType.cast(((HasComponent<C>) Objects.requireNonNull(getActivity())).getComponent());
  }
}