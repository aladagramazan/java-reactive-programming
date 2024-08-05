package com.rem.reactive_programming_playground.sec12;

import com.rem.reactive_programming_playground.common.Util;
import com.rem.reactive_programming_playground.sec12.assignment.SlackMember;
import com.rem.reactive_programming_playground.sec12.assignment.SlackRoom;

public class Lec08SlackAssignment {

    public static void main(String[] args) {
        var room = new SlackRoom("General");
        var sam = SlackMember.builder().name("sam").build();
        var mike = SlackMember.builder().name("mike").build();
        var rem = SlackMember.builder().name("rem").build();

        room.addMember(sam);
        room.addMember(mike);
        sam.says("Hello");

        Util.sleepSeconds(4);

        mike.says("Hello from Mike");
        sam.says("Hello again");
        Util.sleepSeconds(4);

        room.addMember(rem);
        rem.says("Hello from Rem");
    }
}
