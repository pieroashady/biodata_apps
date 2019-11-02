package com.pieroashady.biodataapps;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.pieroashady.biodataapps.ApiService.APIClient;
import com.pieroashady.biodataapps.ApiService.APIInterfaceRest;
import com.pieroashady.biodataapps.Model.AllData.Datum;
import com.pieroashady.biodataapps.Model.AllData.Status;
import com.pieroashady.biodataapps.Model.ById.StatusId;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;
    EditText editNim, editNama, editAlamat;
    String nim, nama, alamat;
    LinearLayout linearLayout;
    Context context = this;
    TextView txtView;
    ProgressDialog loading;
    APIInterfaceRest apiInterfaceRest;
    Status status;
    List<Datum> dataMahasiswa;
    TableLayout tableLayout;
    ArrayList<Button> editButton = new ArrayList<>();
    ArrayList<Button> deleteButton = new ArrayList<>();
    TextView headerId, headerNim, headerNama, headerAlamat, headerAction,
            viewId, viewNim, viewNama, viewAlamat, viewAction;
    TableRow tableRow;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.linear);


        viewId = new TextView(MainActivity.this);
        viewNim = new TextView(MainActivity.this);
        viewNama = new TextView(MainActivity.this);
        viewAlamat = new TextView(MainActivity.this);

        tableLayout = findViewById(R.id.tableLayout);
        tableRow = new TableRow(this);
        tableRow.setBackgroundColor(Color.CYAN);

        headerId = new TextView(MainActivity.this);
        headerNim = new TextView(MainActivity.this);
        headerNama = new TextView(MainActivity.this);
        headerAlamat = new TextView(MainActivity.this);
        headerAction = new TextView(MainActivity.this);

        headerId.setText("No");
        headerId.setTextColor(Color.WHITE);
        headerId.setPadding(5, 1, 5, 1);
        headerNim.setText("NIM");
        headerNim.setTextColor(Color.WHITE);
        headerNim.setPadding(5, 1, 5, 1);
        headerNama.setText("Nama");
        headerNama.setPadding(5, 1, 5, 1);
        headerAlamat.setText("Alamat");
        headerAlamat.setPadding(5, 1, 5, 1);
        headerAction.setText("Action");
        headerAction.setPadding(5, 1, 5, 1);

        tableRow.addView(headerId);
        tableRow.addView(headerNim);
        tableRow.addView(headerNama);
        tableRow.addView(headerAlamat);
        tableRow.addView(headerAction);

        tableLayout.addView(tableRow, new TableLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener((v) -> {
            showAlert();
        });

        getData();
    }

    private void getData() {
        apiInterfaceRest = APIClient.getClientWithApi().create(APIInterfaceRest.class);

        showLoading("Get Data ...");
        Call<Status> call = apiInterfaceRest.getData();
        call.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                loading.dismiss();

                status = response.body();
                dataMahasiswa = response.body().getData();

                if (status.getStatus().equals(1)) {
                    for (int i = 0; i < dataMahasiswa.size(); i++) {
                        int no = i;
                        String nim = dataMahasiswa.get(i).getNim();
                        String nama = dataMahasiswa.get(i).getNama();
                        String alamat = dataMahasiswa.get(i).getAlamat();
                        String id = dataMahasiswa.get(i).getId().toString();
                        int idMhs = dataMahasiswa.get(i).getId();

                        layout(no, id, nim, nama, alamat);

                        editButton.add(i, new Button(MainActivity.this));
                        editButton.get(i).setId(Integer.parseInt(nim));
                        editButton.get(i).setTag("Edit");
                        editButton.get(i).setText("Edit");
                        editButton.get(i).setBackgroundResource(R.drawable.btn_rounded_accent);
                        editButton.get(i).setTextColor(Color.WHITE);
                        editButton.get(i).setOnClickListener((v) -> {
                            getDataById(idMhs);
                        });
                        tableRow.addView(editButton.get(i));

                        deleteButton.add(i, new Button(MainActivity.this));
                        deleteButton.get(i).setId(Integer.parseInt(nim));
                        deleteButton.get(i).setTag("Delete");
                        deleteButton.get(i).setText("Delete");
                        deleteButton.get(i).setBackgroundResource(R.drawable.btn_rounded_accent);
                        deleteButton.get(i).setTextColor(Color.WHITE);
                        deleteButton.get(i).setOnClickListener((v) -> {
                            deleteData();
                        });
                        tableRow.addView(deleteButton.get(i));

                        tableLayout.addView(tableRow, new TableLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.MATCH_PARENT));
                    }
                } else {
                    loading.dismiss();
                    showSnackbar("Something went wrong...");
                }
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                loading.dismiss();
                Log.d("debug", t.getMessage());
                showSnackbar("Connection Error, try again later");
            }
        });
    }

    private void deleteData() {

    }

    private void getDataById(int id) {
        apiInterfaceRest = APIClient.getClientWithApi().create(APIInterfaceRest.class);

        Call<StatusId> call = apiInterfaceRest.getDataById(id);
        call.enqueue(new Callback<StatusId>() {
            @Override
            public void onResponse(Call<StatusId> call, Response<StatusId> response) {
                StatusId statusId;
                statusId = response.body();
                if (statusId.getStatus().equals(1)){
                    nim = statusId.getData().getNim();
                    showSnackbar("Edit NIM " + nim + " ?");
                } else {
                    showSnackbar("NIM not found");
                }
            }

            @Override
            public void onFailure(Call<StatusId> call, Throwable t) {
                Log.d("DEBUG", t.getMessage());
                showSnackbar("Connection Error, try again");
            }
        });
    }

    public void showAlert() {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.form, null);
        editNim = view.findViewById(R.id.inputNim);
        txtView = view.findViewById(R.id.dialogTitle);

        AlertDialog.Builder builders = new AlertDialog.Builder(MainActivity.this);
        builders.setCancelable(false)
                .setView(view)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        nim = editNim.getText().toString();
                        if (nim.equals("")) {
                            showSnackbar("Save failed, please fill the field");
                        } else {
                            showSnackbar("NIM " + nim + " has been successfully saved");
                            startActivity(getIntent());
                        }
                    }
                });
        builders.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builders.show();
    }

    public void showSnackbar(String message) {
        Snackbar snackbar = Snackbar.make(linearLayout, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    private void showLoading(String message) {
        loading = new ProgressDialog(MainActivity.this);
        loading.setMessage(message);
        loading.show();
    }

    private void layout(int no, String rowNumber, String nim, String nama, String alamat) {
        tableRow = new TableRow(MainActivity.this);

        if (no % 2 == 0) {
            tableRow.setBackgroundColor(Color.LTGRAY);
        }

        viewId = new TextView(MainActivity.this);
        viewId.setText(rowNumber);
        viewId.setPadding(5, 1, 5, 1);
        tableRow.addView(viewId);

        viewNim = new TextView(MainActivity.this);
        viewNim.setText(nim);
        viewNim.setPadding(5, 1, 5, 1);
        tableRow.addView(viewNim);

        viewNama = new TextView(MainActivity.this);
        viewNama.setText(nama);
        viewNama.setPadding(5, 1, 5, 1);
        tableRow.addView(viewNama);

        viewAlamat = new TextView(MainActivity.this);
        viewAlamat.setText(alamat);
        viewAlamat.setPadding(5, 1, 5, 1);
        tableRow.addView(viewAlamat);
    }

    private void headerLayout() {
        TableRow tableRow = new TableRow(this);

        headerId.setText("No");
        headerId.setPadding(5, 1, 5, 1);
        headerNim.setText("NIM");
        headerNim.setPadding(5, 1, 5, 1);
        headerNama.setText("Nama");
        headerNama.setPadding(5, 1, 5, 1);
        headerAlamat.setText("Alamat");
        headerAlamat.setPadding(5, 1, 5, 1);
        headerAction.setText("Action");
        headerAction.setPadding(5, 1, 5, 1);

        tableRow.addView(headerId);
        tableRow.addView(headerNim);
        tableRow.addView(headerNama);
        tableRow.addView(headerAlamat);
        tableRow.addView(headerAction);

        tableLayout.addView(tableRow, new TableLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));

    }
}