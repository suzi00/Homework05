package fmi.informatics.DescendingComparators;

import fmi.informatics.AscendingComparators.PersonComparator;
import fmi.informatics.extending.Person;

public class WeightDescendingComparator extends PersonComparator {

    @Override
    public int compare(Person person1, Person person2) {

        if (person1.getWeight() < person2.getWeight()) {
            return 1 * sortOrder;
        } else if (person1.getWeight() > person2.getWeight()) {
            return -1 * sortOrder;
        }

        return 0;
    }
}
