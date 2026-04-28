package queue;

import java.util.HashMap;
import java.util.LinkedList;

public class QuickMarker {
    private final LinkedList<Applicant> queue;
    private final Applicant[] applicants;
    private final int numberApplicants;
    private final int skip;

    private HashMap<Integer, Boolean> map = new HashMap<Integer, Boolean>();


    public QuickMarker(int numberApplicants, int skip) {
        this.numberApplicants = numberApplicants;
        this.applicants = new Applicant[numberApplicants];
        this.skip = skip;
        this.queue = new LinkedList<>();

        initApplicants();
    }

    public Applicant[] getMarking() {
        runMarking();
        return applicants;
    }

    private void initApplicants() {
        for (int i = 0; i < this.numberApplicants; i++) {
            applicants[i] = new Applicant(i + 1, 0);

            // queue the applicant
            queue.add(applicants[i]);
        }
    }

    private void runMarking() {
        // each applicant needs to choose a number less than n
        int applicantsMarked = 0;

        while (this.queue.peek() != null) {
            // get the applicant from the queue
            Applicant a = getApplicant(skip);
            markApplicant(a, applicantsMarked);

            applicantsMarked++;
        }
    }

    private void markApplicant(Applicant a, int n) {
        float mark = ((float) (n + 1) / this.numberApplicants) * 100.0f;
        a.setMark(mark);
    }

    private Applicant getApplicant(int skip) {

        // for 1 less iteration, remove and requeue the applicant
        for (int i = 0; i < skip - 1; i++) {
            Applicant a = queue.poll();
            queue.add(a);
        }

        // return the last applicant
        return queue.poll();
    }

    public static void main(String[] args) {
        QuickMarker quickMarker = new QuickMarker(10, 4);
        Applicant[] applicants = quickMarker.getMarking();

        for (Applicant applicant : applicants) {
            System.out.println(applicant);
        }
    }
}
