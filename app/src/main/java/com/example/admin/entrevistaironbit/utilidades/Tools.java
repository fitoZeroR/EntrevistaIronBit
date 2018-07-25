package com.example.admin.entrevistaironbit.utilidades;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.admin.entrevistaironbit.R;

public class Tools {
    public static void mensajeInformativo(Activity activity, String mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(mensaje);
        builder.setPositiveButton(activity.getString(R.string.action_accept), (dialog, which) -> {
            dialog.dismiss();
            activity.finish();
        });
        builder.setCancelable(false);
        builder.create();
        builder.show();
    }

    public static boolean isConnectionNetwork(Activity activity) {
        NetworkInfo netInfo = null;
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            netInfo = cm.getActiveNetworkInfo();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return netInfo != null && netInfo.isAvailable();
    }

    public static double distanciaCoord(double lat1, double lng1, double lat2, double lng2) {
        double radioTierra = 6371;//en kilómetros
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
        double distancia = radioTierra * va2;

        return distancia;
    }
}