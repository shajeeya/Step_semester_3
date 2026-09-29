package data_structures.class_problems;

public class Problem1 {

    static abstract class Question {
        protected String questionText;
        protected String correctAnswer;

        public Question(String questionText, String correctAnswer) {
            this.questionText = questionText;
            this.correctAnswer = correctAnswer;
        }

        public abstract boolean isCorrect(String answer);
    }

    static class MultipleChoiceQuestion extends Question {

        public MultipleChoiceQuestion(String questionText, String correctAnswer) {
            super(questionText, correctAnswer);
        }

        @Override
        public boolean isCorrect(String answer) {
            return correctAnswer.equals(answer);
        }
    }

    static class Attempt {
        private boolean submitted;
        private int correctAnswers;

        public Attempt() {
            submitted = false;
            correctAnswers = 0;
        }

        public void submit(Question[] questions, String[] answers) {
            if (submitted) {
                System.out.println("Attempt already submitted.");
                return;
            }

            for (int i = 0; i < questions.length; i++) {
                if (questions[i].isCorrect(answers[i])) {
                    correctAnswers++;
                }
            }

            submitted = true;
        }

        public int getCorrectAnswers() {
            return correctAnswers;
        }

        public boolean isSubmitted() {
            return submitted;
        }
    }

    static class Examination {
        private String title;
        private Question[] questions;

        public Examination(String title, Question[] questions) {
            this.title = title;
            this.questions = questions;
        }

        public Attempt startAttempt() {
            System.out.println(
                "Examination '" + title + "' started by Student."
            );
            return new Attempt();
        }

        public void submitAttempt(Attempt attempt, String[] answers) {
            if (attempt.isSubmitted()) {
                return;
            }

            for (int i = 0; i < answers.length; i++) {
                System.out.println(
                    "Question " + (i + 1) +
                    " answered with '" + answers[i] + "'."
                );
            }

            attempt.submit(questions, answers);

            System.out.println(
                "Examination '" + title + "' submitted successfully."
            );

            System.out.println(
                "Result for '" + title +
                "' attempt: " +
                attempt.getCorrectAnswers() +
                "/" + questions.length +
                " correct"
            );
        }
    }

    public static void main(String[] args) {

        Question[] questions = {
            new MultipleChoiceQuestion(
                "What is 2 + 2?",
                "A"
            ),
            new MultipleChoiceQuestion(
                "Which is a programming language?",
                "B"
            )
        };

        Examination examination =
            new Examination("Math Quiz", questions);

        Attempt attempt = examination.startAttempt();

        String[] answers = {"A", "C"};

        examination.submitAttempt(attempt, answers);
    }
}