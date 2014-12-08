package com.example.pomoc_starijima.Sat;

import java.util.List;

import com.example.pomoc_starijima.CustomAdapter;
import com.example.pomoc_starijima.R;
import baze.SQLiteRodjendani;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
//import android.support.v7.app.ActionBarActivity;

import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

public class PodaciRodjendanActivity extends Activity {

	ListView listaRodjendana;
	SQLiteRodjendani db;
	List<_Rodjendan> rodjendani;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setIcon(R.drawable.slika44);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_1podaci_rodjendan);
		db = new SQLiteRodjendani(this);
		initialize();

		listaRodjendana.setOnItemClickListener(new AdapterView.OnItemClickListener() {

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
								PodaciRodjendanActivity.this);
						builder1.setMessage("DA LI STE SIGURNI DA �ELITE DA OBRI�ETE RODJENDAN ?");
						builder1.setCancelable(true);
						builder1.setPositiveButton("DA",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
									//	int p1 = positionToRemove;
										_Rodjendan r = rodjendani.get(positionToRemove);
//										Log.d("PozitionToRemove",
//												Integer.toString(p1));
//										Log.d("Ime", r.getIme());
//										Log.d("id", Integer.toString(r.getId()));
										db.obrisiRodjendan(r.getId());
										napuniRodjendane();
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
	}

	// private void inicijalizuj() {
	// listaRodjendana = (ListView) findViewById(R.id.listaRodjendana);
	//
	// int ukupanBroj = db.vratiBrojRodjendana();
	//
	// String[] poruke = new String[ukupanBroj];
	// if (ukupanBroj > 0) {
	//
	// for (int i = 0; i < ukupanBroj; i++) {
	// poruke[i] = db.vratiRodjendan(i + 1);
	// }
	//
	// ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
	// R.layout.list_row, R.id.title, poruke);
	//
	// listaRodjendana.setAdapter(adapter1);
	// } else {
	//
	// }
	//
	// }

	private void initialize() {
		listaRodjendana = (ListView) findViewById(R.id.listaRodjendana);
		napuniRodjendane();

		if (rodjendani.isEmpty()) {

		} else {
			CustomAdapter ca = new CustomAdapter(this, R.layout.list_item,
					R.id.title1, rodjendani);

			// ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
			// R.layout.list_item, R.id.title, programi);

			listaRodjendana.setAdapter(ca);
		}
	}

	private void napuniRodjendane() {
		rodjendani = db.vratiSveRodjendane();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

}
