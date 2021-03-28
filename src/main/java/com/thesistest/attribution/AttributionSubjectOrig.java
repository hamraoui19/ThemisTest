package com.thesistest.attribution;

/**
 *    Java Program to Implement Gale Shapley Algorithm
 **/

/** Class AttributionSubject **/
public class AttributionSubjectOrig
{
    private int N, engagedCount;
    private String[][] studentPref;
    private String[][] subjectPref;
    private String[] student;
    private String[] subject;
    private String[] subjectPartner;
    private boolean[] studentEngaged;

    /** Constructor **/
    public AttributionSubjectOrig(String[] m, String[] w, String[][] mp, String[][] wp)
    {
        N = mp.length;
        engagedCount = 0;
        student = m;
        subject = w;
        studentPref = mp;
        subjectPref = wp;
        studentEngaged = new boolean[N];
        subjectPartner = new String[N];

        calcMatches();
    }
    /** function to calculate all matches **/
    private void calcMatches()
    {
        while (engagedCount < N)
        {
            int free;
            for (free = 0; free < N; free++)
                if (!studentEngaged[free])
                    break;


            for (int i = 0; i < N && !studentEngaged[free]; i++)
            {
                int index = subjectIndexOf(studentPref[free][i]);

                if (subjectPartner[index] == null)
                {
                    subjectPartner[index] = student[free];
                    studentEngaged[free] = true;
                    engagedCount++;
                }
              /*  else
                {
                    String currentPartner = subjectPartner[index];
                    if (morePreference(currentPartner, student[free], index))
                    {
                        subjectPartner[index] = student[free];
                        studentEngaged[free] = true;
                        studentEngaged[studentIndexOf(currentPartner)] = false;
                    }
                }*/
            }
        }
        printCouples();
    }
    /** function to check if subject prefers new partner over old assigned partner **/
    private boolean morePreference(String curPartner, String newPartner, int index)
    {
        for (int i = 0; i < N; i++)
        {
            if (subjectPref[index][i].equals(newPartner))
                return true;
            if (subjectPref[index][i].equals(curPartner))
                return false;
        }
        return false;
    }
    /** get student index **/
    private int studentIndexOf(String str)
    {
        for (int i = 0; i < N; i++)
            if (student[i].equals(str))
                return i;
        return -1;
    }
    /** get subject index **/
    private int subjectIndexOf(String str)
    {
        for (int i = 0; i < N; i++)
            if (subject[i].equals(str))
                return i;
        return -1;
    }
    /** print couples **/
    public void printCouples()
    {
        System.out.println("Couples are : ");
        for (int i = 0; i < N; i++)
        {
            System.out.println("Student: "+subjectPartner[i] +" | "+  "Subject: "+subject[i]);
        }
    }
    /** main function **/
    public static void main(String[] args)
    {
        System.out.println("Gale Shapley Algorithm\n");
        /** list of students **/
        String[] students = {"Jeremy","Hussein","David","Christophe","Romain","Antony"};
        /** list of subjects **/
        String[] subjects = {"Subject1", "Subject2", "Subject3", "Subject4", "Subject5", "Subject6"};

        System.out.println("Veuillez introduire vos 5 préférences de sujet:");

        /** subject preference **/
        String[][] mp = {{"Subject1", "Subject2", "Subject3", "Subject4", "Subject5", "Subject6"},
                {"Subject1", "Subject2", "Subject1", "Subject4", "Subject5", "Subject6"},
                {"Subject2", "Subject1", "Subject3", "Subject4", "Subject5", "Subject6"},
                {"Subject4", "Subject2", "Subject3", "Subject4", "Subject5", "Subject1"},
                {"Subject5", "Subject2", "Subject3", "Subject4", "Subject5", "Subject1"},
                {"Subject6", "Subject2", "Subject3", "Subject4", "Subject5", "Subject1"}};
        /** student preference **/
        String[][] wp = {{"Jeremy","Hussein","David","Christophe","Romain","Antony"},
                {"Hussein","Jeremy","David","Christophe","Romain","Antony"},
                {"David","Jeremy","Hussein","Christophe","Romain","Antony"},
                {"Christophe","Jeremy","Hussein","David","Romain","Antony"},
                {"Romain","Jeremy","Hussein","David","Christophe","Antony"},
                {"Antony","Jeremy","Hussein","David","Christophe","Romain"}};

        AttributionSubjectOrig gs = new AttributionSubjectOrig(students, subjects, mp, wp);
    }
}
