package org.bonial.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class Test {

    private List<Pair<String, List<String>>> normalize(List<Pair<String, List<String>>> list) {

        int size = list.size();
        if (size == 1) {
            if (list.get(0).getValue().size() == 1) {
                return list;
            } else {
                return new ArrayList<>();
            }
        }
        Pair<String, List<String>> first = list.get(0);
        List<Pair<String, List<String>>> newList = list
                .stream()
                .filter(l -> l.getKey() != first.getKey())
                .collect(Collectors.toList());
        List<Pair<String, List<String>>> imNewList = ImmutableList.copyOf(newList);

        List<Pair<String, List<String>>> result = first
                .getValue()
                .stream()
                .map(channel -> createTestItem(first.getKey(), channel))
                .map(testItem -> extracted(imNewList, testItem))
                .filter(l -> l.size() == size)
                .filter(this::validate)
                .findFirst()
                .orElse(new ArrayList<>());
        return result;

    }

    private List<Pair<String, List<String>>> extracted(List<Pair<String, List<String>>> imNewList,
            Pair<String, List<String>> testItem) {
        String testChannel = testItem.getValue().get(0);
        List<Pair<String, List<String>>> testList = removeTestChannelFromList(imNewList, testChannel);
        List<Pair<String, List<String>>> r = normalize(testList);
        r.add(0, testItem);
        return r;
    }

    private List<Pair<String, List<String>>> removeTestChannelFromList(List<Pair<String, List<String>>> imNewList,
            String testChannel) {
        return imNewList
                .stream()
                .map(item -> removeTestChannelFromItem(item, testChannel))
                .collect(Collectors.toList());
    }

    private Pair<String, List<String>> removeTestChannelFromItem(Pair<String, List<String>> item, String testChannel) {
        List<String> dcs = item
                .getValue()
                .stream()
                .filter(channel -> channel != testChannel)
                .collect(Collectors.toList());
        dcs = ImmutableList.copyOf(dcs);
        return Pair.of(item.getLeft(), dcs);
    }

    private Pair<String, List<String>> createTestItem(String id, String dc) {
        List<String> dcs = Collections.singletonList(dc);
        return Pair.of(id, dcs);
    }

    private boolean validate(List<Pair<String, List<String>>> list) {
        return list.stream().allMatch(l -> l.getValue().size() == 1);
    }

    public static void main(String[] args) {
        Test test = new Test();

        Pair<String, List<String>> first = Pair.of("1", Lists.newArrayList("B", "C", "D"));
        Pair<String, List<String>> second = Pair.of("2", Lists.newArrayList("A", "B"));
        Pair<String, List<String>> third = Pair.of("3", Lists.newArrayList("A", "B"));
        Pair<String, List<String>> forth = Pair.of("4", Lists.newArrayList("A", "B", "D"));
        List<Pair<String, List<String>>> normalized = test.normalize(Lists.newArrayList(first, second, third, forth));
        System.out.println(normalized);
    }
}
