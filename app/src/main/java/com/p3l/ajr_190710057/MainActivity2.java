package com.p3l.ajr_190710057;

import static android.os.Environment.getExternalStoragePublicDirectory;
import static com.android.volley.Request.Method.GET;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.google.gson.Gson;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.p3l.ajr_190710057.api.LaporanApi;
import com.p3l.ajr_190710057.api.MobilApi;
import com.p3l.ajr_190710057.models.LaporanCustomerTransaksiTerbanyak;
import com.p3l.ajr_190710057.models.LaporanDetailPendapatanTransaksi;
import com.p3l.ajr_190710057.models.LaporanDriverTransaksiTerbanyak;
import com.p3l.ajr_190710057.models.LaporanPenyewaanMobil;
import com.p3l.ajr_190710057.models.LaporanPerformaDriver;
import com.p3l.ajr_190710057.preferences.PegawaiPreferences;
import com.p3l.ajr_190710057.responses.LaporanCustomerTransaksiTerbanyakResponse;
import com.p3l.ajr_190710057.responses.LaporanDetailPendapatanTransaksiResponse;
import com.p3l.ajr_190710057.responses.LaporanDriverTransaksiTerbanyakResponse;
import com.p3l.ajr_190710057.responses.LaporanPenyewaanMobilResponse;
import com.p3l.ajr_190710057.responses.LaporanPerformaDriverResponse;
import com.p3l.ajr_190710057.responses.MobilResponse;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private Spinner spMonth, spYear;
    private CardView btnLaporanPenyewaanMobil, btnLaporanDetailPendapatanTransaksi, btnLaporanDriverTerbanyak, btnLaporanPerformaDriver, btnLaporanCustomerTerbanyak;
    private PegawaiPreferences pegawaiPreferences;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        pegawaiPreferences = new PegawaiPreferences(MainActivity2.this);
        spMonth = findViewById(R.id.spinnerMonth);
        spYear = findViewById(R.id.spinnerYear);
        queue = Volley.newRequestQueue(this);
        btnLaporanPenyewaanMobil = findViewById(R.id.btnLaporanPenyewaanMobil);
        btnLaporanDetailPendapatanTransaksi = findViewById(R.id.btnLaporanDetailPendapatanTransaksi);
        btnLaporanDriverTerbanyak = findViewById(R.id.btnLaporanDriverTransaksiTerbanyak);
        btnLaporanPerformaDriver = findViewById(R.id.btnLaporanPerformaDriver);
        btnLaporanCustomerTerbanyak = findViewById(R.id.btnLaporanCustomerTransaksiTerbanyak);


        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(MainActivity2.this, R.array.month_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMonth.setAdapter(adapter1);
        spMonth.setOnItemSelectedListener(MainActivity2.this);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(MainActivity2.this, R.array.year_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spYear.setAdapter(adapter2);
        spYear.setOnItemSelectedListener(MainActivity2.this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
        String monthSelected = null, yearSelected = null, temp = null;

        monthSelected = spMonth.getSelectedItem().toString();
        yearSelected = spYear.getSelectedItem().toString();
        String finalMonthSelected = monthSelected;
        String finalYearSelected = yearSelected;

        if(finalMonthSelected.equals("1")){
            temp = "Januari";
        }else if(finalMonthSelected.equals("2")){
            temp = "Februari";
        }else if(finalMonthSelected.equals("3")){
            temp = "Maret";
        }else if(finalMonthSelected.equals("4")){
            temp = "April";
        }else if(finalMonthSelected.equals("5")){
            temp = "Mei";
        }else if(finalMonthSelected.equals("6")){
            temp = "Juni";
        }else if(finalMonthSelected.equals("7")){
            temp = "Juli";
        }else if(finalMonthSelected.equals("8")){
            temp = "Agustus";
        }else if(finalMonthSelected.equals("9")){
            temp = "September";
        }else if(finalMonthSelected.equals("10")){
            temp = "Oktober";
        }else if(finalMonthSelected.equals("11")) {
            temp = "November";
        }else if(finalMonthSelected.equals("12")) {
            temp = "Desember";
        }

        String finalTemp = temp;
        btnLaporanPenyewaanMobil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(finalMonthSelected.equals("") && finalYearSelected.equals("")){
                    Toast.makeText(MainActivity2.this, "Silakan pilih bulan dan tahun terlebih dahulu", Toast.LENGTH_SHORT).show();
                }else if(finalMonthSelected.equals("")){
                    Toast.makeText(MainActivity2.this, "Silakan pilih bulan terlebih dahulu", Toast.LENGTH_SHORT).show();
                }else if(finalYearSelected.equals("")) {
                    Toast.makeText(MainActivity2.this, "Silakan pilih tahun terlebih dahulu", Toast.LENGTH_SHORT).show();
                }else{
                    StringRequest stringRequest = new StringRequest(GET, LaporanApi.GET_LAPORAN+finalMonthSelected+"/"+finalYearSelected+"/penyewaan-mobil", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Gson gson = new Gson();
                            LaporanPenyewaanMobilResponse laporanPenyewaanMobilResponse = gson.fromJson(response, LaporanPenyewaanMobilResponse.class);
                            Log.d("API RESPONSE", response);

                            File folder= getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);

                            if (!folder.exists()) {
                                folder.mkdir();
                            }
                            Log.d("PATH", String.valueOf(folder));
                            Date currentTime = Calendar.getInstance().getTime();
                            String pdfName = "LaporanPenyewaanMobil_"+finalTemp+"_"+finalYearSelected+".pdf";
                            File pdfFile = new File(folder.getAbsolutePath(), pdfName);

                            OutputStream outputStream = null;
                            try {
                                outputStream = new FileOutputStream(pdfFile);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            com.itextpdf.text.Document document = new com.itextpdf.text.Document(PageSize.A4);
                            try {
                                PdfWriter.getInstance(document, outputStream);
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            document.open();

                            Paragraph judul = new Paragraph("ATMA JOGJA RENTAL \n\n",
                                    new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 16,
                                            Font.BOLD, BaseColor.BLACK));
                            judul.setAlignment(Element.ALIGN_CENTER);
                            try {
                                document.add(judul);
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            try {
                                document.add(new LineSeparator());
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            LineSeparator sep = new LineSeparator();
                            try {
                                document.add(sep);
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            Paragraph subjudul = new Paragraph("Laporan Penyewaan Mobil Bulan "+finalTemp +" "+finalYearSelected+"\n\n",
                                    new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 14,
                                            Font.BOLD, BaseColor.BLACK));
                            subjudul.setAlignment(Element.ALIGN_CENTER);
                            try {
                                document.add(subjudul);
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            com.itextpdf.text.Font f = new
                                    com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10,
                                    com.itextpdf.text.Font.NORMAL, BaseColor.BLACK);
                            PdfPTable tableHeader = new PdfPTable(new float[]{5, 5, 5, 5, 5});

                            tableHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

                            tableHeader.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
                            tableHeader.getDefaultCell().setFixedHeight(30);
                            tableHeader.setTotalWidth(PageSize.A4.getWidth());
                            tableHeader.setWidthPercentage(100);

                            PdfPCell h1 = new PdfPCell(new Phrase("Nomor"));
                            h1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            h1.setPaddingBottom(5);
                            PdfPCell h2 = new PdfPCell(new Phrase("Tipe Mobil"));
                            h2.setHorizontalAlignment(Element.ALIGN_CENTER);
                            h2.setPaddingBottom(5);
                            PdfPCell h3 = new PdfPCell(new Phrase("Nama Mobil"));
                            h3.setHorizontalAlignment(Element.ALIGN_CENTER);
                            h3.setPaddingBottom(5);
                            PdfPCell h4 = new PdfPCell(new Phrase("Jumlah Peminjaman"));
                            h4.setHorizontalAlignment(Element.ALIGN_CENTER);
                            h4.setPaddingBottom(5);
                            PdfPCell h5 = new PdfPCell(new Phrase("Total Pendapatan"));
                            h5.setHorizontalAlignment(Element.ALIGN_CENTER);
                            h5.setPaddingBottom(5);
                            tableHeader.addCell(h1);
                            tableHeader.addCell(h2);
                            tableHeader.addCell(h3);
                            tableHeader.addCell(h4);
                            tableHeader.addCell(h5);

                            for (PdfPCell cells : tableHeader.getRow(0).getCells()) {
                                cells.setBackgroundColor(BaseColor.LIGHT_GRAY);
                            }
                            try {
                                document.add(tableHeader);
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            PdfPTable tableData = new PdfPTable(new float[]{5, 5, 5, 5, 5});

                            tableData.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                            tableData.getDefaultCell().setFixedHeight(30);
                            tableData.setTotalWidth(PageSize.A4.getWidth());
                            tableData.setWidthPercentage(100);
                            tableData.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
                            int i=1;
                            for (LaporanPenyewaanMobil laporanPenyewaanMobil : laporanPenyewaanMobilResponse.getLaporanPenyewaanMobilList()) {
                                tableData.addCell(String.valueOf(i));
                                tableData.addCell(laporanPenyewaanMobil.getTipeMobil());
                                tableData.addCell(laporanPenyewaanMobil.getNamaMobil());
                                tableData.addCell(String.valueOf(laporanPenyewaanMobil.getJumlahPeminjaman()));
                                tableData.addCell(String.valueOf(laporanPenyewaanMobil.getTotalPendapatan()));
                                i=i+1;
                            }
                            try {
                                document.add(tableData);
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            com.itextpdf.text.Font h = new

                                    com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10,
                                    com.itextpdf.text.Font.NORMAL);
                            String tglDicetak = currentTime.toLocaleString();
                            Paragraph P = new Paragraph("\nDibuat pada " + tglDicetak, h);
                            P.setAlignment(Element.ALIGN_RIGHT);
                            try {
                                document.add(P);
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            document.close();
                            previewPdf(pdfFile);
                            Toast.makeText(MainActivity2.this, "Laporan berhasil dibuat", Toast.LENGTH_SHORT).show();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            try{
                                String responseBody = new String(error.networkResponse.data,
                                        StandardCharsets.UTF_8);
                                JSONObject errors = new JSONObject(responseBody);
                                Toast.makeText(MainActivity2.this,
                                        errors.getString("message"), Toast.LENGTH_SHORT).show();
                            }catch (Exception e) {
                                Toast.makeText(MainActivity2.this, e.getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }

                        }
                    }){
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            HashMap<String, String> headers = new HashMap<String, String>();
                            headers.put("Accept", "application/json");
                            headers.put("Authorization", "Bearer "+
                                    pegawaiPreferences.getPegawaiLogin().getAccessToken());
                            return headers;
                        }

                    };

                    queue.add(stringRequest);
                }
            }

        });

        btnLaporanDetailPendapatanTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(finalMonthSelected.equals("") && finalYearSelected.equals("")){
                    Toast.makeText(MainActivity2.this, "Silakan pilih bulan dan tahun terlebih dahulu", Toast.LENGTH_SHORT).show();
                }else if(finalMonthSelected.equals("")){
                    Toast.makeText(MainActivity2.this, "Silakan pilih bulan terlebih dahulu", Toast.LENGTH_SHORT).show();
                }else if(finalYearSelected.equals("")) {
                    Toast.makeText(MainActivity2.this, "Silakan pilih tahun terlebih dahulu", Toast.LENGTH_SHORT).show();
                }else{
                    StringRequest stringRequest = new StringRequest(GET, LaporanApi.GET_LAPORAN+finalMonthSelected+"/"+finalYearSelected+"/detail-transaksi", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Gson gson = new Gson();
                            LaporanDetailPendapatanTransaksiResponse laporanDetailPendapatanTransaksiResponse = gson.fromJson(response, LaporanDetailPendapatanTransaksiResponse.class);
                            Log.d("API RESPONSE", response);

                            File folder= getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);

                            if (!folder.exists()) {
                                folder.mkdir();
                            }
                            Log.d("PATH", String.valueOf(folder));
                            Date currentTime = Calendar.getInstance().getTime();
                            String pdfName = "LaporanDetailPendapatanTransaksi_"+finalTemp+"_"+finalYearSelected+".pdf";
                            File pdfFile = new File(folder.getAbsolutePath(), pdfName);

                            OutputStream outputStream = null;
                            try {
                                outputStream = new FileOutputStream(pdfFile);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            com.itextpdf.text.Document document = new com.itextpdf.text.Document(PageSize.A4);
                            try {
                                PdfWriter.getInstance(document, outputStream);
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            document.open();

                            Paragraph judul = new Paragraph("ATMA JOGJA RENTAL \n\n",
                                    new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 16,
                                            Font.BOLD, BaseColor.BLACK));
                            judul.setAlignment(Element.ALIGN_CENTER);
                            try {
                                document.add(judul);
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            try {
                                document.add(new LineSeparator());
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            LineSeparator sep = new LineSeparator();
                            try {
                                document.add(sep);
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            Paragraph subjudul = new Paragraph("Laporan Detail Pendapatan Transaksi Bulan "+finalTemp +" "+finalYearSelected+"\n\n",
                                    new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 14,
                                            Font.BOLD, BaseColor.BLACK));
                            subjudul.setAlignment(Element.ALIGN_CENTER);
                            try {
                                document.add(subjudul);
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            com.itextpdf.text.Font f = new
                                    com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10,
                                    com.itextpdf.text.Font.NORMAL, BaseColor.BLACK);
                            PdfPTable tableHeader = new PdfPTable(new float[]{5,5, 5, 8, 5, 5});

                            tableHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

                            tableHeader.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
                            tableHeader.getDefaultCell().setFixedHeight(30);
                            tableHeader.setTotalWidth(PageSize.A4.getWidth());
                            tableHeader.setWidthPercentage(100);

                            PdfPCell h1 = new PdfPCell(new Phrase("Nomor"));
                            h1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            h1.setPaddingBottom(5);
                            PdfPCell h2 = new PdfPCell(new Phrase("Nama Customer"));
                            h2.setHorizontalAlignment(Element.ALIGN_CENTER);
                            h2.setPaddingBottom(5);
                            PdfPCell h3 = new PdfPCell(new Phrase("Nama Mobil"));
                            h3.setHorizontalAlignment(Element.ALIGN_CENTER);
                            h3.setPaddingBottom(5);
                            PdfPCell h4 = new PdfPCell(new Phrase("Jenis Transaksi"));
                            h4.setHorizontalAlignment(Element.ALIGN_CENTER);
                            h4.setPaddingBottom(5);
                            PdfPCell h5 = new PdfPCell(new Phrase("Jumlah Transaksi"));
                            h5.setHorizontalAlignment(Element.ALIGN_CENTER);
                            PdfPCell h6 = new PdfPCell(new Phrase("Pendapatan"));
                            h5.setHorizontalAlignment(Element.ALIGN_CENTER);
                            h5.setPaddingBottom(5);
                            tableHeader.addCell(h1);
                            tableHeader.addCell(h2);
                            tableHeader.addCell(h3);
                            tableHeader.addCell(h4);
                            tableHeader.addCell(h5);
                            tableHeader.addCell(h6);

                            for (PdfPCell cells : tableHeader.getRow(0).getCells()) {
                                cells.setBackgroundColor(BaseColor.LIGHT_GRAY);
                            }
                            try {
                                document.add(tableHeader);
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            PdfPTable tableData = new PdfPTable(new float[]{5, 5, 5, 8, 5, 5});

                            tableData.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                            tableData.getDefaultCell().setFixedHeight(30);
                            tableData.setTotalWidth(PageSize.A4.getWidth());
                            tableData.setWidthPercentage(100);
                            tableData.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
                            int i=1;
                            for (LaporanDetailPendapatanTransaksi laporanDetailPendapatanTransaksi : laporanDetailPendapatanTransaksiResponse.getLaporanDetailPendapatanTransaksi()) {
                                tableData.addCell(String.valueOf(i));
                                tableData.addCell(laporanDetailPendapatanTransaksi.getNamaCustomer());
                                tableData.addCell(laporanDetailPendapatanTransaksi.getNamaMobil());
                                tableData.addCell(laporanDetailPendapatanTransaksi.getJenisTransaksi());
                                tableData.addCell(String.valueOf(laporanDetailPendapatanTransaksi.getJumlahTransaksi()));
                                tableData.addCell(String.valueOf(laporanDetailPendapatanTransaksi.getPendapatan()));
                                i=i+1;
                            }
                            try {
                                document.add(tableData);
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            com.itextpdf.text.Font h = new

                                    com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10,
                                    com.itextpdf.text.Font.NORMAL);
                            String tglDicetak = currentTime.toLocaleString();
                            Paragraph P = new Paragraph("\nDibuat pada " + tglDicetak, h);
                            P.setAlignment(Element.ALIGN_RIGHT);
                            try {
                                document.add(P);
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            document.close();
                            previewPdf(pdfFile);
                            Toast.makeText(MainActivity2.this, "Laporan berhasil dibuat", Toast.LENGTH_SHORT).show();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            try{
                                String responseBody = new String(error.networkResponse.data,
                                        StandardCharsets.UTF_8);
                                JSONObject errors = new JSONObject(responseBody);
                                Toast.makeText(MainActivity2.this,
                                        errors.getString("message"), Toast.LENGTH_SHORT).show();
                            }catch (Exception e) {
                                Toast.makeText(MainActivity2.this, e.getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }

                        }
                    }){
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            HashMap<String, String> headers = new HashMap<String, String>();
                            headers.put("Accept", "application/json");
                            headers.put("Authorization", "Bearer "+
                                    pegawaiPreferences.getPegawaiLogin().getAccessToken());
                            return headers;
                        }

                    };

                    queue.add(stringRequest);
                }
            }
        });

        btnLaporanDriverTerbanyak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(finalMonthSelected.equals("") && finalYearSelected.equals("")){
                    Toast.makeText(MainActivity2.this, "Silakan pilih bulan dan tahun terlebih dahulu", Toast.LENGTH_SHORT).show();
                }else if(finalMonthSelected.equals("")){
                    Toast.makeText(MainActivity2.this, "Silakan pilih bulan terlebih dahulu", Toast.LENGTH_SHORT).show();
                }else if(finalYearSelected.equals("")) {
                    Toast.makeText(MainActivity2.this, "Silakan pilih tahun terlebih dahulu", Toast.LENGTH_SHORT).show();
                }else{
                    StringRequest stringRequest = new StringRequest(GET, LaporanApi.GET_LAPORAN+finalMonthSelected+"/"+finalYearSelected+"/top-driver", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Gson gson = new Gson();
                            LaporanDriverTransaksiTerbanyakResponse laporanDriverTransaksiTerbanyakResponse = gson.fromJson(response, LaporanDriverTransaksiTerbanyakResponse.class);
                            Log.d("API RESPONSE", response);

                            File folder= getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);

                            if (!folder.exists()) {
                                folder.mkdir();
                            }
                            Log.d("PATH", String.valueOf(folder));
                            Date currentTime = Calendar.getInstance().getTime();
                            String pdfName = "LaporanDriverTransaksiTerbanyak_"+finalTemp+"_"+finalYearSelected+".pdf";
                            File pdfFile = new File(folder.getAbsolutePath(), pdfName);

                            OutputStream outputStream = null;
                            try {
                                outputStream = new FileOutputStream(pdfFile);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            com.itextpdf.text.Document document = new com.itextpdf.text.Document(PageSize.A4);
                            try {
                                PdfWriter.getInstance(document, outputStream);
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            document.open();

                            Paragraph judul = new Paragraph("ATMA JOGJA RENTAL \n\n",
                                    new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 16,
                                            Font.BOLD, BaseColor.BLACK));
                            judul.setAlignment(Element.ALIGN_CENTER);
                            try {
                                document.add(judul);
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            try {
                                document.add(new LineSeparator());
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            LineSeparator sep = new LineSeparator();
                            try {
                                document.add(sep);
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            Paragraph subjudul = new Paragraph("Laporan Driver Transaksi Terbanyak Bulan "+finalTemp +" "+finalYearSelected+"\n\n",
                                    new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 14,
                                            Font.BOLD, BaseColor.BLACK));
                            subjudul.setAlignment(Element.ALIGN_CENTER);
                            try {
                                document.add(subjudul);
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            com.itextpdf.text.Font f = new
                                    com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10,
                                    com.itextpdf.text.Font.NORMAL, BaseColor.BLACK);
                            PdfPTable tableHeader = new PdfPTable(new float[]{5, 5, 5, 5});

                            tableHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

                            tableHeader.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
                            tableHeader.getDefaultCell().setFixedHeight(30);
                            tableHeader.setTotalWidth(PageSize.A4.getWidth());
                            tableHeader.setWidthPercentage(100);

                            PdfPCell h1 = new PdfPCell(new Phrase("Nomor"));
                            h1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            h1.setPaddingBottom(5);
                            PdfPCell h2 = new PdfPCell(new Phrase("ID Driver"));
                            h2.setHorizontalAlignment(Element.ALIGN_CENTER);
                            h2.setPaddingBottom(5);
                            PdfPCell h3 = new PdfPCell(new Phrase("Nama Driver"));
                            h3.setHorizontalAlignment(Element.ALIGN_CENTER);
                            h3.setPaddingBottom(5);
                            PdfPCell h4 = new PdfPCell(new Phrase("Jumlah Transaksi"));
                            h4.setHorizontalAlignment(Element.ALIGN_CENTER);
                            h4.setPaddingBottom(5);
                            tableHeader.addCell(h1);
                            tableHeader.addCell(h2);
                            tableHeader.addCell(h3);
                            tableHeader.addCell(h4);

                            for (PdfPCell cells : tableHeader.getRow(0).getCells()) {
                                cells.setBackgroundColor(BaseColor.LIGHT_GRAY);
                            }
                            try {
                                document.add(tableHeader);
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            PdfPTable tableData = new PdfPTable(new float[]{5, 5, 5, 5});

                            tableData.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                            tableData.getDefaultCell().setFixedHeight(30);
                            tableData.setTotalWidth(PageSize.A4.getWidth());
                            tableData.setWidthPercentage(100);
                            tableData.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
                            int i=1;
                            for (LaporanDriverTransaksiTerbanyak laporanDriverTransaksiTerbanyak : laporanDriverTransaksiTerbanyakResponse.getLaporanDriverTransaksiTerbanyak()) {
                                tableData.addCell(String.valueOf(i));
                                tableData.addCell(laporanDriverTransaksiTerbanyak.getIdDriver());
                                tableData.addCell(laporanDriverTransaksiTerbanyak.getNamaDriver());
                                tableData.addCell(String.valueOf(laporanDriverTransaksiTerbanyak.getJumlahTransaksi()));
                                i=i+1;
                            }
                            try {
                                document.add(tableData);
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            com.itextpdf.text.Font h = new

                                    com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10,
                                    com.itextpdf.text.Font.NORMAL);
                            String tglDicetak = currentTime.toLocaleString();
                            Paragraph P = new Paragraph("\nDibuat pada " + tglDicetak, h);
                            P.setAlignment(Element.ALIGN_RIGHT);
                            try {
                                document.add(P);
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            document.close();
                            previewPdf(pdfFile);
                            Toast.makeText(MainActivity2.this, "Laporan berhasil dibuat", Toast.LENGTH_SHORT).show();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            try{
                                String responseBody = new String(error.networkResponse.data,
                                        StandardCharsets.UTF_8);
                                JSONObject errors = new JSONObject(responseBody);
                                Toast.makeText(MainActivity2.this,
                                        errors.getString("message"), Toast.LENGTH_SHORT).show();
                            }catch (Exception e) {
                                Toast.makeText(MainActivity2.this, e.getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }

                        }
                    }){
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            HashMap<String, String> headers = new HashMap<String, String>();
                            headers.put("Accept", "application/json");
                            headers.put("Authorization", "Bearer "+
                                    pegawaiPreferences.getPegawaiLogin().getAccessToken());
                            return headers;
                        }

                    };

                    queue.add(stringRequest);
                }
            }
        });

        btnLaporanPerformaDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(finalMonthSelected.equals("") && finalYearSelected.equals("")){
                    Toast.makeText(MainActivity2.this, "Silakan pilih bulan dan tahun terlebih dahulu", Toast.LENGTH_SHORT).show();
                }else if(finalMonthSelected.equals("")){
                    Toast.makeText(MainActivity2.this, "Silakan pilih bulan terlebih dahulu", Toast.LENGTH_SHORT).show();
                }else if(finalYearSelected.equals("")) {
                    Toast.makeText(MainActivity2.this, "Silakan pilih tahun terlebih dahulu", Toast.LENGTH_SHORT).show();
                }else{
                    StringRequest stringRequest = new StringRequest(GET, LaporanApi.GET_LAPORAN+finalMonthSelected+"/"+finalYearSelected+"/top-average-rating", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Gson gson = new Gson();
                            LaporanPerformaDriverResponse laporanPerformaDriverResponse = gson.fromJson(response, LaporanPerformaDriverResponse.class);
                            Log.d("API RESPONSE", response);

                            File folder= getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);

                            if (!folder.exists()) {
                                folder.mkdir();
                            }
                            Log.d("PATH", String.valueOf(folder));
                            Date currentTime = Calendar.getInstance().getTime();
                            String pdfName = "LaporanPerformaDriver_"+finalTemp+"_"+finalYearSelected+".pdf";
                            File pdfFile = new File(folder.getAbsolutePath(), pdfName);

                            OutputStream outputStream = null;
                            try {
                                outputStream = new FileOutputStream(pdfFile);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            com.itextpdf.text.Document document = new com.itextpdf.text.Document(PageSize.A4);
                            try {
                                PdfWriter.getInstance(document, outputStream);
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            document.open();

                            Paragraph judul = new Paragraph("ATMA JOGJA RENTAL \n\n",
                                    new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 16,
                                            Font.BOLD, BaseColor.BLACK));
                            judul.setAlignment(Element.ALIGN_CENTER);
                            try {
                                document.add(judul);
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            try {
                                document.add(new LineSeparator());
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            LineSeparator sep = new LineSeparator();
                            try {
                                document.add(sep);
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            Paragraph subjudul = new Paragraph("Laporan Performa Driver Bulan "+finalTemp +" "+finalYearSelected+"\n\n",
                                    new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 14,
                                            Font.BOLD, BaseColor.BLACK));
                            subjudul.setAlignment(Element.ALIGN_CENTER);
                            try {
                                document.add(subjudul);
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            com.itextpdf.text.Font f = new
                                    com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10,
                                    com.itextpdf.text.Font.NORMAL, BaseColor.BLACK);
                            PdfPTable tableHeader = new PdfPTable(new float[]{5, 5, 5, 5,5});

                            tableHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

                            tableHeader.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
                            tableHeader.getDefaultCell().setFixedHeight(30);
                            tableHeader.setTotalWidth(PageSize.A4.getWidth());
                            tableHeader.setWidthPercentage(100);

                            PdfPCell h1 = new PdfPCell(new Phrase("Nomor"));
                            h1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            h1.setPaddingBottom(5);
                            PdfPCell h2 = new PdfPCell(new Phrase("ID Driver"));
                            h2.setHorizontalAlignment(Element.ALIGN_CENTER);
                            h2.setPaddingBottom(5);
                            PdfPCell h3 = new PdfPCell(new Phrase("Nama Driver"));
                            h3.setHorizontalAlignment(Element.ALIGN_CENTER);
                            h3.setPaddingBottom(5);
                            PdfPCell h4 = new PdfPCell(new Phrase("Jumlah Transaksi"));
                            h4.setHorizontalAlignment(Element.ALIGN_CENTER);
                            h4.setPaddingBottom(5);
                            PdfPCell h5 = new PdfPCell(new Phrase("Rerata Rating"));
                            h5.setHorizontalAlignment(Element.ALIGN_CENTER);
                            h5.setPaddingBottom(5);
                            tableHeader.addCell(h1);
                            tableHeader.addCell(h2);
                            tableHeader.addCell(h3);
                            tableHeader.addCell(h4);
                            tableHeader.addCell(h5);

                            for (PdfPCell cells : tableHeader.getRow(0).getCells()) {
                                cells.setBackgroundColor(BaseColor.LIGHT_GRAY);
                            }
                            try {
                                document.add(tableHeader);
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            PdfPTable tableData = new PdfPTable(new float[]{5, 5, 5, 5, 5});

                            tableData.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                            tableData.getDefaultCell().setFixedHeight(30);
                            tableData.setTotalWidth(PageSize.A4.getWidth());
                            tableData.setWidthPercentage(100);
                            tableData.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
                            int i=1;
                            for (LaporanPerformaDriver laporanPerformaDriver : laporanPerformaDriverResponse.getLaporanPerformaDriver()) {
                                tableData.addCell(String.valueOf(i));
                                tableData.addCell(laporanPerformaDriver.getIdDriver());
                                tableData.addCell(laporanPerformaDriver.getNamaDriver());
                                tableData.addCell(String.valueOf(laporanPerformaDriver.getJumlahTransaksi()));
                                tableData.addCell(String.format("%.1f",laporanPerformaDriver.getRerataRating()));
                                i=i+1;
                            }
                            try {
                                document.add(tableData);
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            com.itextpdf.text.Font h = new

                                    com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10,
                                    com.itextpdf.text.Font.NORMAL);
                            String tglDicetak = currentTime.toLocaleString();
                            Paragraph P = new Paragraph("\nDibuat pada " + tglDicetak, h);
                            P.setAlignment(Element.ALIGN_RIGHT);
                            try {
                                document.add(P);
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            document.close();
                            previewPdf(pdfFile);
                            Toast.makeText(MainActivity2.this, "Laporan berhasil dibuat", Toast.LENGTH_SHORT).show();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            try{
                                String responseBody = new String(error.networkResponse.data,
                                        StandardCharsets.UTF_8);
                                JSONObject errors = new JSONObject(responseBody);
                                Toast.makeText(MainActivity2.this,
                                        errors.getString("message"), Toast.LENGTH_SHORT).show();
                            }catch (Exception e) {
                                Toast.makeText(MainActivity2.this, e.getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }

                        }
                    }){
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            HashMap<String, String> headers = new HashMap<String, String>();
                            headers.put("Accept", "application/json");
                            headers.put("Authorization", "Bearer "+
                                    pegawaiPreferences.getPegawaiLogin().getAccessToken());
                            return headers;
                        }

                    };

                    queue.add(stringRequest);
                }
            }
        });

        btnLaporanCustomerTerbanyak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(finalMonthSelected.equals("") && finalYearSelected.equals("")){
                    Toast.makeText(MainActivity2.this, "Silakan pilih bulan dan tahun terlebih dahulu", Toast.LENGTH_SHORT).show();
                }else if(finalMonthSelected.equals("")){
                    Toast.makeText(MainActivity2.this, "Silakan pilih bulan terlebih dahulu", Toast.LENGTH_SHORT).show();
                }else if(finalYearSelected.equals("")) {
                    Toast.makeText(MainActivity2.this, "Silakan pilih tahun terlebih dahulu", Toast.LENGTH_SHORT).show();
                }else{
                    StringRequest stringRequest = new StringRequest(GET, LaporanApi.GET_LAPORAN+finalMonthSelected+"/"+finalYearSelected+"/top-customer", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Gson gson = new Gson();
                            LaporanCustomerTransaksiTerbanyakResponse laporanCustomerTransaksiTerbanyakResponse = gson.fromJson(response, LaporanCustomerTransaksiTerbanyakResponse.class);
                            Log.d("API RESPONSE", response);

                            File folder= getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);

                            if (!folder.exists()) {
                                folder.mkdir();
                            }
                            Log.d("PATH", String.valueOf(folder));
                            Date currentTime = Calendar.getInstance().getTime();
                            String pdfName = "LaporanCustomerTransaksiTerbanyak_"+finalTemp+"_"+finalYearSelected+".pdf";
                            File pdfFile = new File(folder.getAbsolutePath(), pdfName);

                            OutputStream outputStream = null;
                            try {
                                outputStream = new FileOutputStream(pdfFile);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            com.itextpdf.text.Document document = new com.itextpdf.text.Document(PageSize.A4);
                            try {
                                PdfWriter.getInstance(document, outputStream);
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            document.open();

                            Paragraph judul = new Paragraph("ATMA JOGJA RENTAL \n\n",
                                    new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 16,
                                            Font.BOLD, BaseColor.BLACK));
                            judul.setAlignment(Element.ALIGN_CENTER);
                            try {
                                document.add(judul);
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            try {
                                document.add(new LineSeparator());
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            LineSeparator sep = new LineSeparator();
                            try {
                                document.add(sep);
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            Paragraph subjudul = new Paragraph("Laporan Customer Transaksi Terbanyak Bulan "+finalTemp +" "+finalYearSelected+"\n\n",
                                    new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 14,
                                            Font.BOLD, BaseColor.BLACK));
                            subjudul.setAlignment(Element.ALIGN_CENTER);
                            try {
                                document.add(subjudul);
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            com.itextpdf.text.Font f = new
                                    com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10,
                                    com.itextpdf.text.Font.NORMAL, BaseColor.BLACK);
                            PdfPTable tableHeader = new PdfPTable(new float[]{5, 5, 5});

                            tableHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

                            tableHeader.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
                            tableHeader.getDefaultCell().setFixedHeight(30);
                            tableHeader.setTotalWidth(PageSize.A4.getWidth());
                            tableHeader.setWidthPercentage(100);

                            PdfPCell h1 = new PdfPCell(new Phrase("Nomor"));
                            h1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            h1.setPaddingBottom(5);
                            PdfPCell h2 = new PdfPCell(new Phrase("Nama Customer"));
                            h2.setHorizontalAlignment(Element.ALIGN_CENTER);
                            h2.setPaddingBottom(5);
                            PdfPCell h3 = new PdfPCell(new Phrase("Jumlah Transaksi"));
                            h3.setHorizontalAlignment(Element.ALIGN_CENTER);
                            h3.setPaddingBottom(5);

                            tableHeader.addCell(h1);
                            tableHeader.addCell(h2);
                            tableHeader.addCell(h3);

                            for (PdfPCell cells : tableHeader.getRow(0).getCells()) {
                                cells.setBackgroundColor(BaseColor.LIGHT_GRAY);
                            }
                            try {
                                document.add(tableHeader);
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            PdfPTable tableData = new PdfPTable(new float[]{5, 5, 5});

                            tableData.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                            tableData.getDefaultCell().setFixedHeight(30);
                            tableData.setTotalWidth(PageSize.A4.getWidth());
                            tableData.setWidthPercentage(100);
                            tableData.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
                            int i=1;
                            for (LaporanCustomerTransaksiTerbanyak laporanCustomerTransaksiTerbanyak : laporanCustomerTransaksiTerbanyakResponse.getLaporanCustomerTransaksiTerbanyak()) {
                                tableData.addCell(String.valueOf(i));
                                tableData.addCell(laporanCustomerTransaksiTerbanyak.getNamaCustomer());
                                tableData.addCell(String.valueOf(laporanCustomerTransaksiTerbanyak.getJumlahTransaksi()));
                                i=i+1;
                            }
                            try {
                                document.add(tableData);
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            com.itextpdf.text.Font h = new

                                    com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10,
                                    com.itextpdf.text.Font.NORMAL);
                            String tglDicetak = currentTime.toLocaleString();
                            Paragraph P = new Paragraph("\nDibuat pada " + tglDicetak, h);
                            P.setAlignment(Element.ALIGN_RIGHT);
                            try {
                                document.add(P);
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                            document.close();
                            previewPdf(pdfFile);
                            Toast.makeText(MainActivity2.this, "Laporan berhasil dibuat", Toast.LENGTH_SHORT).show();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            try{
                                String responseBody = new String(error.networkResponse.data,
                                        StandardCharsets.UTF_8);
                                JSONObject errors = new JSONObject(responseBody);
                                Toast.makeText(MainActivity2.this,
                                        errors.getString("message"), Toast.LENGTH_SHORT).show();
                            }catch (Exception e) {
                                Toast.makeText(MainActivity2.this, e.getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }

                        }
                    }){
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            HashMap<String, String> headers = new HashMap<String, String>();
                            headers.put("Accept", "application/json");
                            headers.put("Authorization", "Bearer "+
                                    pegawaiPreferences.getPegawaiLogin().getAccessToken());
                            return headers;
                        }

                    };

                    queue.add(stringRequest);
                }
            }
        });

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void previewPdf(File pdfFile) {
        PackageManager packageManager = getPackageManager();
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        testIntent.setType("application/pdf");
        List<ResolveInfo> list =
                packageManager.queryIntentActivities(testIntent,
                        PackageManager.MATCH_DEFAULT_ONLY);
        if (list.size() > 0) {
            Uri uri;
            uri = FileProvider.getUriForFile(MainActivity2.this, getPackageName() +
                            ".provider",
                    pdfFile);
            Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
            pdfIntent.setDataAndType(uri, "application/pdf");
            pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            pdfIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            pdfIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            pdfIntent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            pdfIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            this.grantUriPermission(getPackageName(), uri,
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION |
                            Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(pdfIntent);
        }
    }
}