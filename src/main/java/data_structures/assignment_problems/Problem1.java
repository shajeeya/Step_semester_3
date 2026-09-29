package data_structures.assignment_problems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem1 {

    enum HackathonState {
        OPEN, JUDGING, PUBLISHED
    }

    interface ScoringRule {
        double calculateScore(double idea, double execution, double presentation);
        String getTrackName();
    }

    static class InnovationScoring implements ScoringRule {

        @Override
        public double calculateScore(double idea, double execution, double presentation) {
            return idea * 0.50 + execution * 0.30 + presentation * 0.20;
        }

        @Override
        public String getTrackName() {
            return "Innovation";
        }
    }

    static class OpenScoring implements ScoringRule {

        @Override
        public double calculateScore(double idea, double execution, double presentation) {
            return (idea + execution + presentation) / 3.0;
        }

        @Override
        public String getTrackName() {
            return "Open";
        }
    }

    static class Student {
        private String name;

        public Student(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    static class Judge {
        private String name;

        public Judge(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    static class Score {
        private double idea;
        private double execution;
        private double presentation;
        private double finalScore;

        public Score(double idea, double execution, double presentation,
                     ScoringRule scoringRule) {

            if (idea < 0 || idea > 10 ||
                execution < 0 || execution > 10 ||
                presentation < 0 || presentation > 10) {
                throw new IllegalArgumentException("Ratings must be between 0 and 10");
            }

            this.idea = idea;
            this.execution = execution;
            this.presentation = presentation;

            this.finalScore =
                    scoringRule.calculateScore(idea, execution, presentation);
        }

        public double getIdea() {
            return idea;
        }

        public double getFinalScore() {
            return finalScore;
        }
    }

    static class Project {
        private String name;
        private Score score;
        private Judge judge;

        public Project(String name) {
            this.name = name;
        }

        public void submitScore(Judge judge,
                                double idea,
                                double execution,
                                double presentation,
                                ScoringRule scoringRule,
                                HackathonState state) {

            if (state == HackathonState.PUBLISHED) {
                throw new IllegalStateException(
                        "Results have already been published.");
            }

            if (score != null) {
                throw new IllegalStateException(
                        "A score has already been recorded.");
            }

            this.judge = judge;
            this.score = new Score(
                    idea,
                    execution,
                    presentation,
                    scoringRule
            );
        }

        public String getName() {
            return name;
        }

        public Score getScore() {
            return score;
        }
    }

    static class Team {
        private String name;
        private List<Student> members;
        private ScoringRule scoringRule;
        private Project project;

        public Team(String name, List<Student> members,
                    ScoringRule scoringRule) {

            if (members.size() < 2 || members.size() > 4) {
                throw new IllegalArgumentException(
                        "A team must have 2 to 4 members.");
            }

            this.name = name;
            this.members = new ArrayList<>(members);
            this.scoringRule = scoringRule;
        }

        public void submitProject(String projectName,
                                  HackathonState state) {

            if (state == HackathonState.PUBLISHED) {
                throw new IllegalStateException(
                        "Cannot submit after results are published.");
            }

            if (project != null) {
                throw new IllegalStateException(
                        "A team can submit only one project.");
            }

            project = new Project(projectName);

            System.out.println(
                    "Project '" + projectName +
                    "' submitted by " + name + ".");
        }

        public void scoreProject(Judge judge,
                                 double idea,
                                 double execution,
                                 double presentation,
                                 HackathonState state) {

            if (project == null) {
                throw new IllegalStateException(
                        "No project has been submitted.");
            }

            project.submitScore(
                    judge,
                    idea,
                    execution,
                    presentation,
                    scoringRule,
                    state
            );

            System.out.println(
                    "Score recorded for '" +
                    project.getName() + "'.");
        }

        public void printFinalScore() {

            if (project != null && project.getScore() != null) {
                System.out.printf(
                        "Final score: %.2f%n",
                        project.getScore().getFinalScore()
                );
            }
        }

        public String getName() {
            return name;
        }

        public List<Student> getMembers() {
            return members;
        }

        public ScoringRule getScoringRule() {
            return scoringRule;
        }

        public Project getProject() {
            return project;
        }
    }

    static class Hackathon {
        private String name;
        private List<Team> teams;
        private Set<Student> registeredStudents;
        private HackathonState state;

        public Hackathon(String name) {
            this.name = name;
            this.teams = new ArrayList<>();
            this.registeredStudents = new HashSet<>();
            this.state = HackathonState.OPEN;
        }

        public void registerTeam(Team team) {

            if (state != HackathonState.OPEN) {
                throw new IllegalStateException(
                        "Registration is closed.");
            }

            for (Student student : team.getMembers()) {

                if (registeredStudents.contains(student)) {
                    throw new IllegalArgumentException(
                            "A student can belong to only one team per hackathon.");
                }
            }

            teams.add(team);
            registeredStudents.addAll(team.getMembers());

            System.out.println(
                    "Team " + team.getName() +
                    " registered (" +
                    team.getMembers().size() +
                    " members, " +
                    team.getScoringRule().getTrackName() +
                    " track)."
            );
        }

        public void startJudging() {
            state = HackathonState.JUDGING;
        }

        public void publishResults() {

            if (state != HackathonState.JUDGING) {
                throw new IllegalStateException(
                        "Hackathon is not ready for publishing.");
            }

            state = HackathonState.PUBLISHED;

            for (Team team : teams) {
                team.printFinalScore();
            }

            System.out.println("Results published.");
        }

        public HackathonState getState() {
            return state;
        }
    }

    public static void main(String[] args) {

        Hackathon hackathon =
                new Hackathon("Code Sprint");

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");
        Student kiran = new Student("Kiran");

        Judge judge =
                new Judge("Judge 1");

        ScoringRule innovation =
                new InnovationScoring();

        ScoringRule open =
                new OpenScoring();

        // Team 1 - valid
        List<Student> byteBustersMembers =
                new ArrayList<>();

        byteBustersMembers.add(asha);
        byteBustersMembers.add(ravi);
        byteBustersMembers.add(neha);

        Team byteBusters =
                new Team(
                        "ByteBusters",
                        byteBustersMembers,
                        innovation
                );

        try {
            hackathon.registerTeam(byteBusters);
        } catch (Exception e) {
            System.out.println(
                    "Registration failed: " + e.getMessage());
        }

        // Team 2 - invalid because it has only 1 member
        List<Student> soloCoderMembers =
                new ArrayList<>();

        soloCoderMembers.add(kiran);

        try {
            Team soloCoder =
                    new Team(
                            "SoloCoder",
                            soloCoderMembers,
                            open
                    );

            hackathon.registerTeam(soloCoder);

        } catch (Exception e) {
            System.out.println(
                    "Registration failed: " + e.getMessage());
        }

        // Project submission
        byteBusters.submitProject(
                "SmartAttend",
                hackathon.getState()
        );

        // Start judging
        hackathon.startJudging();

        // Score project
        byteBusters.scoreProject(
                judge,
                8,
                7,
                9,
                hackathon.getState()
        );

        // Publish results
        hackathon.publishResults();

        // Attempt to rescore after publishing
        try {
            byteBusters.scoreProject(
                    judge,
                    10,
                    7,
                    9,
                    hackathon.getState()
            );

        } catch (Exception e) {
            System.out.println(
                    "Rescore rejected: " + e.getMessage());
        }
    }
}