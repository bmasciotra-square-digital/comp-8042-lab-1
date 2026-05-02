package queue;

import java.util.LinkedList;

public class QuickMarker {
    private final LinkedList<Applicant> queue;
    private final Applicant[] applicants;
    private final int numberApplicants;
    private final int skip;

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

        QuizMarkerInput[] inputs = new QuizMarkerInput[]{
                new QuizMarkerInput(10, 4),
                new QuizMarkerInput(12, 3),
                new QuizMarkerInput(180, 37),
                new QuizMarkerInput(1100, 259)
        };

        for (QuizMarkerInput i : inputs) {
            QuickMarker quickMarker = new QuickMarker(i.numberOfApplicants(), i.skip());

            System.out.printf("Running Quiz Marker for Values [Number of applicants: %d] and [skip: %d] \n\n", i.numberOfApplicants(), i.skip());
            Applicant[] applicants = quickMarker.getMarking();

            for (Applicant applicant : applicants) {
                System.out.println(applicant);
            }

            System.out.println("\n");
        }
    }
}

