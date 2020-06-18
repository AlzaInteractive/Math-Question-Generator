package com.alza.quiz.util;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by ewin.sutriandi@gmail.com on 24/12/16.
 */

public class CommonFunctionAndValues {
	public final static String MJXTAG = "$$";
	public final static DecimalFormat DF2PLC = new DecimalFormat("#.##");
	
	public static int hashSimple(String s) {
		int hash = 7;
		for (int i = 0; i < s.length(); i++) {
		    hash = hash*31 + s.charAt(i);
		}
		return hash;
	}

	public static String enclosedWithMathJaxExp(String s) {
		return MJXTAG + s + MJXTAG;
	}

	public static String[] nameElder = { "Mbah Jamil", "Papuq Abok", "Ninik Senah", "Papuq Senep", " Wak Ikoh",
			"Wak Marli", "Eyang Segep", "Eyang Ahmad", "Ninik Zaenab", "Mbah Soleh", "Datuk Sunar", "Datuk Menggep",
			"Aki Izudin", " Aki Jarwo" };

	public static String[] localDays(Locale loc) {
		DateFormatSymbols dfs = new DateFormatSymbols(loc);
		String weekdays[] = dfs.getWeekdays();
		String[] days = new String[7];
		for (int i = 1; i < weekdays.length; i++) {
			days[i - 1] = weekdays[i];
		}
		return days;
	}

