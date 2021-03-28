package com.thesistest.attribution;

/**
 *    Java Program to Implement Algorithm Attribution subject with the Algorithm Gale Shapley
 **/

import java.util.Arrays;
import java.util.Collection;

/** Class AttributionSubject **/
public class AttributionSubject
{
    private int N, engagedCount;
    private String[][] studentPref;
    private String[][] subjectPref;
    private String[] student;
    private String[] subject;
    private String[] subjectPartner;
    private boolean[] studentEngaged;

    /** Constructor **/
    public AttributionSubject(String[] m, String[] w, String[][] sp)
    {
        N = sp.length;
        engagedCount = 0;
        student = m;
        subject = w;
        studentPref = sp;
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
            }
        }
        printAttributions();
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
    /** print attributions **/
    public void printAttributions()
    {
        System.out.println("The attributions are: ");
        for (int i = 0; i < N; i++)
        {
            System.out.println("Student: "+subjectPartner[i] +" have "+  "Choice: "+subject[i]+"\n");
        }
    }
    /** main function **/
    public static void main(String[] args)
    {
        /** list of students **/
        String[] students = {"Jeremy","Hussein","David","Christophe","Romain","Antony"};
        /** list of subjects **/
        String[] subjects = {"Subject1", "Subject2", "Subject3", "Subject4", "Subject5", "Subject6"};

        /** subject preference **/
        String[][] sp = {{"Subject1", "Subject2", "Subject3", "Subject4", "Subject5", "Subject6"},
                {"Subject1", "Subject2", "Subject1", "Subject4", "Subject5", "Subject6"},
                {"Subject2", "Subject1", "Subject3", "Subject4", "Subject5", "Subject6"},
                {"Subject4", "Subject2", "Subject3", "Subject4", "Subject5", "Subject1"},
                {"Subject5", "Subject2", "Subject3", "Subject4", "Subject5", "Subject1"},
                {"Subject6", "Subject2", "Subject3", "Subject4", "Subject5", "Subject1"}};

        AttributionSubject as = new AttributionSubject(students, subjects, sp);
    }
}
