package org.me.genetic.tools;

import org.me.genetic.vo.Wolf;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Mutator {

    //cele zle
    public static Stream<Wolf> mutateWolf(Wolf wolf){
        Random random = new Random();
        List<Wolf> newGeneration = new ArrayList<>();

        for(int i =0; i< 4; i++){
                newGeneration.addAll(Stream
                        .of(
                                random.nextInt(3),
                                random.nextInt(3)+3,
                                random.nextInt(4)+6)
                        .map(genomeIndex-> {
                            Wolf newWolf = Optional
                                    .ofNullable(wolf.clone())
                                    .orElseGet(() -> Wolf.zeroWolf());

                            newWolf.getLines()
                                    .get(genomeIndex)
                                    .x2(random.nextInt(WolfGenerator.lineLength));

                            newWolf.color(new Color(
                                    random.nextInt(255),
                                    random.nextInt(255),
                                    random.nextInt(255)));

                            return newWolf;})
                        .collect(Collectors.toList()));
        }

        return newGeneration.stream();
    }

}
