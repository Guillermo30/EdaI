package org.eda1.actividadesSeptiembre.actividad04;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

public class MapSeptiembre2014TestJUnit4 {
	String directorioEntrada;
	File archivoEntradas;

	@Before
	public void setUp() throws Exception {
		directorioEntrada = System.getProperty("user.dir");

		directorioEntrada = directorioEntrada + File.separator + 
				"src" + File.separator + 
				"org" + File.separator + 
				"eda1" + File.separator + 
				"actividadesSeptiembre" + File.separator +
				"actividad04" + File.separator;
	}

	@Test
	public void testSimilarWords() {

		long start, end;
		long timeBF, timeImpr, timeFast;
		String stringArchivoEntrada = "dictionary.txt";
		stringArchivoEntrada = directorioEntrada + stringArchivoEntrada;

        SimilarWords similarWords = new SimilarWords();

        List<String> words = similarWords.readWords(stringArchivoEntrada);
        
        assertTrue(words.size() == 58110);
        
        Map<Integer, Integer> distWords = similarWords.computeDistributionOfDictionary(words);
        String strDistWords = "";
        int countWords = 0;
        for (Map.Entry<Integer, Integer> entry : distWords.entrySet()) {
        	countWords += entry.getValue();
        	strDistWords += entry.getKey() + " --- " + entry.getValue() + "\n";
        }

        assertTrue(distWords.size() == 21);
        
        assertTrue(words.size() == countWords);
        
        String strDistWordsOutput = "2 --- 47" + "\n";
        strDistWordsOutput += "3 --- 589" + "\n";
        strDistWordsOutput += "4 --- 2294" + "\n";
        strDistWordsOutput += "5 --- 4266" + "\n";
        strDistWordsOutput += "6 --- 6936" + "\n";
        strDistWordsOutput += "7 --- 9203" + "\n";
        strDistWordsOutput += "8 --- 9396" + "\n";
        strDistWordsOutput += "9 --- 7696" + "\n";
        strDistWordsOutput += "10 --- 6377" + "\n";
        strDistWordsOutput += "11 --- 4557" + "\n";
        strDistWordsOutput += "12 --- 3101" + "\n";
        strDistWordsOutput += "13 --- 1880" + "\n";
        strDistWordsOutput += "14 --- 924" + "\n";
        strDistWordsOutput += "15 --- 493" + "\n";
        strDistWordsOutput += "16 --- 193" + "\n";
        strDistWordsOutput += "17 --- 99" + "\n";
        strDistWordsOutput += "18 --- 38" + "\n";
        strDistWordsOutput += "19 --- 9" + "\n";
        strDistWordsOutput += "20 --- 9" + "\n";
        strDistWordsOutput += "21 --- 2" + "\n";
        strDistWordsOutput += "22 --- 1" + "\n";
    	assertEquals(strDistWords, strDistWordsOutput);

        Map<String,List<String>> mapSimilarWords;
        start = System.currentTimeMillis();
        mapSimilarWords = similarWords.computeSimilarWordsBruteForce(words);
        end = System.currentTimeMillis();
        timeBF = end-start;

        start = System.currentTimeMillis();
        mapSimilarWords = similarWords.computeSimilarWordsImproved(words);
        end = System.currentTimeMillis();
        timeImpr = end-start;

        assertTrue(timeBF >= timeImpr);
        
        start = System.currentTimeMillis();
        mapSimilarWords = similarWords.computeSimilarWords(words);
        end = System.currentTimeMillis();
        timeFast = end-start;

        assertTrue(timeBF >= timeFast);
        assertTrue(timeImpr >= timeFast);
        
        List<String> mostChangeable = similarWords.findMostChangeable(mapSimilarWords);
        assertTrue(mostChangeable.size() == 2);
        String str = similarWords.showMostChangeables(mostChangeable, mapSimilarWords);

        String strOutput = "mare: bare" + "\n"
        + " care" + "\n"
        + " dare" + "\n"
        + " fare" + "\n"
        + " hare" + "\n"
        + " pare" + "\n"
        + " rare" + "\n"
        + " ware" + "\n"
        + " mere" + "\n"
        + " mire" + "\n"
        + " more" + "\n"
        + " mace" + "\n"
        + " made" + "\n"
        + " make" + "\n"
        + " male" + "\n"
        + " mane" + "\n"
        + " mate" + "\n"
        + " maze" + "\n"
        + " mara" + "\n"
        + " mark" + "\n"
        + " marl" + "\n"
        + " mars" + "\n"
        + " mart" + "\n"
        + " marx" + "\n"
        + " mary" + "\n"
        + " (25 words)" + "\n"
        + "say: bay" + "\n"
        + " day" + "\n"
        + " gay" + "\n"
        + " hay" + "\n"
        + " jay" + "\n"
        + " lay" + "\n"
        + " may" + "\n"
        + " nay" + "\n"
        + " pay" + "\n"
        + " ray" + "\n"
        + " way" + "\n"
        + " shy" + "\n"
        + " sky" + "\n"
        + " sly" + "\n"
        + " soy" + "\n"
        + " spy" + "\n"
        + " sty" + "\n"
        + " sac" + "\n"
        + " sad" + "\n"
        + " sag" + "\n"
        + " sam" + "\n"
        + " san" + "\n"
        + " sap" + "\n"
        + " sat" + "\n"
        + " saw" + "\n"
        + " (25 words)" + "\n";
    	assertEquals(str, strOutput);
    	
        Map<String, Integer> highChangeablesWords = similarWords.findHighChangeables(mapSimilarWords, 22);
        assertTrue(highChangeablesWords.size() == 30);
        str = "";
        for (Map.Entry<String, Integer> entry : highChangeablesWords.entrySet())
        	str += entry.getKey() + " <" + entry.getValue() + ">" + "\n";
        
        strOutput = "bad <22>" + "\n"
        + "bag <24>" + "\n"
        + "bale <23>" + "\n"
        + "bare <22>" + "\n"
        + "bat <22>" + "\n"
        + "care <23>" + "\n"
        + "cares <23>" + "\n"
        + "core <22>" + "\n"
        + "cot <23>" + "\n"
        + "cow <22>" + "\n"
        + "done <23>" + "\n"
        + "lag <23>" + "\n"
        + "lap <22>" + "\n"
        + "line <22>" + "\n"
        + "mad <23>" + "\n"
        + "mag <22>" + "\n"
        + "male <22>" + "\n"
        + "map <22>" + "\n"
        + "mare <25>" + "\n"
        + "pale <23>" + "\n"
        + "pap <23>" + "\n"
        + "pat <22>" + "\n"
        + "rad <22>" + "\n"
        + "rag <22>" + "\n"
        + "rap <22>" + "\n"
        + "san <22>" + "\n"
        + "sap <23>" + "\n"
        + "sat <22>" + "\n"
        + "say <25>" + "\n"
        + "ware <23>" + "\n";
    	assertEquals(str, strOutput);

	}

}
