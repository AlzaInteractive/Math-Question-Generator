package com.alza.quiz.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by ewin.sutriandi@gmail.com on 24/12/16.
 */

public class CommonFunctionAndValues {
    public static String[] nameElder = {
            "Mbah Jamil","Papuq Abok","Ninik Senah","Papuq Senep"," Wak Ikoh", "Wak Marli",
            "Eyang Segep","Eyang Ahmad","Ninik Zaenab","Mbah Soleh","Datuk Sunar","Datuk Menggep",
            "Aki Izudin"," Aki Jarwo"
    };
    public static String[] hari = {
            "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu"
    };
    public static String[] bulan = {
            "Jan","Feb","Mar","Apr","Mei","Jun",
            "Jul","Ags","Sep","Okt","Nov","Des"
    };
    public static String[] bulanExt = {
            "Januari","Februari","Maret","April","Mei","Juni",
            "Juli","Agustus","September","Oktober","November","Desember"
    };
    public static String[] nameSingle = {
            "Juwi","Itet","Yopi","Uli","Aris","Gibran","Rondi","Dudi","Herman","Hari","Toni","Zul",
            "Alda", "Liza", "Yanti", "Zaenab", "Jamilah", "Linda", "Azizah", "Afifa", "Laila","Alwi",
            "Shinta", "Puspita", "Sari", "Nana", "Neni" ,"Eka", "Jamil", "Syarif", "Evelyn", "Bryan",
            "Utari", "Kurnia", "Vicky", "Firman","Ito", "Indah","Felisha", "Luffy", "Sanji", "Nami",
            "Robin"
    };

    public static String[] nameBapak = {
            "Pak Joko","Pak Idrus", "Koh Ahong", "Mang Enal", "Mang Eman", "Pak Romli", "Koh Jaya",
            "Pak Sofyan", "Pak Mamat", "Pak Karwo", "Amaq Taesir", "Amaq Ancun", "Amaq Suleman"
    };
    public static String[] nameIbu = {
            "Inaq Esun","Inaq Sinerah", "Inaq Genceng", "Bude Linda", "Bude Harnum", "Tante Sylvia", "Tante Ning",
            "Bik Itet", "Bik Yeyen", "Ibu Surti", "Ibu Lintang", "Jeng Sari", "Jeng Lina"
    };
    public static String[][] namePairs = {
            {"Aalia","Zahra"},
            {"Aqeela","Najwa"},
            {"Agi","Rinda"},
            {"Arfi","Davva"},
            {"Kevin","Aldi"},
            {"Yuni","Tiwi"},
            {"Amrin","Jacky"},
            {"Soleh","Sobirin"},
            {"Rizki","Soleh"},
            {"Abdullah","Ali"},
            {"Anandi","Ananda"},
            {"Hari","Subhan"},
            {"Sukiman","Naufal"},
            {"Widya","Ilma"},
            {"Ratih","Daus"},
            {"Ningrum","Ratih"},
            {"Galuh","Ratna"},
    };

    public static String[][] sportPairs = {
            {"berenang","bersepeda"},
            {"taekwondo","bersepeda"},
            {"jogging","taekwondo"},
            {"bulutangkis","memanah"},
            {"memanah","berenang"},
            {"sepatu roda","silat"},
            {"jogging","silat"},
            {"tenis","sepatu roda"},
            {"senam","memanah"},
            {"wushu","senam"},
            {"memancing","berenang"},

    };

    public static String[][] lesPairs = {
            {"piano","tahfiz"},
            {"tahfiz","biola"},
            {"gitar","paduan suara"},
            {"fisika","piano"},
            {"matematika","gitar"},
            {"Bahasa Inggris","piano"},
            {"Kimia","tahfiz"},
    };

