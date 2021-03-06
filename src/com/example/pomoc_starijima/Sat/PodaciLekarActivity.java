package com.example.pomoc_starijima.Sat;

import java.util.List;

import baze.SQLitePregledi;

import com.example.pomoc_starijima.CustomAdapterLekar;
import com.example.pomoc_starijima.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class PodaciLekarActivity extends Activity {

	Button btnBack;
	ListView listaPregleda;
	SQLitePregledi db;
	List<_Pregled> pregledi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getActionBar().setIcon(R.drawable.slika44);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_1podaci_lekar);
		db = new SQLitePregledi(this);
		initialize();

		listaPregleda
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@SuppressLint("NewApi")
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						final int positionToRemove = position;

						// View viewToRemove = view;

						// Intent i1 = new
						// Intent("com.example.pomoc_starijima.RODJENDAN");
						// startActivity(i1);
						// TODO Auto-generated method stub
						AlertDialog.Builder builder1 = new AlertDialog.Builder(
								PodaciLekarActivity.this);
						builder1.setMessage("DA LI STE SIGURNI DA �ELITE DA OBRI�ETE PREGLED ?");
						builder1.setCancelable(true);
						builder1.setPositiveButton("DA",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										int p1 = positionToRemove;
										_Pregled p = pregledi.get(p1);

										// Log.d("id",
										// Integer.toString(r.getId()));
										db.obrisiPregled(p.getId());
										int rqs = 30000+p.getId();
										PreglediActivity.cancelAlarm(rqs, getBaseContext());
										napuniPreglede();
										finish();

										// initialize();
									}
								});
						builder1.setNegativeButton("NE",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										dialog.cancel();
									}
								});

						builder1.show();

					}
				});

		btnBack.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	private void initialize() {
		listaPregleda = (ListView) findViewById(R.id.listaPregleda);
		btnBack = (Button) findViewById(R.id.btnBack);
		napuniPreglede();

		if (pregledi.isEmpty()) {

		} else {
			CustomAdapterLekar ca = new CustomAdapterLekar(this,
					R.layout.list_item, R.id.title1, pregledi);

			// ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
			// R.layout.list_item, R.id.title, programi);

			listaPregleda.setAdapter(ca);
		}
	}

	private void napuniPreglede() {
		pregledi = db.vratiSvePreglede();
	}
}