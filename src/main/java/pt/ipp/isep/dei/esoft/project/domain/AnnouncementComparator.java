package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Comparator;

public class AnnouncementComparator implements Comparator<Announcement> {


    @Override
    public int compare(Announcement request1, Announcement request2) {
        if (request1.getDate().isBefore(request2.getDate())) {
            return -1;
        }
        if (request1.getDate().isAfter(request2.getDate())) {
            return 1;
        }
        return 0;
    }
}
