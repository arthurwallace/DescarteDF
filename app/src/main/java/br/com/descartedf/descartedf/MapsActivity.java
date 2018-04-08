package br.com.descartedf.descartedf;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        float zoomLevel = 15.0f;

        //TAGUATINGA
        LatLng jarjourTaguatinga = new LatLng(-15.839957, -48.050569);
        mMap.addMarker(new MarkerOptions().position(jarjourTaguatinga).title("Posto Jarjour"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jarjourTaguatinga, zoomLevel));

        //SOBRADINHO
        LatLng informaticaJunior = new LatLng(-15.691507, -47.825692);
        mMap.addMarker(new MarkerOptions().position(informaticaJunior).title("Informática do Junior"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(informaticaJunior, zoomLevel));

        //ASA SUL
        LatLng marista = new LatLng(-15.825287, -47.899272);
        mMap.addMarker(new MarkerOptions().position(marista).title("Colégio Maristinha de Brasília"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marista, zoomLevel));

        LatLng anoes = new LatLng(-15.825743, -47.922801);
        mMap.addMarker(new MarkerOptions().position(anoes).title("Posto dos Anões"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(anoes, zoomLevel));

        LatLng jarjourAsaSul = new LatLng(-15.820127, -47.908312);
        mMap.addMarker(new MarkerOptions().position(jarjourAsaSul).title("Posto Jarjour"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jarjourAsaSul, zoomLevel));

        LatLng impConcursos = new LatLng(-15.811537, -47.883919);
        mMap.addMarker(new MarkerOptions().position(impConcursos).title("IMP Concursos"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(impConcursos, zoomLevel));

        LatLng brasasEnglishAsaSul = new LatLng(-15.821290, -47.910395);
        mMap.addMarker(new MarkerOptions().position(brasasEnglishAsaSul).title("Brasas English"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(brasasEnglishAsaSul, zoomLevel));

        LatLng clubeCoat = new LatLng(-15.817215, -47.874181);
        mMap.addMarker(new MarkerOptions().position(clubeCoat).title("Academia Clube Coat"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(clubeCoat, zoomLevel));

        // ASA NORTE
        LatLng laBoutique = new LatLng(-15.747372, -47.884524);
        mMap.addMarker(new MarkerOptions().position(laBoutique).title("Padaria La Boutique"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(laBoutique, zoomLevel));

        LatLng jarjourAsaNorte = new LatLng(-15.768320, -47.881765);
        mMap.addMarker(new MarkerOptions().position(jarjourAsaNorte).title("Posto Jarjour"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jarjourAsaNorte, zoomLevel));

        LatLng restauraCar = new LatLng(-15.782655, -47.884365);
        mMap.addMarker(new MarkerOptions().position(restauraCar).title("RestauraCar"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(restauraCar, zoomLevel));

        LatLng brasasEnglishAsaNorte = new LatLng(-15.750847, -47.885664);
        mMap.addMarker(new MarkerOptions().position(brasasEnglishAsaNorte).title("Brasas English"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(brasasEnglishAsaNorte, zoomLevel));
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    LatLng latLng = new LatLng(latitude, longitude);
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
                        String str = addressList.get(0).getLocality();
                        MarkerOptions markerOptions = new MarkerOptions();
                        mMap.addMarker(new MarkerOptions()
                                .position(latLng).title("Você está aqui")
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.localatual)));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            });
        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();
                            LatLng latLng = new LatLng(latitude, longitude);
                            Geocoder geocoder = new Geocoder(getApplicationContext());
                            try {
                                List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
                                String str = addressList.get(0).getLocality();
                                MarkerOptions markerOptions = new MarkerOptions();
                                mMap.addMarker(new MarkerOptions().position(latLng).title("Local atual").icon(BitmapDescriptorFactory.fromResource(R.drawable.localatual)));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onStatusChanged(String s, int i, Bundle bundle) {

                        }

                        @Override
                        public void onProviderEnabled(String s) {

                        }

                        @Override
                        public void onProviderDisabled(String s) {

                        }
                    }
            );
        } else {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("GPS desativado!");
            alertDialog.setMessage("Ativar GPS?");
            alertDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
            });
            alertDialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            alertDialog.show();
        }
    }
}
