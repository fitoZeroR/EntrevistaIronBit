package com.example.admin.entrevistaironbit.view.activity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.admin.entrevistaironbit.R;

import java.util.Objects;

public class DetalleActivity extends ToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.txt_detalle));
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_arrow);
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
    protected int getLayoutResource() {
        return R.layout.activity_detalle;
    }

}
