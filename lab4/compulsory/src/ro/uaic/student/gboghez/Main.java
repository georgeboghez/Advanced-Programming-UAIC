package ro.uaic.student.gboghez;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Resident r0 = new Resident("R0");
        Resident r1 = new Resident("R1");
        Resident r2 = new Resident("R2");
        Resident r3 = new Resident("R3");
        List<Resident> residentsList = Stream.of(r1, r0, r3, r2).collect(Collectors.toCollection(ArrayList::new));
        sortResidents(residentsList);

        Hospital h0 = new Hospital("H0", 1);
        Hospital h1 = new Hospital("H1", 2);
        Hospital h2 = new Hospital("H2", 2);
        Set<Hospital> hospitalsSet = Stream.of(h2, h0, h1).collect(Collectors.toCollection(TreeSet::new));


        Map<Resident, List<Hospital>> residentPreferences = getResidentsPreferences(residentsList, hospitalsSet);
        System.out.println("Residents preferences: ");
        printMap(residentPreferences);
        System.out.println();

        Map<Hospital, List<Resident>> hospitalPreferences = getHospitalsPreferences(hospitalsSet, residentsList);
        System.out.println("Hospitals preferences: ");
        printMap(hospitalPreferences);
        System.out.println();

        System.out.println("Residents who find acceptable H0 and H2:");
        residentsList.stream().filter(r -> residentPreferences.get(r).containsAll(Arrays.asList(h0, h2))).forEach(r -> System.out.print(r.getName() + " "));
        System.out.println();

        System.out.println("Residents who find acceptable R0:");
        hospitalsSet.stream().filter(h -> hospitalPreferences.get(h).contains(r0)).forEach(h -> System.out.print(h.getName() + " "));
    }

    private static void sortResidents(List<Resident> residents) {
        residents.sort(new Comparator<Resident>() {
            @Override
            public int compare(Resident o1, Resident o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }

    private static <K, V> void printMap(Map<K, List<V>> map) {
        for (Map.Entry key : map.entrySet()) {
            System.out.print(key.getKey().toString() + ": (");
            int counter = 0;
            for (V value : map.get(key.getKey())) {
                if (++counter < map.get(key.getKey()).size())
                    System.out.print(value.toString() + ", ");
                else
                    System.out.print(value.toString());
            }
            System.out.println(")");
        }
    }

    private static Map<Resident, List<Hospital>> getResidentsPreferences(List<Resident> residents, Set<Hospital> hospitalsSet) {
        Map<Resident, List<Hospital>> residentPreferences = new HashMap<>();
        residentPreferences.put(residents.get(0), hospitalsSet.stream().filter(element -> (element.getName().equals("H0") || element.getName().equals("H1") || element.getName().equals("H2"))).collect(Collectors.toList()));
        residentPreferences.put(residents.get(1), hospitalsSet.stream().filter(element -> (element.getName().equals("H0") || element.getName().equals("H1") || element.getName().equals("H2"))).collect(Collectors.toList()));
        residentPreferences.put(residents.get(2), hospitalsSet.stream().filter(element -> (element.getName().equals("H0") || element.getName().equals("H1"))).collect(Collectors.toList()));
        residentPreferences.put(residents.get(3), hospitalsSet.stream().filter(element -> (element.getName().equals("H0") || element.getName().equals("H2"))).collect(Collectors.toList()));
        return residentPreferences;
    }

    private static Map<Hospital, List<Resident>> getHospitalsPreferences(Set<Hospital> hospitalsSet, List<Resident> residentsList) {
        Map<Hospital, List<Resident>> hospitalPreferences = new LinkedHashMap<>();
        Iterator<Hospital> it = hospitalsSet.iterator();
        hospitalPreferences.put(it.next(), Stream.of(residentsList.get(3), residentsList.get(0), residentsList.get(1), residentsList.get(2)).collect(Collectors.toList()));
        hospitalPreferences.put(it.next(), Stream.of(residentsList.get(0), residentsList.get(2), residentsList.get(1)).collect(Collectors.toList()));
        hospitalPreferences.put(it.next(), residentsList.stream().filter(element -> (!element.getName().equals("R2"))).collect(Collectors.toList()));
        return hospitalPreferences;
    }
}
