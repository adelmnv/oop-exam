package studying;

import java.util.Comparator;
import java.util.List;

public class LessonComparator implements Comparator<Lesson> {
    private static final List<String> DAYS_ORDER = List.of(
        "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
    );

    @Override
    public int compare(Lesson l1, Lesson l2) {
        int dayComparison = Integer.compare(DAYS_ORDER.indexOf(l1.getDayOfWeek()), DAYS_ORDER.indexOf(l2.getDayOfWeek()));
        if (dayComparison != 0) {
            return dayComparison;
        }
        return l1.getTime().compareTo(l2.getTime());
    }
}