    public static String[] getPairofPeople(){
        int rnd = new Random().nextInt(namePairs.length);
        return namePairs[rnd];

    }
    public static int[][] simpleIntPairs = {
            {2,3},{2,4},{2,5},{2,6},{2,7},{2,8},{2,9},
            {3,4},{3,5},{3,6},{3,7},{3,8},{3,9},
            {4,5},{4,6},{4,7},{4,8},{4,9},
            {5,6},{5,7},{5,8},{5,9},
            {6,7},{6,8},{6,9},
            {7,8},{7,9},
            {8,9}
    };
    public static int[] simpleInt = {
            2,3,4,5,6,7,8,9
    };
    public static int[] simpleIntLarger = {
            2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18
    };
    public static int[] getPairOfIntSimple(){
        int rnd = new Random().nextInt(simpleIntPairs.length);
        return simpleIntPairs[rnd];
    }

    public static String buildScenario(String base, String[] people, int[] values){
        String[] peopleExp = {"#orang1?","#orang2?","#orang3?","#orang4?","#orang5?"};
        int i = 0;
        for (String s: peopleExp) {
            if (base.contains(s)){
                base = base.replace(s,people[i]);
            }
            i++;
        }
        base = buildScenario(base,values);
        return base;
    }

    public static String buildScenario(String base, int[] values){

        String[] valueExp = {"#val1?","#val2?","#val3","#val4?","#val5"};
        int i = 0;
        for (String s : valueExp){

            if (base.contains(s)){
                base = base.replace(s,String.valueOf(values[i]));
            }
            i++;
        }
        if (base.contains("#orang1?")&&base.contains("#orang2?")&&!base.contains("#orang3?")){
            String[] peopleExp = {"#orang1?","#orang2?"};
            String[] people = getPairofPeople();
            int j = 0;
            for (String s: peopleExp) {
                if (base.contains(s)){
                    base = base.replace(s,people[j]);
                }
                j++;
            }
        }
        if (base.contains("#orang1?")){
            shuffleArray(nameSingle);
            String orang = nameSingle[0];
            base.replace("#orang1?",orang);
        }
        if (base.contains("#orang")){
            String[] peopleExp = {"#orang1?","#orang2?","#orang3?","#orang4?","#orang5?"};
            shuffleArray(nameSingle);
            int k = 0;
            for (String s: peopleExp) {
                if (base.contains(s)){
                    base = base.replace(s,nameSingle[k]);
                }
                k++;
            }
        }
        if (base.contains("#elder")){
            String[] peopleExp = {"#elder1?","#elder?","#elder3?","#elder4?","#elder?"};
            shuffleArray(nameElder);
            int l = 0;
            for (String s: peopleExp) {
                if (base.contains(s)){
                    base = base.replace(s,nameElder[l]);
                }
                l++;
            }
        }
        if (base.contains("#bapak")){
            String[] peopleExp = {"#bapak1?","#bapak2?","#bapak3?","#bapak4?","#bapak5?"};
            shuffleArray(nameBapak);
            int l = 0;
            for (String s: peopleExp) {
                if (base.contains(s)){
                    base = base.replace(s,nameBapak[l]);
                }
                l++;
            }
        }
        if (base.contains("#sport")){
            shuffleArray(sportPairs);
            base = base.replace("#sport1?",sportPairs[0][0]);
            base = base.replace("#sport2?",sportPairs[0][1]);
        }
        if (base.contains("#les")){
            shuffleArray(lesPairs);
            base = base.replace("#les1?",lesPairs[0][0]);
            base = base.replace("#les2?",lesPairs[0][1]);
        }
        return base;
    }

    public static void shuffleArray(int[] ar)
    {
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
    public static void shuffleArray(int[][] ar)
    {
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            int[] a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
    public static void shuffleArray(String[] ar)
    {
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            String a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
    public static void shuffleArray(Object[] ar)
    {
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            Object a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
    public static String dateToString(Date dt){
        String s;
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        s = ""+ c.get(Calendar.DATE);
        String bl = bulan[c.get(Calendar.MONTH)];
        String tahun = ""+c.get(Calendar.YEAR);
        s = s + " "+ bl +" "+ tahun;
        return s;
    }
}