	public static boolean hasAmPmClock(Locale locale) {
		DateFormat stdFormat = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.US);
		DateFormat localeFormat = DateFormat.getTimeInstance(DateFormat.LONG, locale);
		String midnight = "";
		try {
			midnight = localeFormat.format(stdFormat.parse("12:00 AM"));
		} catch (ParseException ignore) {
		
		}
		return midnight.contains("12");
	}

	public static String minutesToHour(int min, Locale loc) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MINUTE, min);
		DateFormat dfStd = new SimpleDateFormat("HH:mm");
		DateFormat dfStdAMPM = new SimpleDateFormat("KK:mm a");
		if (hasAmPmClock(loc)) {
			return dfStdAMPM.format(c.getTime());
		}
		return dfStd.format(c.getTime());
	}

	public static String[] nameSingle = { "Juwi", "Itet", "Yopi", "Uli", "Aris", "Gibran", "Rondi", "Dudi", "Herman",
			"Hari", "Toni", "Zul", "Alda", "Liza", "Yanti", "Zaenab", "Jamilah", "Linda", "Azizah", "Afifa", "Laila",
			"Alwi", "Shinta", "Puspita", "Sari", "Nana", "Neni", "Eka", "Jamil", "Syarif", "Evelyn", "Bryan", "Utari",
			"Kurnia", "Vicky", "Firman", "Ito", "Indah", "Felisha", "Luffy", "Sanji", "Nami", "Robin" };

	public static String[] nameBapak = { "Pak Joko", "Pak Idrus", "Koh Ahong", "Mang Enal", "Mang Eman", "Pak Romli",
			"Koh Jaya", "Pak Sofyan", "Pak Mamat", "Pak Karwo", "Amaq Taesir", "Amaq Ancun", "Amaq Suleman" };
	public static String[] nameIbu = { "Inaq Esun", "Inaq Sinerah", "Inaq Genceng", "Bude Linda", "Bude Harnum",
			"Tante Sylvia", "Tante Ning", "Bik Itet", "Bik Yeyen", "Ibu Surti", "Ibu Lintang", "Jeng Sari",
			"Jeng Lina" };
	public static String[][] namePairs = { { "Aalia", "Zahra" }, { "Aqeela", "Najwa" }, { "Agi", "Rinda" },
			{ "Arfi", "Davva" }, { "Kevin", "Aldi" }, { "Yuni", "Tiwi" }, { "Amrin", "Jacky" }, { "Soleh", "Sobirin" },
			{ "Rizki", "Soleh" }, { "Abdullah", "Ali" }, { "Anandi", "Ananda" }, { "Hari", "Subhan" },
			{ "Sukiman", "Naufal" }, { "Widya", "Ilma" }, { "Ratih", "Daus" }, { "Ningrum", "Ratih" },
			{ "Galuh", "Ratna" }, };

	public static String[][] sportPairs = { { "berenang", "bersepeda" }, { "taekwondo", "bersepeda" },
			{ "jogging", "taekwondo" }, { "bulutangkis", "memanah" }, { "memanah", "berenang" },
			{ "sepatu roda", "silat" }, { "jogging", "silat" }, { "tenis", "sepatu roda" }, { "senam", "memanah" },
			{ "wushu", "senam" }, { "memancing", "berenang" },

	};

	public static String[][] lesPairs = { { "piano", "tahfiz" }, { "tahfiz", "biola" }, { "gitar", "paduan suara" },
			{ "fisika", "piano" }, { "matematika", "gitar" }, { "Bahasa Inggris", "piano" }, { "Kimia", "tahfiz" }, };

	public static String[] getPairofPeople() {
		int rnd = new Random().nextInt(namePairs.length);
		return namePairs[rnd];

	}

	public static int[][] simpleIntPairs = { { 2, 3 }, { 2, 4 }, { 2, 5 }, { 2, 6 }, { 2, 7 }, { 2, 8 }, { 2, 9 },
			{ 3, 4 }, { 3, 5 }, { 3, 6 }, { 3, 7 }, { 3, 8 }, { 3, 9 }, { 4, 5 }, { 4, 6 }, { 4, 7 }, { 4, 8 },
			{ 4, 9 }, { 5, 6 }, { 5, 7 }, { 5, 8 }, { 5, 9 }, { 6, 7 }, { 6, 8 }, { 6, 9 }, { 7, 8 }, { 7, 9 },
			{ 8, 9 } };
	public static int[] simpleInt = { 2, 3, 4, 5, 6, 7, 8, 9 };
	public static int[] simpleIntLarger = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18 };
	public static int[] prime101stDigit = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29 };

	public static int getPrime101stRandom() {
		int rnd = new Random().nextInt(prime101stDigit.length);
		return prime101stDigit[rnd];
	}

	public static int[] getPairOfIntSimple() {
		int rnd = new Random().nextInt(simpleIntPairs.length);
		return simpleIntPairs[rnd];
	}

	public static int getRandom(int[] array) {
		int rnd = new Random().nextInt(array.length);
		return array[rnd];
	}

	public static String buildScenario(String base, String[] people, int[] values) {
		String[] peopleExp = { "#orang1?", "#orang2?", "#orang3?", "#orang4?", "#orang5?" };
		int i = 0;
		for (String s : peopleExp) {
			if (base.contains(s)) {
				base = base.replace(s, people[i]);
			}
			i++;
		}
		base = buildScenario(base, values);
		return base;
	}

	public static String buildScenario(String base, int[] values) {

		String[] valueExp = { "#val1?", "#val2?", "#val3", "#val4?", "#val5" };
		int i = 0;
		for (String s : valueExp) {

			if (base.contains(s)) {
				base = base.replace(s, String.valueOf(values[i]));
			}
			i++;
		}
		base = buildScenario(base);
		return base;
	}

	public static String buildScenario(String base) {
		if (base.contains("#orang1?") && base.contains("#orang2?") && !base.contains("#orang3?")) {
			String[] peopleExp = { "#orang1?", "#orang2?" };
			String[] people = getPairofPeople();
			int j = 0;
			for (String s : peopleExp) {
				if (base.contains(s)) {
					base = base.replace(s, people[j]);
				}
				j++;
			}
		}
		if (base.contains("#orang1?")) {
			shuffleArray(nameSingle);
			String orang = nameSingle[0];
			base.replace("#orang1?", orang);
		}
		if (base.contains("#orang")) {
			String[] peopleExp = { "#orang1?", "#orang2?", "#orang3?", "#orang4?", "#orang5?" };
			shuffleArray(nameSingle);
			int k = 0;
			for (String s : peopleExp) {
				if (base.contains(s)) {
					base = base.replace(s, nameSingle[k]);
				}
				k++;
			}
		}
		if (base.contains("#elder")) {
			String[] peopleExp = { "#elder1?", "#elder?", "#elder3?", "#elder4?", "#elder?" };
			shuffleArray(nameElder);
			int l = 0;
			for (String s : peopleExp) {
				if (base.contains(s)) {
					base = base.replace(s, nameElder[l]);
				}
				l++;
			}
		}
		if (base.contains("#bapak")) {
			String[] peopleExp = { "#bapak1?", "#bapak2?", "#bapak3?", "#bapak4?", "#bapak5?" };
			shuffleArray(nameBapak);
			int l = 0;
			for (String s : peopleExp) {
				if (base.contains(s)) {
					base = base.replace(s, nameBapak[l]);
				}
				l++;
			}
		}
		if (base.contains("#sport")) {
			shuffleArray(sportPairs);
			base = base.replace("#sport1?", sportPairs[0][0]);
			base = base.replace("#sport2?", sportPairs[0][1]);
		}
		if (base.contains("#les")) {
			shuffleArray(lesPairs);
			base = base.replace("#les1?", lesPairs[0][0]);
			base = base.replace("#les2?", lesPairs[0][1]);
		}
		return base;
	}

	public static void shuffleArray(int[] ar) {
		Random rnd = ThreadLocalRandom.current();
		for (int i = ar.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			int a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}

	public static void shuffleArray(int[][] ar) {
		Random rnd = ThreadLocalRandom.current();
		for (int i = ar.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			int[] a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}

	public static void shuffleArray(String[] ar) {
		Random rnd = ThreadLocalRandom.current();
		for (int i = ar.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			String a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}

	public static void shuffleArray(Object[] ar) {
		Random rnd = ThreadLocalRandom.current();
		for (int i = ar.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			Object a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}

	public static String dateToString(Date dt, Locale loc) {
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, loc);
		String formattedDate = df.format(dt);
		return formattedDate;
	}

	public static int[] getShuffledSimpleInts() {
		shuffleArray(simpleInt);
		return simpleInt;
	}

	public static int[] getShuffledSimpleIntsLarger() {
		shuffleArray(simpleIntLarger);
		return simpleIntLarger;
	}

	public static int getRandomInt(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max);
	}

	public static List<String> getStringCollection(ResourceBundle bundle, String key) {
		Enumeration<String> a = bundle.getKeys();
		List<String> l = new ArrayList<String>();
		while (a.hasMoreElements()) {
			String string = (String) a.nextElement();
			// System.out.println(string+" : "+bundle.getString(string));
			if (string.startsWith(key)) {
				l.add(bundle.getString(string));
				// System.out.println(string+" : "+bundle.getString(string));
			}
		}
		return l;
	}
}
