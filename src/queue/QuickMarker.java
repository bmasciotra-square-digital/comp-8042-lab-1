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
        int i = 0;
        while (!this.queue.isEmpty()) {
            // get the applicant from the queue
            Applicant a = queue.poll();

            if (i == skip - 1) {
                markApplicant(a, this.applicants.length - this.queue.size());
                i = 0;
                continue;
            }

            queue.add(a);
            i++;
        }
    }

    private void markApplicant(Applicant a, int n) {
        float mark = ((float) n / this.numberApplicants) * 100.0f;
        a.setMark(mark);
    }

    public static void main(String[] args) {
         QuickMarker quickMarker = new QuickMarker(10, 4);
        //   QuickMarker quickMarker = new QuickMarker(12, 3);
//        QuickMarker quickMarker = new QuickMarker(180, 37);
      //  QuickMarker quickMarker = new QuickMarker(1100, 259);

        Applicant[] applicants = quickMarker.getMarking();

        for (Applicant applicant : applicants) {
            System.out.println(applicant);
        }
    }
}
