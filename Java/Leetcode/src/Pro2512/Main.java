package Pro2512;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    Map<Integer, Integer> studentScores;
    public List<Integer> topStudents(String[] positive_feedback, String[] negative_feedback, String[] report, int[] student_id, int k) {
        studentScores = new HashMap<>();
        Set<String> positiveFeedBack = new HashSet<>(Arrays.stream(positive_feedback).toList());
        Set<String> negativeFeedBack = new HashSet<>(Arrays.stream(negative_feedback).toList());
        int n = report.length;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int id = student_id[i];
            int score = getStudentScore(positiveFeedBack, negativeFeedBack, report[i]);
            studentScores.put(id, score);
            list.add(id);
        }
        list.sort((o1, o2) -> {
            if (Objects.equals(studentScores.get(o1), studentScores.get(o2))) {
                return Integer.compare(o1, o2);
            }
            return Integer.compare(studentScores.get(o2), studentScores.get(o1));
        });

        List<Integer> ans = new ArrayList<>();
        for (int i = 0 ;i < k;i++) {
            ans.add(list.get(i));
        }
        return ans;
    }

    private int getStudentScore(Set<String> positiveFeedBack, Set<String> negativeFeedBack, String report) {
        int score = 0;
        String[] reports = report.split(" ");
        for (String str : reports) {
            if (positiveFeedBack.contains(str)) {
                score += 3;
            } else if (negativeFeedBack.contains(str)) {
                score--;
            }
        }
        return score;
    }
}
