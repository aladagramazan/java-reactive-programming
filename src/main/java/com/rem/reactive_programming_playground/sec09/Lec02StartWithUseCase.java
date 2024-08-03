package com.rem.reactive_programming_playground.sec09;

import com.rem.reactive_programming_playground.common.Util;
import com.rem.reactive_programming_playground.sec09.helper.NameGenerator;

public class Lec02StartWithUseCase {

    public static void main(String[] args) {

        var nameGenerator = new NameGenerator();
        nameGenerator.generateNames()
                .take(2)
                .subscribe(
                        Util.subscriber("rem")
                );

        nameGenerator.generateNames()
                .take(2)
                .subscribe(
                        Util.subscriber("mike")
                );

        nameGenerator.generateNames()
                .take(3)
                .subscribe(
                        Util.subscriber("sam")
                );
    }
}
