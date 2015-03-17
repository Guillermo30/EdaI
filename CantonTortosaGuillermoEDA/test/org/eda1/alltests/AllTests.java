package org.eda1.alltests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	
	// Practica 01
	org.eda1.practica01.ejercicio01.EditorTestJUnit4.class,
	org.eda1.practica01.ejercicio01.UseEditorTestJUnit4.class,
	org.eda1.practica01.ejercicio02.ProcesarDatosTestJUnit4.class,

	
	// Practica 02
	org.eda1.practica02.ejercicio01.ProcesarDireccionesTestJUnit4.class,
	org.eda1.practica02.ejercicio02.ProcesarDatosTestJUnit4.class,
	org.eda1.practica02.ejercicio03.CorrectorOrtograficoTestJUnit4.class,
	
	// Practica 03
	org.eda1.practica03.ejercicio01.ProcesarDireccionesTestJUnit4.class,
	org.eda1.practica03.ejercicio02.ProcesarDatosTestJUnit4.class,
	org.eda1.practica03.ejercicio03.ConcordanciaTestJUnit4.class,
	
	// Practica 04
	org.eda1.practica04.RoadNetworkTestJUnit4.class,

	// Actividad 01
	org.eda1.actividad01.serializacionED.SerializacionEDTestJUnit4.class,

	// Actividad 02
	org.eda1.actividad02.ejercicio01.HeapTestJUnit4.class,
	org.eda1.actividad02.ejercicio02.HeapSortTestJUnit4.class,

	// Actividad 03
	org.eda1.actividad03.AVLTree.AVLTreeTestJUnit4.class,
	org.eda1.actividad03.AVLTreeJCF.AVLTreeJCFTestJUnit4.class,
	org.eda1.actividad03.BS_AVL_RB_Tree.BS_AVL_RB_TreeTestJUnit4.class,
	org.eda1.actividad03.BSTree.BSTreeTestJUnit4.class,

	// Actividad 04
	org.eda1.actividad04.ejercicio01.HashTableTestJUnit4.class,
	org.eda1.actividad04.ejercicio02.SpellCheckerTestJUnit4.class,
	org.eda1.actividad04.ejercicio03.ThesaurusTestJUnit4.class,
	
	// Actividad 05
	org.eda1.actividad05.NetworkTestJUnit4.class,

	// EXAMENES PARCIALES 2013
	// Examen Parcial Grupo A
	org.eda1.examenParcialGrupoA.ejercicio01.HeapExamenParcialGrupoATestJUnit4.class,
	org.eda1.examenParcialGrupoA.ejercicio02.ProcessTextsWFTestJUnit4.class,
	
	// Examen Parcial Grupo B
	org.eda1.examenParcialGrupoB.ejercicio01.ProcesarDatosEPCBTestJUnit4.class,
	org.eda1.examenParcialGrupoB.ejercicio02.BSTreeExamenParcialGrupoB18112013TestJUnit4.class,

	// EXAMENES PARCIAL 2014
	org.eda1.examenSegundoParcial.ejercicio01.ProcesarDatosExamenParcialTestJUnit4.class,
	org.eda1.examenSegundoParcial.ejercicio02.RoadNetworkExamenParcialTestJUnit4.class,

	// ACTIVIDADES SEPTIEMBRE 2014
	// Actividad 01
	org.eda1.actividadesSeptiembre.actividad01.HeapSeptiembre2014TestJUnit4.class,

	// Actividad 02
	org.eda1.actividadesSeptiembre.actividad02.BSTreeSeptiembre2014TestJUnit4.class,

	// Actividad 03
	org.eda1.actividadesSeptiembre.actividad03.HashTableSeptiembre2014TestJUnit4.class,

	// Actividad 04
	org.eda1.actividadesSeptiembre.actividad04.MapSeptiembre2014TestJUnit4.class,

	// Actividad 05
	org.eda1.actividadesSeptiembre.actividad05.NetworkSeptiembre2014TestJUnit4.class
	
})

public class AllTests {

}
