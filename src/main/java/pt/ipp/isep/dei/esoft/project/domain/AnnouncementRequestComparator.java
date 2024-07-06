package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Calendar;
import java.util.Comparator;

public class AnnouncementRequestComparator implements Comparator<AnnouncementRequest> {




    @Override
    public int compare(AnnouncementRequest request1, AnnouncementRequest request2) {
        if (request1.getDate().isBefore(request2.getDate())) {
            return -1;
        }
        if (request1.getDate().isAfter(request2.getDate())) {
            return 1;
        }
        return 0;
    }
}



