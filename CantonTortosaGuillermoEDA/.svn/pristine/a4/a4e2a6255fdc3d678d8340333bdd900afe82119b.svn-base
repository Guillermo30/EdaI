package org.eda1.examenSegundoParcial.ejercicio01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class ProcesarDatos {

	private TreeMap<String, TreeMap<String, TreeMap<String, DatosCiudad>>> mapa;

	public ProcesarDatos() {
		this.mapa = new TreeMap<String, TreeMap<String, TreeMap<String, DatosCiudad>>>();
	}

	public void cargarArchivo(String archivo) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(
					archivo)));
			String line = br.readLine();
			String[] words;
			while (line != null) {
				words = line.split(" ");
				if (mapa.containsKey(words[0])) {
					TreeMap<String, TreeMap<String, DatosCiudad>> b = mapa
							.get(words[0]);
					if (b.containsKey(words[1])) {
						TreeMap<String, DatosCiudad> a = b.get(words[1]);
						if (!a.containsKey(words[2])) {
							TreeSet<String> setAux = new TreeSet<String>();
							for (int i = 5; i < words.length; i++) {
								setAux.add(words[i]);
							}
							DatosCiudad aux = new DatosCiudad(words[3],
									words[4], setAux);
							a.put(words[2], aux);
						}
					} else {
						TreeMap<String, DatosCiudad> a = new TreeMap<String, DatosCiudad>();
						TreeSet<String> setAux = new TreeSet<String>();
						for (int i = 5; i < words.length; i++) {
							setAux.add(words[i]);
						}
						DatosCiudad aux = new DatosCiudad(words[3], words[4],
								setAux);
						a.put(words[2], aux);
						b.put(words[1], a);
					}
				} else {
					TreeSet<String> setAux = new TreeSet<String>();
					for (int i = 5; i < words.length; i++) {
						setAux.add(words[i]);
					}
					TreeMap<String, DatosCiudad> a = new TreeMap<String, DatosCiudad>();
					TreeMap<String, TreeMap<String, DatosCiudad>> b = new TreeMap<String, TreeMap<String, DatosCiudad>>();
					DatosCiudad aux = new DatosCiudad(words[3], words[4],
							setAux);
					a.put(words[2], aux);
					b.put(words[1], a);
					mapa.put(words[0], b);
				}
				line = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int size() {
		return mapa.size();
	}

	public TreeMap<String, ArrayList<String>> consulta1() {
		boolean t = false;
		String line;
		TreeMap<String, ArrayList<String>> toReturn = new TreeMap<String, ArrayList<String>>();
		for (Map.Entry<String, TreeMap<String, TreeMap<String, DatosCiudad>>> e : mapa
				.entrySet()) {
			ArrayList<String> aux = new ArrayList<String>();
			TreeMap<String, TreeMap<String, DatosCiudad>> b = mapa.get(e
					.getKey());
			for (Map.Entry<String, TreeMap<String, DatosCiudad>> e2 : b
					.entrySet()) {
				TreeMap<String, DatosCiudad> a = b.get(e2.getKey());
				for (Map.Entry<String, DatosCiudad> e3 : a.entrySet()) {
					if (e3.getValue().getContinente().equals("Europe")) {
						t = true;
						line = e2.getKey() + "<" + e3.getKey() + "."
								+ e3.getValue().getPais() + "."
								+ e3.getValue().getContinente() + "(";
						Iterator<String> it = e3.getValue().getDirecciones()
								.iterator();
						while (it.hasNext()) {
							String auxLine = it.next();
							line += auxLine;
							if (it.hasNext())
								line += ", ";
						}
						line += ")>";
						aux.add(line);
					}
				}
			}
			if (t) {
				t = false;
				toReturn.put(e.getKey(), aux);
			}
		}
		return toReturn;
	}

	public TreeMap<String, ArrayList<String>> consulta2() {
		boolean t = false;
		String line;
		TreeMap<String, ArrayList<String>> toReturn = new TreeMap<String, ArrayList<String>>();
		for (Map.Entry<String, TreeMap<String, TreeMap<String, DatosCiudad>>> e : mapa
				.entrySet()) {
			TreeMap<String, TreeMap<String, DatosCiudad>> b = mapa.get(e
					.getKey());
			for (Map.Entry<String, TreeMap<String, DatosCiudad>> e2 : b
					.entrySet()) {
				TreeMap<String, DatosCiudad> a = b.get(e2.getKey());
				for (Map.Entry<String, DatosCiudad> e3 : a.entrySet()) {
					Iterator<String> it = e3.getValue().getDirecciones()
							.iterator();
					while (it.hasNext()) {
						ArrayList<String> aux = new ArrayList<String>();
						String direccion = it.next();
						aux.add(e3.getKey());
						for (Map.Entry<String, TreeMap<String, TreeMap<String, DatosCiudad>>> q : mapa
								.entrySet()) {
							if(e.getKey().equals(q.getKey())) continue;
							TreeMap<String, TreeMap<String, DatosCiudad>> c = mapa
									.get(q.getKey());
							for (Map.Entry<String, TreeMap<String, DatosCiudad>> q2 : c
									.entrySet()) {
								TreeMap<String, DatosCiudad> d = c.get(q2
										.getKey());
								for (Map.Entry<String, DatosCiudad> q3 : d
										.entrySet()) {
									if (q3.getValue().getDirecciones()
											.contains(direccion)) {
										t = true;
										line =  "(" + q.getKey() + ", "
												+ q2.getKey() + ")";
										aux.add(line);	
									}
								}
							}
						}
						if(t){
							t = false;
							line = "(" + e.getKey() + ", "
								+ e2.getKey() + ")";
							aux.add(line);
							toReturn.put(direccion, aux);
						}
					}
				}
			}
		}
		return toReturn;
	}

}
